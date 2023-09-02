package entity;

import java.sql.Date;

public class Shipping {
	private String shippingId;
	private String memberId ; //fk
	private int sum 		;
	private String name ;		
	private String phone ;	
	private String postcode ;	
	private String address 	;
	private String memo ;		
	private String orderDate ;
	private String status;
	
	
	
	
	public Shipping(String shippingId, String memberId, int sum, String name, String phone, String postcode,
			String address, String memo, String orderDate, String status) {
		super();
		this.shippingId = shippingId;
		this.memberId = memberId;
		this.sum = sum;
		this.name = name;
		this.phone = phone;
		this.postcode = postcode;
		this.address = address;
		this.memo = memo;
		this.orderDate = orderDate;
		this.status = status;
	}




	public Shipping() {
		super();
	}




	public String getShippingId() {
		return shippingId;
	}




	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}




	public String getMemberId() {
		return memberId;
	}




	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}




	public int getSum() {
		return sum;
	}




	public void setSum(int sum) {
		this.sum = sum;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getPostcode() {
		return postcode;
	}




	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getMemo() {
		return memo;
	}




	public void setMemo(String memo) {
		this.memo = memo;
	}




	public String getOrderDate() {
		return orderDate;
	}




	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "Shipping [shippingId=" + shippingId + ", memberId=" + memberId + ", sum=" + sum + ", name=" + name
				+ ", phone=" + phone + ", postcode=" + postcode + ", address=" + address + ", memo=" + memo
				+ ", orderDate=" + orderDate + ", status=" + status + "]";
	}
	
	
	
}
