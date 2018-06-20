package CookBookView.listview;

import javafx.fxml.FXML;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ListViewController {
	@FXML
	private ImageView vegen;
	@FXML
	private ImageView egg_milk;
	@FXML
	private ImageView meat;
	@FXML
	private Text Recipename;
	@FXML
	private Label favourite;
	@FXML
	private VBox recipeVBox;
	private Stage stage;
	private Scene scene; 

	// Event Listener on ImageView[#vegen].onDragDetected
	@FXML
	public void showVegen(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#egg_milk].onDragDetected
	@FXML
	public void showEgg_Mlik(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#meat].onDragDetected
	@FXML
	public void showMeat(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Text[#Recipename].onDragDetected
	@FXML
	public void showRecipename(MouseEvent event) {
		// TODO Autogenerated
	}
	public void setStage(Stage stage) {
		this.stage = stage; 
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public VBox getVBox() {
		return this.recipeVBox;
	}
	
}
