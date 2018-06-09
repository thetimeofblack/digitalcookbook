package CookBookEntity;

public class User {
	private String password ; 
	private String username; 
	User(String password , String username){
		this.password = password; 
		this.username = username; 
		
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean verifyuser(User other) {
		
		return false; 
	}
	
}
