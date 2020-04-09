package com.gde.integral.service.expert.service;

import com.gde.integral.service.common.pojo.GdeExpert;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * GDE社区达人服务接口
 *
 * @author ~
 * @date 2019/7/1
 */
public interface ExpertService {

    /**
     * 获取指定月份解惑达人和求助达人
     *
     * @param startDate 指定的数据日期
     * @return 达人数据结果集
     */
    Map<String, List<GdeExpert>> appointMonth(String startDate);

    /**
     * 获取指定月份求助达人
     *
     * @param pageable 分页查询对象
     * @param recordDate 数据记录日期
     * @param w3idList 屏蔽名单
     * @param startDate 数据开始日期
     * @param endDate 数据结束日期
     * @return 求组达人列表
     */
    List<GdeExpert> helpExpertByAppointMonth(Pageable pageable, String recordDate, List w3idList, String startDate, String endDate);

    /**
     * 获取指定月份解惑达人
     *
     * @param pageable 分页查询对象
     * @param recordDate 数据记录日期
     * @param w3idList 屏蔽名单
     * @param startDate 数据开始日期
     * @param endDate 数据结束日期
     * @return 解惑达人列表
     */
    List<GdeExpert> disabuseExpertByAppointMonth(Pageable pageable, String recordDate, List w3idList, String startDate, String endDate);

}
