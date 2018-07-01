package CookBookView.editview;

import javafx.scene.control.*;
import java.awt.event.ActionEvent;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Iterator;
import java.util.LinkedList;

import com.mysql.cj.exceptions.ExceptionInterceptorChain;

import CookBookEntity.PreparationStep;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StepController  {
	
	@FXML
	private VBox vbox ; 
	
	private int addnumber =0 ;
	
	private LinkedList<PreparationStep> steps; 
	
	private StepHBoxController stepcontroller;
	
	public void showSteps() throws Exception{
		Iterator iterator = this.steps.iterator(); 
		while(iterator.hasNext()) {
			PreparationStep step = (PreparationStep)iterator.next();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("stpanehbox.fxml"));
			HBox hbox = (HBox)loader.load();
			StepHBoxController controller = loader.getController(); 
			controller.setStepText(step.getDescription());
			vbox.getChildren().add(hbox);
			this.addnumber = this.addnumber +1; 
		}
	}
	
	public void AddLine() throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader  = new FXMLLoader(getClass().getResource("stpanehbox.fxml"));
		HBox hBox = (HBox)loader.load(); 
		
		this.vbox.getChildren().add(hBox); 
		addnumber = addnumber+1; 
		System.out.println("Now the addnumber is "+addnumber);
	}
	
	public void SubLine() throws Exception{
		if(addnumber>0) {
		this.vbox.getChildren().remove(addnumber-1);
		addnumber = addnumber-1; 
		}
	}
	
	public void savesteps() {
		int num = addnumber; 
		this.steps = new LinkedList<PreparationStep>();
		while(num>0) {
			PreparationStep step = new PreparationStep(); 
			
			HBox hbox =  (HBox)this.vbox.getChildren().get(num-1);
			TextArea stepdetail =(TextArea)hbox.getChildren().get(1); 
			step.setDescription(stepdetail.getText());
			this.steps.add(step);
			num=num-1; 
 		}
		
	}
	
	
	public void cancelSteps() {
		int num =addnumber; 
		while(num>0) {
			this.vbox.getChildren().remove(num-1);
			num=num-1;
		}
		this.addnumber = num ; 
	}
	
	
	public void setSteps(LinkedList<PreparationStep> steps) {
		this.steps = steps; 
		
	}
	
	public LinkedList<PreparationStep> getSteps(){
		return this.steps; 
	}
	

	public VBox getVBox() {
		return this.vbox;
	}


}
