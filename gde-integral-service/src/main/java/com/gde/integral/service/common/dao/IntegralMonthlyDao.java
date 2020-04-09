package com.gde.integral.service.common.dao;

import com.gde.integral.service.common.pojo.IntegralMonthly;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * 月度总积分数据访问接口
 *
 * @author ~
 * @date 2019/6/26
 */
public interface IntegralMonthlyDao extends JpaRepository<IntegralMonthly, Integer> {

    /**
     * 根据w3id和startDate获取
     *
     * @param w3id w3id
     * @param startDate 数据开始日期
     * @return 月度积分
     */
    IntegralMonthly findByW3idAndStartDate(String w3id, String startDate);

    /**
     * 分页查询已存在月份开始日期的集合
     *
     * @param pageable 分页查询对象
     * @return 返回已存在月份开始日期的集合(分页)
     */
    @Query("select distinct(startDate) from IntegralMonthly")
    List<String> existStartDate(Pageable pageable);

    /**
     * 分页查询已存在月份开始日期的集合(不包括当前月份)
     *
     * @param pageable 分页查询对象
     * @param startDate 指定的月份(以月份第一天的形式)
     * @return 返回已存在月份开始日期的集合(分页)
     */
    @Query("select distinct(startDate) from IntegralMonthly where startDate<>?1")
    List<String> existStartDate(Pageable pageable, String startDate);

    /**
     * 分页查询指定月份开始日期的月度积分数据(屏蔽w3id列表用户)
     *
     * @param startDate 月度开始日期
     * @param w3idList 需屏蔽的w3id列表
     * @param pageable 分页查询对象
     * @return 返回月度积分数据的集合
     */
    List<IntegralMonthly> findByStartDateAndW3idNotIn(String startDate, List<String> w3idList, Pageable pageable);

}
