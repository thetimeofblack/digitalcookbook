package CookBookView.editview;


import javafx.scene.control.TextField;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.Iterator;
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
	private VBox vbox;
	@FXML
	private Button confirm;

	@FXML
	private ImageView add;
	// Event Listener on ImageView[#add].onDragDetected
	
	@FXML
	private Button addIngredient;
	
	@FXML
	private Button deleteIngredient;
	

	@FXML	
	private TextField Name ;
	
	@FXML
	private TextField Usage; 
	
	@FXML
	private TextField Unit ; 
	
	@FXML
	private TextField Description; 
	
	
	
	private int addnumber =0;
	

	
	private LinkedList<Ingredient> Ingredientlist;
	@FXML
	public void addIngredient() throws Exception{
		// TODO Autogenerated
		FXMLLoader loader = new FXMLLoader(getClass().getResource("inpanehbox.fxml"));
		HBox hBox = loader.load();
		addnumber = addnumber+1;
		vbox.getChildren().add(hBox);
	}
	
	@FXML
	public void deleteIngredient() throws Exception{
		if(this.addnumber>0) {
			this.vbox.getChildren().remove(addnumber-1);
			addnumber=addnumber-1;
		}
		
	}
	@FXML
	public void saveIngredient() {
		//this.Ingredientlist = new LinkedList<Ingredient>();
		int num = this.addnumber;
		while(num>0) {
		Ingredient ingredient = new Ingredient();
		HBox hBox = (HBox) this.vbox.getChildren().get(this.addnumber-1);
		TextField Name = (TextField)hBox.getChildren().get(0);
		
		TextField Usage = (TextField)hBox.getChildren().get(1);
		TextField Unit = (TextField)hBox.getChildren().get(2);
		TextField Description = (TextField)hBox.getChildren().get(3);
		String usage = Usage.getText(); 
		if(usage.equals("")) usage = "0";
		ingredient.setName(Name.getText());
		ingredient.setUnit(Unit.getText());
		ingredient.setDescription(Description.getText());
		ingredient.setAmount(Double.parseDouble(Usage.getText()));
		
		
		num=num-1;
		this.Ingredientlist.add(ingredient);
		}
		
		
		
	}
	
	@FXML
	public void cancelIngredient() {
		int num = this.addnumber; 
		while(num>1) {
			this.vbox.getChildren().remove(num);
			
			num = num-1; 
		}
		this.addnumber = 0 ;
	}
	
	public VBox getInpane() {
		return this.vbox;
	}
	
	public LinkedList<Ingredient> getIngredientList() {
		return this.Ingredientlist;
	}
	
	public void setIngredientList(LinkedList<Ingredient> ingredients) {
		this.Ingredientlist = ingredients; 
	}

	public VBox getVBox() {
		// TODO Auto-generated method stub
		return this.vbox;
	}
	
	public void showIngredients() throws Exception{
		Iterator iterator = this.Ingredientlist.iterator(); 
		while(iterator.hasNext()) {
			Ingredient ingredient =(Ingredient)iterator.next(); 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("inpanehbox.fxml"));
			HBox hbox = (HBox)loader.load(); 
			inpanehboxController controller = loader.getController(); 
			controller.showingredient(ingredient);
			this.addnumber =this.addnumber+1;
			this.vbox.getChildren().add(hbox);
			
		}
	}
	
	public LinkedList<Ingredient> getIngredients() {
		return this.Ingredientlist; 
	}
	
	public void setAddnumber(int number) {
		this.addnumber = number; 
	}
}
