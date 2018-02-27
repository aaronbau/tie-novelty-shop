package model;

public class Order {
	private String username;
	private String productName;
	private int quantity;
	public Order(String username, String productName, int quantity) {
		super();
		this.username = username;
		this.productName = productName;
		this.quantity = quantity;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
