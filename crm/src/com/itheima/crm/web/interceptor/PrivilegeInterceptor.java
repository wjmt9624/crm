package com.itheima.crm.web.interceptor;


import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

/**
 * 权限拦截器
 * @author 若风
 * @version 1.0
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //判断session中是否是USER登录信息
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user1");
        if(user == null){
            //存错误信息
            ActionSupport actionSupport = (ActionSupport) invocation.getAction();
            actionSupport.addActionError("您还没有登录！请登录---");
            return Action.LOGIN;
        }else {
            //以经登录
            return invocation.invoke();
        }
    }
}
