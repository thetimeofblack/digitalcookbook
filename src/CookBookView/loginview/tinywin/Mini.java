package CookBookView.loginview.tinywin;

import CookBookView.loginview.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Mini extends javafx.application.Application{
	public void start(Stage primaryStage) throws Exception {
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("mini.fxml"));
            Scene scene = new Scene(root, 320, 200);
     
            

            primaryStage.setScene(scene);
            primaryStage.setTitle("");
            primaryStage.setResizable(false);
            
           // scene.getStylesheets().add(LoginView.class.getResource("loginview.css").toExternalForm());  
            primaryStage.show(); 
            //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/earth.png")));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }



}
