import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoginMenu extends JFrame {

	private JPanel contentPane;
	private int loggedIn;
	private User loggedInUser;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu frame = new LoginMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginMenu() throws FileNotFoundException {
		
		//Basic frame set-up 
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLayout(new GridLayout(3, 0, 0, 0));
		
		//Gets list of users from UserAccounts.txt
		File userAccountsFile = new File("UserAccounts.txt");
		Scanner userFileScanner = new Scanner(userAccountsFile);
		List<String> userFileList = new ArrayList<String>();
		while(userFileScanner.hasNextLine()) {
			String line = userFileScanner.nextLine();
			userFileList.add(line);
		}
		userFileScanner.close();
		List<User> users = new ArrayList<User>();
		for(int i = 0; i<userFileList.size(); i++) {
			String [] userDetails = (userFileList.get(i)).split(", ");
			int uniqueId = Integer.parseInt(userDetails[0]);
			String userName = userDetails[1];
			String surname = userDetails[2];
			int houseNum = Integer.parseInt(userDetails[3]);
			String postcode = userDetails[4];
			String city = userDetails[5];
			if ((userFileList.get(i)).endsWith("admin")){
				Admin user = new Admin(uniqueId, userName, surname, houseNum, postcode, city);
				users.add(user);
			}
			else if ((userFileList.get(i)).endsWith("customer")) {
				Customer user = new Customer(uniqueId, userName, surname, houseNum, postcode, city);
				users.add(user);
			}
		}
		
		//Produces log in list of userIDs
		DefaultListModel<Integer> userIDs = new DefaultListModel<Integer>();
		for (User person : users) {
			userIDs.addElement(person.getId());
		}
		
		//Label, userID list and button created and added to frame
		JLabel loginLabel = new JLabel("Please select your User ID:", SwingConstants.CENTER);
		loginLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 16));
		loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(loginLabel);
		
		JList<Integer> listOfUsers = new JList<Integer>(userIDs);
		add(listOfUsers);
		listOfUsers.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
	

		JButton loginButton = new JButton("Login");
		add(loginButton);

		//Method to obtain selected User ID to login
		listOfUsers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				loggedIn = listOfUsers.getSelectedValue();
			}
		}); 
		
		//Method to create a ProductFrame with a list of products once the login button is clicked - only works if a user ID has been selected
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (User person : users) {
					if (person.getId() == loggedIn) {
						loggedInUser = person;
					}
				}
				try {
					if (loggedInUser != null) {
						ProductFrame productFrame = new ProductFrame(loggedInUser);
						dispose();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
	}

}
