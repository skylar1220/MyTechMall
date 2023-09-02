package entity;

public class OrderDetail {
	private String orderDetailId ;
	private String shippingId;
	private String productId ;
	private int quantity;
	
	
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(String orderDetailId, String shippingId, String productId, int quantity) {
		super();
		this.orderDetailId = orderDetailId;
		this.shippingId = shippingId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", shippingId=" + shippingId + ", productId=" + productId
				+ ", quantity=" + quantity + "]";
	}
	
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getShippingId() {
		return shippingId;
	}
	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
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
	
	
	
}
