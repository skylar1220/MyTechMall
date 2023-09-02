package entity;

import java.sql.Timestamp;

public class Comment {
	
	private int c_number;
	private String productId;
	private String writerId;
	private String c_content;
	private Timestamp c_regdate;
	
	public Comment(int c_number, String productId, String writerId, String c_content, Timestamp c_regdate) {
		super();
		this.c_number = c_number;
		this.productId = productId;
		this.writerId = writerId;
		this.c_content = c_content;
		this.c_regdate = c_regdate;
	}


	public Comment() {
		super();
	}


	public int getC_number() {
		return c_number;
	}


	public void setC_number(int c_number) {
		this.c_number = c_number;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getWriterId() {
		return writerId;
	}


	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}


	public String getC_content() {
		return c_content;
	}


	public void setC_content(String c_content) {
		this.c_content = c_content;
	}


	public Timestamp getC_regdate() {
		return c_regdate;
	}


	public void setC_regdate(Timestamp c_regdate) {
		this.c_regdate = c_regdate;
	}


	@Override
	public String toString() {
		return "Comment [c_number=" + c_number + ", productId=" + productId + ", writerId=" + writerId + ", c_content="
				+ c_content + ", c_regdate=" + c_regdate + "]";
	}



}
	

