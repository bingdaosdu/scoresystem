package com.gde.integral.service.dispose.service;

import com.gde.integral.service.common.pojo.IntegralBestAnswerMonth;
import com.gde.integral.service.common.pojo.IntegralContributeMonth;
import com.gde.integral.service.common.pojo.IntegralExpertMonthly;

import java.util.List;

/**
 * GDE社区积分处理服务接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface GdeIntegralDisposeService {

    /**
     * 将月度贡献值处理为月度积分
     *
     * @param startDate 数据开始日期
     * @return 返回处理好的月度贡献列表
     */
    List<IntegralContributeMonth> contributeToIntegralByMonth(String startDate);

    /**
     * 将指定日期区间内的最佳答复处理为月度积分
     *
     * @param startDate 数据开始日期
     * @param endDate 数据结束日期
     * @return 返回处理好的月度最佳答案积分
     */
    List<IntegralBestAnswerMonth> bestAnswerToIntegralByMonth(String startDate, String endDate);

    /**
     * 将指定月份的达人数据处理为月度达人积分数据
     *
     * @param startDate 数据开始日期
     * @return 返回处理好的月度达人积分列表
     */
    List<IntegralExpertMonthly> expertToIntegralByMonth(String startDate);

    /**
     * 将计算出来的月度积分计入总积分(指定月份)
     *
     * @param startDate 数据开始日期
     * @return 返回是否成功将月度积分保存到总积分
     */
    boolean saveIntegralMonthToAll(String startDate);

}
