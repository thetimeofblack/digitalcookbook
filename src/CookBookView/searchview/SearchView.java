package CookBookView.searchview;

import CookBookView.loginview.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchView extends javafx.application.Application{
	public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader  = new  FXMLLoader(getClass().getResource("searchView.fxml"));
            SearchViewController svc = loader.getController();
            GridPane root = (GridPane) loader.load();
            Scene scene = new Scene(root, 290, 470);
            svc.setScene(scene);
            svc.setStage(primaryStage);

            
            primaryStage.setTitle("Welcome back");
            
            //primaryStage.setResizable(false);
            
            
            
            primaryStage.setScene(scene);
            //scene.getStylesheets().add(LoginView.class.getResource("firstview.css").toExternalForm());  
            primaryStage.show(); 
            //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/earth.png")));
           
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	  public static void main(String[] args) {
	        launch(args);
	    }



	}
