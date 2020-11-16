public class Mouse extends Product {
	
	private int numOfButtons;
	
	//Constructor
	public Mouse(long barcode, String productType, String brand, String colour, String connection, int stockQuantity, float originalCost, float retailPrice, int numOfButtons) {
		super(barcode, productType, brand, colour, connection, stockQuantity, originalCost, retailPrice);
		this.numOfButtons = numOfButtons; 
	}
	
	public int getButtons() {
		return this.numOfButtons;
	}
	 
	//Method to return the mouse object as a string without the original price for the customer to view it
	public String toStringCustomer() {
		return ("Product Type: " + getProductType() + " mouse | Brand: " + getBrand() + " | Colour: " + getColour() + " | Connection: " + getConnection() + " | # of buttons: " + getButtons() + "| Stock: " + getStockQuantity() + " | Price: £" + getRetailPrice() +" | Barcode: " + getBarcode());
	}
	
	//Method to return the mouse object as a string for the admin to view it
	public String toStringAdmin() {
		return ("Barcode: " + getBarcode() + " | Product Type: " + getProductType() + " mouse | Brand: " + getBrand() + " | Colour: " + getColour() + " | Connection: " + getConnection() + " | # of buttons: " + getButtons() + "| Stock: " + getStockQuantity() + " | Retail Price: £" + getRetailPrice() +" | Original Cost: £" + getOriginalCost());
	}
	
	//Method to return the mouse object in the string format mouse are stored in Stock.txt
	public String toString(){
		return ("\n" + getBarcode() + ", mouse, " + getProductType() + ", " + getBrand() + ", " + getColour() + ", " + getConnection() + ", " + getStockQuantity() + ", " + getOriginalCost() + ", " + getRetailPrice() + ", " + getButtons());
	}
}