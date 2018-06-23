package CookBookView.loginview;

import java.io.IOException;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.User;
import CookBookView.loginview.tinywin.miniController;
import CookBookView.registerview.registerViewController;
import CookBookView.searchview.SearchViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class loginController {
	@FXML
	private Button confirm;
	@FXML
	private TextField nameText;
	@FXML
	private Button clearpw;
	@FXML
	private Button clearun;
	@FXML
	private Button register;
	@FXML
	private PasswordField passwordText;

	private User user;

	private Stage stage;

	private Scene scene ;

	// Event Listener on Button[#confirm].onAction
	@FXML
	public void checkNameAndPassword(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = (GridPane) loader.load();
		SearchViewController svc = loader.getController();
		
		if(nameText.getText()!=null) {
			
		}
		svc.setStage(stage);
		svc.setScene(scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#clearpw].onAction
	@FXML
	public void clearPassword(ActionEvent event) {
		this.passwordText.setText("");
	}

	// Event Listener on Button[#clearun].onAction
	@FXML
	public void clearUserName(ActionEvent event) {
		this.nameText.setText("");
	}

	// Event Listener on Button[#register].onAction
	@FXML
	public void backtoRegisterView(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../registerview/registerView.fxml"));
		GridPane root = (GridPane) loader.load();
		registerViewController rvc = loader.getController();
		rvc.setStageAndScene(stage, scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void setUser(User user) {
		this.user = user;
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
