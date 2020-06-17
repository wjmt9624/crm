package com.itheima.crm.dao;

import com.itheima.crm.domain.User;

/**
 * @author 若风
 * @version 1.0
 */
public interface UserDao extends BaseDao<User>{

    void regist(User user);

    User login(User user);
}
