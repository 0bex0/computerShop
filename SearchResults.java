import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SearchResults extends JFrame {

	private JPanel contentPane;
	private boolean keyboardFilter;
	private String brandFilter = "";
	private User loggedInUser;

	//Constructor with brand filter applied
	public SearchResults(boolean keyboardFilter, String brandFilter, User loggedInUser) throws FileNotFoundException {
		this.keyboardFilter = keyboardFilter;
		this.brandFilter = brandFilter;
		this.loggedInUser = loggedInUser;
		createFrame();
	}
	
	//Constructor without brand filter applied
	public SearchResults(boolean keyboardFilter, User loggedInUser) throws FileNotFoundException {
		this.keyboardFilter = keyboardFilter;
		this.loggedInUser = loggedInUser;
		createFrame();
	}
	
	public void createFrame() throws FileNotFoundException {
		
		//Basic frame set-up
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		
		ProductList listOfProducts = new ProductList();
		
		//Labels and buttons 
		JPanel topPanel = new JPanel(new GridLayout(2,0,0,0));
		JPanel filterPanel = new JPanel (new GridLayout(0,2,0,0));
		JPanel brandFilterPanel = new JPanel (new GridLayout(0,3,0,0));
		
		JLabel productsLabel = new JLabel("PRODUCTS:", SwingConstants.CENTER);
		productsLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		
		//Determines whether keyboardFilter has already been toggled and thus should appear selected in new frame
		if (keyboardFilter) {
			JToggleButton keyboardFilterButton = new JToggleButton("Add UK Keyboard Layout Filter", true);
			filterPanel.add(keyboardFilterButton);
		}
		else {
			JToggleButton keyboardFilterButton = new JToggleButton("Add UK Keyboard Layout Filter");
			filterPanel.add(keyboardFilterButton);
		}
		topPanel.add(productsLabel);
		
		JToggleButton applyFilters = new JToggleButton("Apply Filter(s)", true);
		JPanel listPanel = new JPanel(new GridLayout());
		
		//Brand filter
		JComboBox<String> brandList = new JComboBox<String>();
		brandList.setEditable(false);
		
		//For loop goes through brands in Products list to create a HashSet of unique brands to use as options in brand filter
		ArrayList<Product> productArray = listOfProducts.getProducts();
		HashSet<String> brands = new HashSet<String>();
		for (Product item : productArray) {
			brands.add(item.getBrand());
		}
		brandList.addItem("Filter by brand:");
		for (String brand : brands) {
			brandList.addItem(brand);
		}
		brandFilterPanel.add(brandList);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		brandFilterPanel.add(separator);
		brandFilterPanel.add(applyFilters);
		filterPanel.add(brandFilterPanel);
		
		add(topPanel, BorderLayout.NORTH);
		
		//Buttons on bottom part of frame 
		JPanel buttonPanel = new JPanel (new GridLayout());
		JButton logout = new JButton("Logout");
		JButton addToBasket = new JButton("Add to basket");
		JButton saveBasket = new JButton("Save Basket");
		JButton cancelBasket = new JButton("Cancel Basket");
		JButton checkout = new JButton("Checkout");
		buttonPanel.add(logout);
		buttonPanel.add(addToBasket);
		buttonPanel.add(saveBasket);
		buttonPanel.add(cancelBasket);
		buttonPanel.add(checkout);
		topPanel.add(filterPanel);
		
		//Produces list of products which meet requirements set by the filters in Constructor
		DefaultListModel<String> products = new DefaultListModel<String>();
		JList<String> productList = new JList<String>(products);
		
		if (brandFilter.equals("")) {
			ArrayList<String> customerProductList = listOfProducts.searchProducts(true);
			for (String product : customerProductList) {
				products.addElement(product);
			}
		}
		else {
			ArrayList<String> customerProductList = listOfProducts.searchProducts(keyboardFilter, brandFilter);
			for (String product : customerProductList) {
				products.addElement(product);
			}
		}
			
		listPanel.add(productList);
		
		getContentPane().add(listPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		//If applyFilters is clicked, it's unselected so full product list in ProductFrame is displayed again
		applyFilters.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					try {
						ProductFrame productFrame = new ProductFrame(loggedInUser);
						dispose();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
			}
		});
		
		//If addBasket is clicked a quantityFrame is displayed prompting the user to enter the quantity of the item to be added to basket
		addToBasket.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					try {
						QuantityFrame quantityFrame = new QuantityFrame(productList.getSelectedValue(), loggedInUser);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
			}
		});
		
		//If saveBasket is clicked the basket content is logged through saveBasket() method and a displayInfo frame appears to inform user 
		saveBasket.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Customer customer = (Customer) loggedInUser;
				try {
					customer.saveBasket();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//If cancelBasket is clicked the basket content is logged through cancelBasket() method and a displayInfo frame appears to inform user 
		cancelBasket.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Customer customer = (Customer) loggedInUser;
				try {
					customer.cancelBasket();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		});
		
		//If checkout is clicked new checkoutChoice frame opens so user selects a payment method
		checkout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CheckoutOptions checkoutChoice = new CheckoutOptions(loggedInUser);
			}
		});
		
		//If logout is closed the current frame is disposed of and the login menu reappears
		logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					LoginMenu newLogin = new LoginMenu();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
	}
}
