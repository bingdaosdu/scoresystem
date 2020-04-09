package com.gde.integral.service.checkin.service.impl;

import com.gde.integral.service.checkin.dao.CheckinInfoDao;
import com.gde.integral.service.checkin.dao.IntegralCheckinMonthDao;
import com.gde.integral.service.checkin.pojo.CheckinInfo;
import com.gde.integral.service.checkin.pojo.IntegralCheckinMonth;
import com.gde.integral.service.checkin.service.GdeCheckinService;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.dispose.service.IntegralAlterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * GDE社区签到服务实现类
 *
 * @author ~
 * @date 2019/6/26
 */
@Service
public class GdeCheckinServiceImpl implements GdeCheckinService {

    private final IntegralAlterInfoService integralAlterInfoService;

    private final CheckinInfoDao checkinInfoDao;

    private final IntegralCheckinMonthDao integralCheckinMonthDao;

    @Autowired
    public GdeCheckinServiceImpl(IntegralAlterInfoService integralAlterInfoService, CheckinInfoDao checkinInfoDao, IntegralCheckinMonthDao integralCheckinMonthDao) {
        this.integralAlterInfoService = integralAlterInfoService;
        this.checkinInfoDao = checkinInfoDao;
        this.integralCheckinMonthDao = integralCheckinMonthDao;
    }

    /**
     * 核验指定w3id用户当天是否已签到 (true已签到) (false未签到)
     *
     * @param w3id w3id
     * @return 返回是否已签到
     */
    @Override
    public boolean isCheckedIn(String w3id) {
        int checkinCount = integralAlterInfoService.countByW3idAndRecordTime(w3id, "签到", IntegralDateUtils.appointDate(0), IntegralDateUtils.appointDate(1));
        return checkinCount != 0;
    }

    /**
     * 用户签到
     *
     * @param w3id w3id
     * @param name 用户姓名
     * @return 返回结果集
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> checkin(String w3id, String name) {
        return checkin(w3id, name, new Date());
    }


    /**
     * 用户签到(指定日期)
     *
     * @param w3id 用户id
     * @param name 用户姓名
     * @param appointTime 指定日期
     * @return 用户签到结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> checkin(String w3id, String name, Date appointTime) {
        // 创建结果集Map
        Map<String, Object> resultMap = new HashMap<>(4);
        int userSignInCount = 1;

        // 声明一个用于记录当前签到分数的double类型的属性
        double alterValue = 0.50;

        // 根据w3id获取包含签到信息的Optional对象
        Optional<CheckinInfo> checkinInfoOptional = checkinInfoDao.findById(w3id);
        CheckinInfo checkinInfo = null;

        // 如果存在, 就从Optional对象中获取签到信息
        if (checkinInfoOptional.isPresent()) {
            checkinInfo = checkinInfoOptional.get();
        }

        // 判断签到信息对象是否为空
        if (checkinInfo != null) {
            // 如果存在说明已经有签到记录了
            Date lastTime = checkinInfo.getLastTime();
            String lastDateStr = IntegralDateUtils.dateFormatStr(lastTime);
            if (lastDateStr.equals(IntegralDateUtils.relativeDateAppointDateStr(0, appointTime))) {
                // 如果等于今天, 说明今天已签到, 直接返回false
                resultMap.put("userSignInCount", 0);
                return resultMap;
            } else if (lastDateStr.equals(IntegralDateUtils.relativeDateAppointDateStr(-1, appointTime))) {
                // 如果等于昨天, 说明今天是连续签到, 先获取截止昨日的签到次数
                int checkinCount = checkinInfo.getCheckinCount();
                // 今天签到次数等于昨天签到次数+1
                int todayCheckinCount = checkinCount+1;
                userSignInCount = todayCheckinCount;
                // 将今天的签到次数赋予签到信息对象
                checkinInfo.setCheckinCount(todayCheckinCount);
                // 判断用户连续签到次数, 如果今天签到次数%5 == 0 就说明今天是第五天签到, 分数+2.00;
                if (todayCheckinCount % 5 == 0) {
                    alterValue += 2.00;
                }
            } else {
                // 其他情况都将连续签到值设置为1
                checkinInfo.setCheckinCount(1);
            }
        } else {
            // 如果不存在说明是第一次签到, 新建一个成员签到信息对象
            checkinInfo = new CheckinInfo();
            checkinInfo.setW3id(w3id);
            checkinInfo.setName(name);
            checkinInfo.setCheckinCount(1);
            checkinInfo.setCheckinTotal(0);
            checkinInfo.setFirstTime(appointTime);
        }

        // 统一设置总签到次数
        checkinInfo.setCheckinTotal(checkinInfo.getCheckinTotal() + 1);
        // 统一设置最后一次签到时间
        checkinInfo.setLastTime(appointTime);

        // 保存签到信息对象到数据库
        checkinInfoDao.save(checkinInfo);

        IntegralCheckinMonth integralCheckinMonth = integralCheckinMonthDao.findByW3idAndStartDate(w3id, IntegralDateUtils.getStartDateStrFromDate(appointTime));

        if (integralCheckinMonth == null) {
            // 如果为空, 则需要新建一个月度积分签到对象
            integralCheckinMonth = new IntegralCheckinMonth();
            integralCheckinMonth.setW3id(w3id);
            integralCheckinMonth.setName(name);
            integralCheckinMonth.setMonthIntegral(alterValue);
            integralCheckinMonth.setStartDate(IntegralDateUtils.getStartDateStrFromDate(appointTime));
            integralCheckinMonth.setEndDate(IntegralDateUtils.getEndDateStrFromDate(appointTime));
        } else {
            // 否则说明已有, 只需修改月度积分的值
            integralCheckinMonth.setMonthIntegral(integralCheckinMonth.getMonthIntegral() + alterValue);
        }

        // 保存到月度签到积分数据
        integralCheckinMonthDao.save(integralCheckinMonth);

        // 保存到总积分数据
        integralAlterInfoService.integralAlter(name, w3id, alterValue, "签到", IntegralDateUtils.getStartDateStrFromDate(appointTime), appointTime, "SYSTEM");

        resultMap.put("continuousCount", userSignInCount);

        return resultMap;
    }
}
