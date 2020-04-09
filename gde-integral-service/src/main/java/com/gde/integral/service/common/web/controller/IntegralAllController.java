package com.gde.integral.service.common.web.controller;

import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.common.pojo.IntegralAll;
import com.gde.integral.service.common.service.IntegralAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户总积分Controller类
 *
 * @author ~
 * @date 2019/6/29
 */
@CrossOrigin
@RestController
@RequestMapping("/integralAll")
public class IntegralAllController {

    private final IntegralAllService integralAllService;

    @Autowired
    public IntegralAllController(IntegralAllService integralAllService) {
        this.integralAllService = integralAllService;
    }

    /**
     * 根据传入的w3id获取对应的用户总积分对象
     *
     * @param w3id 主键
     * @return 返回Result
     */
    @RequestMapping("/search/{w3id}")
    public Result findById(@PathVariable String w3id) {
        return new Result(true, StatusCode.OK, "查询成功", integralAllService.findById(w3id));
    }

}
