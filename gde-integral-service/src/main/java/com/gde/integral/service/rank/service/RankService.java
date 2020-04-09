package com.gde.integral.service.rank.service;

import com.gde.integral.service.common.pojo.IntegralMonthly;

import java.util.List;
import java.util.Map;

/**
 * GDE社区积分排行服务接口
 *
 * @author ~
 * @date 2019/6/28
 */
public interface RankService {

    /**
     * 获取积分排行榜数据
     *
     * @return 返回积分排行榜数据
     */
    public Map<String, List<IntegralMonthly>> rankTop();

}
