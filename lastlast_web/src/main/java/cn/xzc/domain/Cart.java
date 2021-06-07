package cn.xzc.domain;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	private double price;//���ﳵ���ܼ�
	
	//�����ﳵ�����һ����ķ�����Ҳ���ṩ����Ʒ��ӵ�����Ĺ��ܣ�
	public void add(Good good) {
		//�õ���Ӧ�Ĺ�����
		CartItem item = map.get(good.getId());
		
		//�����null��˵�����ﳵ�л�û�иù�����
		if (item == null) {
			item = new CartItem();
			item.setGood(good);
			item.setQuantity(1);
			
			//�ѹ�����ӵ����ﳵ��
			map.put(good.getId(), item);
		}  else {
			//������ﳵ�иù������ˣ���ô�������������+1
			item.setQuantity(item.getQuantity() + 1);
		}
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	//���ﳵ�ļ�Ǯ�ǹ������Ǯ���ܺ�
	public double getPrice() {
		double totalprice = 0;
		for (Map.Entry<String, CartItem> entry : map.entrySet()) {
			totalprice += entry.getValue().getPrice();
		}
		this.price = totalprice;
		return price;
	}
}


