package com.gde.integral.service.dispose.service;

import java.util.Date;

/**
 * 积分变更服务接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface IntegralAlterInfoService {

    /**
     * 积分修改
     *
     * @param name 名字
     * @param w3id w3id
     * @param alterValue 变更的值
     * @param alterReason 变更理由
     * @param startDate 数据开始日期
     * @param recordTime 数据记录日期
     * @param operator 操作者
     * @return 返回修改是否成功
     */
    boolean integralAlter(String name, String w3id, double alterValue, String alterReason, String startDate, Date recordTime, String operator);

    /**
     * 根据w3id获取指定数据记录日期指定变更理由的积分变更信息数据的数量
     *
     * @param w3id w3id
     * @param alterReason 变更理由
     * @param startRecordTime 开始记录时间
     * @param endRecordTime 结束记录时间
     * @return 返回数量
     */
    int countByW3idAndRecordTime(String w3id, String alterReason, Date startRecordTime, Date endRecordTime);

}
