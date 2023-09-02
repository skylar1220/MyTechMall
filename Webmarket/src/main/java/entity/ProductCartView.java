package entity;

public class ProductCartView extends Product {
	
	private int cartId;
	private String memberId ;
	private int quantity ; 

	public ProductCartView() {
	}



	public ProductCartView(int cartId, String memberId, String productId, String pname, Integer unitPrice, int quantity) {
		super(productId, pname, unitPrice);
		this.cartId = cartId ; 
		this.memberId = memberId ; 
		this.quantity = quantity ; 
		
	}
	
//	public ProductCartView(String productId, String pname, Integer unitPrice, int quantity) {
//		super(productId, pname, unitPrice);
//		this.quantity = quantity ; 
//		
//	}



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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	
	
	

}
