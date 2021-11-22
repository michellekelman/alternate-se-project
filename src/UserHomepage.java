/* UserHomepage class
 * creates menu and homepage for logged in users
 */
import java.awt.event.*;
import javax.swing.*;

public class UserHomepage {
	public static JPanel userHomepage(JFrame frame, JPanel homepage, User user) {
		// instantiates JPanel
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// creates user homepage label
		JLabel userLabel = new JLabel("Welcome " + user.getUsername() + "!");
		userLabel.setBounds(140, 10, 300, 20);
		panel.add(userLabel);
		
		// creates add post button
		JButton cButton = new JButton("Add Post");
		cButton.setBounds(125, 50, 150, 20);
		panel.add(cButton);
		// action listener for add post button
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				JPanel addPost = UserPanels.addPost(frame, panel, user);
				frame.getContentPane().add(addPost);
				addPost.setVisible(true);
				frame.setTitle("Add Post");
			}
		});
		
		// creates search button
		JButton sButton = new JButton("Search");
		sButton.setBounds(125, 90, 150, 20);
		panel.add(sButton);
		// action listener for search button
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				JPanel search = UserPanels.search(frame, panel);
				frame.getContentPane().add(search);
				search.setVisible(true);
				frame.setTitle("Search");
			}
		});
		
		// creates user posts button
		JButton pButton = new JButton("User Posts");
		pButton.setBounds(125, 130, 150, 20);
		panel.add(pButton);
		// action listener for posts button
		pButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				JScrollPane posts = UserPanels.postDisplay(frame, panel, user);
				frame.getContentPane().add(posts);
				posts.setVisible(true);
				frame.setTitle("User Posts");
			}
		});
		
		// creates account info button
		JButton vButton = new JButton("Account Information");
		vButton.setBounds(125, 170, 150, 20);
		panel.add(vButton);
		// action listener for account info button
		vButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// displays message with account info
				JOptionPane.showMessageDialog(panel, "<html>Account Information:<br>" + DefaultController.printAccount(user) + "</html>");
			}
		});
		
		// creates logout button
		JButton lButton = new JButton("Logout");
		lButton.setBounds(125, 210, 150, 20);
		panel.add(lButton);
		// action listener for logout button
		lButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// displays success message and returns to default homepage
				JOptionPane.showMessageDialog(panel, "Logout Successful");
				panel.setVisible(false);
				frame.getContentPane().add(homepage);
				homepage.setVisible(true);
				frame.setTitle("Default Homepage");
			}
		});
		
		return panel;
	}
}
