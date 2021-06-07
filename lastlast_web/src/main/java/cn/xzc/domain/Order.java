package cn.xzc.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {
	private String id;
	private Date ordertime;//�µ�ʱ��
	private boolean status;//����״̬
	private double price;//�����ܼ�
	
	private User user;//��ס�µ���
	private Set<OrderItem> orderitems = new HashSet<OrderItem>();//��ס�������еĶ�����
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<OrderItem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(Set<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
}
