package com.gde.integral.service.expert.service.impl;

import com.gde.integral.service.common.dao.GdeQaDailyDao;
import com.gde.integral.service.common.pojo.GdeExpert;
import com.gde.integral.service.common.pojo.GdeExpertInterface;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.dispose.dao.GdeWhiteListDao;
import com.gde.integral.service.expert.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GDE社区达人接口实现类
 *
 * @author ~
 * @date 2019/7/1
 */
@Service
public class ExpertServiceImpl implements ExpertService {

    private final GdeQaDailyDao gdeQaDailyDao;

    private final GdeWhiteListDao gdeWhiteListDao;

    @Autowired
    public ExpertServiceImpl(GdeQaDailyDao gdeQaDailyDao, GdeWhiteListDao gdeWhiteListDao) {
        this.gdeQaDailyDao = gdeQaDailyDao;
        this.gdeWhiteListDao = gdeWhiteListDao;
    }

    /**
     * 获取指定月份解惑达人和求助达人
     *
     * @param startDate 指定的数据日期
     * @return 达人数据结果集
     */
    @Override
    public Map<String, List<GdeExpert>> appointMonth(String startDate) {
        String recordDate = IntegralDateUtils.getEndDateStrFromDateStr(startDate);
        String endDate = IntegralDateUtils.getStartDateStrFromDateStr(IntegralDateUtils.appointDateStr(1));

        // 分页查询对象
        Pageable pageable = PageRequest.of(0, 3);

        // 需要屏蔽的对象
        List<String> w3idList = gdeWhiteListDao.findAllW3id();

        // 解惑达人
        List<GdeExpert> helpExpertList = helpExpertByAppointMonth(pageable, recordDate, w3idList, startDate, endDate);
        List<GdeExpert> disabuseExpertList = disabuseExpertByAppointMonth(pageable, recordDate, w3idList, startDate, endDate);

        Map<String, List<GdeExpert>> resultMap = new HashMap<>(4);
        resultMap.put("helpExpert", helpExpertList);
        resultMap.put("disabuseExpert", disabuseExpertList);

        return resultMap;
    }

    /**
     * 获取指定月份求助达人
     *
     * @param pageable   分页查询对象
     * @param recordDate 数据记录日期
     * @param w3idList   屏蔽名单
     * @param startDate  数据开始日期
     * @param endDate    数据结束日期
     * @return 求组达人列表
     */
    @Override
    public List<GdeExpert> helpExpertByAppointMonth(Pageable pageable, String recordDate, List w3idList, String startDate, String endDate) {
        List<GdeExpertInterface> gdeExpertInterfaceList = gdeQaDailyDao.helpExpert(pageable, recordDate, w3idList, startDate, endDate);
        return expertInterface2expertEntity(gdeExpertInterfaceList);
    }

    /**
     * 获取指定月份解惑达人
     *
     * @param pageable   分页查询对象
     * @param recordDate 数据记录日期
     * @param w3idList   屏蔽名单
     * @param startDate  数据开始日期
     * @param endDate    数据结束日期
     * @return 解惑达人列表
     */
    @Override
    public List<GdeExpert> disabuseExpertByAppointMonth(Pageable pageable, String recordDate, List w3idList, String startDate, String endDate) {
        List<GdeExpertInterface> gdeExpertInterfaceList = gdeQaDailyDao.disabuseExpert(pageable, recordDate, w3idList, startDate, endDate);
        return expertInterface2expertEntity(gdeExpertInterfaceList);
    }

    /**
     * 将gdeExpertInterface列表转为expert实体类列表
     *
     * @param gdeExpertInterfaceList gdeExpertInterface列表
     * @return 达人实体类列表
     */
    private List<GdeExpert> expertInterface2expertEntity(List<GdeExpertInterface> gdeExpertInterfaceList) {
        List<GdeExpert> gdeExpertList = new ArrayList<>();

        gdeExpertInterfaceList.forEach(gdeExpertInterface -> {
            GdeExpert gdeExpert = new GdeExpert();
            gdeExpert.setName(gdeExpertInterface.getName());
            gdeExpert.setW3id(gdeExpertInterface.getW3id());
            gdeExpert.setAmount(gdeExpertInterface.getAmount());
            gdeExpert.setVisit(gdeExpertInterface.getVisitTotal());
            gdeExpertList.add(gdeExpert);
        });

        return gdeExpertList;
    }

}
