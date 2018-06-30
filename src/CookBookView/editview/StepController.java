package CookBookView.editview;

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
	
	private void AddLine(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader  = new FXMLLoader(getClass().getResource("stpanehbox.fxml"));
		HBox hBox = (HBox)loader.load(); 
		
		this.vbox.getChildren().add(hBox); 
		addnumber = addnumber+1; 
	}
	
	private void SubLine() throws Exception{
		this.vbox.getChildren().remove(addnumber+1);
		addnumber = addnumber-1; 
	}
	
	public void savesteps() {
		int num = addnumber; 
		while(num>0) {
			
			this.vbox.getChildren().get(num+1);
		}
	}
	
	public void cancelSteps() {
		int num =addnumber; 
	}
	
	public void setSteps(LinkedList<PreparationStep> steps) {
		this.steps = steps; 
		
	}
}
