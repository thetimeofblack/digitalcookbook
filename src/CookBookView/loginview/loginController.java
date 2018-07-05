package CookBookView.loginview;

import java.io.IOException;
import java.util.ResourceBundle.Control;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.User;
import CookBookView.loginview.tinywin.miniController;
import CookBookView.registerview.registerViewController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
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
import CookBookEntity.*;
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
	
	private DatabaselayerObject dao; 
	
	private CookBook cookbook; 
	

	// Event Listener on Button[#confirm].onAction
	@FXML
	public void checkNameAndPassword(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = (GridPane) loader.load();
		
		
		String resultstring = null ; 
		if(nameText.getText()!="") {
			
		User user = new User(nameText.getText(),passwordText.getText());
		
		
		this.cookbook =  new CookBook(); 
		int result = cookbook.userLogin(user);
		
		
		if(result==-1) {
			resultstring = "username does not exist";
			settiny(result);
					
			System.out.println("kasjkdjakj");
		}
		
		if(result==0) {
			resultstring = "user password is not right";
			settiny(result);
			System.out.println("kasjkdjakj");

		}
		if(result==1) {
		resultstring = "user login successfully";
		//System.out.println("this is"+this.user.getUserID());
		
		SearchViewController svc = loader.getController();
		
		//scene.getStylesheets().clear();
		//svc.setDatabaselayerObject(this.dao);
		svc.setStage(stage);
		svc.setScene(scene);
		//cookbook.setUser(new User(user.getUserName(),user.getUserID()));
		svc.setCookBook(this.cookbook);
		svc.setusername();
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
		
		}
		
		//System.out.println(resultstring);
		}
	}

	public void settiny(int result) throws IOException {
		
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("../loginview/tinywin/mini.fxml"));
		GridPane root = (GridPane) fxml.load();		
		miniController controller = fxml.getController();
		controller.changeText(result);
		Scene scene = new Scene(root, 270, 160);
		Stage stage = new Stage();		
		stage.setScene(scene);
		stage.show();		
		controller.setStage(stage);
			
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
	
	public void setDatabase(DatabaselayerObject dao) {
		this.dao = dao;
	}
}
