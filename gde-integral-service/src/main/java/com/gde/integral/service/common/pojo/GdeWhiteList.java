package com.gde.integral.service.common.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GDE社区白名单实体类
 *
 * @author ~
 * @date 2019/6/25
 */
@Entity
@Table(name = "gde_white_list")
public class GdeWhiteList {

    @Id
    private int id;

    /**
     * w3id
     */
    private String w3id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 角色
     */
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getW3id() {
        return w3id;
    }

    public void setW3id(String w3id) {
        this.w3id = w3id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
