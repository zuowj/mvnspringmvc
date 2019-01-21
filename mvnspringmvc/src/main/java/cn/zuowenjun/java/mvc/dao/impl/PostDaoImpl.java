package cn.zuowenjun.java.mvc.dao.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.*;
import org.springframework.transaction.support.*;

import cn.zuowenjun.java.mvc.dao.*;
import cn.zuowenjun.java.mvc.model.*;

@Repository
public class PostDaoImpl extends BaseDao implements PostDao {

	private PlatformTransactionManager transactionManager;
	
	@Autowired
	public PostDaoImpl(DataSource dataSource,PlatformTransactionManager transactionManager) {
		super(dataSource);
		this.transactionManager=transactionManager;
	}

	@Override
	public Post get(int id) {
		String sql="select * from TA_TestPost where id=:id";
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("id", id);
		return this.getNamedParameterJdbcTemplate().queryForObject(sql, mapSqlParameterSource,new BeanPropertyRowMapper<>(Post.class));
	}

	@Override
	public List<Post> getList(Date frmDate, Date toDate) {
		String sql="select * from TA_TestPost where createTime between :frmDate and :toDate";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("frmDate", frmDate);
		paramMap.put("toDate", toDate);
		
		return this.getNamedParameterJdbcTemplate().query(sql, paramMap,new BeanPropertyRowMapper<>(Post.class));
	}

	@Override
	public int create(Post post) {
		String sql="insert into TA_TestPost(title, content, author, createTime) values(:title,:content,:author,getdate())";
		BeanPropertySqlParameterSource beanPropParam=new BeanPropertySqlParameterSource(post);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
		int r= this.getNamedParameterJdbcTemplate().update(sql, beanPropParam, keyHolder);
		if(r>0) {
			System.out.println("create is ok!");
			return keyHolder.getKey().intValue();
		}
		else {
			System.out.println("create is failed!");
			return -1;
		}
	}

	@Override
	public Boolean delete(int id) {
		TransactionDefinition def = new DefaultTransactionDefinition();
	    TransactionStatus status = transactionManager.getTransaction(def);
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("postid",id);
			NamedParameterJdbcTemplate namedJdbcTemplate=this.getNamedParameterJdbcTemplate();
			namedJdbcTemplate.update("delete from TA_TestPost where id=:postid",paramMap);
			namedJdbcTemplate.update("delete from TA_TestPostComment where postid=:postid", paramMap);
			transactionManager.commit(status);
			
			System.out.println("delete is ok!");
			
			return true;
			 
		}catch(DataAccessException daEx) {
			System.out.printf("delete is failed,Exception:%s",daEx.getMessage());
			transactionManager.rollback(status);
			return false;
		}
	}

	@Override
	public Boolean update(Post post) {
		String sql="update TA_TestPost set title=:title,content=:content,author=:author,createTime=getdate() where id=:id";
		BeanPropertySqlParameterSource beanPropParam=new BeanPropertySqlParameterSource(post);
		int r= this.getNamedParameterJdbcTemplate().update(sql, beanPropParam);
		if(r>0) {
			System.out.println("update is ok!");
			return true;
		}
		else {
			System.out.println("update is failed!");
			return false;
		}
	}

}
