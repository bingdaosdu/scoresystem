package com.gde.integral.service.trainingcamp.web.controller;

import com.gde.integral.service.trainingcamp.service.TrainingCampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TrainingCampController类
 *
 * @author ~
 * @date 2019/7/3
 */
@CrossOrigin
@RestController
@RequestMapping("/trainingCamp")
public class TrainingCampController {

    private final TrainingCampService trainingCampService;

    @Autowired
    public TrainingCampController(TrainingCampService trainingCampService) {
        this.trainingCampService = trainingCampService;
    }

    /**
     * 记录用户学习课程信息
     * @param w3id w3id
     */
    @GetMapping("/recordUserOfLearning")
    public void recordUserOfLearning(@RequestParam(value = "w3id") String w3id) {
        trainingCampService.recordUserOfLearning(w3id);
    }

    /**
     * 获取课程学习人数
     * @return 学习人数
     */
    @GetMapping("/getNumOfLearning")
    public int getNumOfLearning() {
        return trainingCampService.getNumOfLearning();
    }

}
