package cn.xzc.dao;
import java.util.List;

import cn.xzc.domain.Good;
import cn.xzc.domain.QueryResult;

public interface GoodDao {

	void add(Good b);
	
	void setNum(String name, String type,double num);

	Good find(String id);

	//����ͼ��ķ�ҳ���ݣ�ͼ��һ����˵�кܶ࣬����Ҫ��ҳ������֮�⣬����ϲ���˲�ѯ������������ô����Ҫ��øò�ѯ�����µķ�ҳ���ݣ�
	QueryResult pageQuery(int startindex, int pagesize, String where, Object param);
	
	public List<Good> getAll();

	void del(String id);
}
