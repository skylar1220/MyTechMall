package entity;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -4274700572038677000L; // who are you?

	private String productId;	 
	private String pname;		 
	private Integer unitPrice; 
	private String description; 
	private String manufacturer; 
	private String category; 	 
	private long unitsInStock; 
	private String filename; 	 
	private boolean pub;
	private int quantity ; 			// 장바구니에 담은 개수


	public Product() {
		super();
	}

	public Product(String productId, String pname, Integer unitPrice) {
		this.productId = productId;
		this.pname = pname;
		this.unitPrice = unitPrice;
	}
	

	

	public Product(String productId, String pname, Integer unitPrice, String description, String manufacturer,
			String category, long unitsInStock, String filename,   boolean pub) {
		this.productId = productId;
		this.pname = pname;
		this.unitPrice = unitPrice;
		this.description = description;
		this.manufacturer = manufacturer;
		this.category = category;
		this.unitsInStock = unitsInStock;
		this.filename = filename;
		this.pub = pub;
	}

	

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", pname=" + pname + ", unitPrice=" + unitPrice + ", description="
				+ description + ", manufacturer=" + manufacturer + ", category=" + category + ", unitsInStock="
				+ unitsInStock + ", filename=" + filename +   ", pub=" + pub + "]";
	}

	

	public String getProductId() {
		return productId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}


	public int getQuantity() {
		return quantity;
	}

	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}


	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}		
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
