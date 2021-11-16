import java.util.ArrayList;
import java.time.LocalDate;

// Search class creates a search object using keywords, authors, and dates
// executes search method to return corresponding posts

public class Search {
    private String[] keywords;
    private String[] authors;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String[] tags;

    // set authors
    public void setAuthors(String[] authors) {
        this.authors = authors;
    }
    
    // set date from
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }
    
    // set date to
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
    
    // set keywords
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }
    
    // set tags
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    
    // get authors
    public String[] getAuthors() {
        return authors;
    }
    
    // get date from
    public LocalDate getDateFrom() {
        return dateFrom;
    }
    
    // get date to
    public LocalDate getDateTo() {
        return dateTo;
    }
    
    // get keywords 
    public String[] getKeywords() {
        return keywords;
    }
    
    // get tags
    public String[] getTags() {
        return tags;
    }
    
    public ArrayList<Post> search(ArrayList<Post> posts) {
    	ArrayList<Post> results = new ArrayList<Post>();
    	results = searchByKeywords(posts);
    	results = searchByAuthors(results);
    	results = searchByTags(results);
    	results = searchByDateFrom(results);
    	results = searchByDateTo(results);
        return results;
    }
    
    private ArrayList<Post> searchByKeywords(ArrayList<Post> posts) {
    	if (keywords.length == 1 && keywords[0].equals("")) 
    		return posts;
    	ArrayList<Post> results = new ArrayList<Post>();
    	for (Post p: posts) {
    		for (String k: keywords) {
    			if (p.getContent().indexOf(k) > -1) {
    				results.add(p);
    				break;
    			}
    		}
    	}
        return results;
    }
    
    private ArrayList<Post> searchByAuthors(ArrayList<Post> posts) {
    	if (authors.length == 1 && authors[0].equals("")) 
    		return posts;
    	ArrayList<Post> results = new ArrayList<Post>();
    	for (Post p: posts) {
    		for (String a: authors) {
    			if (p.getUsername().equals(a)) {
    				results.add(p);
    				break;
    			}
    		}
    	}
        return results;
    }
    
    private ArrayList<Post> searchByTags(ArrayList<Post> posts) {
    	if (tags.length == 1 && tags[0].equals("")) 
    		return posts;
    	ArrayList<Post> results = new ArrayList<Post>();
    	for (Post p: posts) {
    		for (String t: p.getTags()) {
    			for (String tag: tags) {
        			if (tag.equals(t)) {
        				results.add(p);
        				break;
        			}
        		}
    		}
    	}
        return results;
    }
    
    private ArrayList<Post> searchByDateFrom(ArrayList<Post> posts) {
    	if (dateFrom == null) 
    		return posts;
    	ArrayList<Post> results = new ArrayList<Post>();
    	for (Post p: posts) {
    		if (dateFrom.isBefore(p.getDatePosted()) || dateFrom.isEqual(p.getDatePosted())) {
    			results.add(p);
    		}
    	}
        return results;
    }
    
    private ArrayList<Post> searchByDateTo(ArrayList<Post> posts) {
    	if (dateTo == null) 
    		return posts;
    	ArrayList<Post> results = new ArrayList<Post>();
    	for (Post p: posts) {
    		if (dateTo.isAfter(p.getDatePosted()) || dateTo.isEqual(p.getDatePosted())) {
    			results.add(p);
    		}
    	}
        return results;
    }
}