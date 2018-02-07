package com.lanou.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lanou.entity.SysEmployee;
import com.lanou.service.EmpService;
import com.lanou.util.RandomNumUtil;
import com.lanou.util.VerifyCodeUtils;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class UserAction extends ActionSupport{

	
    private ByteArrayInputStream inputStream;
    
    @Autowired
    private EmpService empService;
    

    private SysEmployee employee;
	public SysEmployee getEmployee() {
		return employee;
	}
	public void setEmployee(SysEmployee employee) {
		this.employee = employee;
	}

	/**
	 * 请求验证码
	 */
	public String random() {
		// 耦合获取response
        RandomNumUtil rdnu = RandomNumUtil.Instance();
        
        HttpSession session = ServletActionContext.getRequest().getSession();
        //  获取验证码
        String verifyCode = rdnu.getString();
        // 移除之前的验证码
        session.removeAttribute("verifyCode");
        //放入当前的验证码
        session.setAttribute("verifyCode", verifyCode);
        this.setInputStream(rdnu.getImage());/// 取得带有随机字符串的图片
        return SUCCESS;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SysEmployee s = empService.login(employee);
		if (s!=null) {
			session.setAttribute("employee", s);
			return SUCCESS;
		}else {
			request.setAttribute("msg", "账号或密码错误！");
			return ERROR;
		}
	}
	
	
	
}
