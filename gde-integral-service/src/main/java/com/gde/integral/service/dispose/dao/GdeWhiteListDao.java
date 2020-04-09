package com.gde.integral.service.dispose.dao;

import com.gde.integral.service.common.pojo.GdeWhiteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * GDE社区白名单数据访问接口
 *
 * @author ~
 * @date 2019/6/25
 */
public interface GdeWhiteListDao extends JpaRepository<GdeWhiteList, Integer> {

    /**
     * 获取指定role的白名单用户
     *
     * @param roleArray
     * @return
     */
    List<GdeWhiteList> findByRoleIn(String[] roleArray);

    /**
     * 获取所有白名单用户的w3id
     *
     * @return w3id的集合
     */
    @Query("select w3id from GdeWhiteList")
    List<String> findAllW3id();

}
