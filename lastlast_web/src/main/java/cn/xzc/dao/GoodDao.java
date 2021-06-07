package cn.xzc.dao;
import java.util.List;

import cn.xzc.domain.Good;
import cn.xzc.domain.QueryResult;

public interface GoodDao {

	void add(Good b);
	
	void setNum(String name, String type,double num);

	Good find(String id);

	//查找图书的分页数据（图书一般来说有很多，所以要分页。除此之外，如果上层带了查询条件过来，那么就需要获得该查询条件下的分页数据）
	QueryResult pageQuery(int startindex, int pagesize, String where, Object param);
	
	public List<Good> getAll();

	void del(String id);
}
