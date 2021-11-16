import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SearchController {
	public static void search(Scanner sc, ArrayList<Post> allPosts) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.print("\tKeywords (enter separated by spaces): ");
	    String k = sc.nextLine();
	    System.out.print("\tAuthors (enter separated by spaces): ");
		String a = sc.nextLine();
		System.out.print("\tTags (enter separated by spaces): ");
	    String t = sc.nextLine();
	    System.out.print("\tDate From (MM/DD/YYYY): ");
	    String d1 = sc.nextLine();
	    System.out.print("\tDate To (MM/DD/YYYY): ");
	    String d2 = sc.nextLine();
	    
	    Search s = new Search();
	    s.setKeywords(k.split(" "));
	    s.setAuthors(a.split(" "));
	    s.setTags(t.split(" "));
	    
	    if (d1.length() != 0) {
	    	LocalDate dateFrom = LocalDate.parse(d1, formatter);
		    s.setDateFrom(dateFrom);
	    }
	    
	    if (d2.length() != 0) {
	    	LocalDate dateTo = LocalDate.parse(d2, formatter);
		    s.setDateTo(dateTo);
	    }
	    
	    if (s.getDateFrom() != null && s.getDateTo() != null) {
	    	if (s.getDateTo().isBefore(s.getDateFrom())) {
	    		System.out.println("\tDate To cannot be before Date From, please search again");
	    		return;
	    	}
	    }
	    
	    ArrayList<Post> results = s.search(allPosts);
	    if (results.size() == 0) {
	    	System.out.println("\tNo matches found");
	    	return;
	    }
	    for (Post r: results) {
	    	DefaultController.printPost(r, formatter);
	    }
	}
}
