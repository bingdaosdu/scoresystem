package com.gde.integral.service.common.service;

/**
 * 社区成员每日贡献信息服务接口
 *
 * @author ~
 * @date 2019/6/29
 */
public interface ContributeInfoDailyService {

    /**
     * 获取指定用户原创博文数量
     *
     * @param w3id w3id
     * @return 原创博文数量
     */
    Integer originalBlogCount(String w3id);

    /**
     * 获取指定用户求助数量
     *
     * @param w3id w3id
     * @return 求助数量
     */
    Integer helpCount(String w3id);

}
