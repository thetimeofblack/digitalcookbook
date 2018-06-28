package CookBookView.createview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.LinkedList;

import CookBookEntity.Comment;
import CookBookEntity.Ingredient;
import CookBookEntity.PreparationStep;
import CookBookEntity.Recipe;
import CookBookView.firstview.fvController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class createpaneController {
	@FXML
	private TextField recipename;
	@FXML
	private Label star5;
	@FXML
	private TextField preparationTime;
	@FXML
	private TextField Category;
	@FXML
	private TextField Cooktime;
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
	private Button cancel;
	@FXML
	private Pane mainPane;
	
	@FXML 
	private ScrollPane scrollpane;
	
	
	private Pane subinpane ;
	
	private Pane substpane ;
	
	private Pane subcmpane;
	
	private Pane previouspain;
	
	private boolean subsm = false;
	private boolean subin = false;
	private boolean subst = false;

	
	private CookBook cookbook;
	private Recipe recipe ; 
	private LinkedList<Ingredient> ingredients; 
	private LinkedList<PreparationStep> steps ; 
	private Comment comment ; 
	
	private Stage stage; 
	private Scene scene; 
	private Scene previousscene ;
	// Event Listener on Label[#favourite].onDragDetected
	@FXML
	public void choosetobeFavourite(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#logout].onAction
	@FXML
	public void logOut(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../firstview/fv.fxml"));
		Parent root = loader.load();
		fvController controller = loader.getController();
		controller.setStageAndScene(stage, scene);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}
	// Event Listener on TextField[#servingperson].onAction
	@FXML
	public void servingPerson(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#cancel].onAction
	@FXML
	public void back(ActionEvent event) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane root = (GridPane) loader.load();
		SearchViewController controller = loader.getController(); 
		controller.setCookBook(this.cookbook);
		controller.setScene(this.scene);
		controller.setStage(this.stage);
		this.scene.setRoot(root);
		this.stage.setScene(this.scene);
	}
	// Event Listener on Button.onAction
	@FXML
	public void createRecipe(ActionEvent event) throws Exception{
		// TODO Autogenerated
		
		this.recipe = new Recipe();
		String cooktime = this.Cooktime.getText(); 
		String preparationtime = this.preparationTime.getText(); 
		if(cooktime.equals("")) cooktime = "0";
		if(preparationtime.equals("")) preparationtime = "0";
		this.recipe.setCategory(this.Category.getText());
		this.recipe.setCookingTime(Integer.parseInt(cooktime));
		this.recipe.setDescription("");
		this.recipe.setName(this.recipename.getText());
		this.recipe.setPreparationTime(Integer.parseInt(preparationtime));
		//please notice the problem that the character is input into the number textfield;
		this.recipe.setIngredientlist(this.ingredients);
		this.recipe.setPreparationSteps(this.steps);
		cookbook.saveRecipe(recipe);
		if(this.comment!=null) cookbook.saveComment(comment, this.recipe.getRecipeID());
		
		
		
		
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void createIngredient(ActionEvent event) throws Exception {
		this.scrollpane = new ScrollPane();
		if(this.subin==false)
		{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("inpane.fxml"));
		this.subinpane = (Pane)loader.load();
		inpaneController controller = loader.getController(); 
		this.ingredients = new LinkedList<Ingredient>();
		controller.setIngredientList(this.ingredients);
		this.subin = true; 
		System.out.println("create ingredient");
		}
		System.out.println("create ingredient");
		Pane pane = new Pane() ;
		pane.getChildren().add(this.subinpane);
		this.scrollpane.setContent(pane); 
	}
	// Event Listener on Button.onAction
	@FXML
	public void createSteps(ActionEvent event) throws Exception {
		this.mainPane= new Pane(); 
		if(this.subst==false)
		{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("steppane.fxml"));
		this.substpane = (Pane)loader.load();
		steppaneController controller = loader.getController();
		this.steps = new LinkedList<PreparationStep>();
		controller.setPreparationStepList(this.steps);
		this.subst = true; 
		System.out.println("create steps");
		}
		System.out.println("create steps");
		Pane pane = new Pane();
		pane.getChildren().add(this.substpane);
		this.mainPane.getChildren().add(this.substpane);
		}
	// Event Listener on Button.onAction
	@FXML
	public void createComment(ActionEvent event) throws Exception {
		if(this.subsm==false) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("commentpane.fxml"));
		this.subcmpane =(AnchorPane) loader.load() ; 
		commentController controller = loader.getController(); 
		this.comment = new Comment();
		controller.setComment(comment);
		this.subsm=true; 
		
		}
		Pane pane = new Pane();
		this.previouspain = pane; 
		this.previouspain.getChildren().add(this.subcmpane);
		this.mainPane.getChildren().add(this.previouspain);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage ;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene ; 
	}
	
	public void setCookBook(CookBook cookbook) {
		this.cookbook = cookbook;
	}
	
	public void setPreviousScene(Scene scene) {
		this.scene = scene; 
			
	}
	
	
}
