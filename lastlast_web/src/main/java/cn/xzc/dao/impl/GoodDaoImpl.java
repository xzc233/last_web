package cn.xzc.dao.impl;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.xzc.dao.GoodDao;
import cn.xzc.domain.Good;
import cn.xzc.domain.QueryResult;
import cn.xzc.utils.JdbcUtils;

public class GoodDaoImpl implements GoodDao {
	@Override
	public void add(Good b) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into good(id,name,price,image,description,category_id,userid,num) values(?,?,?,?,?,?,?,?)";
			Object[] params = {b.getId(), b.getName(), b.getPrice(), b.getImage(), b.getDescription(), b.getCategory().getId(),b.getUserid(),b.getNum()};
			runner.update(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void del(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "delete from good where id='"+id+"'";
			runner.update(conn, sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void setNum(String id, String type,double num) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "update good set "+type+"='"+num+"' where id='"+id+"'";
			runner.update(conn, sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Good find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from good where id=?";
			return runner.query(conn, sql, id, new BeanHandler<Good>(Good.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//商品在找的时候，一定要分页
	/*
	 * 用户带where条件过来，则该方法返回分类下面的分页数据；
	 * 如果没有带where条件过来，则该方法返回所有书的分页数据
	 * 
	 * where条件的书写格式：String where = "where category_id = ?"
	 */
	private List<Good> getPageData(int startindex, int pagesize, String where, Object param) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			if (where == null || where.trim().equals("")) {
				//返回所有书的分页数据
				String sql = "select * from good limit ?,?";
				Object[] params = {startindex, pagesize};
				return runner.query(conn, sql, params, new BeanListHandler<Good>(Good.class));
			} else {
				//如果上层带了查询条件过来，那么就需要获得该查询条件下的分页数据
				String sql = "select * from good " + where + " limit ?,?";
				Object[] params = {param, startindex, pagesize};
				return runner.query(conn, sql, params, new BeanListHandler<Good>(Good.class));
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//分页的时候，获得总记录数
	private int getPageTotalRecord(String where, Object param) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			
			if (where == null || where.trim().equals("")) {
				String sql = "select count(*) from good";
				return runner.query(conn, sql, new ScalarHandler<Long>()).intValue();
			} else {
				String sql = "select count(*) from good " + where;
				return runner.query(conn, sql, param, new ScalarHandler<Long>()).intValue();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public QueryResult pageQuery(int startindex, int pagesize, String where, Object param) {
		List<Good> list = getPageData(startindex, pagesize, where, param);
		int totalrecord = getPageTotalRecord(where, param);
		QueryResult result = new QueryResult();
		result.setList(list);
		result.setTotalrecord(totalrecord);
		return result;
	}
	
	public List<Good> getAll() {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from good";
			return runner.query(conn, sql, new BeanListHandler<Good>(Good.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

