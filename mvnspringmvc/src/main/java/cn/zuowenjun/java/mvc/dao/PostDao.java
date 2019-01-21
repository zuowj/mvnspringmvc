package cn.zuowenjun.java.mvc.dao;

import java.util.Date;
import java.util.List;

import cn.zuowenjun.java.mvc.model.Post;

public interface PostDao {
	Post get(int id);
	List<Post> getList(Date frmDate,Date toDate);
	int create(Post post);
	Boolean delete(int id);
	Boolean update(Post post);
	
}
