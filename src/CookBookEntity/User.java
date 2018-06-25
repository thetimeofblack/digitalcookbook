package CookBookEntity;

public class User {
	private String UserID;
	private String UserName;
	private String UserPassword;
				
	public User() {
		
	}

	public User( String userName, String userPassword) {
		this.UserID = "";
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
	
	public boolean isExist() {
		if(this.UserID==null||this.UserID.equals("")) return false ;
		return true; 
	}
	
}