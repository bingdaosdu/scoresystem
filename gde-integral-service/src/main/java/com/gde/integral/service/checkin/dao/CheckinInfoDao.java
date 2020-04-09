package com.gde.integral.service.checkin.dao;

import com.gde.integral.service.checkin.pojo.CheckinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 成员签到信息数据访问接口
 *
 * @author ~
 * @date 2019/6/26
 */
public interface CheckinInfoDao extends JpaRepository<CheckinInfo, String> {
}
