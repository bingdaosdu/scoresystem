package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.IntegralBestAnswerMonth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 月度积分(根据最佳答复)数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface IntegralBestAnswerMonthDao extends JpaRepository<IntegralBestAnswerMonth, Integer> {

    /**
     * 获取指定月份的月度最佳答复积分
     *
     * @param startDate
     * @return
     */
    List<IntegralBestAnswerMonth> findByStartDate(String startDate);

}
