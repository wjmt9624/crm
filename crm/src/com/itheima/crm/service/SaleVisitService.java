package com.itheima.crm.service;

import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public interface SaleVisitService {
    PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

    void save(SaleVisit saleVisit);
}
