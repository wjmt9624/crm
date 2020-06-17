package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    @Override
    public List<Customer> findAll() {

        return (List<Customer>) this.getHibernateTemplate().find("from Customer");
    }

    @Override
    public void save(Customer customer) {
        this.getHibernateTemplate().save(customer);
    }

    /**
     * DAO中带条件统计
     * @param detachedCriteria
     * @return
     */
    @Override
    public Integer findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    @Override
    public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        //把findcount里面的条件取消掉
        detachedCriteria.setProjection(null);
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
        return list;
    }

    @Override
    public void delete(Customer customer) {
        this.getHibernateTemplate().delete(customer);
    }

    @Override
    public Customer findById(Long cust_id) {
        Customer customer = this.getHibernateTemplate().get(Customer.class,cust_id);
        return customer;
    }

    @Override
    public void update(Customer customer) {
        this.getHibernateTemplate().update(customer);
    }

}
