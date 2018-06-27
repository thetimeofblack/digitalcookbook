package CookBookView.detailview;
import CookBookEntity.Recipe;
import DigitalCookbook.CookBookApp;
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
		
		ScrollPane pane = mdcontroller.getpane();
		Recipe recipe = CookBookApp.createGongBaoJiding();
		//Pane pane1 = (Pane)loader1.load();
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ingredientpane.fxml"));
		Pane pane1 = (Pane)loader1.load();
		VBox vbox = (VBox) pane1.getChildren().get(1);
		mdcontroller.setRecipe(recipe);
		mdcontroller.showbasicRecipe();
		int number = 50;
		while(number >0) {
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("inpanehbox.fxml"));
			HBox hBox = loader2.load();
			vbox.getChildren().add(hBox);
			number = number-1; 
		}
		//vbox.getChildren().add(pane1);
		//scrollpane.setContent(vbox);
		//scrollpane.setContent(vbox);
		
		Scene scene = new Scene(root,600,800);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
