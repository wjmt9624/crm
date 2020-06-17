package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.domain.SaleVisit;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.SaleVisitService;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    private SaleVisit saleVisit = new SaleVisit();

    @Override
    public SaleVisit getModel() {
        return saleVisit;
    }

    //action中注入service
    @Resource(name = "saleVisitService")
    private SaleVisitService saleVisitService;

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Resource(name = "userService")
    private UserService userService;

    private Integer currPage = 1;;
    private Integer pageSize = 10;

    public void setCurrPage(Integer currPage) {
        if(currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    //接受数据
    private Date visit_end_time;

    public void setVisit_end_time(Date visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    public SaleVisit getSaleVisit() {
        return saleVisit;
    }

    public Date getVisit_end_time() {
        return visit_end_time;
    }

    public String findAll(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
        //设置条件
        if(saleVisit.getVisit_time() != null){
            detachedCriteria.add(Restrictions.ge("visit_time",saleVisit.getVisit_time()));
        }
        if(visit_end_time != null){
            detachedCriteria.add(Restrictions.le("visit_time",visit_end_time));
        }
        PageBean<SaleVisit> pageBean = (PageBean<SaleVisit>) saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String saveUI(){

        List<Customer> list = customerService.findAll();

        List<User> list1 = userService.findAll();

        ActionContext.getContext().getValueStack().set("list",list);
        ActionContext.getContext().getValueStack().set("list1",list1);

        return "saveUI";
    }

    public String save(){
        saleVisitService.save(saleVisit);
        return "saveSuccess";
    }
}
