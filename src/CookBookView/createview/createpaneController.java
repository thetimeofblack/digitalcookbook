package CookBookView.createview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.LinkedList;

import CookBookEntity.Comment;
import CookBookEntity.Ingredient;
import CookBookEntity.PreparationStep;
import CookBookEntity.Recipe;
import CookBookView.firstview.fvController;
import CookBookView.searchview.SearchViewController;
import DigitalCookbook.CookBook;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
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
	
	
	@FXML
	private MenuButton description ; 
	
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
	
	private CommentController cmcontroller; 
	private inpaneController incontroller; 
	private steppaneController stcontroller; 
	
	private Stage stage; 
	private Scene scene; 
	private Scene previousscene ;
	@FXML
	private RadioButton vegetarian;
	@FXML
	private RadioButton vegan; 
	@FXML
	private RadioButton meat;
	
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
		if(this.servingperson==null||this.servingperson.getText().equals("")) this.servingperson.setText("999999");
		this.recipe.setServeNumber(Integer.parseInt(this.servingperson.getText()));
		if(vegetarian.isSelected()) {
			this.recipe.setDescription("Egg");
		}else if(vegan.isSelected()) {
			this.recipe.setDescription("Vegetable");
		}else if(meat.isSelected()) {
			this.recipe.setDescription("Meat");
		}
	
		//please notice the problem that the character is input into the number textfield;
		this.stcontroller.saveSteps();
		this.steps = this.stcontroller.getPreparationStepList(); 
		this.incontroller.saveIngredient();
		this.ingredients = this.incontroller.getIngredientList();
		this.recipe.setIngredientlist(this.ingredients);
		this.recipe.setPreparationSteps(this.steps);
		if(!recipe.getName().equals("")) {
		this.cookbook.saveRecipe(recipe);
		}else {
			System.out.println("Please enter a recipe name");
		}
		if(this.comment!=null) cookbook.saveComment(comment, this.recipe.getRecipeID());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../searchview/searchView.fxml"));
		GridPane gridpane = loader.load(); 
		SearchViewController controller = loader.getController(); 
		controller.setCookBook(this.cookbook);
		controller.setScene(this.scene);
		controller.setStage(this.stage);
		this.scene.setRoot(gridpane);
		this.stage.setScene(this.scene);
		
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void createIngredient() throws Exception {
		
		if(this.subin==false)
		{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("inpane.fxml"));
		this.subinpane = (Pane)loader.load();
		this.incontroller= loader.getController(); 
		this.ingredients = new LinkedList<Ingredient>();
		this.incontroller.setIngredientList(this.ingredients);
		this.subin = true; 
		this.incontroller.saveIngredient();
		System.out.println("create ingredient");
		}
		System.out.println("create ingredient");
		
		this.scrollpane.setContent(this.subinpane); 
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void createSteps() throws Exception {
	
		
		if(this.subst==false)
		{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("steppane2.fxml"));
		this.substpane = (Pane)loader.load();
		this.stcontroller = loader.getController();
		this.steps = new LinkedList<PreparationStep>();
		this.stcontroller.setPreparationStepList(this.steps);
		this.subst = true; 
		System.out.println("create steps");
		this.stcontroller.saveSteps();
		}
		System.out.println("create steps");
		
		this.scrollpane.setContent(this.substpane);
		}
	
	// Event Listener on Button.onAction
	@FXML
	public void createComment() throws Exception {
		if(this.subsm==false) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("commentpane.fxml"));
		this.subcmpane =(AnchorPane) loader.load() ; 
		this.cmcontroller = loader.getController(); 
		this.comment = new Comment();
		this.cmcontroller.setComment(comment);
		this.cmcontroller.saveComments();
		this.subsm=true; 
		
		}
		this.scrollpane.setContent(subcmpane);
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
	
	public void initializeDescription() {
		ToggleGroup radioGroup = new ToggleGroup();
        
		vegetarian.setToggleGroup(radioGroup);
		vegan.setToggleGroup(radioGroup);
		meat.setToggleGroup(radioGroup);
		/*
		radioGroup.selectedToggleProperty().addListener(
				(ObservableValue<? extends Toggle>ov , Toggle old_toggle , Toggle new_toggle)->{
					if(group.setSelectedToggle()!= null) {
						get
					}
				}
				);
		*/
		
        
	}
	
	
	
}
