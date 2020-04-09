package com.gde.integral.service.common.service;

import com.gde.integral.service.common.pojo.IntegralAll;

/**
 * 用户总积分服务接口
 *
 * @author ~
 * @date 2019/6/29
 */
public interface IntegralAllService {

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    IntegralAll findById(String id);

}
