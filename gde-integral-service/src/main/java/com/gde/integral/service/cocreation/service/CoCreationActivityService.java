package com.gde.integral.service.cocreation.service;

import com.gde.integral.service.cocreation.pojo.CoCreationActivity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 共创活动服务接口
 *
 * @author ~
 * @date 2019/7/5
 */
public interface CoCreationActivityService {

    /**
     * 分页 + 条件查询
     *
     * @param searchMap 查询参数Map
     * @param page 指定的查询页码
     * @param size 每页展示数据条数
     * @return 共创活动List集合
     */
    Page<CoCreationActivity> findBySpec(Map searchMap, int page, int size);

    /**
     * 根据主键查询
     *
     * @param tid 共创活动主键
     * @return 共创活动对象
     */
    CoCreationActivity findById(String tid);

    /**
     * 更新修改的共创活动对象
     *
     * @param coCreationActivity 共创活动对象
     */
    void update(CoCreationActivity coCreationActivity);

    /**
     * 获取所有活动类型
     *
     * @return 活动类型List集合
     */
    List<String> getCategories();

    /**
     * 获取活动数量和活动参与人数
     *
     * @return 存放结果的Map集合
     */
    Map getActivitiesInfo();
}
