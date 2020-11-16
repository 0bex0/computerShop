public abstract class Product { 
	private long barcode;
	private String productType; 
	private String brand; 
	private String colour; 
	private String connnection;
	private int stockQuantity;
	private float originalCost; 
	private float retailPrice;
	
	//Constructor
	public Product (long barcode, String productType, String brand, String colour, String connnection, int stockQuantity, float originalCost, float retailPrice) {
		this.barcode = barcode; 
		this.productType = productType;
		this.brand = brand; 
		this.colour = colour;
		this.connnection = connnection; 
		this.stockQuantity = stockQuantity;
		this.originalCost = originalCost; 
		this.retailPrice = retailPrice;
	}
	 
	//Getters
	public long getBarcode() {
		return this.barcode;
	}
	public String getProductType() {
		return this.productType;
	}
	public String getBrand() {
		return this.brand;
	}
	public String getColour() {
		return this.colour;
	}
	public String getConnection() {
		return this.connnection;
	}
	public int getStockQuantity() {
		return this.stockQuantity;
	}
	public float getOriginalCost() {
		return this.originalCost;
	}
	public float getRetailPrice() {
		return this.retailPrice;
	}
	
	public abstract String toStringCustomer();
	public abstract String toStringAdmin();
	public abstract String toString();
}