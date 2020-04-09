package com.gde.integral.service.common.service.impl;

import com.gde.integral.service.dispose.dao.ContributeInfoMonthDao;
import com.gde.integral.service.common.service.ContributeInfoMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 月度贡献值信息服务实现类
 *
 * @author ~
 * @date 2019/6/25
 */
@Service
public class ContributeInfoMonthServiceImpl implements ContributeInfoMonthService {

    private final ContributeInfoMonthDao contributeInfoMonthDao;

    @Autowired
    public ContributeInfoMonthServiceImpl(ContributeInfoMonthDao contributeInfoMonthDao) {
        this.contributeInfoMonthDao = contributeInfoMonthDao;
    }
}
