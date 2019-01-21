package cn.zuowenjun.java.mvc.service.impl;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuowenjun.java.mvc.dao.PostCommentDao;
import cn.zuowenjun.java.mvc.model.PostComment;
import cn.zuowenjun.java.mvc.service.PostCommentService;

@Service
public class PostCommentServiceImpl implements PostCommentService {

	@Autowired
	private PostCommentDao postCommentDao;
	
	@Override
	public PostComment get(int id) {
		return postCommentDao.get(id);
	}

	@Override
	public List<PostComment> getList(int postId) {
		return postCommentDao.getList(postId);
	}

	@Override
	public Boolean create(PostComment postCmmt) {
		String result=verifyModel(postCmmt,true);
		if(!result.isEmpty()) {
			throw new InvalidParameterException(result);
		}
		postCmmt.setCreateTime(new Date());
		return postCommentDao.create(postCmmt);
	}

	@Override
	public Boolean delete(int id) {
		return postCommentDao.delete(id);
	}

	@Override
	public Boolean update(PostComment postCmmt) {
		String result=verifyModel(postCmmt,false);
		if(!result.isEmpty()) {
			throw new InvalidParameterException(result);
		}
		
		return postCommentDao.update(postCmmt);
	}
	
	private String verifyModel(PostComment postCmmt,Boolean isNew) {
		StringBuilder errMsgBuilder=new StringBuilder();
		
		if(!isNew && postCmmt.getId()<=0) {
			errMsgBuilder.append("ID不能为空！");
		}
		
		if(postCmmt.getPostid()<=0) {
			errMsgBuilder.append("文章ID不能为空！");
		}
		
		if(postCmmt.getContent().trim().isEmpty()) {
			errMsgBuilder.append("内容不能为空！");
		}
		
		if(postCmmt.getCreateby().trim().isEmpty()) {
			errMsgBuilder.append("回复者不能为空！");
		}
		
		return errMsgBuilder.toString();
	}

}
