package CookBookView.createview;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import CookBookEntity.Comment;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

public class CommentController {
	
	@FXML
	private TextField comment;
	@FXML
	private Button confirm;
	
	private Comment recipecomment; 
	
	// Event Listener on Button[#confirm].onAction
	@FXML
	public void saveComments() {
		recipecomment.setComment(comment.getText());
		
	}
	
	public void setComment(Comment comment) {
		this.recipecomment = comment ; 
	}
	
	public TextField getCommentTextField() {
		return this.comment; 
	}
	
	public Comment getComment(Comment comment) {
		return this.recipecomment; 
	}
}
