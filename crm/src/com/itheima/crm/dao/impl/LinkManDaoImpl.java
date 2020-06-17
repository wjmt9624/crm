package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {


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
    public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
        detachedCriteria.setProjection(null);
        List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
        return list;
    }

    @Override
    public void save(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    @Override
    public LinkMan findById(Long lkm_id) {

        return this.getHibernateTemplate().get(LinkMan.class,lkm_id);
    }

    @Override
    public void update(LinkMan linkMan) {
        this.getHibernateTemplate().update(linkMan);
    }

    @Override
    public void delete(LinkMan linkMan) {
        this.getHibernateTemplate().delete(linkMan);
    }
}
