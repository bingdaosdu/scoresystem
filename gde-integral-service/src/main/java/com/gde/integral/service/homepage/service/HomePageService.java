package com.gde.integral.service.homepage.service;

import com.gde.integral.service.common.pojo.IssueStat;

import java.util.Map;

/**
 * GDE社区首页服务接口
 *
 * @author ~
 * @date 2019/6/29
 */
public interface HomePageService {

    /**
     * 首页成员信息
     *
     * @param w3id 需要查询的w3id
     * @return 所有查询得到信息的Map集合
     */
    Map<String, Object> memberInfo(String w3id);

    /**
     * 获取问题分析数据
     *
     * @return 问题分析数据
     */
    IssueStat getIssueStat();
}
