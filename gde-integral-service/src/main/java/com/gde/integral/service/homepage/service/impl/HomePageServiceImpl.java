package com.gde.integral.service.homepage.service.impl;

import com.gde.integral.service.checkin.service.GdeCheckinService;
import com.gde.integral.service.common.dao.ContributeInfoDailyDao;
import com.gde.integral.service.common.dao.IntegralAllDao;
import com.gde.integral.service.common.pojo.IntegralAll;
import com.gde.integral.service.common.pojo.IssueStat;
import com.gde.integral.service.dispose.dao.GdeQaDao;
import com.gde.integral.service.homepage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * GDE社区首页服务实现类
 *
 * @author ~
 * @date 2019/6/29
 */
@Service
public class HomePageServiceImpl implements HomePageService {

    private final ContributeInfoDailyDao contributeInfoDailyDao;

    private final GdeQaDao gdeQaDao;

    private final IntegralAllDao integralAllDao;

    private final GdeCheckinService gdeCheckinService;

    @Autowired
    public HomePageServiceImpl(ContributeInfoDailyDao contributeInfoDailyDao,
                               GdeQaDao gdeQaDao,
                               IntegralAllDao integralAllDao,
                               GdeCheckinService gdeCheckinService) {
        this.contributeInfoDailyDao = contributeInfoDailyDao;
        this.gdeQaDao = gdeQaDao;
        this.integralAllDao = integralAllDao;
        this.gdeCheckinService = gdeCheckinService;
    }

    /**
     * 首页成员信息
     *
     * @param w3id 需要查询的w3id
     * @return 所有查询得到信息的Map集合
     */
    @Override
    public Map<String, Object> memberInfo(String w3id) {
        Map<String, Object> resultMap = new HashMap<>(4);

        // 成员原创博文数量
        Integer blogOriginalCount = contributeInfoDailyDao.getSumBlogOriginalByW3id(w3id);
        // 成员提问数量
        Integer helpCount = gdeQaDao.countByQuestionerW3id(w3id);
        // 成员最佳答案数量
        Integer bestAnswerCount = gdeQaDao.countByBestAnswerW3id(w3id);
        // 成员当前总积分
        double integralTotal = 0;
        Optional<IntegralAll> integralAllOptional = integralAllDao.findById(w3id);
        if (integralAllOptional.isPresent()) {
            integralTotal = integralAllOptional.get().getIntegralTotal();
        }
        // 成员当天签到情况
        boolean isCheckedIn = gdeCheckinService.isCheckedIn(w3id);

        resultMap.put("blogOriginalCount", blogOriginalCount == null ? 0 : blogOriginalCount);
        resultMap.put("helpCount", helpCount == null ? 0 : helpCount);
        resultMap.put("bestAnswerCont", bestAnswerCount == null ? 0 : bestAnswerCount);
        resultMap.put("integralTotal", integralTotal);
        resultMap.put("checkinStatus", !isCheckedIn);

        return resultMap;
    }

    /**
     * 获取问题分析数据
     *
     * @return 问题分析数据
     */
    @Override
    public IssueStat getIssueStat() {
        Integer solvedCount = gdeQaDao.countByBestAnswerW3idIsNotNull();
        long count = gdeQaDao.count();
        IssueStat issueStat = new IssueStat();
        issueStat.setIssueTotal(Math.toIntExact(count));
        issueStat.setRepliedCount(solvedCount);
        issueStat.setUnansweredCount(Math.toIntExact(count - solvedCount));
        return issueStat;
    }

}
