package com.gde.integral.service.dispose.service.impl;

import com.gde.integral.service.common.pojo.*;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.dispose.dao.*;
import com.gde.integral.service.dispose.service.GdeIntegralDisposeService;
import com.gde.integral.service.dispose.service.IntegralAlterInfoService;
import com.gde.integral.service.expert.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * GDE社区积分处理服务实现类
 *
 * @author ~
 * @date 2019/6/25
 */
@Service
public class GdeIntegralDisposeServiceImpl implements GdeIntegralDisposeService {

    private final ContributeInfoMonthDao contributeInfoMonthDao;

    private final GdeQaDao gdeQaDao;

    private final GdeWhiteListDao gdeWhiteListDao;

    private final IntegralAlterInfoService integralAlterInfoService;

    private final IntegralContributeMonthDao integralContributeMonthDao;

    private final IntegralBestAnswerMonthDao integralBestAnswerMonthDao;

    private final ExpertService expertService;

    private final IntegralExpertMonthlyDao integralExpertMonthlyDao;

    @Autowired
    public GdeIntegralDisposeServiceImpl(ContributeInfoMonthDao contributeInfoMonthDao,
                                         GdeQaDao gdeQaDao,
                                         GdeWhiteListDao gdeWhiteListDao,
                                         IntegralAlterInfoService integralAlterInfoService,
                                         IntegralContributeMonthDao integralContributeMonthDao,
                                         IntegralBestAnswerMonthDao integralBestAnswerMonthDao,
                                         ExpertService expertService,
                                         IntegralExpertMonthlyDao integralExpertMonthlyDao) {
        this.contributeInfoMonthDao = contributeInfoMonthDao;
        this.gdeQaDao = gdeQaDao;
        this.gdeWhiteListDao = gdeWhiteListDao;
        this.integralAlterInfoService = integralAlterInfoService;
        this.integralContributeMonthDao = integralContributeMonthDao;
        this.integralBestAnswerMonthDao = integralBestAnswerMonthDao;
        this.expertService = expertService;
        this.integralExpertMonthlyDao = integralExpertMonthlyDao;
    }

    /**
     * 将月度贡献值处理为月度积分
     *
     * @param startDate 数据开始日期
     * @return 返回处理好的月度贡献积分
     */
    @Override
    public List<IntegralContributeMonth> contributeToIntegralByMonth(String startDate) {

        // 获取L1， L2 人员名单
        List<GdeWhiteList> gdeWhiteLists = gdeWhiteListDao.findByRoleIn(new String[] {"L1", "L2"});
        Map<String, GdeWhiteList> gdeWhiteMap = new HashMap<>(16);
        gdeWhiteLists.forEach(gdeWhiteList -> gdeWhiteMap.put(gdeWhiteList.getW3id(), gdeWhiteList));

        // 获取指定月份的贡献值信息
        List<ContributeInfoMonth> contributeInfoMonthList = contributeInfoMonthDao.findByStartDate(startDate);

        // 创建一个用于存储月度积分信息(根据月度贡献值)的集合
        List<IntegralContributeMonth> integralContributeMonthList = new ArrayList<>();

        // 遍历所有贡献值信息, 求出指定用户的积分信息
        contributeInfoMonthList.forEach(contributeInfoMonth -> {
            // 社区讨论
            int groupDiscuss = contributeInfoMonth.getGroupDiscuss();
            // 社区讨论回复
            int groupDiscussReply = contributeInfoMonth.getGroupDiscussReply();
            // 社区讨论精华
            int groupDiscussDigest = contributeInfoMonth.getGroupDiscussDigest();
            // 社区讨论分享
            int groupDiscussShare = contributeInfoMonth.getGroupDiscussShare();
            // 社区求助
            int groupHelp = contributeInfoMonth.getGroupHelp();
            // 社区求助回复
            int groupHelpReply = contributeInfoMonth.getGroupHelpReply();
            // 社区原创博文
            int groupBlogOriginal = contributeInfoMonth.getGroupBlogOriginal();
            // 社区精华博文
            int groupBlogDigest = contributeInfoMonth.getGroupBlogDigest();
            // 社区博文分享
//            int groupBlogShare = contributeInfoMonth.getGroupBlogShare();
            // 由于原贡献表博文分享数据不正确，修改为博文总数-原创博文数=博文分享数
            int groupBlogShare = contributeInfoMonth.getGroupBlog() - groupBlogOriginal;

            int groupDiscussIntegral = 1;
            int groupDiscussReplyIntegral = 2;
            int groupDiscussDigestIntegral = 20;
            int groupDiscussShareIntegral = 1;
            int groupHelpIntegral = 1;
            int groupHelpReplyIntegral = 2;
            int groupBlogDigestIntegral = 50;
            int groupBlogOriginalIntegral = 10;
            int groupBlogShareIntegral = 1;

            // 如果为L1, L2人员则问答板块积分设置为0
            String w3id = contributeInfoMonth.getW3id();
            if (gdeWhiteMap.get(w3id) != null) {
                groupHelp = 0;
                groupHelpReply = 0;
            }

            // 计算积分
            double integralMonth = (groupDiscuss * groupDiscussIntegral
                    + groupDiscussReply * groupDiscussReplyIntegral
                    + groupDiscussDigest * groupDiscussDigestIntegral
                    + groupDiscussShare * groupDiscussShareIntegral
                    + groupHelp * groupHelpIntegral
                    + groupHelpReply * groupHelpReplyIntegral
                    + groupBlogDigest * groupBlogDigestIntegral
                    + groupBlogOriginal * groupBlogOriginalIntegral
                    + groupBlogShare * groupBlogShareIntegral);

            // 将已知信息放入月度积分信息对象
            IntegralContributeMonth integralContributeMonth = new IntegralContributeMonth();
            integralContributeMonth.setW3id(contributeInfoMonth.getW3id());
            integralContributeMonth.setName(contributeInfoMonth.getName());
            integralContributeMonth.setMonthIntegral(integralMonth);
            integralContributeMonth.setStartDate(contributeInfoMonth.getStartDate());
            integralContributeMonth.setEndDate(contributeInfoMonth.getEndDate());

            integralContributeMonthList.add(integralContributeMonth);
        });

        return integralContributeMonthList;
    }


