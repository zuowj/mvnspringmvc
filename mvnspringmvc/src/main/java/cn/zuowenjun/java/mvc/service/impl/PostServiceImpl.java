package cn.zuowenjun.java.mvc.service.impl;

import java.security.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuowenjun.java.mvc.model.Post;
import cn.zuowenjun.java.mvc.service.PostService;
import cn.zuowenjun.java.mvc.dao.*;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	
	@Override
	public Post get(int id) {
		return postDao.get(id);
	}

	@Override
	public List<Post> getList(Date frmDate, Date toDate) {
		long frmDateVal=frmDate.getTime();
		long toDateVal=toDate.getTime();
		
		if(frmDateVal>toDateVal) {
			throw new InvalidParameterException("开始时间需<=结束时间");
		}
		return postDao.getList(frmDate, toDate);
	}
	
	@Override
	public List<Post> getAll() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return getList(sdf.parse("1900-1-1"), sdf.parse("2099-12-1"));
		} catch (ParseException e) {
			return null;
		}
	}
	

	@Override
	public int create(Post post) {
		String result=verifyModel(post,true);
		if(!result.isEmpty()) {
			throw new InvalidParameterException(result);
		}
		return postDao.create(post);
	}

	@Override
	public Boolean delete(int id) {
		return postDao.delete(id);
	}

	@Override
	public Boolean update(Post post) {
		String result=verifyModel(post,true);
		if(!result.isEmpty()) {
			throw new InvalidParameterException(result);
		}
		return postDao.update(post);
	}
	
	private String verifyModel(Post post,Boolean isNew) {
		StringBuilder errMsgBuilder=new StringBuilder();
		
		if(!isNew && post.getId()<=0) {
			errMsgBuilder.append("ID不能为空！");
		}
		
		if(post.getTitle().trim().isEmpty()) {
			errMsgBuilder.append("标题不能为空！");
		}
		
		if(post.getContent().trim().isEmpty()) {
			errMsgBuilder.append("内容不能为空！");
		}
		
		if(post.getAuthor().trim().isEmpty()) {
			errMsgBuilder.append("作者不能为空！");
		}
		
		return errMsgBuilder.toString();
	}



}
