package cn.xzc.service;

import java.util.Date;
import java.util.List;

import cn.xzc.domain.Good;
import cn.xzc.domain.Category;
import cn.xzc.domain.PageBean;
import cn.xzc.domain.QueryInfo;
import cn.xzc.domain.User;
import cn.xzc.domain.Cart;
import cn.xzc.domain.Order;

public interface BusinessService {

	/********************************************
	 * 分类相关的服务
	 ********************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List<Category> getAllCategory();

	/********************************************
	 * 图书相关的服务
	 ********************************************/
	void addGood(Good good);

	Good findGood(String id);

	PageBean bookPageQuery(QueryInfo info);
	
	public List<Good> getAllGood();

	/********************************************
	 * 用户相关的服务
	 ********************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);
	
	User findUsername(String name);
	
	User findManager(String username);
	
	void delUser(String id);
	
	void setPassword(String id, String pwd);
	/********************************************
	 * 订单相关的服务
	 ********************************************/
	//使用用户的购物车来生成一个订单，然后存入数据库中
	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List<Order> getOrderByStatus(boolean status);
	
	Order findOrder(String id,String userid);
	
	//更新订单状态
	public void updatOrder(String id, boolean status);
	
	void saveBrowse(String time, String username, String category);
	
	void saveIp(String addr, String username, Boolean status);
	
	void saveOp(String addr, String username, String permission, String op);

	void deleteCartItem(String id, Cart cart);

	void deleteGood(String id);

	List<Good> getGoodbyUserid(String userid);
	
}

