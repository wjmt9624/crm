package com.itheima.crm.service;

import com.itheima.crm.domain.User;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public interface UserService {

    void regist(User user);

    User login(User user);

    List<User> findAll();
}
