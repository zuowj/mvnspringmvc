package cn.zuowenjun.java.mvc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.zuowenjun.java.mvc.dao.PostCommentDao;
import cn.zuowenjun.java.mvc.model.PostComment;
import cn.zuowenjun.java.mvc.model.mapper.PostCommentMapper;

@Repository
public class PostCommentDaoImpl extends BaseDao implements PostCommentDao {

	@Autowired
	public PostCommentDaoImpl(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PostComment get(int id) {
		String sql = "select * from TA_TestPostComment where id=?";

		// 第一种：基于定义好的实现了RowMapper的PostCommentMapper实例
		// return this.getJdbcTemplate().queryForObject(sql,new Object[] {id},new
		// PostCommentMapper());

		// 第二种：直接使用匿名内部类（可以理解为与C#的委托类型）
//		return this.getJdbcTemplate().queryForObject(sql,new Object[]{id},new RowMapper<PostComment>() {
//
//			@Override
//			public PostComment mapRow(ResultSet rs, int rowNum) throws SQLException {
//				PostComment model=new PostComment();
//				model.setId(rs.getInt("id"));
//				model.setPostid(rs.getInt("postid"));
//				model.setContent(rs.getString("content"));
//				model.setCreateby(rs.getString("createby"));
//				model.setCreateTime(rs.getDate("createtime"));
//				return model;
//			}
//		});

		// 第三种：直接使用Lambda表达式，这个与C#lambda就比较像了，因为JAVA抄自C#
		return this.getJdbcTemplate().queryForObject(sql, new Object[] { id }, (rs, i) -> {
			PostComment model = new PostComment();
			model.setId(rs.getInt("id"));
			model.setPostid(rs.getInt("postid"));
			model.setContent(rs.getString("content"));
			model.setCreateby(rs.getString("createby"));
			model.setCreateTime(rs.getDate("createtime"));
			return model;
		});

	}

	@Override
	public List<PostComment> getList(int postId) {
		String sql = "select * from TA_TestPostComment where postid=?";
		return this.getJdbcTemplate().query(sql, new Object[] { postId }, new PostCommentMapper());
	}

	@Override
	public Boolean create(PostComment postCmmt) {
		String sql = "insert into TA_TestPostComment(postid, content, createby, createTime) values(?,?,?,?)";
		int r = this.getJdbcTemplate().update(sql, new Object[] { postCmmt.getPostid(),
				postCmmt.getContent(), postCmmt.getCreateby(), postCmmt.getCreateTime() });

		if (r > 0) {
			System.out.println("create is ok!");
			return true;
		} else {
			System.out.println("create is failed!");
			return false;
		}
	}

	@Override
	public Boolean delete(int id) {
		String sql = "delete from TA_TestPostComment where id=?";
		int r = this.getJdbcTemplate().update(sql, new Object[] { id });
		if (r > 0) {
			System.out.println("delete is ok!");
			return true;
		} else {
			System.out.println("delete is failed!");
			return false;
		}
	}

	@Override
	public Boolean update(PostComment postCmmt) {
		String sql = "update TA_TestPostComment set postid=?,content=?,createby=?,createTime=getdate() where id=?";
		int r = this.getJdbcTemplate().update(sql, (pss) -> {
			pss.setInt(1, postCmmt.getPostid());
			pss.setString(2, postCmmt.getContent());
			pss.setString(3, postCmmt.getCreateby());
			pss.setInt(4, postCmmt.getId());
		});

		if (r > 0) {
			System.out.println("update is ok!");
			return true;
		} else {
			System.out.println("update is failed!");
			return false;
		}

	}

}
