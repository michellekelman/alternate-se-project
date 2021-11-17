import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.format.DateTimeFormatter;
@SuppressWarnings("serial")

public class DefaultHomepage extends JFrame
{
	public DefaultHomepage() {
		//JFRAME
		JFrame frame = new JFrame("Default Homepage");
        frame.setPreferredSize(new Dimension(400,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        //HOMEPAGE PANEL
		JPanel homepagePanel = new JPanel();
		frame.getContentPane().add(homepagePanel);
		homepagePanel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to Twitter Search!");
		welcomeLabel.setBounds(120, 10, 300, 20);
		homepagePanel.add(welcomeLabel);
		
		JButton cButton = new JButton("Create Account");
		cButton.setBounds(125, 50, 150, 20);
		homepagePanel.add(cButton);
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepagePanel.setVisible(false);
				JPanel createAccount = DefaultPanels.createAccount(frame, homepagePanel);
				frame.getContentPane().add(createAccount);
				createAccount.setVisible(true);
				frame.setTitle("Create Account");
			}
		});
		
		JButton lButton = new JButton("Login");
		lButton.setBounds(125, 100, 150, 20);
		homepagePanel.add(lButton);
		lButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepagePanel.setVisible(false);
				JPanel login = DefaultPanels.login(frame, homepagePanel);
				frame.getContentPane().add(login);
				login.setVisible(true);
				frame.setTitle("Login");
			}
		});
		
		JButton sButton = new JButton("Search");
		sButton.setBounds(125, 150, 150, 20);
		homepagePanel.add(sButton);
		sButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepagePanel.setVisible(false);
				JPanel search = DefaultPanels.search(frame, homepagePanel);
				frame.getContentPane().add(search);
				search.setVisible(true);
				frame.setTitle("Search");
			}
		});
		
		JButton eButton = new JButton("Exit");
		eButton.setBounds(125, 200, 150, 20);
		homepagePanel.add(eButton);
		eButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	frame.dispose();
			}
		});
	}
	
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Database.hardcodedData();
            	new DefaultHomepage();
;            }
        });
    }
}
