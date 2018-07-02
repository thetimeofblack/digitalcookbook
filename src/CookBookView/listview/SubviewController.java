package CookBookView.listview;

import java.io.IOException;
import java.sql.SQLException;

import javax.crypto.spec.IvParameterSpec;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Comment;
import CookBookEntity.Recipe;
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
	private CookBook cookBook;
	
	private boolean isfavourite;

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void setName() {
		this.recipeName.setText(recipe.getName());
	}

	public void setStar() throws Exception {
		Image image = new Image(getClass().getResourceAsStream("star.png"));
		ImageView iv = new ImageView(image);
		iv.setFitHeight(20);
		iv.setFitWidth(20);
		Comment comment = new Comment();
		comment = cookBook.getComment(recipe.getRecipeID());
		int grade = comment.getGrade();
		if (grade == 1) {
			star1.setGraphic(iv);
		} else if (grade == 2) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
		} else if (grade == 3) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
			star3.setGraphic(iv);
		} else if (grade == 4) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
			star3.setGraphic(iv);
			star4.setGraphic(iv);
		} else if (grade == 5) {
			star1.setGraphic(iv);
			star2.setGraphic(iv);
			star3.setGraphic(iv);
			star4.setGraphic(iv);
			star5.setGraphic(iv);

		}
	}

	public void setFavourite() throws Exception {
		if (this.cookBook.isFavourite(recipe)) {
			Image image = new Image(getClass().getResourceAsStream("fullheart.png"));
			ImageView iv = new ImageView(image);
			iv.setFitHeight(20);
			iv.setFitWidth(20);
			favourite.setImage(image);
			
			
			this.isfavourite = true; 
		
		}else {
			this.isfavourite =false; 
			Image image = new Image(getClass().getResourceAsStream("emptyheart.png"));
			favourite.setImage(image);
		}
	}
	private void setFullHeart() {
		Image image = new Image(getClass().getResourceAsStream("fullheart.png"));
		favourite.setImage(image);
	}
	
	private void setEmptyHeart() {
		Image image = new Image(getClass().getResourceAsStream("emptyheart.png"));
		favourite.setImage(image);
	}
	public void MouseClickedFavourite() throws Exception{
		if(this.isfavourite) {
			this.cookBook.deleteFavourite(this.recipe);
			this.isfavourite = false;
			this.setEmptyHeart();
			
		}else {
			this.cookBook.setFavourite(this.recipe.getRecipeID());
			this.isfavourite = true;
			this.setFavourite();
			this.setFullHeart();
		}
	}
	
	public void MouseEnterFavourite() {
		if(isfavourite) {
			Image image = new Image(getClass().getResourceAsStream("emptyheart.png"));
			favourite.setImage(image);
		}else {
			Image image = new Image(getClass().getResourceAsStream("fullheart.png"));
			favourite.setImage(image);
		}
	}
	
	public void MouseExistFavourite() {
		if(!isfavourite) {
			Image image = new Image(getClass().getResourceAsStream("emptyheart.png"));
			favourite.setImage(image);
		}else {
			Image image = new Image(getClass().getResourceAsStream("fullheart.png"));
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
		detailcontroller.setCookBook(this.cookBook);
		detailcontroller.setRecipe(this.recipe);
		detailcontroller.showbasicRecipe();
		
		this.scene.setRoot(detailpane);
		this.stage.setScene(this.scene);
		this.stage.show();
		detailcontroller.setScene(scene);
		detailcontroller.setStage(stage);
	}

	public void setCookBook(CookBook cookBook) {
		this.cookBook = cookBook;
	}
}
