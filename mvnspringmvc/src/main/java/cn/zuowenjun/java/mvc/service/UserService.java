package cn.zuowenjun.java.mvc.service;

public interface UserService {
	String login(String uid,String pwd);
	String logout();
	String getLoginUserName();
}
