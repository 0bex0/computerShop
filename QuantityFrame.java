import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class QuantityFrame extends JFrame {

	private JPanel contentPane;
	static volatile Product itemToAdd;

	public QuantityFrame(String selectedItem, User loggedInUser) throws FileNotFoundException{
		ProductList listOfProducts = new ProductList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Basic frame set-up
		setVisible(true);
		setBounds(300, 300, 300, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//Get item to be added to basket 
		Long barcode = Long.parseLong(selectedItem.substring(selectedItem.length() -6));
		for (Product product : listOfProducts.getProducts()) {
			if (product.getBarcode() == barcode) {
				itemToAdd = product;
			}
		}
		 
		//Frame labels, buttons, spinner
		JLabel title = new JLabel("Quantity:", SwingConstants.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		JButton add = new JButton("Add to basket");
		contentPane.add(add, BorderLayout.SOUTH);
		
		SpinnerModel spinnerRange = new SpinnerNumberModel(1, 1, itemToAdd.getStockQuantity(), 1);
		JSpinner stockQuantity = new JSpinner(spinnerRange);
		contentPane.add(stockQuantity, BorderLayout.CENTER);
		
		//When add is clicked addToBasket() method is called using user inputed quantity.
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Customer customer = (Customer) loggedInUser;
				int quantity = (int) stockQuantity.getValue();
				String comment  = customer.addToBasket(itemToAdd, quantity);
				DisplayInfo response = new DisplayInfo(comment);
				dispose();
			}
		});
		
	}

}
