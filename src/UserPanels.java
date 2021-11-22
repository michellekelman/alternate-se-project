/* UserPanels class
 * * JPanels for 2 user use cases
 * * Add Post
 * * Search
 */
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

public class UserPanels {
	// add posts by user account
	public static JPanel addPost(JFrame frame, JPanel userHomepage, User user) {
		// instantiate JPanel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		// creates JPanel labels
		JLabel cLabel = new JLabel("Content:");
		cLabel.setBounds(10, 30, 150, 20);
		panel.add(cLabel);
		
		JLabel tLabel = new JLabel("Tags:");
		tLabel.setBounds(10, 60, 150, 20);
		panel.add(tLabel);
		
		// creates JPanel text fields
		JTextField content = new JTextField();
		content.setBounds(125, 30, 150, 20);
		panel.add(content);
		
		JTextField tags = new JTextField();
		tags.setBounds(125, 60, 150, 20);
		panel.add(tags);
		
		// creates add post button
		JButton aButton = new JButton("Add Post");
		aButton.setBounds(125, 90, 150, 20);
		panel.add(aButton);
		// action listener for add post button
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c = content.getText();
				String t = tags.getText();
				// checks content length
				if (c.length() < 1 || c.length() > 300)
					JOptionPane.showMessageDialog(panel, "Content invalid, must be between 1 and 300 characters, please enter valid content");
				else {
					// creates new post object
					Post tempPost = new Post();
					tempPost.setContent(c);
					tempPost.setTags(t.split(" "));
					tempPost.setUsername(user.getUsername());
					tempPost.setDatePosted(LocalDate.now());
					user.addPost(tempPost);
					Database.postDB.add(tempPost);
					
					// displays post message with post information
					String printPost = "<html>Post added:<br>";
					printPost += "Content: " + tempPost.getContent() + "<br>";
					printPost += "Author: " + tempPost.getUsername() + "<br>";
					printPost += "Tags: ";
					if (tempPost.getTags() != null) {
						for (String tag: tempPost.getTags())
							printPost += tag + " ";
					}
					printPost += "<br>Date Posted: " + TwitterSearch.formatter.format(tempPost.getDatePosted()) + "</html>";
					
					JOptionPane.showMessageDialog(panel, printPost);
					panel.setVisible(false);
					frame.getContentPane().add(userHomepage);
					userHomepage.setVisible(true);
					frame.setTitle("User Homepage");
				}
			}
		});
		// creates return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(100, 120, 200, 20);
		panel.add(rButton);
		// action listener for return to homepage button
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				frame.getContentPane().add(userHomepage);
				userHomepage.setVisible(true);
				frame.setTitle("User Homepage");
			}
		});
		
		return panel;
	}
	
	// Search for posts based on specific fields
	public static JPanel search(JFrame frame, JPanel userHomepage) {
		// instantiates JPanel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		// creates JPanel labels
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
		
		// creates JPanel text fields
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
		
		// creates search button
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
					ArrayList<Post> results = s.search(Database.postDB);
					// switch to search display panel
					JScrollPane searchDisplay = UserPanels.searchDisplay(frame, userHomepage, panel, results);
					frame.getContentPane().add(searchDisplay);
					searchDisplay.setVisible(true);
				}
			}
		});
		// creates return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(100, 210, 200, 20);
		panel.add(rButton);
		// action listener for return to homepage button
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				frame.getContentPane().add(userHomepage);
				userHomepage.setVisible(true);
				frame.setTitle("User Homepage");
			}
		});
		
		return panel;
	}
	
	// Displays posts corresponding to search
	public static JScrollPane searchDisplay(JFrame frame, JPanel userHomepage, JPanel searchPanel, ArrayList<Post> posts) {
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
				frame.getContentPane().add(userHomepage);
				userHomepage.setVisible(true);
				frame.setTitle("User Homepage");
			}
		});
		
		return scroll;
	}
	
	// Displays user posts
	public static JScrollPane postDisplay(JFrame frame, JPanel userHomepage, User user) {
		// instantiate main JPanel
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(2,1);
		layout.setVgap(10);
		panel.setLayout(layout);
		
		// instantiate post JPanel
		JPanel postPanel = new JPanel();
		GridLayout postLayout = new GridLayout(0,1);
		postLayout.setVgap(10);
		postPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		postPanel.setLayout(postLayout);
		
		// add posts to display
		int i = 0;
		for (i = 0; i < user.getPosts().size(); i++) {
			JTextArea postText = new JTextArea(DefaultController.printPost(user.getPosts().get(i)));
			postText.setEditable(false);
			postPanel.add(postText);
		}
		
		// display no posts message
		if (user.getPosts().size() == 0) {
			JTextArea postText = new JTextArea("No posts from " + user.getUsername());
			postText.setEditable(false);
			postPanel.add(postText);
		}
		
		// instantiate button JPanel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
		// add return to homepage button
		JButton rButton = new JButton("Return to Homepage");
		rButton.setBounds(75, 10, 200, 20);
		buttonPanel.add(rButton);
		
		// combine JPanels
		panel.add(postPanel);
		panel.add(buttonPanel);
		
		// create scroll pane
		JScrollPane scroll = new JScrollPane(panel);
		
		// action listener for return to homepage button
		rButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scroll.setVisible(false);
				frame.getContentPane().add(userHomepage);
				userHomepage.setVisible(true);
				frame.setTitle("User Homepage");
			}
		});
		
		return scroll;
	}
}
