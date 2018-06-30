package CookBookEntity;

public class Comment {
	private int grade =0; 
	private String Comment ="";
	private String recipeid ; 
	private String userid; 
	private String Commentid;
	public Comment(int grade , String Comment){
		this.grade = grade ; 
		this.Comment = Comment ; 
		
		
	}
	
	public Comment() {
		this.grade = 0 ; 
		
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getRecipeid() {
		return recipeid;
	}
	public void setRecipeid(String recipeid) {
		this.recipeid = recipeid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCommentid() {
		return Commentid;
	}
	public void setCommentid(String commentid) {
		Commentid = commentid;
	}
	
}
