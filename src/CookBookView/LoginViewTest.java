package CookBookView;

import javafx.application.*;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader; 
import javafx.scene.*;
import javafx.stage.Stage;

public class LoginViewTest extends Application {
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root,600,400));
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
