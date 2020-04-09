package com.gde.integral.service.common.dao;

import com.gde.integral.service.common.pojo.GdeExpertInterface;
import com.gde.integral.service.common.pojo.GdeQaDaily;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * GDE社区每天问答数据访问接口
 *
 * @author ~
 * @date 2019/6/29
 */
public interface GdeQaDailyDao extends JpaRepository<GdeQaDaily, String> {

    /**
     * 查询指定数据记录日期的数据
     *
     * @param pageable 分页查询对象
     * @param recordDate 记录日期
     * @return GDE社区问答列表
     */
    List<GdeQaDaily> findByRecordDate(Pageable pageable, String recordDate);

    /**
     * 获取求助达人(分页 + 排序)
     *
     * @param pageable 分页查询对象
     * @param recordDate 记录日期
     * @param w3idList 屏蔽列表
     * @param startDate 数据开始日期
     * @param endDate 数据结束日期
     * @return 结果集列表
     */
    @Query("select questioner as name, questionerW3id as w3id, count(questionerW3id) as amount, sum(visitNum) as visitTotal" +
            " from GdeQaDaily where recordDate=?1 and questionerW3id not in ?2 and questionTime between ?3 and ?4" +
            " group by questionerW3id order by count(questionerW3id) desc, sum(visitNum) desc")
    List<GdeExpertInterface> helpExpert(Pageable pageable, String recordDate, List w3idList, String startDate, String endDate);

    /**
     * 获取解惑达人(分页 + 排序)
     *
     * @param pageable 分页查询对象
     * @param recordDate 记录日期
     * @param w3idList 屏蔽列表
     * @return 结果集列表
     */
    @Query("select bestAnswer as name, bestAnswerW3id as w3id, count(bestAnswerW3id) as amount, sum(visitNum) as visitTotal" +
            " from GdeQaDaily where recordDate=?1 and bestAnswerW3id not in ?2 and bestAnswerTime between ?3 and ?4" +
            " group by bestAnswerW3id order by count(bestAnswerW3id) desc, sum(visitNum) desc")
    List<GdeExpertInterface> disabuseExpert(Pageable pageable, String recordDate, List w3idList, String startDate, String endDate);

}
