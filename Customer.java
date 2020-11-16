import java.util.*;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class Customer extends User { 

	private HashMap<Long, Integer> basket;
	private HashMap<Long, Float> basketPrices;
	
	//Constructor
	public Customer(int uniqueId, String userName, String surname, int houseNum, String postcode, String city) {
		super(uniqueId, userName, surname, houseNum, postcode, city);
		this.basket = new HashMap<Long, Integer>();
		this.basketPrices = new HashMap<Long, Float>();
	}

	//Method to add a product to Customer's basket 
	public String addToBasket(Product item, int quantity) {
		if (quantity > item.getStockQuantity()) {
			return ("There aren't enough items in stock for this request.");
		}
		else {
			if (basket.containsKey(item.getBarcode())) {
				int newQuantity = quantity + basket.get(item.getBarcode());
				basket.put(item.getBarcode(), newQuantity);
			}
			else {
				basket.put(item.getBarcode(), quantity);
				basketPrices.put(item.getBarcode(), item.getRetailPrice());
			}
			return ("Item(s) added to basket.");
		}
	}

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	//Method to log basket and payment information in Log.txt when customer checks out using credit card
	public String checkout(String creditCardNum, String securityCode) throws FileNotFoundException {
		float basketTotal = 0;
		String status = "purchsed";
		String paymentMethod = "Credit Card";
		Date date = new Date();
		String dateOfTransaction = formatter.format(date);
		Transaction payment = new Transaction(basket, basketPrices, paymentMethod, status, dateOfTransaction, this.getId(), this.getPostcode());
		payment.writeToLog();
		for(HashMap.Entry<Long, Float> entry : basketPrices.entrySet()) {
			basketTotal += entry.getValue();
		}
		basket.clear();
		basketPrices.clear();
		return (String.format("£ %2f paid using Credit Card", basketTotal));
	}
	
	//Method to log basket and payment information in Log.txt when customer checks out using PayPal
	public String checkout(String email) throws FileNotFoundException {
		float basketTotal = 0;
		String status = "purchased";
		String paymentMethod = "PayPal";
		Date date = new Date();
		String dateOfTransaction = formatter.format(date);
		Transaction payment = new Transaction(basket, basketPrices, paymentMethod, status, dateOfTransaction, this.getId(), this.getPostcode());
		payment.writeToLog();
		for(HashMap.Entry<Long, Float> entry : basketPrices.entrySet()) {
			System.out.println(entry.getValue());
			basketTotal += entry.getValue();
		}
		basket.clear();
		basketPrices.clear();
		return (String.format("£ %.2f paid using PayPal", basketTotal));
	} 
	
	//Method to log basket information in Log.txt when customer cancels basket
	public void cancelBasket() throws FileNotFoundException {
		String status = "cancelled";
		Date date = new Date();
		String dateOfTransaction = formatter.format(date);
		Transaction cancellation = new Transaction(basket, basketPrices, status, dateOfTransaction, this.getId(), this.getPostcode());
		cancellation.writeToLog();
		basket.clear();
		basketPrices.clear();
	}

	//Method to log basket information in Log.txt when customer saves basket
	public void saveBasket() throws FileNotFoundException {
		System.out.println("Reached");
		String status = "saved";
		Date date = new Date();
		String dateOfTransaction = formatter.format(date);
		Transaction savedBasket = new Transaction(basket, basketPrices, status, dateOfTransaction, this.getId(), this.getPostcode());
		savedBasket.writeToLog();
		basket.clear();
		basketPrices.clear();
	}
}
