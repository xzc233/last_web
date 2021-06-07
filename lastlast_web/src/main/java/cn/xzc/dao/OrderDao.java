package cn.xzc.dao;
import java.util.List;

import cn.xzc.domain.Order;

	public interface OrderDao {

		void add(Order o);

		/*
		 * 最麻烦的一个方法
		 * 这个方法要把Order对象的基本信息找回来，还要把用户的信息找回来，还要把多个订单项的信息找回来。
		 * 那么就要查找4张表。
		 * 
		 */
		Order find(String id);

		/*
		 * 查看已经发货或没发货的订单信息
		 * 
		 * status为true，表示已发货
		 * status为false，表示未发货
		 */
		List<Order> getAll(boolean status);
		
		//更新订单状态
		public void update(String id, boolean status);
		
	}

