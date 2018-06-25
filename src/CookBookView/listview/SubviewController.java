package CookBookView.listview;

import java.io.IOException;
import java.sql.SQLException;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Recipe;
import CookBookView.detailview.MaindetailController;
import CookBookView.detailview.detailController;
import DigitalCookbook.CookBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SubviewController {
	@FXML
	private Label star1;
	@FXML
	private Label star2;
	@FXML
	private Label star3;
	@FXML
	private Label star4;
	@FXML
	private Label star5;
	@FXML
	private Label favourite;
	@FXML
	private Label recipeName;

	private Recipe recipe;

	private Stage stage;
	private Scene scene;
	private DatabaselayerObject databaselayerObject;
	private CookBook cookBook ; 

	public void toDetailView() throws IOException {

	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setName() {
		this.recipeName.setText(recipe.getName());
	}

	public void setStar() {
		Image image = new Image(getClass().getResourceAsStream("star.png"));
		ImageView iv = new ImageView(image);
		iv.setFitHeight(20);
		iv.setFitWidth(20);
		if (recipe.getRate() == 1) {
			star1.setGraphic(iv);
		} else if (recipe.getRate() == 2) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
		} else if (recipe.getRate() == 3) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
			star3.setGraphic(iv);
		} else if (recipe.getRate() == 4) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
			star3.setGraphic(iv);
			star4.setGraphic(iv);
		} else if (recipe.getRate() == 5) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
			star3.setGraphic(iv);
			star4.setGraphic(iv);
			star5.setGraphic(iv);
			
		}
	}

	public void setFavourite() throws SQLException {
		if (databaselayerObject.judgefavourite(recipe)) {
			Image image = new Image(getClass().getResourceAsStream("favourite.png"));
			favourite.setGraphic(new ImageView(image));
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
	
	public void showDetail() throws IOException {
		FXMLLoader detailloader = new FXMLLoader(getClass().getResource("../detailview/maindetail.fxml"));
		AnchorPane detailpane = (AnchorPane)detailloader.load();
		MaindetailController detailcontroller = detailloader.getController(); 
		detailcontroller.setRecipe(this.recipe);
		detailcontroller.showbasicRecipe();
		this.scene.setRoot(detailpane);
		this.stage.setScene(this.scene);
		this.stage.show();
		
		detailcontroller.setScene(scene);
		detailcontroller.setStage(stage);
		
	}
	public void setCookBook(CookBook cookBook) {
		this.cookBook = cookBook ; 
	}
}
