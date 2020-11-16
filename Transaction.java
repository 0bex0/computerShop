import java.util.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;  
import java.io.FileNotFoundException;

public class Transaction {

	private HashMap<Long, Integer> basket;
	private HashMap<Long, Float> basketPrices;
	private String status;
	private String dateOfTransaction;
	private String paymentMethod;
	private int userId;
	private String postcode;

	//Constructor
	public Transaction(HashMap<Long, Integer> basket, HashMap<Long, Float> basketPrices, String paymentMethod, String status, String dateOfTransaction, int userId, String postcode) {
		this.basketPrices = basketPrices;
		this.status = status;
		this.dateOfTransaction = dateOfTransaction;
		this.paymentMethod = paymentMethod;
		this.userId = userId;
		this.postcode = postcode;
		this.basket = basket;
	}
	
	//Constructor 
	public Transaction(HashMap<Long, Integer> basket, HashMap<Long, Float> basketPrices, String status, String dateOfTransaction, int userId, String postcode) {
		this.basketPrices = basketPrices;
		this.status = status;
		this.dateOfTransaction = dateOfTransaction;
		this.userId = userId;
		this.postcode = postcode;
		this.paymentMethod = "";
		this.basket = basket;
	} 

	public void writeToLog() throws FileNotFoundException {
		
		//Saves all lines already in Log.txt to be re-written later
		File logFile = new File("Log.txt");
		Scanner logFileScanner = new Scanner(logFile);
		List<String> loggedInfo = new ArrayList<String>();
		while(logFileScanner.hasNextLine()) {
			String line = logFileScanner.nextLine();
			loggedInfo.add(line);
		}
		logFileScanner.close();
		
		try {
			//Writes over Log.txt with new log entry
			FileWriter transactionWriter = new FileWriter("Log.txt", false);
			BufferedWriter transactionBuffer = new BufferedWriter(transactionWriter);
			for(HashMap.Entry<Long, Integer> entry : basket.entrySet()){
				Long key = entry.getKey();
				transactionBuffer.write(userId + ", " + postcode + ", " + key + ", " + basketPrices.get(key) +", "+ entry.getValue() + ", " + status + ", " + paymentMethod +", " + dateOfTransaction + "\n");
			}
			//Writes in all saves lines form Log.txt so that all entries are in descending date order
			for (String line : loggedInfo) {
				transactionBuffer.write(line +"\n");
			}
			transactionBuffer.close(); 
		} catch(IOException e) {
			System.out.println("AN ERROR HAS OCCURED");
		}
	}

}