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

public class ProductFrame extends JFrame {

	private JPanel contentPane;

	public ProductFrame(User loggedInUser) throws FileNotFoundException {
		

		ProductList listOfProducts = new ProductList();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Panels and title label
		JPanel topPanel = new JPanel(new GridLayout(2,0,0,0));
		JPanel filterPanel = new JPanel (new GridLayout(0,2,0,0));
		JPanel brandFilterPanel = new JPanel (new GridLayout(0,3,0,0));
		
		JLabel productsLabel = new JLabel("PRODUCTS:", SwingConstants.CENTER);
		productsLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		
		//Filter panel labels and buttons
		JToggleButton keyboardFilter = new JToggleButton("Add UK Keyboard Layout Filter");
		topPanel.add(productsLabel);
		filterPanel.add(keyboardFilter);
		JToggleButton applyFilters = new JToggleButton("Apply Filter(s)");
		JPanel listPanel = new JPanel(new GridLayout());
		
		//For loop goes through brands in Products list to create a HashSet of brands to use as options in brand filter
		JComboBox<String> brandFilter = new JComboBox<String>();
		brandFilter.setEditable(false);
		ArrayList<Product> productArray = listOfProducts.getProducts();
		
		HashSet<String> brands = new HashSet<String>();
		
		for (Product item : productArray) {
			brands.add(item.getBrand());
		}
		brandFilter.addItem("Filter by brand:");
		for (String brand : brands) {
			brandFilter.addItem(brand);
		}
		brandFilterPanel.add(brandFilter);
		
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
		JButton addNewKeyboard = new JButton("Add New Keyboard");
		JButton addNewMouse = new JButton("Add New Mouse");
		JButton saveBasket = new JButton("Save Basket");
		JButton cancelBasket = new JButton("Cancel Basket");
		JButton checkout = new JButton("Checkout");
		buttonPanel.add(logout);
		
		DefaultListModel<String> products = new DefaultListModel<String>();
		JList<String> productList = new JList<String>(products);
		
		//Produces a list of products with their original price 
		if (loggedInUser instanceof Admin) {
			String[] adminProductList = listOfProducts.adminViewProducts();
			for (String product: adminProductList) {
				products.addElement(product);
			}
			//Adds buttons needed by admin
			buttonPanel.add(addNewKeyboard);
			buttonPanel.add(addNewMouse);
		}
		
		//Produces a list of products without their original prices
		if (loggedInUser instanceof Customer) {
			ArrayList<String> customerProductList = listOfProducts.searchProducts(false);
			for (String product : customerProductList) {
				products.addElement(product);
			}
			//Adds buttons needed by user
			buttonPanel.add(addToBasket);
			buttonPanel.add(saveBasket);
			buttonPanel.add(cancelBasket);
			buttonPanel.add(checkout);
			topPanel.add(filterPanel);
		}
			
		listPanel.add(productList);
		getContentPane().add(listPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		
		//If applyFilters is selected it creates a new frame with a filtered list depending on the brand filter & keyboard filter setting
		applyFilters.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (applyFilters.isSelected()) { 
					String brand = (String) brandFilter.getSelectedItem();
					try {
						if (brand.equals("Filter by brand:") && keyboardFilter.isSelected()) {
							SearchResults productFrame = new SearchResults(true, loggedInUser);
							setVisible(false);
						}
						else if (!brand.equals("Filter by brand:")){
							SearchResults productFrame = new SearchResults(keyboardFilter.isSelected(), brand, loggedInUser);
							setVisible(false);
						}
					} catch (FileNotFoundException e1) {
						e1.getStackTrace();
					}
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
					DisplayInfo displayInfo = new DisplayInfo("Basket content saved.");
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
					DisplayInfo displayInfo = new DisplayInfo("Basket content cancelled.");
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
		
		//If addNewKeyboard is clicked a new addKeyboard frame is opened so Admin can enter product details
		addNewKeyboard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddProductFrame addKeyboard = new AddProductFrame("Keyboard", loggedInUser);
			}
		});
		
		//If addNewMouse is clicked a new addKeyboard frame is opened so Admin can enter product details
		addNewMouse.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddProductFrame addMouse = new AddProductFrame("Mouse", loggedInUser);
			}
		});
		
	}

}
