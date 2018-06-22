package CookBookView.detailview;
import javafx.application.*;

import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class DetailView extends Application{
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("maindetail.fxml"));
		
		
		AnchorPane root = (AnchorPane)loader.load();
		MaindetailController mdcontroller = loader.getController();
		Pane pane = mdcontroller.getpane();
		ScrollPane scrollpane= new ScrollPane();
		//Pane pane1 = (Pane)loader1.load();
		VBox vbox = new VBox();
		int number = 50;
		while(number >0) {
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ingredientpane.fxml"));
			Pane pane1 = (Pane)loader1.load();
			vbox.getChildren().add(pane1);
			number = number-1; 
		}
		//vbox.getChildren().add(pane1);
		//scrollpane.setContent(vbox);
		//scrollpane.setContent(vbox);
		mdcontroller.setPane(vbox);
		Scene scene = new Scene(root,290,470);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
