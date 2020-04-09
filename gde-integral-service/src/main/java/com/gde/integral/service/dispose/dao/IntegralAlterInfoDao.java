package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.IntegralAlterInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 积分变更信息数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface IntegralAlterInfoDao extends JpaRepository<IntegralAlterInfo, Integer> {


    /**
     * 根据w3id获取指定数据记录日期指定变更理由的积分变更信息数据的数量
     *
     * @param w3id  w3id
     * @param alterReason 变更理由
     * @param startRecordTime 开始记录时间
     * @param endRecordTime 结束记录时间
     * @return 返回数量
     */
    int countByW3idAndAlterReasonAndRecordTimeBetween(String w3id, String alterReason, Date startRecordTime, Date endRecordTime);

    /**
     * 根据记录时间查询
     * @param recordTime 数据记录时间
     * @return 返回查询结果
     */
    List<IntegralAlterInfo> findByRecordTime(Date recordTime);

    /**
     * 根据大于某个记录时间查询
     * @param recordTime 数据记录时间
     * @return 返回查询结果
     */
    List<IntegralAlterInfo> findByRecordTimeGreaterThan(Date recordTime);

}
