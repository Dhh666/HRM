package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.SpringIOC;
import com.company.hrm.dao.pojo.User;
import com.company.hrm.service.iService.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class UserAction extends ActionSupport implements ModelDriven<NewUser>, SessionAware, RequestAware {

    private NewUser user = new NewUser();
    private Map<String, Object> requestMap;
    private Map<String, Object> sessionMap;
    IUserService userService = (IUserService) SpringIOC.getCtx().getBean("userService");

    @Override
    public NewUser getModel() {
        return user;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        this.requestMap = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    public String login() throws Exception {

        User u = userService.login(user.getUsername(), user.getPassword());
        HttpServletResponse response = ServletActionContext.getResponse();
        sessionMap.put("username", user.getUsername());
        ResResult res = null;
        if (null != u) {
            res = ResResult.success("login success!", u);

        } else {
            res = ResResult.error(404, "no user");
        }

        String jsonResult = new ObjectMapper().writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.println(jsonResult);
        return null;
    }

    public String exist() throws Exception {
        boolean a = userService.isExist(user.getUsername());
        ResResult res = null;
        if (a) {
            res = ResResult.success();

        } else {
            res = ResResult.error(404, "no user");
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        String jsonResult = new ObjectMapper().writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.println(jsonResult);
        return null;
    }

    public String regist() throws Exception {
        User u = new User(user.getUsername(), user.getPassword(), 1);
        String a = userService.regist(u);
        HttpServletResponse response = ServletActionContext.getResponse();
        ResResult res = null;
        if (null != a) {
            res = ResResult.success("regist success!");

        } else {
            res = ResResult.error(404, "regist error");
        }

        String jsonResult = new ObjectMapper().writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.println(jsonResult);
        return null;
    }

}
