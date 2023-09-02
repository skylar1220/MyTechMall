package entity;

public class Cart {
	
	private int cartId;
	private String memberId;
	private String productId;
	private int quantity;
	
//	private String pname ; 
//	private Integer unitPrice  ;
	
	
	public Cart() {
		super();
	}
	
	public Cart(int cartId, String memberId, String productId, int quantity) {
		super();
		this.cartId = cartId;
		this.memberId = memberId;
		this.productId = productId;
		this.quantity = quantity;
//		this.pname = pname;
//		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", memberId=" + memberId + ", productId=" + productId + ", quantity="
				+ quantity + "]";
	}
	
	
	public int getCartId() {
		return cartId;
	}
//	public void setCartId(int cartId) {
//		this.cartId = cartId;
//	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
//	public String getPname() {
//		return pname;
//	}
//	public void setPname(String pname) {
//		this.pname = pname;
//	}
//	public Integer getUnitPrice() {
//		return unitPrice;
//	}
//	public void setUnitPrice(Integer unitPrice) {
//		this.unitPrice = unitPrice;
}
	

