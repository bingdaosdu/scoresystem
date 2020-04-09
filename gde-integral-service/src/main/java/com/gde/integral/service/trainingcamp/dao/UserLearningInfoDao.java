package com.gde.integral.service.trainingcamp.dao;

import com.gde.integral.service.trainingcamp.pojo.UserLearningInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 新手训练营用户学习数据访问接口
 *
 * @author ~
 * @date 2019/7/3
 */
public interface UserLearningInfoDao extends JpaRepository<UserLearningInfo, Integer> {

    /**
     * 获取用户访问数
     *
     * @return 用户访问数
     */
    @Query("select count(distinct w3id) from UserLearningInfo")
    Integer countDistinctW3id();

}
