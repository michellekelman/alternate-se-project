import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultController {
	public static User createAccount() {
		User temp = new User();
		
		boolean invalid = true;
		String username = "";
		while (invalid) {
			System.out.print("\tUsername: ");
			username = Default.sc.nextLine();
			if (username.length() < 8 || username.length() > 20)
				System.out.println("\tUsername invalid, must be between 8 and 20 characters, please select a different Username");
			else if (findAccount(username) != null)
				System.out.println("\tUsername already in use, please select a different Username");
			else
				invalid = false;
		} 
		
		invalid = true;
		String password = "";
		while (invalid) {
			System.out.print("\tPassword: ");
			password = Default.sc.nextLine();
			if (password.length() < 8 || password.length() > 20)
				System.out.println("\tPassword invalid, must be between 8 and 20 characters, please select a different Password");
			else
				invalid = false;
		} 
		
	    System.out.print("\tUser Full Name: ");
		String name = Default.sc.nextLine();
	    System.out.print("\tEmail Address: ");
	    String emailAddress = Default.sc.nextLine();
	    System.out.print("\tPhone Number (do not use dashes or spaces): ");
	    String phoneNumber = Default.sc.nextLine();
	    
	    temp.setName(name);
	    temp.setUsername(username);
	    temp.setPassword(password);
	    temp.setEmailAddress(emailAddress);
	    temp.setPhoneNumber(phoneNumber);
	    
		return temp;
	}
	
	public static User findAccount(String username) {
		for (User u: Database.userDB) {
			if (u.getUsername().equalsIgnoreCase(username)) 
				return u;
		}
		return null;
	}
	
	public static User login() {
		System.out.print("\tUsername: ");
		String username = Default.sc.nextLine();
		User temp = findAccount(username);
		if (temp == null) {
			System.out.println("\tUsername not found, please login again");
			return null;
		}
		
		System.out.print("\tPassword: ");
		String password = Default.sc.nextLine();
		if (!temp.getPassword().equals(password)) {
			System.out.println("\tPassword is invalid, please login again");
			return null;
		}
		
		System.out.println("\tLogin Successful");
		return temp;
	}
	
	public static void printPost(Post r) {
		System.out.println("\tContent: " + r.getContent());
	    System.out.println("\tAuthor: " + r.getUsername());
		System.out.println("\tTags: ");
		if (r.getTags() != null) {
			for (String tag: r.getTags()) {
				System.out.println("\t\t" + tag);
			}
		}
	    System.out.println("\tDate Posted: " + Default.formatter.format(r.getDatePosted()));
	    System.out.println();
	}

	public static void search() {
		System.out.print("\tKeywords (enter separated by spaces): ");
	    String k = Default.sc.nextLine();
	    System.out.print("\tAuthors (enter separated by spaces): ");
		String a = Default.sc.nextLine();
		System.out.print("\tTags (enter separated by spaces): ");
	    String t = Default.sc.nextLine();
	    System.out.print("\tDate From (MM/DD/YYYY): ");
	    String d1 = Default.sc.nextLine();
	    System.out.print("\tDate To (MM/DD/YYYY): ");
	    String d2 = Default.sc.nextLine();
	    
	    Search s = new Search();
	    s.setKeywords(k.split(" "));
	    s.setAuthors(a.split(" "));
	    s.setTags(t.split(" "));
	    
	    if (d1.length() != 0) {
	    	LocalDate dateFrom = LocalDate.parse(d1, Default.formatter);
		    s.setDateFrom(dateFrom);
	    }
	    
	    if (d2.length() != 0) {
	    	LocalDate dateTo = LocalDate.parse(d2, Default.formatter);
		    s.setDateTo(dateTo);
	    }
	    
	    if (s.getDateFrom() != null && s.getDateTo() != null) {
	    	if (s.getDateTo().isBefore(s.getDateFrom())) {
	    		System.out.println("\tDate To cannot be before Date From, please search again");
	    		return;
	    	}
	    }
	    
	    ArrayList<Post> results = s.search(Database.postDB);
	    if (results.size() == 0) {
	    	System.out.println("\tNo matches found");
	    	return;
	    }
	    for (Post p: results) {
	    	printPost(p);
	    }
	}
	
	public static Post createPost(User temp) {
		Post post = new Post();
		
		boolean invalid = true;
		String content = "";
		while (invalid) {
			System.out.print("\tContent: ");
			content = Default.sc.nextLine();
			if (content.length() > 300 || content.length() < 1)
				System.out.println("\tContent invalid, must be between 1 and 300 characters, please enter valid content");
			else
				invalid = false;
		}
		
	    System.out.print("\tTags (separated by spaces): ");
		String tags = Default.sc.nextLine();
	    
		post.setUsername(temp.getUsername());
		post.setContent(content);
		post.setTags(tags.split(" "));
		post.setDatePosted(LocalDate.now());
		temp.addPost(post);
	    
		return post;
	}
	
	public static void printAccount(User temp) {
		System.out.println("\tUsername: " + temp.getUsername());
		System.out.println("\tUser Full Name: " + temp.getName());
	    System.out.println("\tEmail Address: " + temp.getEmailAddress());
	    System.out.println("\tPhone Number: " + temp.getPhoneNumber());
	}
}
