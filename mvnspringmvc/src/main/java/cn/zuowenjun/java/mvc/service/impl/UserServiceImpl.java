package cn.zuowenjun.java.mvc.service.impl;

import cn.zuowenjun.java.mvc.service.UserService;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.*;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String login(String uid, String pwd) {
		if(uid.isEmpty() || pwd.isEmpty()) {
			return "用户名与密码都不能为空！";
		}
		
		ResourceBundle userRes= ResourceBundle.getBundle("user");
		String configUid= userRes.getString("user.userid");
		String configPwd=userRes.getString("user.password");
		
		if(configUid.equals(uid) && configPwd.equals(pwd)) {
			
			String configUName=userRes.getString("user.username");
			
			HttpSession session= getRequest().getSession();
			session.setAttribute("loginUid", uid);
			session.setAttribute("loginUname",configUName);
			return null;
		}else{
			return "用户名或密码不正确！";
		}
	}

	@Override
	public String logout() {
		try {
			getRequest().getSession().removeAttribute("loginUid");
			return null;
		}catch(Exception ex) {
			return ex.getMessage();
		}
		
	}

	@Override
	public String getLoginUserName() {
		Object loginUnameObj= getRequest().getSession().getAttribute("loginUname");
		if(loginUnameObj==null) {
			return null;
		}else{
			return  (String)loginUnameObj;
		}
	}

	private HttpServletRequest getRequest() {
		HttpServletRequest  request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
}
