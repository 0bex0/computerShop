import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Admin extends User {
	
	//Constructor
	public Admin(int uniqueId, String userName, String surname, int houseNum, String postcode, String city) {
		super(uniqueId, userName, surname, houseNum, postcode, city);
	}

	//Method to add a new mouse to stock.txt
	public void addProduct(long barcode, String productType, String brand, String colour, String connnection, int stockQuantity, float originalCost, float retailPrice, int numOfButtons) {
		Mouse newMouse = new Mouse(barcode, productType, brand, colour, connnection, stockQuantity, originalCost, retailPrice, numOfButtons);
		try {
			FileWriter productWriter = new FileWriter("Stock.txt", true);
			BufferedWriter productBuffer = new BufferedWriter(productWriter);
			productBuffer.write(newMouse.toString());
			productBuffer.close(); 
		} catch(IOException e) {
			System.out.println("AN ERROR HAS OCCURED");
		}
	}
	
	//Method to add a new keyboard to stock.txt
	public void addProduct(long barcode, String productType, String brand, String colour, String connnection, int stockQuantity, float originalCost, float retailPrice, String layout) {
		Keyboard newKeyboard = new Keyboard(barcode, productType, brand, colour, connnection, stockQuantity, originalCost, retailPrice, layout);
		try {
			FileWriter productWriter = new FileWriter("Stock.txt", true);
			BufferedWriter productBuffer = new BufferedWriter(productWriter);
			productBuffer.write(newKeyboard.toString());
			productBuffer.close(); 
		} catch(IOException e) {
			System.out.println("AN ERROR HAS OCCURED");
		}
	}
}