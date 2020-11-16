import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;


public class CheckoutOptions extends JFrame {

	private JPanel contentPane;

	public CheckoutOptions(User loggedInUser) {
		
		//Basic Frame set-up
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 300, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		//Main label and panel set-up
		JLabel paymentOptions = new JLabel("Select Payment Option");
		paymentOptions.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		contentPane.add(paymentOptions);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//PayPal button and method to open a PaymentInfo Frame when it's pressed
		JButton payPal = new JButton("PayPal");
		payPal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PaymentInfo getInfo = new PaymentInfo("PayPal", loggedInUser);
				dispose();
			}
		});
		panel.add(payPal);
		
		//Credit card button and method to open a PaymentInfo Frame when it's pressed
		JButton creditCard = new JButton("Credit Card");
		creditCard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PaymentInfo getInfo = new PaymentInfo("Credit Card", loggedInUser);
				dispose();
			}
		});
		
		panel.add(creditCard);
	}

}
