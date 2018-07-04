package CookBookView.detailview;

import java.awt.TextArea;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Iterator;
import java.util.LinkedList;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import CookBookEntity.Comment;
import DigitalCookbook.CookBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CommentController {
	@FXML
	private VBox vbox;
	
	//private TextArea commentInput;
	
	//private CookBook cookbook;
	
//	public void setCookBook(CookBook cookbook) {
//		this.cookbook = cookbook;
//	}
//	
	
	public VBox getVBox() {
		return this.vbox; 
		
	}
	
	public void showComments(LinkedList<Comment> comments) throws Exception {
		Iterator iterator = comments.iterator();
		int number = 1;
		while(iterator.hasNext()) {
			Comment comment = (Comment) iterator.next(); 
			//commentInput = new TextArea(cookbook.getComment(recipe.recipeid));
			//commentInput.setSize(200, 300);
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("commenthbox.fxml"));
			HBox hbox = loader.load();
			CommentHBoxController controller = loader.getController(); 
			controller.showComment(comment, number);
			this.vbox.getChildren().add(hbox);
			//this.vbox.getChildren().add(commentInput);
			number = number+1;
//			commentInput.textProperty().addListener(new ChangeListener<>(){
//				
//				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//				cookbook.editComment(this.user.getUserID(),this.recipe.getRecipeID(),newValue);

//			}
			
			
		}
	}
	

}
