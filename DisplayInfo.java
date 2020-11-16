
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisplayInfo extends JFrame {

	private JPanel contentPane;
 
	//Simple frame to display a text message to user based on the action they perform.

	public DisplayInfo(String info) {
	
		//Basic Frame set-up
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 230, 200, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));
		
		
		JLabel infoLabel = new JLabel(info);
		contentPane.add(infoLabel);
		
		//Button to close frame once the user has read the message and clicked OK
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		contentPane.add(btnNewButton);
	}

}
