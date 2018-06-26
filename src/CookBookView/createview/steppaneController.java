package CookBookView.createview;

import javafx.fxml.FXML;

import CookBookEntity.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.TextArea;
import java.util.LinkedList;

import javax.print.event.PrintServiceAttributeListener;

import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class steppaneController {
	@FXML
	private VBox vbox;
	@FXML
	private ImageView add;
	
	private LinkedList<PreparationStep> pslist;
	
	private int addnumber ;
	
	// Event Listener on ImageView[#add].onDragDetected
	@FXML
	public void addLine() throws Exception {
		// TODO Autogenerated
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("stpanehbox.fxml"));
		HBox stHBox = (HBox) loader.load(); 
		addnumber = addnumber + 1;
		System.out.println("add step hbox");
		this.vbox.getChildren().add(stHBox);
	}
	// Event Listener on Button[#confirm].onAction
	@FXML
	public void saveSteps() {
		// TODO Autogenerated
		int num = this.addnumber;
		while(num>0) {
		Ingredient ingredient = new Ingredient();
		HBox hBox = (HBox) this.vbox.getChildren().get(num);
		TextArea description = (TextArea) hBox.getChildren().get(1);
		PreparationStep step = new PreparationStep();
		step.setDescription(description.getText());
		
		num=num-1;
		this.pslist.add(step);
		}
		//PreparationStep step = this.pslist.get(0);
		System.out.println(pslist.toString());
		
	}
	
	public VBox getvbox() {
		return this.vbox ; 
	}
	
	public void setPreparationStepList(LinkedList<PreparationStep> preparationSteps)
	{
		this.pslist = preparationSteps;
	}
	
	@FXML
	public void cancelsteps() {
		int num = this.addnumber+1; 
		while(num>1) {
			this.vbox.getChildren().remove(num);
			
			num = num-1; 
		}
		this.addnumber = 0 ;
	}
	
	@FXML
	public void subLine() {
		if(this.addnumber>0) {
			this.vbox.getChildren().remove(addnumber);
			addnumber=addnumber-1;
		}
	}
	public LinkedList<PreparationStep> getPreparationStepList(){
		return this.pslist; 
	}
	
}
