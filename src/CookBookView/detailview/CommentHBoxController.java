package CookBookView.detailview;

import javafx.scene.control.TextArea;

import CookBookEntity.Comment;
import javafx.fxml.FXML;

public class CommentHBoxController {
	
	@FXML
	TextArea No ; 
	@FXML 
	TextArea UserID;
	@FXML 
	TextArea Comment;
	
	public void showComment(Comment comment , int number) {
		this.No.setText(String.valueOf(number));
		this.UserID.setText(comment.getUsername());
		this.Comment.setText(comment.getComment());
	}
	
	
}
