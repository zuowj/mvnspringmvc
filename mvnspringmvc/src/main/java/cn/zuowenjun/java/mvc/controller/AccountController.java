package cn.zuowenjun.java.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.zuowenjun.java.mvc.service.UserService;

/**
 * seeparambind:http://www.cnblogs.com/xiaoxi/p/5695783.html
 * 
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/signin")
	public String signIn() {
		return "signin";
	}
	
	@RequestMapping(path="/signin",method=RequestMethod.POST)
	public ModelAndView signIn(@RequestParam(required=true) String uid,@RequestParam(required=true) String pwd) {
		String loginResult=userService.login(uid, pwd);
		ModelAndView mv= new ModelAndView();
		if(loginResult==null || loginResult.isEmpty()) {//µÇÂ¼³É¹¦Ìø×ª
			mv.setViewName("redirect:/blog");
		}
		else {
			mv.setViewName("signin");
			mv.addObject("message",loginResult==null || loginResult.isEmpty()?"µÇÂ¼³É¹¦":"µÇÂ¼Ê§°Ü£º" + loginResult);
		}
		
		return mv;
	}
	
	@RequestMapping("/signout")
	public void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		userService.logout();
		response.sendRedirect(request.getContextPath() + "/account/signin");
	}
}
