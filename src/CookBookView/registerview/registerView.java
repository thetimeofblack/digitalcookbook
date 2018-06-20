package CookBookView.registerview;
import CookBookView.firstview.FirstView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class registerView extends javafx.application.Application{
	

	
		public void start(Stage primaryStage) throws Exception {
	        try {
	           // GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("registerView.fxml"));
	            FXMLLoader fxml = new FXMLLoader(getClass().getResource("registerView.fxml"));
	            GridPane root = fxml.load();
	            registerViewController registerController = fxml.getController();
	            
	            
	            Scene scene = new Scene(root, 290, 470);
	            
	            

	            primaryStage.setScene(scene);
	            primaryStage.setTitle("Nice to meet you");
	            primaryStage.setResizable(false);
	            
	            //scene.getStylesheets().add(LoginView.class.getResource("registerview.css").toExternalForm());  
	            //primaryStage.show(); 
	            //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/earth.png")));
	            registerController.setStage(primaryStage);
	            primaryStage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		}
	
	    public static void main(String[] args) {
	        launch(args);
	    }



}
