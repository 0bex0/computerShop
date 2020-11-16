import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProductList {
	
	private ArrayList<Product> products; 
	
	//Constructor 
	public ProductList() throws FileNotFoundException {
		
		//When a ProductList in initiated Stock.txt is read into a String ArrayList
		File stockFile = new File("Stock.txt");
		Scanner stockFileScanner = new Scanner(stockFile);
		List<String> stockList = new ArrayList<String>();
		while(stockFileScanner.hasNextLine()) {
			String line = stockFileScanner.nextLine();
			stockList.add(line);
		}
		stockFileScanner.close();
		
		//String ArrayList is then iterated through to produce a Product object from each line which is then added to Product ArrayList
		products = new ArrayList<Product>();
		for(int i = 0; i< stockList.size(); i++) {
			String [] productDetails = (stockList.get(i)).split(", ");
			long barcode = Long.parseLong(productDetails[0]);
			String productType = productDetails[2];
			String brand = productDetails[3];
			String colour = productDetails[4];
			String connection = productDetails[5];
			int stockQuantity = Integer.parseInt(productDetails[6]);
			float originalCost = Float.parseFloat(productDetails[7]);
			float retailPrice = Float.parseFloat(productDetails[8]);
			if (productDetails[1].equals("mouse")) {
				int numOfButtons = Integer.parseInt(productDetails[9]);
				Mouse product = new Mouse(barcode, productType, brand, colour, connection, stockQuantity, originalCost, retailPrice, numOfButtons);
				products.add(product);
			}
			else if (productDetails[1].equals("keyboard")) {
				String layout = productDetails[9];
				Keyboard product = new Keyboard(barcode, productType, brand, colour, connection, stockQuantity, originalCost, retailPrice, layout);
				products.add(product);
			}
		}
		
		//sorts Product ArrayList into descending stock quantity order
		StockSort prodStockCompare = new StockSort();
		Collections.sort(products, prodStockCompare);
	}
	
	//Getter
	public  ArrayList<Product> getProducts() {
		return products;
	}
	
	//Returns a string array with every product and all it's details including the original price
	public String[] adminViewProducts() {
		String productsToString [] = new String[products.size()];
		for (int i=0; i<products.size(); i++) {
			productsToString[i] = (products.get(i)).toStringAdmin();
		}
		return productsToString;
	}
	
	//Returns ArrayList<String> with all products or only UK Layout keyboards (dependent on keyboardFilter). Doesn't show original price of products
	public ArrayList<String> searchProducts(boolean keyboardFilter) {
		ArrayList<String> customerView = new ArrayList<String>();
		if (keyboardFilter == true) {
			for (Product item : products) {
				if (item instanceof Keyboard){
					Keyboard keyboard = (Keyboard) item;
					if ((keyboard.getLayout()).equals("UK")){
						customerView.add(item.toStringCustomer());
					}
				}
			}
			return customerView;
		}
		else {
			for (Product item : products) {
				customerView.add(item.toStringCustomer());
			}
			return customerView;
		}
	}
	
	//Returns ArrayList<String> with all products of a certain brand or only UK Layout keyboards of that brand. Doesn't show original price of products
	public ArrayList<String> searchProducts(boolean keyboardFilter, String brandFilter) {
		ArrayList<String> customerView = new ArrayList<String>();
		if (keyboardFilter == true) {
			for (Product item : products) {
				if (item instanceof Keyboard){
					Keyboard keyboard = (Keyboard) item;
					if ((keyboard.getLayout()).equals("UK") && (keyboard.getBrand()).equals(brandFilter)){
						customerView.add(item.toStringCustomer());
						System.out.println(item);
					}
			 	}
			}
			return customerView;
		}
		else {
			for (Product item : products) {
				if ((item.getBrand()).equals(brandFilter)) {
					customerView.add(item.toStringCustomer());
				}
			}
			return customerView;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
