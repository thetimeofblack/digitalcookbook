package CookBookView.editview;

import CookBookEntity.Recipe;
import CookBookEntity.User;
import DigitalCookbook.CookBook;
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
public class EditView extends Application{
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("editpane.fxml"));
		
		Recipe recipe = CookBookApp.createGongBaoJiding();
		AnchorPane root = (AnchorPane)loader.load();
		editpaneController edcontroller = loader.getController();
		edcontroller.setRecipe(recipe);
		CookBook cookBook = new CookBook(); 
		User user = new User("Heyining","Heyining");
		cookBook.setUser(user);
		edcontroller.showRecipe();
		
		Scene scene = new Scene(root,600,800);
		primaryStage.setScene(scene);
		edcontroller.setCookBook(cookBook);
		edcontroller.setScene(scene);
		edcontroller.setStage(primaryStage);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}