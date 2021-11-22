/* DefaultController class
 * 3 functions which communicate between objects and GUI classes
 * * findAccount() - determines if an account with a specific username already exists
 * * printPost() - returns String version of a post
 * * printAccount() - returns String version of an user
 */
public class DefaultController {
	// Determines if an account with a specific username already exists in the user database
	// returns the matching account or returns null
	public static User findAccount(String username) {
		for (User u: Database.userDB) {
			if (u.getUsername().equalsIgnoreCase(username)) 
				return u;
		}
		return null;
	}
	
	// Returns the given post as a String
	public static String printPost(Post r) {
		String res = "";
		res += "Content: " + r.getContent();
	    res += "\nAuthor: " + r.getUsername();
		res += "\nTags: ";
		if (r.getTags() != null) {
			for (String tag: r.getTags())
				res += tag + " ";
		}
	    res += "\nDate Posted: " + TwitterSearch.formatter.format(r.getDatePosted());
	    return res;
	}
	
	// Returns the given user account as a String
	public static String printAccount(User temp) {
		String ret = "";
		ret += "Username: " + temp.getUsername() + "<br>";
		ret += "User Full Name: " + temp.getName() + "<br>";
	    ret += "Email Address: " + temp.getEmailAddress() + "<br>";
	    ret += "Phone Number: " + temp.getPhoneNumber();
	    return ret;
	}
}
