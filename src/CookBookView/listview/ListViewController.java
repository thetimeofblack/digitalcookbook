package CookBookView.listview;

import java.awt.ScrollPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListViewController {
	@FXML
	private TextField searcher;
	@FXML
	private Button logout;
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
	private VBox vbox;
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	private ScrollPane pane;
	

	// Event Listener on Button[#logout].onAction
	@FXML
	public void logOut(ActionEvent event) {
		// TODO Autogenerated
	}

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
		return this.vbox;
	}

	public ScrollPane getPane() {
		return pane;
	}

	public void setPane(ScrollPane pane) {
		this.pane = pane;
	}
	
}