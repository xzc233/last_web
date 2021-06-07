package cn.xzc.dao;

import cn.xzc.domain.User;

public interface UserDao {

	void add(User user);
	
	void delete(String id);
	
	void setPassword(String id, String pwd);

	User find(String id);

	User find(String username, String password);
	
	User findManager(String username);

	User findUser(String name);

}