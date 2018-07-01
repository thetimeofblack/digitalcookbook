package CookBookView.detailview;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.Iterator;
import java.util.LinkedList;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import CookBookEntity.Comment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CommentController {
	@FXML
	private VBox vbox;
	
	
	public VBox getVBox() {
		return this.vbox; 
		
	}
	
	public void showComments(LinkedList<Comment> comments) throws Exception {
		Iterator iterator = comments.iterator();
		int number = 1;
		while(iterator.hasNext()) {
			Comment comment = (Comment) iterator.next(); 
			FXMLLoader loader  = new FXMLLoader(getClass().getResource("commenthbox.fxml"));
			HBox hbox = loader.load();
			CommentHBoxController controller = loader.getController(); 
			controller.setComment(comment, number);
			this.vbox.getChildren().add(hbox);
			number = number+1;
			
		}
	}
	

}
