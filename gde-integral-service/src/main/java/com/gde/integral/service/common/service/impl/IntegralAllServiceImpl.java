package com.gde.integral.service.common.service.impl;

import com.gde.integral.service.common.dao.IntegralAllDao;
import com.gde.integral.service.common.pojo.IntegralAll;
import com.gde.integral.service.common.service.IntegralAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户总积分服务实现类
 *
 * @author ~
 * @date 2019/6/29
 */
@Service
public class IntegralAllServiceImpl implements IntegralAllService {

    private final IntegralAllDao integralAllDao;

    @Autowired
    public IntegralAllServiceImpl(IntegralAllDao integralAllDao) {
        this.integralAllDao = integralAllDao;
    }

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 用户总积分
     */
    @Override
    public IntegralAll findById(String id) {
        Optional<IntegralAll> integralAllOptional = integralAllDao.findById(id);
        IntegralAll integralAll = null;
        if (integralAllOptional.isPresent()) {
            integralAll = integralAllOptional.get();
        }
        return integralAll;
    }
}
