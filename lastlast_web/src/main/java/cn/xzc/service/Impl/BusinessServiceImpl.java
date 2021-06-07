package cn.xzc.service.Impl;

import java.sql.Connection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.xzc.dao.GoodDao;
import cn.xzc.dao.OrderDao;
import cn.xzc.dao.CategoryDao;
import cn.xzc.dao.UserDao;
import cn.xzc.domain.Good;
import cn.xzc.domain.Category;
import cn.xzc.domain.PageBean;
import cn.xzc.domain.QueryInfo;
import cn.xzc.domain.QueryResult;
import cn.xzc.domain.User;
import cn.xzc.factory.DaoFactory;
import cn.xzc.service.BusinessService;
import cn.xzc.utils.JdbcUtils;
import cn.xzc.domain.Cart;
import cn.xzc.domain.CartItem;
import cn.xzc.domain.Order;
import cn.xzc.domain.OrderItem;

public class BusinessServiceImpl implements BusinessService {
	
	private CategoryDao cDao = DaoFactory.getInstance().createDao(CategoryDao.class);
	private GoodDao bDao = DaoFactory.getInstance().createDao(GoodDao.class);
	private UserDao uDao = DaoFactory.getInstance().createDao(UserDao.class);
	private OrderDao oDao = DaoFactory.getInstance().createDao(OrderDao.class);
	
	/********************************************
	 * 分类相关的服务
	 ********************************************/
	@Override
	public void addCategory(Category c) {
		cDao.add(c);
	}
	
	@Override
	public Category findCategory(String id) {
		return cDao.find(id);
	}
	
	@Override
	public List<Category> getAllCategory() {
		return cDao.getAll();
	}
	
	/********************************************
	 * 图书相关的服务
	 ********************************************/
	@Override
	public void addGood(Good good) {
		bDao.add(good);
	}
	
	@Override
	public Good findGood(String id) {
		return bDao.find(id);
	}
	
	@Override
	public PageBean bookPageQuery(QueryInfo info) {
		QueryResult result = bDao.pageQuery(info.getStartindex(), info.getPagesize(), info.getWhere(), info.getQueryvalue());
		PageBean bean = new PageBean();
		bean.setCurrentpage(info.getCurrentpage());
		bean.setList(result.getList());
		bean.setPagesize(info.getPagesize());
		bean.setTotalrecord(result.getTotalrecord());
		return bean;
	}
	
	public List<Good> getAllGood() {
		return bDao.getAll();
	}
	public void deleteGood(String id) {
		bDao.del(id);
	}
	/********************************************
	 * 用户相关的服务
	 ********************************************/
	@Override
	public void addUser(User user) {
		uDao.add(user);
	}
	
	@Override
	public User findUser(String username, String password) {
		return uDao.find(username, password);
	}
	
	@Override
	public User findUser(String id) {
		return uDao.find(id);
	}
	
	@Override
	public User findUsername(String name) {
		return uDao.findUser(name);
	}
	
	@Override
	public User findManager(String username) {
		return uDao.findManager(username);
	}
	@Override
	public void setPassword(String id, String pwd) {
		uDao.setPassword(id,pwd);
	}
	public void delUser(String id) {
		uDao.delete(id);
	}

	/********************************************
	 * 订单相关的服务
	 ********************************************/
	//使用用户的购物车来生成一个订单，然后存入数据库中
	@Override
	public void saveOrder(Cart cart, User user) {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setStatus(false);
		order.setUser(user);
		
		//定义一个集合，用于保存所有订单项
		Set<OrderItem> oitems = new HashSet<OrderItem>();
		
		//用购物车中的购物项生成订单项
		Set<Map.Entry<String, CartItem>> set = cart.getMap().entrySet();
		for (Map.Entry<String, CartItem> entry : set) {
			//得到每一个购物项
			CartItem citem = entry.getValue();
			OrderItem oitem = new OrderItem();
			
			//用购物车中的购物项生成订单项
			oitem.setGood(citem.getGood());
			oitem.setId(UUID.randomUUID().toString());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			
			oitems.add(oitem);
		}
		
		order.setOrderitems(oitems);
		oDao.add(order);
	}
	
	@Override
	public Order findOrder(String id) {
		return oDao.find(id);
	}
	
	
	@Override
	public List<Order> getOrderByStatus(boolean status) {
		return oDao.getAll(status);
	}
	@Override
	public Order findOrder(String id,String userid){
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			//找出订单的基本信息
			String sql = "select * from orders where id=?";
			Order order = runner.query(conn, sql, new BeanHandler<Order>(Order.class), id);
			
			//找出订单中的每一个订单项
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = runner.query(conn, sql, new BeanListHandler<OrderItem>(OrderItem.class), id);
			
			//找出每一个订单项代表的每本书（涉及到多表查询）
			for (OrderItem item : list) {
				sql = "select b.* from orderitem oi,good b where oi.id=? and b.id=oi.good_id and b.userid=?";
				Good good = runner.query(conn, sql, new BeanHandler<Good>(Good.class), item.getId(),userid);
				
				item.setGood(good);
			}
			Iterator<OrderItem> iterator = list.iterator();
	        while(iterator.hasNext()){
	        	OrderItem integer = iterator.next();
	            if(integer.getGood()==null)
	                iterator.remove();   
	        }
			order.getOrderitems().addAll(list);
			
			//找出下订单的人（涉及到多表查询）
			sql = "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
			User user = runner.query(conn, sql, new BeanHandler<User>(User.class), id);
			
			order.setUser(user);
			
			return order;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//更新订单状态
	public void updatOrder(String id, boolean status) {
		oDao.update(id, status);
	}
	
	//使用浏览时间，用户，商品种类生成日志，然后存入数据库中
		@Override
		public void saveBrowse(String time, String username, String category) {
			try {
				Connection conn = JdbcUtils.getConnection();
				QueryRunner runner = new QueryRunner();
				String sql = "insert into browse(time, username, category) values(?,?,?)";
				Object[] params = {time,username,category};
				runner.update(conn, sql, params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public void saveIp(String addr, String username, Boolean status) {
			try {
				Connection conn = JdbcUtils.getConnection();
				QueryRunner runner = new QueryRunner();
				String s="wrong";
				Date date=new Date();
				if(status) s="login";
					else s="logout";
				String sql = "insert into ipRemark(time, username, addr, status) values(?,?,?,?)";
				Object[] params = {date,username,addr,s};
				runner.update(conn, sql, params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		public void saveOp(String addr, String username, String permission, String op) {
			try {
				Connection conn = JdbcUtils.getConnection();
				QueryRunner runner = new QueryRunner();
		
				Date date=new Date();
				String sql = "insert into opRemark(time, username, permission, addr, op) values(?,?,?,?,?)";
				Object[] params = {date,username,permission,addr,op};
				runner.update(conn, sql, params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		public void deleteCartItem(String id, Cart cart) {
			cart.getMap().remove(id);
		}
		
		public List<Good> getGoodbyUserid(String userid){
			try {
				Connection conn = JdbcUtils.getConnection();
				QueryRunner runner = new QueryRunner();
				String sql = "select * from good where userid=?";
				return runner.query(conn, sql, userid, new BeanListHandler<Good>(Good.class));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
}

