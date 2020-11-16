import java.util.Comparator;

public class StockSort implements Comparator<Product>  {
	
	//Comparator to sort Products by which one has more quantity in stock
	public int compare(Product p1, Product p2) {
		if (p1.getStockQuantity() > p2.getStockQuantity()) return - 1;
		if (p1.getStockQuantity() < p2.getStockQuantity()) return 1;
		else return 0;
	}
}
 