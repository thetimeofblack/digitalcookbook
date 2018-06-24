package CookBookView.editview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class inpaneController {
	@FXML
	private ImageView add;
	@FXML
	private VBox inpaneVBox;
	@FXML
	private Button confirm;
	
	
	// Event Listener on ImageView[#add].onDragDetected
	@FXML
	public void addLine(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("inpanehbox.fxml"));
		HBox hBox = (HBox)loader.load(); 
		
		this.inpaneVBox.getChildren().add(hBox);
	}

}
