package com.gde.integral.service.common.dao;

import com.gde.integral.service.common.pojo.ContributeInfoDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 社区成员每天贡献信息数据访问接口
 *
 * @author ~
 * @date 2019/6/29
 */
public interface ContributeInfoDailyDao extends JpaRepository<ContributeInfoDaily, String> {

    /**
     * 获取指定用户原创博文总数
     *
     * @param w3id w3id
     * @return 原创博文数量
     */
    @Query("select sum(groupBlogOriginal) from ContributeInfoDaily where w3id=?1")
    Integer getSumBlogOriginalByW3id(String w3id);

    /**
     * 获取指定用户社区求助数量
     *
     * @param w3id w3id
     * @return 求助数量
     */
    @Query("select sum(groupHelp) from ContributeInfoDaily where w3id=?1")
    Integer getSumHelpByW3id(String w3id);

}
