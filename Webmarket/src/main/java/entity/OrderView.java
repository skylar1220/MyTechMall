package entity;

import java.sql.Date;

public class OrderView extends Shipping {
	private String orderDetailId;
	private String productId ;
	private int quantity ;
	private String pname ;
	private int unitPrice ;
	
	public OrderView() {
	}

	public OrderView(String orderDetailId , String productId , int quantity , String pname , int unitPrice , 
			String shippingId, String memberId, int sum, String name, String phone, String postcode,
			String address, String memo, String orderDate, String status) {
		super(shippingId, memberId, sum, name, phone, postcode, address, memo, orderDate, status);
		this.orderDetailId=orderDetailId;
		this.productId=productId;
		this.quantity=quantity;
		this.pname=pname;
		this.unitPrice=unitPrice;
		
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
	
	
}
