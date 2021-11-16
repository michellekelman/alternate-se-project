import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Default
{
    public static void main( String[] args )
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    	
    	ArrayList<Post> postDB = new ArrayList<Post>();
    	ArrayList<User> userDB = new ArrayList<User>();
    	
    	// start of hard coded data
    	User testuser0 = new User();
    	testuser0.setUsername("testuser0");
    	testuser0.setPassword("testpassword");
    	testuser0.setName("Test User");
    	testuser0.setEmailAddress("testuser0@email.com");
    	testuser0.setPhoneNumber("1234567890");
    	userDB.add(testuser0);
    	
    	User testuser1 = new User();
    	testuser1.setUsername("testuser1");
    	testuser1.setPassword("testpassword1");
    	testuser1.setName("Test1 User1");
    	testuser1.setEmailAddress("testuser1@email.com");
    	testuser1.setPhoneNumber("0123456789");
    	userDB.add(testuser1);
    	
    	Post p = new Post();
    	p.setContent("Hello! This is test post 1");
    	String[] pTags = {"test", "post1"};
    	p.setTags(pTags);
    	p.setUsername("testuser1");
    	LocalDate pDate = LocalDate.parse("01/01/2020", formatter);
	    p.setDatePosted(pDate);
	    testuser1.addPost(p);
    	postDB.add(p);
    	
    	Post n = new Post();
    	n.setContent("Hi! This is a hardcoded post");
    	n.setUsername("testuser0");
    	String[] nTags = {"post1"};
    	n.setTags(nTags);
    	LocalDate nDate = LocalDate.parse("06/01/2020", formatter);
	    n.setDatePosted(nDate);
	    testuser0.addPost(n);
    	postDB.add(n);
    	
    	Post q = new Post();
    	q.setContent("Hola! This is test 2");
    	String[] qTags = {"test", "post2"};
    	q.setTags(qTags);
    	q.setUsername("testuser1");
    	LocalDate qDate = LocalDate.parse("01/01/2021", formatter);
	    q.setDatePosted(qDate);
	    testuser1.addPost(q);
    	postDB.add(q);
    	// end of hard coded data
    	
    	boolean endProgram = false;
    	Scanner sc = new Scanner(System.in);
	    while(!endProgram) {
	      System.out.print("Enter default page command or \"M\" for command menu: ");
	      String command = sc.nextLine();
	      switch(command) {
	      case "M":
	        System.out.println("\tC = Create Account \n\tL = Login \n\tS = Search \n\tE = End Program");
	        break;
	      case "C":
	    	User temp = DefaultController.createAccount(sc, userDB);
	    	userDB.add(temp);
	    	System.out.println("Account added: ");
	    	UserController.printAccount(temp);
	        break;
	      case "L": 
	    	User user = DefaultController.login(sc, userDB);
	    	if (user != null) {
	    		boolean logout = false;
		    	while(!logout) {
		    	  System.out.print("Enter user page command for user " + user.getUsername() + " or \"M\" for command menu: ");
		  	      command = sc.nextLine();
		  	      switch(command) {
		  	      case "M":
		  	        System.out.println("\tA = Add Post \n\tS = Search \n\tP = View User Posts \n\tV = View Account Information \n\tL = Logout");
		  	        break;
		  	      case "A": 
		  	    	Post post = UserController.createPost(sc, user);
		  	    	postDB.add(post);
		  	    	System.out.println("Post added: ");
			    	DefaultController.printPost(post, formatter);
		  	        break;
		  	      case "P": 
		  	    	for (Post po: user.getPosts())
		  	    		DefaultController.printPost(po, formatter);
		  	        break;
		  	      case "S": 
		  	    	SearchController.search(sc, postDB);
		  	        break;
		  	      case "V": 
		  	    	UserController.printAccount(user);
		  	        break;
		  	      case "L":
		  	    	logout = true;
		  	    	System.out.println("\tUser " + user.getUsername() + " has been logged out");
		  	        break;
		  	      }
		    	}
	    	}
	        break;
	      case "S": 
	    	SearchController.search(sc, postDB);
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
