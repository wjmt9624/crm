package com.itheima.crm.service;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author 若风
 * @version 1.0
 */
public interface LinkManService {
    void save(LinkMan linkMan) ;

    PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    LinkMan findById(Long lkm_id);

    void update(LinkMan linkMan);

    void delete(LinkMan linkMan);
}
