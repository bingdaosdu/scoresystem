package com.gde.integral.service.common.web.controller;

import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.common.service.ContributeInfoMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 月度贡献信息Controller
 *
 * @author ~
 * @date 2019/6/29
 */
@RestController
@RequestMapping("/contributeInfoMonth")
public class ContributeInfoMonthController {

    private final ContributeInfoMonthService contributeInfoMonthService;

    @Autowired
    public ContributeInfoMonthController(ContributeInfoMonthService contributeInfoMonthService) {
        this.contributeInfoMonthService = contributeInfoMonthService;
    }
}
