package com.gde.integral.service.common.web.controller;

import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.common.service.ContributeInfoDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区每日贡献信息Controller
 *
 * @author ~
 * @date 2019/6/29
 */
@RestController
@RequestMapping("/contributeDaily")
public class ContributeInfoDailyController {

    private final ContributeInfoDailyService contributeInfoDailyService;

    @Autowired
    public ContributeInfoDailyController(ContributeInfoDailyService contributeInfoDailyService) {
        this.contributeInfoDailyService = contributeInfoDailyService;
    }

    /**
     * 获取指定用户的原创博文数量
     *
     * @param w3id w3id
     * @return 原创博文数量
     */
    @RequestMapping("/search/{w3id}")
    public Result originalBlogCount(@PathVariable String w3id) {
        return new Result(true, StatusCode.OK, "查询成功", contributeInfoDailyService.originalBlogCount(w3id));
    }

}
