package com.itheima.crm.domain;

import java.util.Date;

/**
 * 客户拜访记录实体
 * @author 若风
 * @version 1.0
 *   `visit_id` VARCHAR(32) NOT NULL,
 *   `visit_cust_id` BIGINT(32) DEFAULT NULL COMMENT '客户id',
 *   `visit_user_id` BIGINT(32) DEFAULT NULL COMMENT '负责人id',
 *   `visit_time` DATETIME DEFAULT NULL COMMENT '拜访时间',
 *   `visit_addr` VARCHAR(128) DEFAULT NULL COMMENT '拜访地点',
 *   `visit_detail` VARCHAR(256) DEFAULT NULL COMMENT '拜访详情',
 *   `visit_nexttime` DATE DEFAULT NULL COMMENT '下次拜访时间',
 */
public class SaleVisit {
    private String visit_id;
    private Date visit_time;
    private String visit_addr;
    private String visit_detail;
    private Date visit_nexttime;
    //拜访记录所管理的对象
    private Customer customer;
    private User user;

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public Date getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }

    public String getVisit_addr() {
        return visit_addr;
    }

    public void setVisit_addr(String visit_addr) {
        this.visit_addr = visit_addr;
    }

    public String getVisit_detail() {
        return visit_detail;
    }

    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }

    public Date getVisit_nexttime() {
        return visit_nexttime;
    }

    public void setVisit_nexttime(Date visit_nexttime) {
        this.visit_nexttime = visit_nexttime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
