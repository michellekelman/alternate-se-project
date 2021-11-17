public class DefaultController {
	public static User findAccount(String username) {
		for (User u: Database.userDB) {
			if (u.getUsername().equalsIgnoreCase(username)) 
				return u;
		}
		return null;
	}
	
	public static String printPost(Post r) {
		String res = "";
		res += "Content: " + r.getContent();
	    res += "\nAuthor: " + r.getUsername();
		res += "\nTags: ";
		if (r.getTags() != null) {
			for (String tag: r.getTags())
				res += tag + " ";
		}
	    res += "\nDate Posted: " + DefaultHomepage.formatter.format(r.getDatePosted());
	    return res;
	}
	
	public static String printAccount(User temp) {
		String ret = "";
		ret += "Username: " + temp.getUsername() + "<br>";
		ret += "User Full Name: " + temp.getName() + "<br>";
	    ret += "Email Address: " + temp.getEmailAddress() + "<br>";
	    ret += "Phone Number: " + temp.getPhoneNumber();
	    return ret;
	}
}
