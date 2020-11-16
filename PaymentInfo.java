import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class PaymentInfo extends JFrame {
	
	//Frame to take payment information depending on the payment option selected by user in CheckoutOptions Frame

	private JPanel contentPane;
	private JTextField textField;

	public PaymentInfo(String choice, User loggedInUser) {
		
		//Basic frame set-up
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel paymentOptions = new JLabel("Enter payment details:", SwingConstants.CENTER);
		paymentOptions.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		
		//Creates panel to add buttons and labels depending on payment type
		JPanel paymentInfo = new JPanel();
		paymentInfo.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(paymentOptions, BorderLayout.NORTH);
		JButton payByCard = new JButton("Pay by card");
		JButton payByPayPal = new JButton("Pay by PayPal");
		JTextField emailInput = new JTextField(20);
		JTextField codeInput = new JTextField(3);
		JTextField cardInput = new JTextField(16);
		
		
		if (choice.equals("Credit Card")) {
			
			//Panels, buttons and labels added to paymentInfo panel if Credit Card option is selected
			JPanel longCardNumber = new JPanel();
			longCardNumber.setLayout(new GridLayout(0, 2, 0, 0));
			JPanel securityCode = new JPanel();
			securityCode.setLayout(new GridLayout(0, 2, 0, 0));
			JLabel cardNumber = new JLabel("Enter 16 digit card number:");
			JLabel code = new JLabel("Enter 3 digit security code:");
			
			longCardNumber.add(cardNumber);
			longCardNumber.add(cardInput);
			securityCode.add(code);
			securityCode.add(codeInput);
			paymentInfo.add(longCardNumber);
			paymentInfo.add(securityCode);
			add(payByCard, BorderLayout.SOUTH);
		}
		
		if (choice.equals("PayPal")) {
			
			//Panels, buttons and labels added to paymentInfo pane if PayPa option is selected
			JPanel emailPanel = new JPanel();
			emailPanel.setLayout(new GridLayout(0, 2, 0, 0));
			JLabel emailLabel = new JLabel("Enter e-mail address:");
			
			emailPanel.add(emailLabel);
			emailPanel.add(emailInput);
			paymentInfo.add(emailPanel);
			add(payByPayPal, BorderLayout.SOUTH);
		}
		add(paymentInfo, BorderLayout.CENTER);
		
		payByCard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String longNumber = cardInput.getText();
				String securityCode = codeInput.getText();
				
				//if-statement checks whether numbers entered are the right length 
				if ((longNumber.length()==16) && (securityCode.length()==3) ) {
					Customer customer = (Customer) loggedInUser;
					
					//Calls checkout method from Customer object, logging the basket details of customer in Log.txt, then displays returned string in a new frame
					try {
						String comment = customer.checkout(longNumber, securityCode);
						DisplayInfo displayInfo = new DisplayInfo(comment);
						dispose();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				else {
					DisplayInfo displayInfo = new DisplayInfo("Invalid card details");
				}
				
			}
		});
		 
		payByPayPal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String email = emailInput.getText();
				
				//if-statement checks whether entered text has suitable requirements to pass as an e-mail
				if(email.contains("@") && (email.contains(".com") || email.contains(".co.uk"))) {
					Customer customer = (Customer) loggedInUser;
					try {
						
						//Calls checkout method from Customer object, logging the basket details of customer in Log.txt, then displays returned string in a new frame
						String comment = customer.checkout(email);
						DisplayInfo displayInfo = new DisplayInfo(comment);
						dispose();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					DisplayInfo displayInfo = new DisplayInfo("Invalid email address");
				}
			}
		});
		
		
		
	}

}
