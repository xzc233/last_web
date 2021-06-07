package cn.xzc.domain;
public class OrderItem {
	private String id;
	private Good good;//记住订单项代表的是哪一本书
	private int quantity;
	private double price;
	/*
	 * 还有没有必要一个属性记住它属于哪一个订单？没有，这个是为什么没有呢？因为订单项是随着订单来显示的，
	 * 是显示订单的时候就显示订单项，我们从来就没有说去显示某个单独的订单项的时候，去这样写。
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

