package com.itheima.crm.dao;

import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public interface CustomerDao {

    List<Customer> findAll();

    void save(Customer customer);

    Integer findCount(DetachedCriteria detachedCriteria);

    List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

    void delete(Customer customer);

    Customer findById(Long cust_id);

    void update(Customer customer);
}
