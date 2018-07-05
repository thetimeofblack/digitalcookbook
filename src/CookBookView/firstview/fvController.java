package CookBookView.firstview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import CookBookView.loginview.LoginView;
import CookBookView.loginview.loginController;
import CookBookView.registerview.registerViewController;
import javafx.event.ActionEvent;
import CookBookDataBaseAcess.DatabaselayerObject;
public class fvController {
	@FXML
	private Button loginbutton;
	@FXML
	private Button signupbutton;
	

	private Stage stage;

	private Scene scene;

	// Event Listener on Button[#loginbutton].onAction
	@FXML

	public void initialize() {

	}

	public void loginView(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../loginview/login.fxml"));
		GridPane root = (GridPane) loader.load();
		//DatabaselayerObject dao = new DatabaselayerObject();	
		
	
		loginController lc = loader.getController();
		
		lc.setStageAndScene(stage, scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	//Event Listener on Button[#signupbutton].onAction
	@FXML
	public void signupView(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../registerview/registerView.fxml"));
		GridPane root = (GridPane) loader.load();
		registerViewController rvc = loader.getController();
		
		rvc.setStageAndScene(stage, scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void setStageAndScene(Stage stage, Scene scene) {
		this.stage = stage;
		this.scene = scene;
	}
}
