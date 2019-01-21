package cn.zuowenjun.java.mvc.service;

import java.util.List;

import cn.zuowenjun.java.mvc.model.PostComment;

public interface PostCommentService {
	PostComment get(int id);
	List<PostComment> getList(int postId);
	Boolean create(PostComment postCmmt);
	Boolean delete(int id);
	Boolean update(PostComment postCmmt);
}
