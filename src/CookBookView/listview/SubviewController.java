package CookBookView.listview;

import java.io.IOException;
import java.sql.SQLException;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Recipe;
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

	public void toDetailView() throws IOException {

	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setName() {
		this.recipeName.setText(recipe.getName());
	}

	public void setStar() {
		if (recipe.getRate() == 1) {
			Image image = new Image(getClass().getResourceAsStream("star.png"));
			star1.setGraphic(new ImageView(image));
		} else if (recipe.getRate() == 2) {
			Image image = new Image(getClass().getResourceAsStream("star.png"));
			star1.setGraphic(new ImageView(image));
			star2.setGraphic(new ImageView(image));
		} else if (recipe.getRate() == 3) {
			Image image = new Image(getClass().getResourceAsStream("star.png"));
			star1.setGraphic(new ImageView(image));
			star2.setGraphic(new ImageView(image));
			star3.setGraphic(new ImageView(image));
		} else if (recipe.getRate() == 4) {
			Image image = new Image(getClass().getResourceAsStream("star.png"));
			star1.setGraphic(new ImageView(image));
			star2.setGraphic(new ImageView(image));
			star3.setGraphic(new ImageView(image));
			star4.setGraphic(new ImageView(image));
		} else if (recipe.getRate() == 5) {
			Image image = new Image(getClass().getResourceAsStream("star.png"));
			star1.setGraphic(new ImageView(image));
			star2.setGraphic(new ImageView(image));
			star3.setGraphic(new ImageView(image));
			star4.setGraphic(new ImageView(image));
			star5.setGraphic(new ImageView(image));
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

}