    /**
     * 将指定日期区间内的最佳答复处理为月度积分
     *
     * @param startDate 数据开始日期
     * @param endDate 数据结束如期
     * @return 返回处理好的月度最佳答复积分列表
     */
    @Override
    public List<IntegralBestAnswerMonth> bestAnswerToIntegralByMonth(String startDate, String endDate) {

        // 获取需要过滤的人员名单
        List<GdeWhiteList> gdeWhiteLists = gdeWhiteListDao.findByRoleIn(new String[] {"L1", "L2", "OPERATION", "GDE"});
        List<String> w3idList = new ArrayList<>();
        gdeWhiteLists.forEach(gdeWhiteList -> w3idList.add(gdeWhiteList.getW3id()));

        // 获取最佳答复在指定日期区间内的所有问答数据
        List<GdeQa> gdeQaList;

        if (w3idList.size() == 0) {
            gdeQaList = gdeQaDao.findByBestAnswerTimeBetween(startDate, endDate);
        } else {
            gdeQaList = gdeQaDao.findByBestAnswerTimeBetweenAndBestAnswerW3idNotIn(startDate, endDate, w3idList);
        }

        // 创建一个存储最终月度积分信息的集合
        List<IntegralBestAnswerMonth> integralBestAnswerMonthList = new ArrayList<>();

        // 一个以成员w3id为键的Map集合
        Map<String, IntegralBestAnswerMonth> tempMap = new HashMap<>(16);

        // 遍历问答数据,
        gdeQaList.forEach(gdeQa -> {
            // 最佳答复人w3id
            String bestAnswerW3id = gdeQa.getBestAnswerW3id();
            // 先判断map中是否已存在
            if (tempMap.get(bestAnswerW3id) == null) {
                // 如果为空, 则表示map中没有当前最佳答复成员信息
                IntegralBestAnswerMonth integralBestAnswerMonth = new IntegralBestAnswerMonth();
                integralBestAnswerMonth.setW3id(gdeQa.getBestAnswerW3id());
                integralBestAnswerMonth.setName(gdeQa.getBestAnswer());
                integralBestAnswerMonth.setMonthIntegral(20);
                integralBestAnswerMonth.setStartDate(startDate);
                integralBestAnswerMonth.setEndDate(endDate);
                // 将处理好的对象放入tempMap
                tempMap.put(bestAnswerW3id, integralBestAnswerMonth);
            } else {
                // 否则代表map中已经有当前最佳答复成员信息, 直接在已有月度积分对象加分即可
                IntegralBestAnswerMonth integralBestAnswerMonth = tempMap.get(bestAnswerW3id);
                integralBestAnswerMonth.setMonthIntegral(integralBestAnswerMonth.getMonthIntegral() + 20);
                tempMap.put(bestAnswerW3id, integralBestAnswerMonth);
            }
        });

        // 遍历tempMap将value赋予integralBestAnswerMonthList
        tempMap.forEach((key, value) -> integralBestAnswerMonthList.add(value));

        return integralBestAnswerMonthList;
    }

