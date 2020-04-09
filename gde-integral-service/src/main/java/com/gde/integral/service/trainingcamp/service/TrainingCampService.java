package com.gde.integral.service.trainingcamp.service;

/**
 * 新手训练营服务接口
 *
 * @author ~
 * @date 2019/7/3
 */
public interface TrainingCampService {

    /**
     * 记录学习用户信息
     * @param w3id w3id
     */
    void recordUserOfLearning(String w3id);


    /**
     * 查询学习人数
     * @return 学习人数
     */
    int getNumOfLearning();
}
