package CookBookView.searchview;

import CookBookView.loginview.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchView extends javafx.application.Application {
	public void start(Stage primaryStage) throws Exception {
        try {
        	
            FXMLLoader loader  = new  FXMLLoader(getClass().getResource("searchView.fxml"));
            GridPane root = (GridPane) loader.load();

            SearchViewController svc = loader.getController();

            
            Scene scene = new Scene(root, 290, 470);

            int recipenumber = 5; 
            while(recipenumber!= 0 ) {
            	
            }
            svc.setScene(scene);

            primaryStage.setTitle("Welcome back");
            
            //primaryStage.setResizable(false);
            
            
            
            primaryStage.setScene(scene);
            //scene.getStylesheets().add(LoginView.class.getResource("firstview.css").toExternalForm());  
            primaryStage.show(); 
            //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/earth.png")));

            
            svc.setStage(primaryStage);

            //svc.setScene(scene);
            //svc.setStage(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	  public static void main(String[] args) {
	        launch(args);
	    }



	}

