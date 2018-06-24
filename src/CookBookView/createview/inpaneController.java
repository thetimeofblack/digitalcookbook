package CookBookView.createview;


import javafx.scene.control.TextField;

import java.util.LinkedList;

import org.omg.CORBA.Current;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import CookBookEntity.Ingredient;
import CookBookEntity.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class inpaneController {
	@FXML
	private Button addingredient;
	@FXML
	private VBox inpaneVBox;
	@FXML
	private Button confirm;

	@FXML
	private ImageView add;
	// Event Listener on ImageView[#add].onDragDetected
	
	@FXML
	private Button addIngredient;
	
	@FXML
	private Button deleteIngredient;
	

	
	private int addnumber =0;
	

	
	private LinkedList<Ingredient> Ingredientlist;
	@FXML
	public void addIngredient() throws Exception{
		// TODO Autogenerated
		FXMLLoader loader = new FXMLLoader(getClass().getResource("inpanehbox.fxml"));
		HBox hBox = loader.load();
		addnumber = addnumber+1;
		inpaneVBox.getChildren().add(hBox);
	}
	
	@FXML
	public void deleteIngredient() throws Exception{
		if(this.addnumber>0) {
			this.inpaneVBox.getChildren().remove(1+addnumber);
			addnumber=addnumber-1;
		}
		
	}
	@FXML
	public void saveIngredient() {
		this.Ingredientlist = new LinkedList<Ingredient>();
		int num = this.addnumber;
		while(num>0) {
		Ingredient ingredient = new Ingredient();
		HBox hBox = (HBox) this.inpaneVBox.getChildren().get(this.addnumber+1);
		TextField Name = (TextField)hBox.getChildren().get(0);
		
		TextField Usage = (TextField)hBox.getChildren().get(1);
		TextField Unit = (TextField)hBox.getChildren().get(2);
		TextField Description = (TextField)hBox.getChildren().get(3);
		ingredient.setName(Name.getText());
		ingredient.setUnit(Unit.getText());
		ingredient.setDescription(Description.getText());
		ingredient.setAmount(Double.parseDouble(Usage.getText()));
		
		
		num=num-1;
		this.Ingredientlist.add(ingredient);
		}
		Ingredient ingredient = this.Ingredientlist.get(0);
		System.out.println(ingredient.toString());
		
	}
	
	@FXML
	public void cancelIngredient() {
		
	}
	
	public VBox getInpane() {
		return this.inpaneVBox;
	}
	

	
	public LinkedList<Ingredient> getIngredientList() {
		return this.Ingredientlist;
	}
}
