package data;

public class DetailTransaksi {

	private String productID, productName;
	private Integer quantity, hargaItem;
	
	public DetailTransaksi(String productID, String productName, Integer quantity, Integer hargaItem) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
		this.hargaItem = hargaItem;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getHargaItem() {
		return hargaItem;
	}

	public void setHargaItem(Integer hargaItem) {
		this.hargaItem = hargaItem;
	}

}
