/* TwitterSearch class
 * main project class containing default project menu
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
@SuppressWarnings("serial")

public class TwitterSearch extends JFrame
{
	public TwitterSearch() {
		// instantiate JFrame to be used for all JPanels
		JFrame frame = new JFrame("Default Homepage");
        frame.setPreferredSize(new Dimension(400,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        // instantiate default homepage panel
		JPanel homepagePanel = new JPanel();
		frame.getContentPane().add(homepagePanel);
		homepagePanel.setLayout(null);
		
		// create welcome message label
		JLabel welcomeLabel = new JLabel("Welcome to Twitter Search!");
		welcomeLabel.setBounds(120, 10, 300, 20);
		homepagePanel.add(welcomeLabel);
		
		// add TwitterSearch logo
		ImageIcon logo = new ImageIcon("tslogo.png");
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setBounds(150, 15, 100, 100);
		homepagePanel.add(logoLabel);
		
		// create button for create account function
		JButton cButton = new JButton("Create Account");
		cButton.setBounds(125, 110, 150, 20);
		homepagePanel.add(cButton);
		// action listener for create account button
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepagePanel.setVisible(false);
				JPanel createAccount = DefaultPanels.createAccount(frame, homepagePanel);
				frame.getContentPane().add(createAccount);
				createAccount.setVisible(true);
				frame.setTitle("Create Account");
			}
		});
		
		// create login button
		JButton lButton = new JButton("Login");
		lButton.setBounds(125, 140, 150, 20);
		homepagePanel.add(lButton);
		// action listener for login button
		lButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepagePanel.setVisible(false);
				JPanel login = DefaultPanels.login(frame, homepagePanel);
				frame.getContentPane().add(login);
				login.setVisible(true);
				frame.setTitle("Login");
			}
		});
		
		// create search button
		JButton sButton = new JButton("Search");
		sButton.setBounds(125, 170, 150, 20);
		homepagePanel.add(sButton);
		// action listener for search button
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepagePanel.setVisible(false);
				JPanel search = DefaultPanels.search(frame, homepagePanel);
				frame.getContentPane().add(search);
				search.setVisible(true);
				frame.setTitle("Search");
			}
		});
		
		// create exit button
		JButton eButton = new JButton("Exit");
		eButton.setBounds(125, 200, 150, 20);
		homepagePanel.add(eButton);
		// action listener for exit button
		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	frame.dispose();
			}
		});
	}
	
	// public formatter for date objects to be used by all classes
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	// main method to initialize default homepage
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Database.hardcodedData();
            	new TwitterSearch();
;            }
        });
    }
}
