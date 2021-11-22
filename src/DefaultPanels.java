/* DefaultPanels class
 * JPanels for 3 default use cases
 * * Create Account
 * * Login
 * * Search
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
@SuppressWarnings("serial")

public class DefaultPanels extends JFrame {
	// Creates an account
	public static JPanel createAccount(JFrame frame, JPanel homepage) {
		// instantiate JPanel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		// create JPanel labels
		JLabel uLabel = new JLabel("Username:");
		uLabel.setBounds(10, 30, 150, 20);
		panel.add(uLabel);
		
		JLabel pLabel = new JLabel("Password:");
		pLabel.setBounds(10, 60, 150, 20);
		panel.add(pLabel);
		
		JLabel nLabel = new JLabel("User Full Name:");
		nLabel.setBounds(10, 90, 150, 20);
		panel.add(nLabel);
		
		JLabel eLabel = new JLabel("Email Address:");
		eLabel.setBounds(10, 120, 150, 20);
		panel.add(eLabel);
		
		JLabel pnLabel = new JLabel("Phone Number:");
		pnLabel.setBounds(10, 150, 150, 20);
		panel.add(pnLabel);
		
		// create JPanel text fields
		JTextField username = new JTextField();
		username.setBounds(125, 30, 150, 20);
		panel.add(username);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(125, 60, 150, 20);
		panel.add(password);
		
		JTextField name = new JTextField();
		name.setBounds(125, 90, 150, 20);
		panel.add(name);
		
		JTextField email = new JTextField();
		email.setBounds(125, 120, 150, 20);
		panel.add(email);
		
		JTextField phone = new JTextField();
		phone.setBounds(125, 150, 150, 20);
		panel.add(phone);
		
		// create JButton for create account
		JButton cButton = new JButton("Create Account");
		cButton.setBounds(125, 180, 150, 20);
		panel.add(cButton);
		// action listener for create account button
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = username.getText();
				String p = new String(password.getPassword());
				User temp = DefaultController.findAccount(u);
				// check username length, if username already exists, and password length
				if (u.length() < 8 || u.length() > 20)
					JOptionPane.showMessageDialog(panel, "Username invalid, must be between 8 and 20 characters, please select a different Username");
				else if (temp != null)
					JOptionPane.showMessageDialog(panel, "Username already in use, please select a different Username");
				else if (p.length() < 8 || p.length() > 20)
					JOptionPane.showMessageDialog(panel, "Password invalid, must be between 8 and 20 characters, please select a different Password");
				else {
					// create new user and display account information
					User tempUser = new User();
					tempUser.setUsername(u);
				    tempUser.setPassword(p);
				    tempUser.setName(name.getText());
				    tempUser.setEmailAddress(email.getText());
				    tempUser.setPhoneNumber(phone.getText());
				    Database.userDB.add(tempUser);
					JOptionPane.showMessageDialog(panel, "<html>Account added:<br>" + DefaultController.printAccount(tempUser) + "</html>");
					panel.setVisible(false);
					frame.getContentPane().add(homepage);
					homepage.setVisible(true);
					frame.setTitle("Default Homepage");
				}
			}
		});
		// create return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(100, 210, 200, 20);
		panel.add(rButton);
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				frame.getContentPane().add(homepage);
				homepage.setVisible(true);
				frame.setTitle("Default Homepage");
			}
		});
		
		return panel;
	}
	
	// Login to an account
	public static JPanel login(JFrame frame, JPanel homepage) {
		// instantiate JPanel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		// create JPanel labels
		JLabel uLabel = new JLabel("Username:");
		uLabel.setBounds(10, 30, 150, 20);
		panel.add(uLabel);
		
		JLabel pLabel = new JLabel("Password:");
		pLabel.setBounds(10, 60, 150, 20);
		panel.add(pLabel);
		
		// create JPanel text fields
		JTextField username = new JTextField();
		username.setBounds(125, 30, 150, 20);
		panel.add(username);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(125, 60, 150, 20);
		panel.add(password);
		
		// create login button
		JButton lButton = new JButton("Login");
		lButton.setBounds(125, 90, 150, 20);
		panel.add(lButton);
		// action listener for login button
		lButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = username.getText();
				String p = new String(password.getPassword());
				User temp = DefaultController.findAccount(u);
				// check if account exists and validate password
				if (temp == null)
					JOptionPane.showMessageDialog(panel, "Username not found, please login again");
				else if (!temp.getPassword().equals(p)) 
					JOptionPane.showMessageDialog(panel, "Password is invalid, please login again");
				else {
					// direct user to user homepage menu
					JOptionPane.showMessageDialog(panel, "Login Successful");
					panel.setVisible(false);
					JPanel userHomepage = UserHomepage.userHomepage(frame, homepage, temp);
					frame.getContentPane().add(userHomepage);
					userHomepage.setVisible(true);
					frame.setTitle("User Homepage");
				}
			}
		});
		// create return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(100, 120, 200, 20);
		panel.add(rButton);
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				frame.getContentPane().add(homepage);
				homepage.setVisible(true);
				frame.setTitle("Default Homepage");
			}
		});
		
		return panel;
	}
	
	// Search for posts based on specific fields
	public static JPanel search(JFrame frame, JPanel homepage) {
		// instantiate JPanel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		// create JPanel labels
		JLabel kLabel = new JLabel("Keywords:");
		kLabel.setBounds(10, 10, 150, 20);
		panel.add(kLabel);
		
		JLabel aLabel = new JLabel("Authors:");
		aLabel.setBounds(10, 40, 150, 20);
		panel.add(aLabel);
		
		JLabel tLabel = new JLabel("Tags:");
		tLabel.setBounds(10, 70, 150, 20);
		panel.add(tLabel);
		
		JLabel dfLabel = new JLabel("<html>Date From <br>(MM/DD/YYYY):</html>");
		dfLabel.setBounds(10, 90, 150, 40);
		panel.add(dfLabel);
		
		JLabel dtLabel = new JLabel("<html>Date To <br>(MM/DD/YYYY):</html>");
		dtLabel.setBounds(10, 130, 150, 40);
		panel.add(dtLabel);
		
		// create JPanel text fields
		JTextField keywords = new JTextField();
		keywords.setBounds(125, 10, 150, 20);
		panel.add(keywords);
		
		JTextField authors = new JTextField();
		authors.setBounds(125, 40, 150, 20);
		panel.add(authors);
		
		JTextField tags = new JTextField();
		tags.setBounds(125, 70, 150, 20);
		panel.add(tags);
		
		JTextField dateFrom = new JTextField();
		dateFrom.setBounds(125, 110, 150, 20);
		panel.add(dateFrom);
		
		JTextField dateTo = new JTextField();
		dateTo.setBounds(125, 150, 150, 20);
		panel.add(dateTo);
		
		// create search button
		JButton sButton = new JButton("Search");
		sButton.setBounds(125, 180, 150, 20);
		panel.add(sButton);
		// action listener for search button
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String k = keywords.getText();
				String a = authors.getText();
				String t = tags.getText();
				String df = dateFrom.getText();
				String dt = dateTo.getText();
				LocalDate dateFrom = null;
				LocalDate dateTo = null;
				if (df.length() != 0)
			    	dateFrom = LocalDate.parse(df, TwitterSearch.formatter);
			    if (dt.length() != 0)
			    	dateTo = LocalDate.parse(dt, TwitterSearch.formatter);
			    if (dateFrom != null && dateTo != null) {
			    	// check for proper date interval
			    	if (dateTo.isBefore(dateFrom)) 
			    		JOptionPane.showMessageDialog(panel, "Date To cannot be before Date From, please search again");
			    }
				else {
					// create search object
					Search s = new Search();
				    s.setKeywords(k.split(" "));
				    s.setAuthors(a.split(" "));
				    s.setTags(t.split(" "));
				    s.setDateFrom(dateFrom);
				    s.setDateTo(dateTo);
					panel.setVisible(false);
					// switch to search display panel
					ArrayList<Post> results = s.search(Database.postDB);
					JScrollPane searchDisplay = DefaultPanels.searchDisplay(frame, homepage, panel, results);
					frame.getContentPane().add(searchDisplay);
					searchDisplay.setVisible(true);
				}
			}
		});
		// create return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(100, 210, 200, 20);
		panel.add(rButton);
		// action listener for return to homepage button
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				frame.getContentPane().add(homepage);
				homepage.setVisible(true);
				frame.setTitle("Default Homepage");
			}
		});
		
		return panel;
	}
	
	// Displays posts corresponding to search
	public static JScrollPane searchDisplay(JFrame frame, JPanel homepage, JPanel searchPanel, ArrayList<Post> posts) {
		// instantiates main JPanel
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(2,1);
		layout.setVgap(10);
		panel.setLayout(layout);
		
		// instantiates post JPanel
		JPanel postPanel = new JPanel();
		GridLayout postLayout = new GridLayout(0,1);
		postLayout.setVgap(10);
		postPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		postPanel.setLayout(postLayout);
		
		// add posts to post panel
		int i = 0;
		for (i = 0; i < posts.size(); i++) {
			JTextArea postText = new JTextArea(DefaultController.printPost(posts.get(i)));
			postText.setEditable(false);
			postPanel.add(postText);
		}
		
		// display no matches message
		if (posts.size() == 0) {
			JTextArea postText = new JTextArea("No matches found");
			postText.setEditable(false);
			postPanel.add(postText);
		}
		
		// create button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
		// create search again button
		JButton sButton = new JButton("Search Again");
		sButton.setBounds(100, 10, 150, 20);
		buttonPanel.add(sButton);
		
		// create return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(75, 40, 200, 20);
		buttonPanel.add(rButton);
		
		// combine panels
		panel.add(postPanel);
		panel.add(buttonPanel);
		
		// create scroll pane
		JScrollPane scroll = new JScrollPane(panel);
		
		// action listener for search again button
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// return to search page
				scroll.setVisible(false);
				frame.getContentPane().add(searchPanel);
				searchPanel.setVisible(true);
			}
		});
		
		// action listener for return to homepage button
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scroll.setVisible(false);
				frame.getContentPane().add(homepage);
				homepage.setVisible(true);
				frame.setTitle("Default Homepage");
			}
		});
		
		return scroll;
	}
}
