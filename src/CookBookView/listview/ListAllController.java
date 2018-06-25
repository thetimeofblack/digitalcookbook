package CookBookView.listview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.LinkedList;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Recipe;
import CookBookView.firstview.fvController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
import javafx.event.ActionEvent;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

	public void backToSearchView() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = loader.load();
		SearchViewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		controller.setDatabaselayerObject(databaselayerObject);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void createAllRecipeSubView() throws IOException {
		LinkedList<Recipe> list = databaselayerObject.getallrecipelist();
		for (int i = 1; i <= list.size(); i++) {
			Recipe recipe = list.get(i - 1);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("subview.fxml"));
			Pane subView = loader.load();
			SubviewController controller = loader.getController();
			
			controller.setRecipe(recipe);
			controller.setName();
			controller.setStar();
			
			controller.setStageAndScene(stage, scene);
			controller.setDatabaselayerObject(databaselayerObject);
			recipeVBox.getChildren().add(subView);
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

	public void setDatabaselayerObject(DatabaselayerObject databaselayerObject) {
		this.databaselayerObject = databaselayerObject;
	}
	
	public void setCookBook(CookBook cookbook){
		this.cookbook = cookbook ;	
	}

}
