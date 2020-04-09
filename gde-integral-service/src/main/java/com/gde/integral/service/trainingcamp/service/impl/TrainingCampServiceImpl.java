package com.gde.integral.service.trainingcamp.service.impl;

import com.gde.integral.service.trainingcamp.dao.UserLearningInfoDao;
import com.gde.integral.service.trainingcamp.pojo.UserLearningInfo;
import com.gde.integral.service.trainingcamp.service.TrainingCampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 新手训练营服务实现类
 *
 * @author xWX627789
 */
@Service
public class TrainingCampServiceImpl implements TrainingCampService {

    private final UserLearningInfoDao userLearningInfoDao;

    @Autowired
    public TrainingCampServiceImpl(UserLearningInfoDao userLearningInfoDao) {
        this.userLearningInfoDao = userLearningInfoDao;
    }

    /**
     * 记录学习人数
     *
     * @param w3id w3id
     */
    @Override
    public void recordUserOfLearning(String w3id) {
        UserLearningInfo userLearningInfo = new UserLearningInfo();
        userLearningInfo.setW3id(w3id);
        userLearningInfo.setRecordTime(new Date());
        userLearningInfoDao.save(userLearningInfo);
    }

    /**
     * 查询学习人数
     *
     * @return 学习人数
     */
    @Override
    public int getNumOfLearning() {
        return userLearningInfoDao.countDistinctW3id();
    }
}
