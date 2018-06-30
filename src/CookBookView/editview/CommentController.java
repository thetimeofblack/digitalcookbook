package CookBookView.editview;

import CookBookEntity.Comment;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CommentController {
	private Comment comment ; 
	@FXML
	private TextField commentfield; 
	public void setComment(Comment comment ){
		this.comment = comment ; 
	}
	
	
	public void showComment() {
		
		commentfield.setText(comment.getComment());
	}
	
	public Comment getComment() {
		return this.comment; 
	}
	
	public void saveComments() {
		this.comment.setComment(commentfield.getText());
	}
	
}
