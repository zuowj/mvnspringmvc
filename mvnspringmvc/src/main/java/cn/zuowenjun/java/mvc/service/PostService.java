package cn.zuowenjun.java.mvc.service;

import java.util.Date;
import java.util.List;

import cn.zuowenjun.java.mvc.model.Post;

public interface PostService {
	
	Post get(int id);
	List<Post> getList(Date frmDate,Date toDate);
	List<Post> getAll();
	int create(Post post);
	Boolean delete(int id);
	Boolean update(Post post);
}
