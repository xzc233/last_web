package cn.xzc.dao.impl;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.xzc.dao.UserDao;
import cn.xzc.domain.User;
import cn.xzc.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	@Override
	public void add(User user) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "insert into user(id,username,password,phone,email,address,permission) values(?,?,?,?,?,?,?)";
			Object[] params = {user.getId(), user.getUsername(), user.getPassword(), user.getPhone(),  user.getEmail(), user.getAddress(),user.getPermission()};
			runner.update(conn, sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void delete(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "delete from user where id=?";
			runner.update(conn, sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void setPassword(String id, String pwd) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "update user set password='"+pwd+"' where id='"+id+"'";
			runner.update(conn, sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public User find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where id=?";
			return runner.query(conn, sql, id, new BeanHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public User findUser(String name) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where username=?";
			return runner.query(conn, sql, name, new BeanHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public User find(String username, String password) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where username=? and password=?";
			return runner.query(conn, sql, new Object[]{username, password}, new BeanHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findManager(String username) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from user where username=? and permission=?";
			return runner.query(conn, sql, new Object[]{username, "manager"}, new BeanHandler<User>(User.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
