package CookBookView.searchview;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Recipe;
import CookBookView.createview.createpaneController;
import CookBookView.editview.editpaneController;
import CookBookView.firstview.fvController;
import CookBookView.listview.ListAllController;
import CookBookView.listview.ListviewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.Component;
import java.awt.ScrollPane;
import java.io.IOException;

import CookBookView.listview.ListviewController;
//import CookBookView.listview.PaneController;
import CookBookView.loginview.loginController;
import CookBookView.registerview.registerViewController;
import DigitalCookbook.CookBook;
import javafx.event.ActionEvent;





public class SearchViewController {
	@FXML
	private TextField searcher;
	@FXML
	private Button logout;
	@FXML
	private Button blacksearch;
	@FXML
	private Button showrecipes;
	@FXML
	private Button userRecipes;
	@FXML
	private Button favouriterecipes;
	@FXML
	private Button rankedrecipes;
	@FXML
	private Button createrecipes;
	private Stage stage;
	private Scene scene;
	private DatabaselayerObject databaselayerObject;
	private CookBook cookbook;

	// Event Listener on Button[#logout].onAction
	@FXML
	public void logOut(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../firstview/fv.fxml"));
		Parent root = loader.load();
		fvController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	 //Event Listener on Button[#blacksearch].onMouseEntered
	@FXML
	public void showblue(MouseEvent event) {
		Image image = new Image(getClass().getResourceAsStream("搜索-5.png"));
		ImageView iv =new ImageView(image);
		iv.setFitHeight(33);
		iv.setFitWidth(33);
		blacksearch.setGraphic(iv);
	}

	// Event Listener on Button[#blacksearch].onMouseClicked
	@FXML
	public void searchRecipes(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../listview/listview.fxml"));
		Parent root = loader.load();
		ListviewController controller = loader.getController();
		controller.setCookBook(this.cookbook);
		controller.setStageAndScene(stage, scene);
		//controller.setDatabaselayerObject(databaselayerObject);
		controller.createSearchedRecipeSubview(searcher);
		
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#showrecipes].onAction
	@FXML
	public void showAllrecipes(ActionEvent event) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../listview/listall.fxml"));
		Parent root = loader.load();
		ListAllController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		//controller.setDatabaselayerObject(databaselayerObject);
		controller.setCookBook(this.cookbook);
		controller.createAllRecipeSubView();
		
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#userRecipes].onAction
	@FXML
	public void showUserRecipes(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../listview/listview.fxml"));
		Parent root = loader.load();
		ListviewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		controller.setDatabaselayerObject(databaselayerObject);
		controller.createUserRecipeSubView();
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#favouriterecipes].onAction
	@FXML
	public void showUserFavouriteRecipes(ActionEvent event) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../listview/listview.fxml"));
		Parent root = loader.load();
		ListviewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		controller.setDatabaselayerObject(databaselayerObject);
		controller.createUserFavouriteRecipeSubView();
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#rankedrecipes].onAction
	@FXML
	public void showRankedRecipes(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../listview/listview.fxml"));
		Parent root = loader.load();
		ListviewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		controller.setDatabaselayerObject(databaselayerObject);
		controller.createRankedRecipesSubView();
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Button[#createrecipes].onAction
	@FXML
	public void createNewRecipes(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../createview/createpane.fxml"));
		AnchorPane anchorPane = (AnchorPane)loader.load(); 
		createpaneController controller = loader.getController(); 
		CookBook cookBook = new CookBook() ; 
		controller.setCookBook(cookBook);
		controller.setScene(this.scene);
		controller.setStage(this.stage);
	
		this.scene.setRoot(anchorPane);
		this.stage.setScene(this.scene);
		this.stage.show();
		
		
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

	public void setDatabaselayerObject(DatabaselayerObject databaselayerObject) {
		this.databaselayerObject = databaselayerObject;
	}
	
	public void setCookBook(CookBook cookBook) {
		this.cookbook = cookBook;
	}
}
