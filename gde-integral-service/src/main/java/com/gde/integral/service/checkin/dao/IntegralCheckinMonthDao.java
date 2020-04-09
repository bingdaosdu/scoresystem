package com.gde.integral.service.checkin.dao;

import com.gde.integral.service.checkin.pojo.IntegralCheckinMonth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 月度签到积分数据访问接口
 *
 * @author ~
 * @date 2019/6/26
 */
public interface IntegralCheckinMonthDao extends JpaRepository<IntegralCheckinMonth, Integer> {

    /**
     * 根据w3id和数据开始日期获取
     *
     * @param w3id w3id
     * @param startDate 数据开始日期
     * @return 返回获取到的月度签到积分对象
     */
    IntegralCheckinMonth findByW3idAndStartDate(String w3id, String startDate);

}
