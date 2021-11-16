import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Default
{
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	public static Scanner sc = new Scanner(System.in);
	
	public static void main( String[] args )
    {	
    	// fill database with hardcoded data
		Database.hardcodedData();
    	
    	boolean endProgram = false;
	    while(!endProgram) {
	      System.out.print("Enter default page command or \"M\" for command menu: ");
	      String command = sc.nextLine();
	      switch(command) {
	      case "M":
	        System.out.println("\tC = Create Account \n\tL = Login \n\tS = Search \n\tE = End Program");
	        break;
	      case "C":
	    	User u1 = DefaultController.createAccount();
	    	Database.userDB.add(u1);
	    	System.out.println("Account added: ");
	    	DefaultController.printAccount(u1);
	        break;
	      case "L": 
	    	User u2 = DefaultController.login();
	    	if (u2 != null) {
	    		boolean logout = false;
		    	while(!logout) {
		    	  System.out.print("Enter user page command for user " + u2.getUsername() + " or \"M\" for command menu: ");
		  	      command = sc.nextLine();
		  	      switch(command) {
		  	      case "M":
		  	        System.out.println("\tA = Add Post \n\tS = Search \n\tP = View User Posts \n\tV = View Account Information \n\tL = Logout");
		  	        break;
		  	      case "A": 
		  	    	Post p1 = DefaultController.createPost(u2);
		  	    	Database.postDB.add(p1);
		  	    	System.out.println("Post added: ");
			    	DefaultController.printPost(p1);
		  	        break;
		  	      case "P": 
		  	    	for (Post p2: u2.getPosts())
		  	    		DefaultController.printPost(p2);
		  	        break;
		  	      case "S": 
		  	    	DefaultController.search();
		  	        break;
		  	      case "V": 
		  	    	DefaultController.printAccount(u2);
		  	        break;
		  	      case "L":
		  	    	logout = true;
		  	    	System.out.println("\tUser " + u2.getUsername() + " has been logged out");
		  	        break;
		  	      }
		    	}
	    	}
	        break;
	      case "S": 
	    	 DefaultController.search();
	        break;
	      case "E":
	    	endProgram = true;
	    	System.out.println("Twitter Search Program Ended");
	    	sc.close();
	        break;
	      }
	    }
    }
}
