package cn.xzc.domain;
public class OrderItem {
	private String id;
	private Good good;//��ס��������������һ����
	private int quantity;
	private double price;
	/*
	 * ����û�б�Ҫһ�����Լ�ס��������һ��������û�У������Ϊʲôû���أ���Ϊ�����������Ŷ�������ʾ�ģ�
	 * ����ʾ������ʱ�����ʾ��������Ǵ�����û��˵ȥ��ʾĳ�������Ķ������ʱ��ȥ����д��
	 */
	//private Order order;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

