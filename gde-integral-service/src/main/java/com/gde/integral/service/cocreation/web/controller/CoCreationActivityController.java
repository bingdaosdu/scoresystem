package com.gde.integral.service.cocreation.web.controller;

import com.gde.integral.service.cocreation.pojo.CoCreationActivity;
import com.gde.integral.service.cocreation.service.CoCreationActivityService;
import com.gde.integral.service.common.entity.PageResult;
import com.gde.integral.service.common.entity.Result;
import com.gde.integral.service.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 共创活动Controller类
 *
 * @author ~
 * @date 2019/7/5
 */
@CrossOrigin
@RestController
@RequestMapping("/coCreation")
public class CoCreationActivityController {

    private final CoCreationActivityService coCreationActivityService;

    @Autowired
    public CoCreationActivityController(CoCreationActivityService coCreationActivityService) {
        this.coCreationActivityService = coCreationActivityService;
    }

    /**
     * 分页 + 条件查询
     *
     * @param searchMap 查询参数Map
     * @param page 指定页码
     * @param size 每页展示数据条数
     * @return 返回查询结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findBySpec(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<CoCreationActivity> pageList = coCreationActivityService.findBySpec(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据共创活动id查询
     *
     * @param tid 共创空间id
     * @return 查询结果
     */
    @RequestMapping("/search/{tid}")
    public Result findById(@PathVariable String tid) {
        if (coCreationActivityService.findById(tid) == null) {
            return new Result(false, StatusCode.ERROR, "查询失败");
        }
        return new Result(true, StatusCode.OK, "查询成功", coCreationActivityService.findById(tid));
    }

    /**
     * 更新共创活动对象
     *
     * @param coCreationActivity 已被修改的共创活动对象
     * @return 修改结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody CoCreationActivity coCreationActivity) {
        coCreationActivityService.update(coCreationActivity);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 获取所有活动类型
     *
     * @return 查询结果
     */
    @RequestMapping("/categories")
    public Result categories() {
        return new Result(true, StatusCode.OK, "查询成功", coCreationActivityService.getCategories());
    }

    /**
     * 获取众创空间活动数和参与人数
     *
     * @return 查询结果
     */
    @RequestMapping("/activitiesInfo")
    public Result activitiesInfo() {
        return new Result(true, StatusCode.OK, "查询成功", coCreationActivityService.getActivitiesInfo());
    }

}
