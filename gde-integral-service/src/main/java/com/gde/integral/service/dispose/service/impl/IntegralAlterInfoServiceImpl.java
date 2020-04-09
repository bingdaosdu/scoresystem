package com.gde.integral.service.dispose.service.impl;

import com.gde.integral.service.common.dao.IntegralMonthlyDao;
import com.gde.integral.service.common.pojo.IntegralAll;
import com.gde.integral.service.common.pojo.IntegralAlterInfo;
import com.gde.integral.service.common.dao.IntegralAllDao;
import com.gde.integral.service.common.pojo.IntegralMonthly;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.dispose.dao.IntegralAlterInfoDao;
import com.gde.integral.service.dispose.service.IntegralAlterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * 积分变更服务实现类
 *
 * @author ~
 * @date 2019/6/25
 */
@Service
public class IntegralAlterInfoServiceImpl implements IntegralAlterInfoService {

    @Autowired
    private IntegralAlterInfoDao integralAlterInfoDao;

    @Autowired
    private IntegralAllDao integralAllDao;

    @Autowired
    private IntegralMonthlyDao integralMonthlyDao;


    /**
     * 积分修改
     *
     * @param name 名字
     * @param w3id w3id
     * @param alterValue 变更的值
     * @param alterReason 变更理由
     * @param startDate 数据开始日期
     * @param recordTime 数据记录日期
     * @param operator 操作者
     * @return 返回修改是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean integralAlter(String name, String w3id, double alterValue, String alterReason, String startDate, Date recordTime, String operator) {

        // 月度总积分处理
        // 根据w3id和startDate获取用户数据
        IntegralMonthly integralMonthly = integralMonthlyDao.findByW3idAndStartDate(w3id, startDate);
        if (integralMonthly != null) {
            // 如果不为空表示在月度积分中当月已存在该用户
            integralMonthly.setIntegralMonthly(integralMonthly.getIntegralMonthly() + alterValue);
        } else {
            // 反之说明该月还没有此成员信息
            integralMonthly = new IntegralMonthly();
            integralMonthly.setW3id(w3id);
            integralMonthly.setName(name);
            integralMonthly.setIntegralMonthly(alterValue);
            integralMonthly.setStartDate(startDate);
            integralMonthly.setEndDate(IntegralDateUtils.getEndDateStrFromDateStr(startDate));
        }

        // 保存月度总积分信息
        integralMonthlyDao.save(integralMonthly);


        // 总积分处理
        // 根据w3id获取总积分数据中对应的用户信息
        Optional<IntegralAll> integralAllOptional = integralAllDao.findById(w3id);
        IntegralAll integralAll = null;
        if (integralAllOptional.isPresent()) {
            integralAll = integralAllOptional.get();
        }

        IntegralAlterInfo integralAlterInfo = new IntegralAlterInfo();
        integralAlterInfo.setName(name);
        integralAlterInfo.setW3id(w3id);
        integralAlterInfo.setAlterValue(alterValue);
        integralAlterInfo.setAlterReason(alterReason);
        integralAlterInfo.setRecordTime(recordTime);
        integralAlterInfo.setOperator(operator);

        // 如果不为空 说明用户信息在总积分表中已存在
        if (integralAll != null && !"".equals(integralAll.getW3id())) {
            integralAlterInfo.setAlterBefore(integralAll.getIntegralTotal());
            integralAlterInfo.setAlterLast(integralAll.getIntegralTotal() + alterValue);
            integralAll.setIntegralTotal(integralAll.getIntegralTotal() + alterValue);
        } else {
            integralAlterInfo.setAlterBefore(0);
            integralAlterInfo.setAlterLast(alterValue);
            integralAll = new IntegralAll();
            integralAll.setW3id(integralAlterInfo.getW3id());
            integralAll.setName(integralAlterInfo.getName());
            integralAll.setIntegralTotal(integralAlterInfo.getAlterLast());
        }

        // 保存积分修改信息
        integralAlterInfoDao.save(integralAlterInfo);
        // 保存最终积分值到总积分
        integralAllDao.save(integralAll);

        return true;
    }

    /**
     * 根据w3id获取指定数据记录日期区间指定变更理由的积分变更信息数据的数量
     *
     * @param w3id w3id
     * @param alterReason 变更理由
     * @param startRecordTime 开始记录时间
     * @param endRecordTime 结束记录时间
     * @return 返回数量
     */
    @Override
    public int countByW3idAndRecordTime(String w3id, String alterReason, Date startRecordTime, Date endRecordTime) {
        return integralAlterInfoDao.countByW3idAndAlterReasonAndRecordTimeBetween(w3id, alterReason, startRecordTime, endRecordTime);
    }

}
