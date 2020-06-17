package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.UserDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {


    @Override
    public void regist(User user) {
        this.getHibernateTemplate().save(user);

    }

    @Override
    public User login(User user) {
        List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?",user.getUser_code(),user.getUser_password());
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User findById(Serializable id) {
        return null;
    }

    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.getHibernateTemplate().find("from User");
    }

    @Override
    public List<User> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        return null;
    }
}
