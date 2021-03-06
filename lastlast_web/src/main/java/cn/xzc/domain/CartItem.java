package cn.xzc.domain;

public class CartItem {
	private Good good;
	private int quantity;
	private double price;
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
		this.price = this.good.getPrice() * quantity;//书的数量一更新，书的总价马上更新
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
}
