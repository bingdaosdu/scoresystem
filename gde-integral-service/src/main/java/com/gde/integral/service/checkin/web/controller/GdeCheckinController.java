package com.gde.integral.service.checkin.web.controller;

import com.gde.integral.service.checkin.service.GdeCheckinService;
import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * GDE社区用户签到Controller
 *
 * @author ~
 * @date 2019/6/29
 */
@CrossOrigin
@RestController
@RequestMapping("/gdeCheckin")
public class GdeCheckinController {

    private final GdeCheckinService gdeCheckinService;

    @Autowired
    public GdeCheckinController(GdeCheckinService gdeCheckinService) {
        this.gdeCheckinService = gdeCheckinService;
    }

    /**
     * 用户签到
     *
     * @param searchMap 请求参数
     * @return 返回Result
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result checkin(@RequestBody Map searchMap) {
        boolean isCheckedIn = gdeCheckinService.isCheckedIn(searchMap.get("w3id").toString());
        if (isCheckedIn) {
            return new Result(false, StatusCode.ERROR, "签到失败");
        }
        return new Result(true, StatusCode.OK, "签到成功", gdeCheckinService.checkin(searchMap.get("w3id").toString(), searchMap.get("name").toString()));
    }

}
