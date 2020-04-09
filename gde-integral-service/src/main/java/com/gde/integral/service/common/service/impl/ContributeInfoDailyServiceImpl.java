package com.gde.integral.service.common.service.impl;

import com.gde.integral.service.common.dao.ContributeInfoDailyDao;
import com.gde.integral.service.common.service.ContributeInfoDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 社区每日成员贡献信息服务实现类
 *
 * @author ~
 * @date 2019/6/29
 */
@Service
public class ContributeInfoDailyServiceImpl implements ContributeInfoDailyService {

    private final ContributeInfoDailyDao contributeInfoDailyDao;

    @Autowired
    public ContributeInfoDailyServiceImpl(ContributeInfoDailyDao contributeInfoDailyDao) {
        this.contributeInfoDailyDao = contributeInfoDailyDao;
    }

    /**
     * 获取指定用户原创博文数量
     *
     * @param w3id 成员id
     * @return 原创博文数量
     */
    @Override
    public Integer originalBlogCount(String w3id) {
        return contributeInfoDailyDao.getSumBlogOriginalByW3id(w3id);
    }

    /**
     * 获取指定用户求助数量
     *
     * @param w3id 成员id
     * @return 求助数
     */
    @Override
    public Integer helpCount(String w3id) {
        return contributeInfoDailyDao.getSumHelpByW3id(w3id);
    }
}
