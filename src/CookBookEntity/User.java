package CookBookEntity;

public class User {
	private double UserID;
	private String UserName;
	private String UserPassword;
				
	public User() {
		
	}

	public User(double userID, String userName, String userPassword) {
		this.UserID = userID;
		this.UserName = userName;
		this.UserPassword = userPassword;
	}
	
	public double getUserID() {
		return UserID;
	}
	
	public void setUserID(double userID) {
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