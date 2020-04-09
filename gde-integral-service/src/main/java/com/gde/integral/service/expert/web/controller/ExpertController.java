package com.gde.integral.service.expert.web.controller;

import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.common.util.IntegralDateUtils;
import com.gde.integral.service.expert.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GDE社区达人Controller
 *
 * @author ~
 * @date 2019/7/1
 */
@CrossOrigin
@RestController
@RequestMapping("/expert")
public class ExpertController {

    private final ExpertService expertService;

    @Autowired
    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @RequestMapping("/lastMonth")
    public Result lastMonth() {
        return new Result(true, StatusCode.OK, "查询成功", expertService.appointMonth(IntegralDateUtils.lastMonthStartDate()));
    }

}
