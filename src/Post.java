/* Post class 
 * includes post objects with content string, tags, date posted, and link
 * class includes get and set messages for each field
 */
import java.time.LocalDate;

public class Post {
    private String username;
	private String content;
    private String[] tags;
    private LocalDate datePosted;
    private String link;
   
    // set username
    public void setUsername(String username) {
        this.username = username;
    }
    
    // get username
    public String getUsername() {
        return username;
    }
    
    // set content
    public void setContent(String content) {
        this.content = content;
    }
    
    // get content
    public String getContent() {
        return content;
    }
    
    // set date posted
    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }
    
    // get date posted
    public LocalDate getDatePosted() {
        return datePosted;
    }
    
    // set link
    public void setLink(String link) {
        this.link = link;
    }
    
    // get link
    public String getLink() {
        return link;
    }
    
    // set tags
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    
    // get tags
    public String[] getTags() {
        return tags;
    }
}