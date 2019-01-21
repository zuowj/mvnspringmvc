package cn.zuowenjun.java.mvc.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginValidationInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
			String url = request.getRequestURI();
			if(url.indexOf("/signin")>0) {//登录页面无需验证登录,放行
				return true;
			}
			
			if(request.getSession().getAttribute("loginUid")==null) {//检测到未登录，转到登录页面
				response.sendRedirect(request.getContextPath() + "/account/signin");
				return false;
			}else {
				return true;
			}
	}
	
	//其余postHandle、afterCompletion方法未重写，直接使用默认实现，这与C#有区别（C#8.0中也会有默认实现）

}
