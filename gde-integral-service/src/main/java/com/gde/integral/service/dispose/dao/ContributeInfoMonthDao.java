package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.ContributeInfoMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 月度贡献信息数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface ContributeInfoMonthDao extends JpaRepository<ContributeInfoMonth, Integer> {

    /**
     * 根据数据开始记录日期获取
     *
     * @param startDate 数据开始记录日期
     * @return 月度贡献数据集合
     */
    List<ContributeInfoMonth> findByStartDate(String startDate);

}
