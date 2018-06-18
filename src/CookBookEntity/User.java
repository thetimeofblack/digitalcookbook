package CookBookEntity;

public class User {
	private String UserID;
	private String UserName;
	private String UserPassword;
				
	public User() {
		
	}

	public User( String userName, String userPassword) {
		
		this.UserName = userName;
		this.UserPassword = userPassword;
	}
	
	public String getUserID() {
		return UserID;
	}
	
	public void setUserID(String userID) {
		UserID = userID;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getUserPassword() {
		return UserPassword;
	}
	
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	
}