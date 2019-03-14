package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.ServiceConst;
import com.company.hrm.common.SpringIOC;
import com.company.hrm.dao.pojo.Emp;
import com.company.hrm.dao.pojo.User;
import com.company.hrm.service.iService.IEmpService;
import com.company.hrm.service.iService.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpAction extends ActionSupport implements ModelDriven<Emp>, SessionAware, RequestAware {
    private Emp emp = new Emp();
    private Map<String,Object> requestMap;
    private Map<String,Object> sessionMap;
    IEmpService empService = (IEmpService) SpringIOC.getCtx().getBean("empService");
    @Override
    public Emp getModel() {
        return emp;
    }
    @Override
    public void setRequest(Map<String, Object> map) {
        this.requestMap = map;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }

    public String empFindAll() throws Exception {
        ResResult<List<Emp>> result = null;
        HttpServletResponse response = ServletActionContext.getResponse();
        if(null!=sessionMap.get("username")){
            IEmpService empService = (IEmpService)SpringIOC.getCtx().getBean("empService");
            List<Emp> empList = new ArrayList<Emp>();
            empList = empService.findAll();
            if (!empList.isEmpty() && empList.size() > 0) {
                result = ResResult.success("find all success", empList);
            }else {
                result = ResResult.error(404, "no data");
            }
        }else {
            result = ResResult.error(301, "have not login");

        }
        PrintWriter out = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(result);
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }

    public String empFindByName() throws Exception {
        ResResult<List<Emp>> result = null;
        HttpServletResponse response = ServletActionContext.getResponse();
        if(null!=sessionMap.get("username")){
            IEmpService empService = (IEmpService)SpringIOC.getCtx().getBean("empService");
            List<Emp> empList = new ArrayList<Emp>();
            empList = empService.findByName(emp.getEname());
            if (!empList.isEmpty() && empList.size() > 0) {
                result = ResResult.success("find all success", empList);
            }else {
                result = ResResult.error(404, "no data");
            }
        }else {
            result = ResResult.error(301, "have not login");

        }
        PrintWriter out = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(result);
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }
    public String empFindById() throws Exception{
        ResResult<List<Emp>> res = null;
        HttpServletResponse response = ServletActionContext.getResponse();
        List<Emp> empList = new ArrayList<Emp>();
        IEmpService empService = (IEmpService)SpringIOC.getCtx().getBean("empService");
        if (empService.findById(emp.getEmpno()) != null) {
            emp = empService.findById(emp.getEmpno());
            empList.add(emp);
            res = ResResult.success("find success",empList);
        }else {
            res = ResResult.error(404, "no data");
        }
        PrintWriter out = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(res);
        System.out.println(jsonResult);
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }
    public String empSave() throws Exception{
        IEmpService empservice = (IEmpService) SpringIOC.getCtx().getBean("empService");
        Emp e = new Emp(emp.getEmpno(),emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getSal(),emp.getDeptno());
        HttpServletResponse response = ServletActionContext.getResponse();
        String msg = empservice.save(e);
        ResResult<Emp> res = null;
        if (msg.indexOf(ServiceConst.SUCCESS) != -1) {
            res = ResResult.success("save success!");
        }else {
            res = ResResult.error(500, "sava error!");
        }
        PrintWriter out = response.getWriter();
        String jsonObj = new ObjectMapper().writeValueAsString(res);
        out.println(jsonObj);
        out.flush();
        out.close();
        return null;
    }
    public String empUpdate() throws Exception{
        IEmpService empservice = (IEmpService) SpringIOC.getCtx().getBean("empService");
        HttpServletResponse response = ServletActionContext.getResponse();
        String msg = empservice.update(emp);
        ResResult<Emp> res = null;
        if (msg.indexOf(ServiceConst.SUCCESS) != -1) {
            res = ResResult.success("update success!");
        }else {
            res = ResResult.error(500, "sava error!");
        }
        PrintWriter out = response.getWriter();
        String jsonObj = new ObjectMapper().writeValueAsString(res);
        out.println(jsonObj);
        out.flush();
        out.close();
        return null;
    }
    public String empDelete() throws Exception{
        IEmpService empservice = (IEmpService) SpringIOC.getCtx().getBean("empService");
        HttpServletResponse response = ServletActionContext.getResponse();
        String msg = empservice.delete(emp);
        ResResult<Emp> res = null;
        if (msg.indexOf(ServiceConst.SUCCESS) != -1) {
            res = ResResult.success("delete success");
        }else {
            res = ResResult.error(500, "delete error!");
        }
        PrintWriter out = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(res);
        out.println(jsonResult);
        out.flush();
        out.close();
        return null;
    }

}
