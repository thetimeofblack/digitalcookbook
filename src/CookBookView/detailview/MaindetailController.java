package CookBookView.detailview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.awt.TextArea;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;






//import com.sun.org.apache.xpath.internal.operations.And;

import CookBookEntity.Comment;
import CookBookEntity.Ingredient;
import CookBookEntity.PreparationStep;
import CookBookEntity.Recipe;
import CookBookView.editview.editpaneController;
import CookBookView.firstview.fvController;
import CookBookView.listview.ListAllController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class MaindetailController {
	@FXML
	private Label recipeName;
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
	private Label preparationTime;
	@FXML
	private Label category;
	@FXML
	private Label cookTime;
	@FXML
	private Label favourite;
	@FXML
	private Button logout;
	@FXML
	private Label user;
	@FXML
	private TextField servingperson;
	@FXML
	private Label ingredient;
	@FXML
	private Label deletelabel;

	@FXML
	private ImageView delete;

	@FXML
	private ScrollPane scrollpane;

	@FXML
	private ImageView editImage;
	
	@FXML
	private Label description ;

	@FXML
	private Pane mainPane;

	private Recipe recipe;

	private Stage stage;

	private Scene scene;

	private CookBook cookbook;

	private AnchorPane subinpane;

	@FXML
	private VBox mainbox;

	@FXML
	private AnchorPane mainanchor;
	private Pane substpane;

	private Pane subcmpane;

	private boolean subsm = false;
	private boolean subin = false;
	private boolean subst = false;
	
	int previousrate ; 
	
	private void setOneStar() {
		setStar(star1);
		
	}
	
	private void setTwoStar() {
		setStar(star1);
		setStar(star2);	
	}
	
	private void setThreeStar() {
		setStar(star1);
		setStar(star2);
		setStar(star3);
	}
	
	private void setFourStar() {
		setStar(star1);
		setStar(star2);
		setStar(star3);
		setStar(star4);
	}
	
	private void setFiveStar() {
		setStar(star1);
		setStar(star2);
		setStar(star3);
		setStar(star4);
		setStar(star5);
	}
	public void mouseClickStar1(MouseEvent event) throws Exception{
		this.previousrate =1;
		this.cookbook.setRate(this.previousrate, recipe.getRecipeID());
		setOneStar();		
	}

	public void mouseEnterStar1(MouseEvent event) {
		clearAllStar();
		setOneStar();
	}

	public void mouseClickStar2(MouseEvent event) throws Exception{
		this.previousrate =2;
		this.cookbook.setRate(this.previousrate, recipe.getRecipeID());
		clearAllStar();
		setTwoStar();
		
	}

	public void mouseEnterStar2(MouseEvent event) {
		clearAllStar();
		setTwoStar();
	}

	public void mouseClickStar3(MouseEvent event) throws Exception{
		this.previousrate =3 ;
		this.cookbook.setRate(this.previousrate, recipe.getRecipeID());
		clearAllStar();
		setTwoStar();
	}

	public void mouseEnterStar3(MouseEvent event) {
		clearAllStar();
		setThreeStar();
	}

	public void mouseClickStar4(MouseEvent event) throws Exception{
		this.previousrate =4;
		this.cookbook.setRate(this.previousrate, recipe.getRecipeID());
		clearAllStar();
		setFourStar();
	}

	public void mouseEnterStar4(MouseEvent event) {
		clearAllStar();
		setStar(star1);
		setStar(star2);
		setStar(star3);
		setStar(star4);
	}

	public void mouseClickStar5(MouseEvent event) throws Exception {
		this.previousrate =5; 
		this.cookbook.setRate(this.previousrate, recipe.getRecipeID());
		setFiveStar();
		
	}

	public void mouseEnterStar5(MouseEvent event) {
		clearAllStar();
		setFiveStar();
	}

	public void mouseExitStar(MouseEvent event) throws Exception {
		clearAllStar();
		initializeStar();
	}

	// Event Listener on Label[#favourite].onMouseClicked
	@FXML
	public void choosetobeFavourite(MouseEvent event) throws Exception {
		this.cookbook.setFavourite(this.recipe.getRecipeID());
		initializeFavourite();
	}

	// Event Listener on Button[#logout].onAction
	@FXML
	public void logOut(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../firstview/fv.fxml"));
		BorderPane pane = loader.load();
		fvController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		scene.setRoot(pane);
		stage.setScene(scene);
		stage.show();
	}

	// Event Listener on Label[#ingredient].onDragDetected
	@FXML
	public void showIngredients(MouseEvent event) throws Exception {
		// TODO Autogenerated
		LinkedList<Ingredient> ingredientlist = new LinkedList<Ingredient>();
		ingredientlist = recipe.getIngredientlist();
		this.mainanchor = new AnchorPane();
		if (this.subin == false && ingredientlist!=null) {
			this.subinpane = new AnchorPane();
			this.subinpane.setPrefHeight(400);
			this.subinpane.setPrefWidth(600);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ingredientpane.fxml"));
			Pane pane = (Pane) loader.load();
			IngredientController controller = loader.getController();
			controller.addingredients(ingredientlist);
			this.subin = true;
			System.out.println("show ingredient");
			this.subinpane.getChildren().add(pane);
		}
		System.out.println("show ingredient");
		this.scrollpane.setContent(this.subinpane);
	}

	public ScrollPane getpane() {
		return this.scrollpane;
	}

	public void setPane(VBox vbox) {
		this.scrollpane.setContent(vbox);
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void deleteRecipe() throws Exception {
		this.cookbook.deleteUserRecipe(this.recipe.getRecipeID());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = loader.load();
		SearchViewController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		controller.setCookBook(this.cookbook);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void editRecipe() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../editview/editpane.fxml"));
		AnchorPane anchorPane = loader.load();
		editpaneController controller = loader.getController();
		controller.setRecipe(recipe);
		controller.showRecipe();
		controller.setCookBook(cookbook);
		controller.setScene(scene);
		controller.setStage(stage);
		scene.setRoot(anchorPane);
		stage.setScene(scene);
		stage.show();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void showbasicRecipe() throws Exception {
		this.recipeName.setText(recipe.getName());
		this.preparationTime.setText(String.valueOf(recipe.getPrepareTime()));
		this.category.setText(recipe.getCategory());
		this.cookTime.setText(String.valueOf(recipe.getCookTime()));
		this.servingperson.setText(String.valueOf(recipe.getServeNumber()));
		initializeStar();
		initializeFavourite();
	}

	// initialize star
	public void initializeStar() throws Exception {
		Image image = new Image(getClass().getResourceAsStream("../listview/star.png"));
		ImageView iv = new ImageView(image);
		iv.setFitHeight(25);
		iv.setFitWidth(25);
		Comment comment = new Comment();
		comment = this.cookbook.getComment(recipe.getRecipeID());
		this.previousrate = comment.getGrade();
		if (this.previousrate == 1) {
			setOneStar();
		} else if (this.previousrate == 2) {
			setTwoStar();
		} else if (this.previousrate == 3) {
			setThreeStar();
		} else if (this.previousrate == 4) {
			setFourStar();
		} else if (this.previousrate == 5) {
			setFiveStar();
		}
	}

	// initialize favourite
	public void initializeFavourite() throws Exception {
		if (cookbook.isFavourite(recipe)) {
			Image image = new Image(getClass().getResourceAsStream("../listview/fullheart.png"));
			ImageView iv = new ImageView(image);
			iv.setFitHeight(34);
			iv.setFitWidth(34);
			favourite.setGraphic(iv);
		}
	}

	// star operations
	public void setStar(Label label) {
		Image image = new Image(getClass().getResourceAsStream("../listview/star.png"));
		ImageView iv = new ImageView(image);
		iv.setFitHeight(25);
		iv.setFitWidth(25);
		label.setGraphic(iv);
	}

	public void clearAllStar() {
		Image image = new Image(getClass().getResourceAsStream("../searchview/emptystar.png"));
		ImageView iv = new ImageView(image);
		iv.setFitHeight(25);
		iv.setFitWidth(25);
		star1.setGraphic(iv);
		star2.setGraphic(iv);
		star3.setGraphic(iv);
		star4.setGraphic(iv);
		star5.setGraphic(iv);
	}

	public void setCookBook(CookBook cookbook) {
		this.cookbook = cookbook;
	}

	@FXML
	public void showPreparationStep() throws Exception {
		LinkedList<PreparationStep> preparelist = recipe.getPreparationSteps();

		if (this.subst == false ) {
			this.substpane = new AnchorPane();
			this.substpane.setPrefHeight(800);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("steppane.fxml"));
			this.substpane = loader.load();
			steppaneController controller = loader.getController();
			VBox stepvbox = controller.getVbox();
			Iterator<PreparationStep> iterator = preparelist.iterator();
			int number = 0;
			while (iterator.hasNext()) {
				number = number + 1;
				PreparationStep step = iterator.next();
				FXMLLoader hboxloader = new FXMLLoader(getClass().getResource("steppanehbox.fxml"));
				HBox hBox = hboxloader.load();
				Text No = (Text) hBox.getChildren().get(0);
				Text stepdescription = (Text) hBox.getChildren().get(1);
				stepdescription.setText(step.getDescription());
				No.setText(String.valueOf(number));
				stepvbox.getChildren().add(hBox);
			}
			
			this.subst = true;
			System.out.println("show steps");

		}
		System.out.println("show steps");
		this.scrollpane.setContent(this.substpane);

	}

	@FXML
	public void back() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../listview/listall.fxml"));
		Parent root = loader.load();
		ListAllController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		// controller.setDatabaselayerObject(databaselayerObject);
		controller.setCookBook(this.cookbook);
		controller.createAllRecipeSubView();

		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	public void showComment() throws Exception {
		LinkedList<Comment> comments = this.cookbook.getComments(recipe.getRecipeID());
		if (subsm == false ) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Comment.fxml"));
			this.subcmpane = loader.load();
			CommentController controller = loader.getController();
			controller.showComments(comments);
			subsm = true;
			System.out.println("show all comments for this recipe");
		}
		System.out.println("show all comments for this recipe");
		this.scrollpane.setContent(this.subcmpane);
	}
}
