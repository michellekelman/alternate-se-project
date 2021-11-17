import java.awt.event.*;
import javax.swing.*;

public class UserHomepage {
	public static JPanel userHomepage(JFrame frame, JPanel homepage, User user) {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("Welcome " + user.getUsername() + "!");
		userLabel.setBounds(140, 10, 300, 20);
		panel.add(userLabel);
		
		JButton cButton = new JButton("Add Post");
		cButton.setBounds(125, 50, 150, 20);
		panel.add(cButton);
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				JPanel addPost = UserPanels.addPost(frame, panel, user);
				frame.getContentPane().add(addPost);
				addPost.setVisible(true);
				frame.setTitle("Add Post");
			}
		});
		
		JButton sButton = new JButton("Search");
		sButton.setBounds(125, 90, 150, 20);
		panel.add(sButton);
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				JPanel search = UserPanels.search(frame, panel);
				frame.getContentPane().add(search);
				search.setVisible(true);
				frame.setTitle("Search");
			}
		});
		
		JButton pButton = new JButton("User Posts");
		pButton.setBounds(125, 130, 150, 20);
		panel.add(pButton);
		pButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				JScrollPane posts = UserPanels.postDisplay(frame, panel, user);
				frame.getContentPane().add(posts);
				posts.setVisible(true);
				frame.setTitle("User Posts");
			}
		});
		
		JButton vButton = new JButton("Account Information");
		vButton.setBounds(125, 170, 150, 20);
		panel.add(vButton);
		vButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel, "<html>Account Information:<br>" + DefaultController.printAccount(user) + "</html>");
			}
		});
		
		
		JButton lButton = new JButton("Logout");
		lButton.setBounds(125, 210, 150, 20);
		panel.add(lButton);
		lButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
