package com.itheima.crm.web.action;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.BaseDictService;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer = new Customer();
    private CustomerService customerService;
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //使用set反方法接受数据
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if(currPage == null){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    //使用set方法接受每页记录数
    private Integer pageSize = 10;

    public void setPageSize(Integer pageSize) {
        if(pageSize == null){
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }

    /**
     * 跳转到添加页面的方法
     * @return
     */
    public String saveUI(){

        //查询级别存到值栈
        List<BaseDict> baseDictList = baseDictService.findByTypeCode("006");
        ActionContext.getContext().getValueStack().set("baseDictList",baseDictList);
        //查询来源存到值栈
        List<BaseDict> baseDictList1 = baseDictService.findByTypeCode("002");
        ActionContext.getContext().getValueStack().set("baseDictList1",baseDictList1);
        //查询客户行业存到值栈
        List<BaseDict> baseDictList2 = baseDictService.findByTypeCode("001");
        ActionContext.getContext().getValueStack().set("baseDictList2",baseDictList2);

        return LOGIN;
    }

    //文件名称
    private String uploadFileName;
    //上传文件
    private File upload;
    //文件类型
    private String uploadContentType;

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * 保存客户的方法
     * @return
     */
    public String save() throws IOException {
        //上传图片
        if(upload != null){
            //进行文件的上传
            //设置文件上传的路径
            String path = "E:/upload";
            //一个目录下存放的相同文件名：随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            System.out.println(uuidFileName);
            //一个目录下存放的文件过多，目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            //创建目录
            File file = new File(path+realPath);
            if(!file.exists()){
                file.mkdir();
            }
            //文件上传
            File dictFile = new File(path+realPath+"/"+uuidFileName);
            FileUtils.copyFile(upload,dictFile);
            //设置image属性的值
            customer.setCust_image(path+realPath+"/"+uuidFileName);
        }


        //保存客户
        customerService.save(customer);
        return "saveSuccess";
    }

    /**
     * 查询所有客户的方法
     * @return
     */
    public String findAll(){
        //查询级别存到值栈
        List<BaseDict> baseDictList = baseDictService.findByTypeCode("006");
        ActionContext.getContext().getValueStack().set("baseDictList",baseDictList);
        //查询来源存到值栈
        List<BaseDict> baseDictList1 = baseDictService.findByTypeCode("002");
        ActionContext.getContext().getValueStack().set("baseDictList1",baseDictList1);
        //查询客户行业存到值栈
        List<BaseDict> baseDictList2 = baseDictService.findByTypeCode("001");
        ActionContext.getContext().getValueStack().set("baseDictList2",baseDictList2);
        //接受参数，分页参数
        //最好使用detachedcriteria对象（条件查询带分页）
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        //设置条件,在web层设置条件
        if(customer.getCust_name() != null){
            detachedCriteria.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
        }
        if(customer.getBaseDictSource() != null && customer.getBaseDictSource().getDict_id() != null){
            if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())){
                detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id",customer.getBaseDictSource().getDict_id()));
            }
        }
        if(customer.getBaseDictLevel() != null && customer.getBaseDictLevel().getDict_id() != null){
            if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())){
                detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id",customer.getBaseDictLevel().getDict_id()));
            }
        }
        if(customer.getBaseDictIndustry() != null && customer.getBaseDictIndustry().getDict_id() != null){
            if(customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
                detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id",customer.getBaseDictIndustry().getDict_id()));
            }
        }
        //调用业务层查询
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }


    /**
     * 删除客户的方法
     * @return
     */
    public String delete(){
        //先查询，再删除
        customer = customerService.findById(customer.getCust_id());
        //删除图片
        if(customer.getCust_image() != null){
            File file = new File(customer.getCust_image());
            if(file.exists()){
                file.delete();
            }
        }
        customerService.delete(customer);
        return "saveSuccess";
    }


    /**
     * 编辑方法
     * @return
     */
    public String edit(){
        //查询级别存到值栈
        List<BaseDict> baseDictList = baseDictService.findByTypeCode("006");
        ActionContext.getContext().getValueStack().set("baseDictList",baseDictList);
        //查询来源存到值栈
        List<BaseDict> baseDictList1 = baseDictService.findByTypeCode("002");
        ActionContext.getContext().getValueStack().set("baseDictList1",baseDictList1);
        //查询客户行业存到值栈
        List<BaseDict> baseDictList2 = baseDictService.findByTypeCode("001");
        ActionContext.getContext().getValueStack().set("baseDictList2",baseDictList2);
        //根据ID查询
        customer = customerService.findById(customer.getCust_id());
        //将customer传递到页面1.手动压栈2.因为模型驱动的对象默认在栈中
        //如果使用第一种：回显数据：<s:property value="cust_name"/>
        //如果使用第二种：回显数据:<s:property value="model cust_name"/>
        ActionContext.getContext().getValueStack().push(customer);
        return "editSuccess";
    }

    public String update() throws IOException {
        //文件项是否已经选择
        if(upload != null){
            //已经选择了
            //删除原有文件
            if(customer.getCust_image() != null || "".equals(customer.getCust_image())){
                File file = new File(customer.getCust_image());
                if(file.exists()){
                    file.delete();
                }
            }
            //文件上传
            //设置文件上传的路径
            String path = "E:/upload";
            //一个目录下存放的相同文件名：随机文件名
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            System.out.println(uuidFileName);
            //一个目录下存放的文件过多，目录分离
            String realPath = UploadUtils.getPath(uuidFileName);
            //创建目录
            File file = new File(path+realPath);
            if(!file.exists()){
                file.mkdir();
            }
            //文件上传
            File dictFile = new File(path+realPath+"/"+uuidFileName);
            FileUtils.copyFile(upload,dictFile);
            //设置image属性的值
            customer.setCust_image(path+realPath+"/"+uuidFileName);

        }
        customerService.update(customer);
        return "updateSuccess";
    }

    @Override
    public Customer getModel() {
        return customer;
    }
}
