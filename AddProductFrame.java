import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class AddProductFrame extends JFrame {

	private JPanel contentPane;

	public AddProductFrame(String prodType, User loggedInUser) {
		
		//Basic frame set-up
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JLabel titleLabel = new JLabel("Enter Product Details:");
		titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		JPanel productDetails = new JPanel();
		contentPane.add(productDetails, BorderLayout.CENTER);
		productDetails.setLayout(new GridLayout(9, 0, 0, 0));
		
		//Label and text-field pairs for each product detail needed to create a new Product object
		JLabel barcodeLabel = new JLabel("Enter barcode:");
		productDetails.add(barcodeLabel);
		JTextField barcodeInput = new JTextField(6);
		productDetails.add(barcodeInput);
		
		JLabel brandLabel = new JLabel("Enter brand:");
		productDetails.add(brandLabel);
		JTextField brandInput = new JTextField(6);
		productDetails.add(brandInput);
		
		JLabel colourLabel = new JLabel("Enter colour:");
		productDetails.add(colourLabel);
		JTextField colourInput = new JTextField(6);
		productDetails.add(colourInput);
		
		JLabel connectionLabel = new JLabel("Enter connection (wired/wireless):");
		productDetails.add(connectionLabel);
		JTextField connectionInput = new JTextField(6);
		productDetails.add(connectionInput);
		
		JLabel quantityLabel = new JLabel("Enter stock quantity:");
		productDetails.add(quantityLabel);
		JTextField quantityInput = new JTextField(6);
		productDetails.add(quantityInput);
		
		JLabel originalLabel = new JLabel("Enter original cost (£):");
		productDetails.add(originalLabel);
		JTextField originalInput = new JTextField(6);
		productDetails.add(originalInput);
		
		JLabel retailLabel = new JLabel("Enter retail price (£):");
		productDetails.add(retailLabel);
		JTextField retailInput = new JTextField(6);
		productDetails.add(retailInput);
		
		JTextField buttonsInput = new JTextField(6);
		JTextField typeInput = new JTextField(6);
		JTextField layoutInput = new JTextField(6);
		
		JButton addProduct = new JButton("Add Product");
		
		contentPane.add(addProduct, BorderLayout.SOUTH);
		
		//Label and text-fields dependent on the type of product being added
		if (prodType.equals("Keyboard")) {
			
			JLabel typeLabel = new JLabel("Enter keyboard type:");
			productDetails.add(typeLabel);
			productDetails.add(typeInput);
			
			JLabel layoutLabel = new JLabel("Enter keyboard layout (UK/US):");
			productDetails.add(layoutLabel);
			productDetails.add(layoutInput);
		}
		
		if (prodType.equals("Mouse")) {
			JLabel typeLabel = new JLabel("Enter mouse type:");
			productDetails.add(typeLabel);
			productDetails.add(typeInput);
			
			JLabel buttonsLabel = new JLabel("Enter # of buttons:");
			productDetails.add(buttonsLabel);
			productDetails.add(buttonsInput);
		}
		
		//Method to add the product to Stock.txt once 'addProduct' is pressed by user
		addProduct.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				Admin admin = (Admin) loggedInUser;
				Long barcode = Long.parseLong(barcodeInput.getText());
				int stockQuantity = Integer.parseInt(quantityInput.getText());
				float originalCost = Float.parseFloat(originalInput.getText());
				float retailPrice = Float.parseFloat(retailInput.getText());
				
				if (prodType.equals("Mouse")) {
					int info = Integer.parseInt(buttonsInput.getText());
					admin.addProduct(barcode, typeInput.getText(), brandInput.getText(), colourInput.getText(), connectionInput.getText(), stockQuantity, originalCost, retailPrice, info);
					dispose();
				}
				else {
					String info = layoutInput.getText();
					admin.addProduct(barcode, typeInput.getText(), brandInput.getText(), colourInput.getText(), connectionInput.getText(), stockQuantity, originalCost, retailPrice, info);
					dispose();
				}
				DisplayInfo notice = new DisplayInfo("Item added to stock");
			}
		});		
	}
}
