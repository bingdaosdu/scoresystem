package com.gde.integral.service.cocreation.dao;

import com.gde.integral.service.cocreation.pojo.CoCreationActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 共创活动数据访问接口
 *
 * @author ~
 * @date 2019/7/5
 */
public interface CoCreationActivityDao extends JpaRepository<CoCreationActivity, String>, JpaSpecificationExecutor<CoCreationActivity> {

    /**
     * 获取活动对象类型
     * @return 活动对象类型List集合
     */
    @Query("select distinct category from CoCreationActivity")
    List<String> getCategories();

    /**
     * 查询指定是否展示的活动数量
     *
     * @param visible 是否可见
     * @return 指定是否可见的活动数量
     */
    int countByVisible(String visible);

    /**
     * 获取指定是否展示的活动所有回复人数
     *
     * @param visible 是否可见
     * @return 指定是否可见的活动的所有回复人数
     */
    @Query("select sum(replyNum) from CoCreationActivity where visible=?1")
    int sumReplyNumByVisible(String visible);

}
