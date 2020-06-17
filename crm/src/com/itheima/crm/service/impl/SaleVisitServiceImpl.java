package com.itheima.crm.service.impl;

import com.itheima.crm.dao.SaleVisitDao;
import com.itheima.crm.dao.impl.BaseDaoImpl;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
@Transactional
public class SaleVisitServiceImpl  implements SaleVisitService {

    @Resource(name = "saleVisitDao")
    private SaleVisitDao saleVisitDao;

    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {

        PageBean<SaleVisit> pageBean = new PageBean<>();
        pageBean.setCurrPage(currPage);
        pageBean.setPageSize(pageSize);
        Integer totalCount = saleVisitDao.findCount(detachedCriteria);
        Double tPage = Math.ceil(totalCount/pageSize);
        Integer totalPage = tPage.intValue();
        Integer begin = (currPage - 1) * pageSize;
        List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria,begin,pageSize);
        pageBean.setList(list);
        pageBean.setTotalPage(totalPage);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }
}
