package com.gde.integral.service.homepage.web.controller;

import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import com.gde.integral.service.common.pojo.IssueStat;
import com.gde.integral.service.homepage.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * GDE社区首页Controller
 *
 * @author ~
 * @date 2019/6/29
 */
@CrossOrigin
@RestController
@RequestMapping("/homePage")
public class HomePageController {

    private final HomePageService homePageService;

    @Autowired
    public HomePageController(HomePageService homePageService) {
        this.homePageService = homePageService;
    }

    /**
     * 首页成员信息(原创博文数量, 求助数量, 最佳答案数量, 个人总积分)
     *
     * @param w3id 成员id
     * @return 结果集
     */
    @RequestMapping("/memberInfo/{w3id}")
    public Result memberInfo(@PathVariable String w3id) {
        return new Result(true, StatusCode.OK, "查询成功", homePageService.memberInfo(w3id));
    }

    /**
     * 获取GDE首页的'已提出问题数量'和'已解决问题数量'
     */
    @RequestMapping("/getIssueStat")
    public IssueStat getIssueStat() {
        return homePageService.getIssueStat();
    }

}
