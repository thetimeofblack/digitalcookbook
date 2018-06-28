package CookBookView.registerview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;


import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.User;
import DigitalCookbook.CookBook;
public class registerViewController {
	@FXML
	private TextField username;
	@FXML
	private TextField userpassword1;
	@FXML
	private TextField userpassword2;
	@FXML
	private Button ok;

	private Stage stage;
	
	private Scene scene;
	
	
	
	private DatabaselayerObject dao; 
	private CookBook cookbook; 
	// Event Listener on Button[#ok].onAction
	@FXML
	public void userRegister(ActionEvent event) throws Exception {

		// if()
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("mini2.fxml"));
		GridPane root = (GridPane) fxml.load();
		
		miniController controller = fxml.getController();
		//Text resultText = (Text)root.lookup("mainText");
		Text resultText = controller.getText();
		if(this.username.getText().isEmpty()) {
			resultText.setText("please enter the username");
		}else {
			if(this.userpassword1.getText().isEmpty()  | this.userpassword2.getText().isEmpty() | !this.userpassword1.getText().equals(this.userpassword2.getText())) {
				resultText.setText("please rewrite the password");
			}else {
				User user = new User();
				user.setUserName(username.getText());
				user.setUserPassword(userpassword2.getText());
				this.cookbook = new CookBook(); 
				cookbook.userRegister(user);
				resultText.setText("Register successfully");
				FXMLLoader fxmlforlogin  = new FXMLLoader(getClass().getResource("../login.fxml"));
				GridPane rootforlogin = (GridPane)fxmlforlogin.load();
				this.scene = new Scene(rootforlogin,290,470);
				this.stage.setScene(this.scene);
				this.stage.show();
				
			}
		}
		Scene scene = new Scene(root, 270, 160);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
		controller.setStage(stage);
		/**
		 * if()
		 * 
		 */
		// TODO Autogenerated
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
	
	public void setDatabase(DatabaselayerObject dao) {
		this.dao = dao; 
	}
	
	public void setCookBook(CookBook cookbook) {
		this.cookbook = cookbook;
	}
	
	
}
