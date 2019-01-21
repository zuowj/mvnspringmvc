package cn.zuowenjun.java.mvc.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class BaseDao {
	private JdbcTemplate jdbcTemplateObj;
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	public BaseDao(DataSource dataSource) {
		this.jdbcTemplateObj=new JdbcTemplate(dataSource);
		this.namedParamJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
	}
	
	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplateObj;
	}
	
	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return this.namedParamJdbcTemplate;
	}
	
	
}
