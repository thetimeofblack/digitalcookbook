
package CookBookView.listview;

import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Recipe;
import CookBookView.firstview.fvController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListviewController {
	@FXML
	private VBox recipeVBox;
	@FXML
	private Button logout;
	@FXML
	private ImageView toSearchView;
	private Stage stage;
	private Scene scene;
	private DatabaselayerObject databaselayerObject;
	private CookBook cookBook;
	private LinkedList<Recipe> recipelist ; 

	public void logOut(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../firstview/fv.fxml"));
		Parent root = loader.load();
		fvController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void backToSearchView(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = loader.load();
		SearchViewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		
		controller.setCookBook(this.cookBook);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void createSearchedRecipeSubview(TextField searcher) throws Exception {
		this.recipelist = new LinkedList<Recipe>();
		System.out.println("Searching"+searcher.getText());
		this.recipelist = this.cookBook.searchRecipe(searcher.getText());
		 
		for (int i = 1; i <= this.recipelist.size(); i++) {
			Recipe recipe = this.recipelist.get(i - 1);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("subview.fxml"));
			Pane subView = loader.load();
			SubviewController controller = loader.getController();
			
			controller.setRecipe(recipe);
			controller.setName();
			controller.setStar();
			controller.setFavourite();
			controller.setStageAndScene(stage, scene);
			//controller.setDatabaselayerObject(databaselayerObject);
			controller.setCookBook(this.cookBook);
			this.recipeVBox.getChildren().add(subView);
		}
	}

	public void createUserRecipeSubView() throws Exception {
		LinkedList<Recipe> list = this.cookBook.getUserRecipe();
		for (int i = 1; i <= list.size(); i++) {
			Recipe recipe = list.get(i - 1);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("subview.fxml"));
			Pane subView = loader.load();
			SubviewController controller = loader.getController();
			controller.setCookBook(this.cookBook);
			controller.setRecipe(recipe);
			controller.setName();
			controller.setStar();
			controller.setFavourite();
			controller.setStageAndScene(stage, scene);
			
			recipeVBox.getChildren().add(subView);
		}
	}

	public void createUserFavouriteRecipeSubView() throws Exception {
		LinkedList<Recipe> list = this.cookBook.getfavouriterecipe();
		for (int i = 1; i <= list.size(); i++) {
				Recipe recipe = list.get(i - 1);
			
				FXMLLoader loader = new FXMLLoader(getClass().getResource("subview.fxml"));
				Pane subView = loader.load();
				SubviewController controller = loader.getController();
				controller.setCookBook(this.cookBook);
				controller.setRecipe(recipe);
				controller.setStageAndScene(stage, scene);
				controller.setName();
				controller.setStar();
				controller.setFavourite();
				
				
				recipeVBox.getChildren().add(subView);
			
		}
	}

	public void createRankedRecipesSubView() throws Exception {
		LinkedList<Recipe> list = this.cookBook.getRankedRecipeList();
		if(!list.isEmpty()) {
		for (int i = 1; i <= list.size(); i++) {
			Recipe recipe = list.get(i - 1);
			if (recipe.getRate() > 0) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("subview.fxml"));
				Pane subView = loader.load();
				SubviewController controller = loader.getController();
				controller.setRecipe(recipe);
				controller.setName();
				controller.setStar();
				controller.setFavourite();
				controller.setStageAndScene(stage, scene);
				controller.setCookBook(this.cookBook);
				recipeVBox.getChildren().add(subView);
			}
		}
	  }else {
		  this.recipeVBox= new VBox();
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
	
	public void toSearchView() throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = (GridPane) loader.load();
		SearchViewController svc = loader.getController();
		svc.setCookBook(this.cookBook);
		svc.setStage(stage);
		svc.setScene(scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
		}
	
	public void setCookBook(CookBook cookbook) {
		this.cookBook = cookbook ; 
	}
}


