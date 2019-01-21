package cn.zuowenjun.java.mvc.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.zuowenjun.java.mvc.model.PostComment;

public class PostCommentMapper implements RowMapper<PostComment> {

	@Override
	public PostComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostComment model=new PostComment();
		model.setId(rs.getInt("id"));
		model.setPostid(rs.getInt("postid"));
		model.setContent(rs.getString("content"));
		model.setCreateby(rs.getString("createby"));
		model.setCreateTime(rs.getDate("createtime"));
		
		return model;
	}

}
