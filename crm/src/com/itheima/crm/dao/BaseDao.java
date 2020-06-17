package com.itheima.crm.dao;

import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public interface BaseDao<T> {


    public void save(T t);

    public void update(T t);

    public void delete(T t);

    public T findById(Serializable id);

    Integer findCount(DetachedCriteria detachedCriteria);

    List<T> findAll();

    List<T> findByPage(DetachedCriteria detachedCriteria,Integer begin,Integer pageSize);
}