    /**
     * 将指定月份的达人数据处理为月度达人积分数据
     *
     * @param startDate 数据开始日期
     * @return 返回处理好的月度达人积分列表
     */
    @Override
    public List<IntegralExpertMonthly> expertToIntegralByMonth(String startDate) {

        String disabuseExpert = "disabuseExpert";
        String helpExpert = "helpExpert";

        // 获取指定月度的达人数据
        Map<String, List<GdeExpert>> expertMonthlyMap = expertService.appointMonth(startDate);

        List<GdeExpert> gdeDisabuseExpertList = new ArrayList<>();
        List<GdeExpert> gdeHelpExpertList = new ArrayList<>();

        expertMonthlyMap.forEach((key, value) -> {
            if (disabuseExpert.equals(key)) {
                gdeDisabuseExpertList.addAll(value);
            } else if (helpExpert.equals(key)) {
                gdeHelpExpertList.addAll(value);
            }
        });

        List<IntegralExpertMonthly> integralExpertMonthlyList = new ArrayList<>();

        gdeDisabuseExpertList.forEach(gdeDisabuseExpert -> integralExpertMonthlyList.add(new IntegralExpertMonthly(
                gdeDisabuseExpert.getName(),
                gdeDisabuseExpert.getW3id(),
                150,
                startDate,
                IntegralDateUtils.getEndDateStrFromDateStr(startDate))));

        gdeHelpExpertList.forEach(gdeHelpExpert -> integralExpertMonthlyList.add(new IntegralExpertMonthly(
                gdeHelpExpert.getName(),
                gdeHelpExpert.getW3id(),
                50,
                startDate,
                IntegralDateUtils.getEndDateStrFromDateStr(startDate))));

        return integralExpertMonthlyList;
    }

    /**
     * 将计算出来的月度积分计入总积分(指定月份)
     *
     * @param startDate 数据开始日期
     * @return 返回是否成功将月度积分保存到总积分
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveIntegralMonthToAll(String startDate) {
        // 当前时间
        Date currentDate = new Date();

        // 获取指定月份的月度贡献积分
        List<IntegralContributeMonth> integralContributeMonthList = integralContributeMonthDao.findByStartDate(startDate);
        // 获取指定月份的月度最佳答案积分
        List<IntegralBestAnswerMonth> integralBestAnswerMonthList = integralBestAnswerMonthDao.findByStartDate(startDate);
        // 获取指定月份的月度达人积分
        List<IntegralExpertMonthly> integralExpertMonthlyList = integralExpertMonthlyDao.findByStartDate(startDate);

        // 将各月度积分保存至总月度积分和总积分
        for (IntegralContributeMonth integralContributeMonth : integralContributeMonthList) {
            integralAlterInfoService.integralAlter(integralContributeMonth.getName(), integralContributeMonth.getW3id(), integralContributeMonth.getMonthIntegral(), "月度贡献积分", integralContributeMonth.getStartDate(), currentDate, "SYSTEM");
        }
        for (IntegralBestAnswerMonth integralBestAnswerMonth : integralBestAnswerMonthList) {
            integralAlterInfoService.integralAlter(integralBestAnswerMonth.getName(), integralBestAnswerMonth.getW3id(), integralBestAnswerMonth.getMonthIntegral(), "月度最佳答案积分", integralBestAnswerMonth.getStartDate(), currentDate, "SYSTEM");
        }
        for (IntegralExpertMonthly integralExpertMonthly : integralExpertMonthlyList) {
            integralAlterInfoService.integralAlter(integralExpertMonthly.getName(), integralExpertMonthly.getW3id(), integralExpertMonthly.getIntegralMonthly(), "月度达人积分", integralExpertMonthly.getStartDate(), currentDate, "SYSTEM");
        }

        return true;
    }

}
