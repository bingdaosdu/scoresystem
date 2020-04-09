package com.gde.integral.service.rank.web.controller;

import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.rank.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GDE社区积分排行Controller类
 *
 * @author ~
 * @date 2019/6/28
 */
@RestController
@RequestMapping("/rank")
@CrossOrigin
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    /**
     * 获取积分排行榜数据
     *
     * @return 返回Result并包括成功查询到的积分排行榜数据
     */
    @RequestMapping("/top10List")
    public Result rankTop() {
        return new Result(true, StatusCode.OK, "查询成功", rankService.rankTop());
    }

}
