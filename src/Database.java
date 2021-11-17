import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
	public static ArrayList<Post> postDB = new ArrayList<Post>();
	public static ArrayList<User> userDB = new ArrayList<User>();
	
	public static void hardcodedData() {
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
    	LocalDate pDate = LocalDate.parse("01/01/2020", DefaultHomepage.formatter);
	    p.setDatePosted(pDate);
	    testuser1.addPost(p);
    	postDB.add(p);
    	
    	Post n = new Post();
    	n.setContent("Hi! This is a hardcoded post");
    	n.setUsername("testuser0");
    	String[] nTags = {"post1"};
    	n.setTags(nTags);
    	LocalDate nDate = LocalDate.parse("06/01/2020", DefaultHomepage.formatter);
	    n.setDatePosted(nDate);
	    testuser0.addPost(n);
    	postDB.add(n);
    	
    	Post q = new Post();
    	q.setContent("Hola! This is test 2");
    	String[] qTags = {"test", "post2"};
    	q.setTags(qTags);
    	q.setUsername("testuser1");
    	LocalDate qDate = LocalDate.parse("01/01/2021", DefaultHomepage.formatter);
	    q.setDatePosted(qDate);
	    testuser1.addPost(q);
    	postDB.add(q);
    	// end of hard coded data
	}
}
