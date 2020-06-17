package com.itheima.crm.web.action;

import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

/**
 * @author 若风
 * @version 1.0
 */
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

    private BaseDictService baseDictService;
    private BaseDict baseDict = new BaseDict();

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    @Override
    public BaseDict getModel() {
        return baseDict;
    }

    //根据类型名称查询字典
    public String findByTypeCode() throws IOException {
        List<BaseDict> baseDictList = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        //将这个list转成JSON
        JsonConfig jsonConfig = new JsonConfig();
        //jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(baseDictList,jsonConfig);
        System.out.println(jsonArray.toString());
        //将JSON打印到界面
        ServletActionContext.getResponse().setContentType("text/html,charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }
}
