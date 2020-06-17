package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

/**
 * @author 若风
 * @version 1.0
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册的方法
     * @return
     */
    public String regist(){

        userService.regist(user);

        return LOGIN;
    }

    /**
     * 用户登录的方法
     * @return
     */
    public String login(){
        User user1 = userService.login(user);
        if(user1 == null){
            //添加错误信息
            this.addActionError("用户名或密码错误！");
            return LOGIN;
        }else {
            ActionContext.getContext().getSession().put("user1",user1);
            return SUCCESS;
        }
    }

    @Override
    public User getModel() {
        return user;
    }
}
