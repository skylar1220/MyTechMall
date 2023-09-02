package entity;

public class ProductView extends Product {

	private int cmtCount ;
	
	
	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public ProductView() {
	}
	
	public ProductView(String productId, String pname, Integer unitPrice, String description, String manufacturer,
			String category, long unitsInStock, String filename, boolean pub, int cmtCount) {
		super(productId, pname, unitPrice, description, manufacturer, category, unitsInStock, filename, pub);
		this.cmtCount = cmtCount;
	}
	
	
	}
	
