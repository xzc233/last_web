package cn.xzc.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.xzc.dao.OrderDao;
import cn.xzc.domain.Good;
import cn.xzc.domain.Order;
import cn.xzc.domain.OrderItem;
import cn.xzc.domain.User;
import cn.xzc.utils.JdbcUtils;

//�ѵ�
public class OrderDaoImpl implements OrderDao {
	@Override
	public void add(Order o) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			//���涩���Ļ�����Ϣ
			String sql = "insert into orders(id,ordertime,status,price,user_id) values(?,?,?,?,?)";
			Object[] params = {o.getId(), o.getOrdertime(), o.isStatus(), o.getPrice(), o.getUser().getId()};
			runner.update(conn, sql, params);
			
			//�ٱ�����������Ļ�����Ϣ
			Set<OrderItem> set = o.getOrderitems();
			for (OrderItem item : set) {
				sql = "insert into orderitem(id,quantity,price,good_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(), item.getGood().getId(), o.getId()};
				runner.update(conn, sql, params);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * ���鷳��һ������
	 * �������Ҫ��Order����Ļ�����Ϣ�һ�������Ҫ���û�����Ϣ�һ�������Ҫ�Ѷ�����������Ϣ�һ�����
	 * ��ô��Ҫ����4�ű�
	 * 
	 */
	@Override
	public Order find(String id) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			//�ҳ������Ļ�����Ϣ
			String sql = "select * from orders where id=?";
			Order order = runner.query(conn, sql, new BeanHandler<Order>(Order.class), id);
			
			//�ҳ������е�ÿһ��������
			sql = "select * from orderitem where order_id=?";
			List<OrderItem> list = runner.query(conn, sql, new BeanListHandler<OrderItem>(OrderItem.class), id);
			
			//�ҳ�ÿһ������������ÿ���飨�漰������ѯ��
			for (OrderItem item : list) {
				sql = "select b.* from orderitem oi,good b where oi.id=? and b.id=oi.good_id";
				Good good = runner.query(conn, sql, new BeanHandler<Good>(Good.class), item.getId());
				item.setGood(good);
			}
			order.getOrderitems().addAll(list);
			
			//�ҳ��¶������ˣ��漰������ѯ��
			sql = "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
			User user = runner.query(conn, sql, new BeanHandler<User>(User.class), id);
			
			order.setUser(user);
			
			return order;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * �鿴�Ѿ�������û�����Ķ�����Ϣ
	 * 
	 * statusΪtrue����ʾ�ѷ���
	 * statusΪfalse����ʾδ����
	 */
	@Override
	public List<Order> getAll(boolean status) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "select * from orders where status=?";
			List<Order> list = runner.query(conn, sql, new BeanListHandler<Order>(Order.class), status);
			//�ҳ�ÿһ���������µ���
			for (Order o : list) {
				sql = "select u.* from orders o,user u where o.id=? and u.id=o.user_id";
				User user = runner.query(conn, sql, new BeanHandler<User>(User.class), o.getId());
				o.setUser(user);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//���¶���״̬
	public void update(String id, boolean status) {
		try {
			Connection conn = JdbcUtils.getConnection();
			QueryRunner runner = new QueryRunner();
			String sql = "update orders set status=? where id=?";
			Object[] parmas = {status, id};
			runner.update(conn, sql, parmas);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
