package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.GdeQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * GDE社区问答数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface GdeQaDao extends JpaRepository<GdeQa, String> {

    /**
     * 获取最佳答复在某个时间段内的所有问答数据
     *
     * @param startDate 数据开始日期
     * @param endDate 数据结束日期
     * @return 问答数据集合
     */
    List<GdeQa> findByBestAnswerTimeBetween(String startDate, String endDate);

    /**
     * 获取最佳答复在某个时间段内的所有问答数据(过滤指定人员)
     *
     * @param startDate 数据开始日期
     * @param endDate 数据结束日期
     * @param w3idList 过滤名单
     * @return 问答数据集合
     */
    List<GdeQa> findByBestAnswerTimeBetweenAndBestAnswerW3idNotIn(String startDate, String endDate, List<String> w3idList);

    /**
     * 获取指定用户最佳答案数量
     *
     * @param w3id w3id
     * @return 最佳答案数
     */
    Integer countByBestAnswerW3id(String w3id);

    /**
     * 获取指定用户求助数
     *
     * @param w3id w3id
     * @return 用户求助数
     */
    Integer countByQuestionerW3id(String w3id);

    /**
     * 获取已解答问题数量
     *
     * @return 已解答问题数量
     */
    @Query("select count(bestAnswerW3id) from GdeQa where bestAnswerW3id is not null and bestAnswerW3id <> ''")
    Integer countByBestAnswerW3idIsNotNull();

}
