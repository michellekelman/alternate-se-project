import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DefaultController {
	public static User createAccount(Scanner sc, ArrayList<User> allUsers) {
		User temp = new User();
		
		boolean invalid = true;
		String username = "";
		while (invalid) {
			System.out.print("\tUsername: ");
			username = sc.nextLine();
			if (username.length() < 8 || username.length() > 20)
				System.out.println("\tUsername invalid, must be between 8 and 20 characters, please select a different Username");
			else if (findAccount(username, allUsers) != null)
				System.out.println("\tUsername already in use, please select a different Username");
			else
				invalid = false;
		} 
		
		invalid = true;
		String password = "";
		while (invalid) {
			System.out.print("\tPassword: ");
			password = sc.nextLine();
			if (password.length() < 8 || password.length() > 20)
				System.out.println("\tPassword invalid, must be between 8 and 20 characters, please select a different Password");
			else
				invalid = false;
		} 
		
	    System.out.print("\tUser Full Name: ");
		String name = sc.nextLine();
	    System.out.print("\tEmail Address: ");
	    String emailAddress = sc.nextLine();
	    System.out.print("\tPhone Number (do not use dashes or spaces): ");
	    String phoneNumber = sc.nextLine();
	    
	    temp.setName(name);
	    temp.setUsername(username);
	    temp.setPassword(password);
	    temp.setEmailAddress(emailAddress);
	    temp.setPhoneNumber(phoneNumber);
	    
		return temp;
	}
	
	public static User findAccount(String username, ArrayList<User> allUsers) {
		for (User u: allUsers) {
			if (u.getUsername().equalsIgnoreCase(username)) 
				return u;
		}
		return null;
	}
	
	public static User login(Scanner sc, ArrayList<User> allUsers) {
		System.out.print("\tUsername: ");
		String username = sc.nextLine();
		User temp = findAccount(username, allUsers);
		if (temp == null) {
			System.out.println("\tUsername not found, please login again");
			return null;
		}
		
		System.out.print("\tPassword: ");
		String password = sc.nextLine();
		if (!temp.getPassword().equals(password)) {
			System.out.println("\tPassword is invalid, please login again");
			return null;
		}
		
		System.out.println("\tLogin Successful");
		return temp;
	}
	
	public static void printPost(Post r, DateTimeFormatter formatter) {
		System.out.println("\tContent: " + r.getContent());
	    System.out.println("\tAuthor: " + r.getUsername());
		System.out.println("\tTags: ");
		if (r.getTags() != null) {
			for (String tag: r.getTags()) {
				System.out.println("\t\t" + tag);
			}
		}
	    System.out.println("\tDate Posted: " + formatter.format(r.getDatePosted()));
	    System.out.println();
	}
}
