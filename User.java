public abstract class User {
	private int uniqueId; 
	private String userName;
	private String surname; 
	private int houseNum; 
	private String postcode; 
	private String city;
	
	//Constructor
	public User(int uniqueId, String userName, String surname, int houseNum, String postcode, String city) {
		this.uniqueId = uniqueId; 
		this.userName = userName; 
		this.surname = surname; 
		this.houseNum = houseNum; 
		this.postcode = postcode; 
		this.city = city; 
	}
	
	//Getters
	public int getId() {
		return this.uniqueId;
	} 
	public String getUserName() {
		return this.userName; 
	}
	public String getSurname() {
		return this.surname; 
	}
	public int getHouseNum() {
		return this.houseNum;
	}
	public String getPostcode() {
		return this.postcode;
	}
	public String getCity() {
		return this.city;
	}
}