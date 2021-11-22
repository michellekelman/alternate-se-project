/* User class 
 * includes user information fields and posts
 * use to add or remove posts from the database
 */
import java.util.ArrayList;

public class User {
    private String name;
    private String username;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private Search[] searchHistory;
    ArrayList<Post> posts;
    
    // constructor for posts
    public User() {
        posts = new ArrayList<Post>();
    }
    
    // set email
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    // set name
    public void setName(String name) {
        this.name = name;
    }
    
    //set password
    public void setPassword(String password) {
        this.password = password;
    }
    
    // set phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    // set posts
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }
    
    // set search history
    public void setSearchHistory(Search[] searchHistory) {
        this.searchHistory = searchHistory;
    }
    
    // set username
    public void setUsername(String username) {
        this.username = username;
    }
    
    //get email
    public String getEmailAddress() {
        return emailAddress;
    }
    
    // get name
    public String getName() {
        return name;
    }
    
    // get password
    public String getPassword() {
        return password;
    }
    
    // get phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    // get posts
    public ArrayList<Post> getPosts() {
        return posts;
    }
    
    // get search history
    public Search[] getSearchHistory() {
        return searchHistory;
    }
    
    // get username
    public String getUsername() {
        return username;
    }
    
    // add post to user account
    public void addPost(Post post) {
        posts.add(post);
    }
    
    //remove and edit use cases not implemented for this project
    
    // remove post from user account
    public void deletePost(Post post) {
        posts.remove(post);
    }
    
    // edit post in user account
    public void editPost(Post oldPost , Post newPost) {
        posts.remove(oldPost);
        posts.add(newPost);
    }
}  