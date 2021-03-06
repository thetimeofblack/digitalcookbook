package CookBookView.listview;

import java.io.IOException;
import java.sql.SQLException;

import javax.crypto.spec.IvParameterSpec;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Comment;
import CookBookEntity.Recipe;
import CookBookEntity.User;
import CookBookView.detailview.MaindetailController;

import DigitalCookbook.CookBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
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
	private ImageView favourite;
	@FXML
	private Label recipeName;

	private Recipe recipe;

	private Stage stage;
	private Scene scene;
	private DatabaselayerObject databaselayerObject;
	private CookBook cookbook;

	private boolean isfavourite;

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setName() {
		this.recipeName.setText(recipe.getName());
	}

	public void setStar() throws Exception {
		Comment comment = null;
		String recipeId = recipe.getRecipeID();
		System.out.println(recipeId+"lalala");
		comment = this.cookbook.getComment(recipeId);

		int grade = comment.getGrade();
		if (grade == 1) {
			setFullStar(star1);
		} else if (grade == 2) {
			setFullStar(star1);
			setFullStar(star2);
		} else if (grade == 3) {
			setFullStar(star1);
			setFullStar(star2);
			setFullStar(star3);
		} else if (grade == 4) {
			setFullStar(star1);
			setFullStar(star2);
			setFullStar(star3);
			setFullStar(star4);
			
		} else if (grade == 5) {
			setFullStar(star1);
			setFullStar(star2);
			setFullStar(star3);
			setFullStar(star4);
			setFullStar(star5);
	
		}
	}
	
	private void setFullStar(Label star) {
		Image image = new Image(getClass().getResourceAsStream("../pic/star.png"));
		ImageView iv = new ImageView(image);
		iv.setFitHeight(20);
		iv.setFitWidth(20);
		star.setGraphic(iv);
	}

	public void setFavourite() throws Exception {
		if (this.cookbook.isFavourite(recipe)) {
			Image image = new Image(getClass().getResourceAsStream("../pic/fullheart.png"));
			ImageView iv = new ImageView(image);
			iv.setFitHeight(20);
			iv.setFitWidth(20);
			favourite.setImage(image);

			this.isfavourite = true;

		} else {
			this.isfavourite = false;
			Image image = new Image(getClass().getResourceAsStream("../pic/emptyheart.png"));
			favourite.setImage(image);
		}
	}

	private void setFullHeart() {
		Image image = new Image(getClass().getResourceAsStream("../pic/fullheart.png"));
		favourite.setImage(image);
	}

	private void setEmptyHeart() {
		Image image = new Image(getClass().getResourceAsStream("../pic/emptyheart.png"));
		favourite.setImage(image);
	}

	public void MouseClickedFavourite() throws Exception {
		if (this.isfavourite) {
			this.cookbook.deleteFavourite(this.recipe);
			this.isfavourite = false;
			this.setEmptyHeart();

		} else {
			this.cookbook.setFavourite(this.recipe.getRecipeID());
			this.isfavourite = true;
			this.setFavourite();
			this.setFullHeart();
		}
	}

	public void MouseEnterFavourite() {
		if (isfavourite) {
			Image image = new Image(getClass().getResourceAsStream("../pic/emptyheart.png"));
			favourite.setImage(image);
		} else {
			Image image = new Image(getClass().getResourceAsStream("../pic/fullheart.png"));
			favourite.setImage(image);
		}
	}

	public void MouseExistFavourite() {
		if (!isfavourite) {
			Image image = new Image(getClass().getResourceAsStream("../pic/emptyheart.png"));
			favourite.setImage(image);
		} else {
			Image image = new Image(getClass().getResourceAsStream("../pic/fullheart.png"));
			favourite.setImage(image);
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

	public void showDetail() throws Exception {
		FXMLLoader detailloader = new FXMLLoader(getClass().getResource("../detailview/maindetail.fxml"));
		AnchorPane detailpane = (AnchorPane) detailloader.load();
		MaindetailController detailcontroller = detailloader.getController();
		detailcontroller.setCookBook(this.cookbook);
		detailcontroller.setRecipe(this.recipe);
		detailcontroller.showbasicRecipe();
		detailcontroller.showPreparationStep();
		
		detailcontroller.showIngredients();
		
		this.scene.setRoot(detailpane);
		this.stage.setScene(this.scene);
		this.stage.show();
		detailcontroller.setScene(scene);
		detailcontroller.setStage(stage);
	}

	public void setCookBook(CookBook cookbook) {
		this.cookbook = cookbook;
	}
}
