package CookBookView.listview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import org.omg.CORBA.PUBLIC_MEMBER;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Recipe;
import CookBookView.firstview.fvController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
import javafx.event.ActionEvent;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ListAllController {
	@FXML
	private Label vegen;
	@FXML
	private Label egg_milk;
	@FXML
	private Label meat;
	@FXML
	private Button logout;
	@FXML
	private VBox recipeVBox;
	@FXML
	private Label toSearchView;
	private Stage stage;
	private Scene scene;
	//private DatabaselayerObject databaselayerObject;
	private CookBook cookbook; 
	private LinkedList<Recipe> recipelist; 
	@FXML
	private ScrollPane mainpane; 
	
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

	public void backToSearchView() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = loader.load();
		SearchViewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		//controller.setDatabaselayerObject(databaselayerObject);
		controller.setCookBook(this.cookbook);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void createAllRecipeSubView() throws Exception {
		this.recipelist = this.cookbook.getallrecipelist();
		if(!this.recipelist.isEmpty()) {
		this.createSubview(recipelist);
		}
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

	
	
	public void setCookBook(CookBook cookbook){
		this.cookbook = cookbook ;	
	}
	
	@FXML
	public void createVegrecipelist() throws Exception{
		this.cookbook.getallrecipelist();
		this.recipelist = this.cookbook.getVegrecipelist();
		this.createSubview(recipelist);
	}
	
	@FXML
	public void createeggrecipelist() throws Exception{
		this.cookbook.getallrecipelist();
		this.recipelist = this.cookbook.getEggrecipelist();
		this.createSubview(recipelist);
	}
	
	@FXML
	public void createmeatrecipelist() throws Exception{
		this.cookbook.getallrecipelist();
		this.recipelist = this.cookbook.getMeatrecipelist(); 
		this.createSubview(recipelist);
	}
	
	private void createSubview(LinkedList<Recipe> recipelist) throws Exception{
		//this.recipeVBox = new VBox();
		if(!recipelist.isEmpty()) {
		this.recipeVBox = new VBox();	
		for (int i = 1; i <= recipelist.size(); i++) {
			Recipe recipe = recipelist.get(i - 1);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("subview.fxml"));
			Pane pane = new Pane(); 
			pane = loader.load(); 
			
			SubviewController controller = loader.getController();
			
			this.cookbook.setRateComment(recipe);
			
			controller.setCookBook(this.cookbook);
			controller.setRecipe(recipe);
			controller.setName();
			controller.setStar();
			controller.setFavourite();
			
			controller.setStageAndScene(stage, scene);
			//controller.setDatabaselayerObject(databaselayerObject);
			
			
			this.recipeVBox.getChildren().add(pane);
			this.mainpane.setContent(this.recipeVBox);
		}
		
			
		}
		else {
			this.recipeVBox = new VBox();	
			

			this.mainpane.setContent(this.recipeVBox);
		}
	
			
		
	}
	
	

}
