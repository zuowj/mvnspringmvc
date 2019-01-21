package cn.zuowenjun.java.mvc.dao;

import java.util.List;

import cn.zuowenjun.java.mvc.model.PostComment;

public interface PostCommentDao {
	PostComment get(int id);
	List<PostComment> getList(int postId);
	Boolean create(PostComment postCmmt);
	Boolean delete(int id);
	Boolean update(PostComment postCmmt);
}
