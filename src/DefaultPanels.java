import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
@SuppressWarnings("serial")

public class DefaultPanels extends JFrame {
	public static JPanel createAccount(JFrame frame, JPanel homepage) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
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
		
		JButton cButton = new JButton("Create Account");
		cButton.setBounds(125, 180, 150, 20);
		panel.add(cButton);
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = username.getText();
				String p = new String(password.getPassword());
				User temp = DefaultController.findAccount(u);
				if (u.length() < 8 || u.length() > 20)
					JOptionPane.showMessageDialog(panel, "Username invalid, must be between 8 and 20 characters, please select a different Username");
				else if (temp != null)
					JOptionPane.showMessageDialog(panel, "Username already in use, please select a different Username");
				else if (p.length() < 8 || p.length() > 20)
					JOptionPane.showMessageDialog(panel, "Password invalid, must be between 8 and 20 characters, please select a different Password");
				else {
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
	
	public static JPanel login(JFrame frame, JPanel homepage) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel uLabel = new JLabel("Username:");
		uLabel.setBounds(10, 30, 150, 20);
		panel.add(uLabel);
		
		JLabel pLabel = new JLabel("Password:");
		pLabel.setBounds(10, 60, 150, 20);
		panel.add(pLabel);
		
		JTextField username = new JTextField();
		username.setBounds(125, 30, 150, 20);
		panel.add(username);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(125, 60, 150, 20);
		panel.add(password);
		
		JButton lButton = new JButton("Login");
		lButton.setBounds(125, 90, 150, 20);
		panel.add(lButton);
		lButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String u = username.getText();
				String p = new String(password.getPassword());
				User temp = DefaultController.findAccount(u);
				if (temp == null)
					JOptionPane.showMessageDialog(panel, "Username not found, please login again");
				else if (!temp.getPassword().equals(p)) 
					JOptionPane.showMessageDialog(panel, "Password is invalid, please login again");
				else {
					JOptionPane.showMessageDialog(panel, "Login Successful");
					panel.setVisible(false);
					JPanel userHomepage = UserHomepage.userHomepage(frame, homepage, temp);
					frame.getContentPane().add(userHomepage);
					userHomepage.setVisible(true);
					frame.setTitle("User Homepage");
				}
			}
		});
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
	
	public static JPanel search(JFrame frame, JPanel homepage) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
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
		
		JButton sButton = new JButton("Search");
		sButton.setBounds(125, 180, 150, 20);
		panel.add(sButton);
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
			    	if (dateTo.isBefore(dateFrom)) 
			    		JOptionPane.showMessageDialog(panel, "Date To cannot be before Date From, please search again");
			    }
				else {
					Search s = new Search();
				    s.setKeywords(k.split(" "));
				    s.setAuthors(a.split(" "));
				    s.setTags(t.split(" "));
				    s.setDateFrom(dateFrom);
				    s.setDateTo(dateTo);
					panel.setVisible(false);
					ArrayList<Post> results = s.search(Database.postDB);
					JScrollPane searchDisplay = DefaultPanels.searchDisplay(frame, homepage, panel, results);
					frame.getContentPane().add(searchDisplay);
					searchDisplay.setVisible(true);
				}
			}
		});
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
	
	public static JScrollPane searchDisplay(JFrame frame, JPanel homepage, JPanel searchPanel, ArrayList<Post> posts) {
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(2,1);
		layout.setVgap(10);
		panel.setLayout(layout);
		
		JPanel postPanel = new JPanel();
		GridLayout postLayout = new GridLayout(0,1);
		postLayout.setVgap(10);
		postPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		postPanel.setLayout(postLayout);
		
		int i = 0;
		for (i = 0; i < posts.size(); i++) {
			JTextArea postText = new JTextArea(DefaultController.printPost(posts.get(i)));
			postText.setEditable(false);
			postPanel.add(postText);
		}
		
		if (posts.size() == 0) {
			JTextArea postText = new JTextArea("No matches found");
			postText.setEditable(false);
			postPanel.add(postText);
		}
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
		JButton sButton = new JButton("Search Again");
		sButton.setBounds(100, 10, 150, 20);
		buttonPanel.add(sButton);
		
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(75, 40, 200, 20);
		buttonPanel.add(rButton);
		
		panel.add(postPanel);
		panel.add(buttonPanel);
		
		JScrollPane scroll = new JScrollPane(panel);
		
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scroll.setVisible(false);
				frame.getContentPane().add(searchPanel);
				searchPanel.setVisible(true);
			}
		});
		
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
