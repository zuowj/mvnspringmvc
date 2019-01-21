package cn.zuowenjun.java.mvc.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginValidationInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
			String url = request.getRequestURI();
			if(url.indexOf("/signin")>0) {//��¼ҳ��������֤��¼,����
				return true;
			}
			
			if(request.getSession().getAttribute("loginUid")==null) {//��⵽δ��¼��ת����¼ҳ��
				response.sendRedirect(request.getContextPath() + "/account/signin");
				return false;
			}else {
				return true;
			}
	}
	
	//����postHandle��afterCompletion����δ��д��ֱ��ʹ��Ĭ��ʵ�֣�����C#������C#8.0��Ҳ����Ĭ��ʵ�֣�

}
