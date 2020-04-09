package com.gde.integral.service.checkin.service;

import java.util.Date;
import java.util.Map;

/**
 * GDE社区签到服务接口
 *
 * @author ~
 * @date 2019/6/26
 */
public interface GdeCheckinService {

    /**
     * 核验指定w3id用户当天是否已签到 (true已签到) (false未签到)
     *
     * @param w3id w3id
     * @return 返回是否已签到
     */
    boolean isCheckedIn(String w3id);

    /**
     * 用户签到
     *
     * @param w3id w3id
     * @param name 用户姓名
     * @return 返回是否签到成功
     */
    Map<String, Object> checkin(String w3id, String name);

    /**
     * 用户签到(指定日期)
     *
     * @param w3id w3id
     * @param name 用户姓名
     * @param appointDate
     * @return
     */
    Map<String, Object> checkin(String w3id, String name, Date appointDate);

}
