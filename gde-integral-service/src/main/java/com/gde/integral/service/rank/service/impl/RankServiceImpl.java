package com.gde.integral.service.rank.service.impl;

import com.gde.integral.service.common.dao.IntegralMonthlyDao;
import com.gde.integral.service.common.pojo.IntegralMonthly;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.dispose.dao.GdeWhiteListDao;
import com.gde.integral.service.rank.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * GDE社区积分排行服务实现类
 *
 * @author ~
 * @date 2019/6/28
 */
@Service
public class RankServiceImpl implements RankService {

    private final IntegralMonthlyDao integralMonthlyDao;

    private final GdeWhiteListDao gdeWhiteListDao;

    @Autowired
    public RankServiceImpl(IntegralMonthlyDao integralMonthlyDao, GdeWhiteListDao gdeWhiteListDao) {
        this.integralMonthlyDao = integralMonthlyDao;
        this.gdeWhiteListDao = gdeWhiteListDao;
    }

    /**
     * 获取积分排行榜数据
     *
     * @return 返回积分排行榜数据
     */
    @Override
    public Map<String, List<IntegralMonthly>> rankTop() {
        Map<String, List<IntegralMonthly>> resultMap = new LinkedHashMap<>();

        // 获取已有数据的月份(最多限12个)
        Pageable pageable = PageRequest.of(0, 12, Sort.Direction.DESC, "startDate");
        List<String> startDateList = integralMonthlyDao.existStartDate(pageable, IntegralDateUtils.getStartDateStrFromDate(new Date()));

        // 获取需要屏蔽的对象
        List<String> w3idList = gdeWhiteListDao.findAllW3id();

        // 遍历获取到的月份开始日期并查询出对应月份的积分榜数据
        startDateList.forEach(startDate -> {
            List<IntegralMonthly> integralMonthlyList = integralMonthlyDao.findByStartDateAndW3idNotIn(startDate, w3idList, PageRequest.of(0, 10, Sort.Direction.DESC, "integralMonthly"));
            // 将查询出的结果放入结果集, 并以startDate为键
            resultMap.put(startDate, integralMonthlyList);
        });

        return resultMap;
    }
}
