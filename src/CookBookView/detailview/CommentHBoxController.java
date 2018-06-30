package CookBookView.detailview;

import java.awt.TextArea;

import CookBookEntity.Comment;
import javafx.fxml.FXML;

public class CommentHBoxController {
	@FXML
	TextArea No ; 
	@FXML 
	TextArea UserID;
	@FXML 
	TextArea Comment;
	
	public void setComment(Comment comment , int number) {
		this.No.setText(String.valueOf(number));
		this.UserID.setText(comment.getUserid());
		this.Comment.setText(comment.getComment());
	}
}