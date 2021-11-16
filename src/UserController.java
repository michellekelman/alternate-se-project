import java.time.LocalDate;
import java.util.Scanner;

public class UserController {
	public static Post createPost(Scanner sc, User temp) {
		Post post = new Post();
		
		boolean invalid = true;
		String content = "";
		while (invalid) {
			System.out.print("\tContent: ");
			content = sc.nextLine();
			if (content.length() > 300 || content.length() < 1)
				System.out.println("\tContent invalid, must be between 1 and 300 characters, please enter valid content");
			else
				invalid = false;
		}
		
	    System.out.print("\tTags (separated by spaces): ");
		String tags = sc.nextLine();
	    
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
