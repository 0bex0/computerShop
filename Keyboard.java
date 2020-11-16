public class Keyboard extends Product {
	
	private String layout;
	
	//Constructor
	public Keyboard(long barcode, String productType, String brand, String colour, String connection, int stockQuantity, float originalCost, float retailPrice, String layout) {
		super(barcode, productType, brand, colour, connection, stockQuantity, originalCost, retailPrice);
		this.layout = layout; 
	}
	
	public String getLayout() {
		return this.layout;
	}
	 
	//Method to return the keyboard object as a string without the original price for the customer to view it
	public String toStringCustomer() {
		return ("Product Type: " + getProductType() + " keyboard | Brand: " + getBrand() + " | Colour: " + getColour() + " | Connection: " + getConnection() + " | Layout: " + getLayout() + "| Stock: " + getStockQuantity() + " | Price: £" + getRetailPrice() +" | Barcode: " + getBarcode());
	}
	
	//Method to return the keyboard object as a string for the admin to view it
	public String toStringAdmin () {
		return ("Barcode: " + getBarcode() + " | Product Type: " + getProductType() + " keyboard | Brand: " + getBrand() + " | Colour: " + getColour() + " | Connection: " + getConnection() + " | Layout: " + getLayout() + "| Stock: " + getStockQuantity() + " | Retail Price: £" + getRetailPrice() +" | Original Cost: £" + getOriginalCost());
	}
	
	//Method to return the keyboard object in the string format keyboards are stored in Stock.txt
	public String toString(){
		return ("\n" + getBarcode() + ", keyboard, " + getProductType() + ", " + getBrand() + ", " + getColour() + ", " + getConnection() + ", " + getStockQuantity() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", " + getLayout());
	}
} 