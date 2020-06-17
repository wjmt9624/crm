package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

    private LinkMan linkMan = new LinkMan();

    private LinkManService linkManService;

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    //分页参数
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


    /**
     * find all linkMan
     * @return
     */
    public String findAll(){
        //创建离线条件查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        //设置条件
        if(linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name())){
            detachedCriteria.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }
        if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
            detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
        }
        PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }


    /**
     * 跳转到添加界面
     * @return
     */
    public String saveUI(){
        //ActionContext.getContext().getValueStack().pop();

        //查询所有客户
        List<Customer> list = customerService.findAll();
        //将list保存到值栈中
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }

    public String save(){
        linkManService.save(linkMan);
        return "saveSuccess";
    }

    public String edit(){
        //查询某个联系人，所有客户
        List<Customer> list = customerService.findAll();
        //根据ID查联系人
        linkMan = linkManService.findById(linkMan.getLkm_id());
        ActionContext.getContext().getValueStack().set("list",list);
        //linkMan默认在模型驱动
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }
    public String update(){
        linkManService.update(linkMan);
        return "updateSuccess";
    }

    public String delete(){
        //ActionContext.getContext().getValueStack().pop();
        linkManService.delete(linkManService.findById(linkMan.getLkm_id()));
        return "saveSuccess";
    }

    public String find(){
        //ActionContext.getContext().getValueStack().pop();
        //创建离线条件查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }
}
