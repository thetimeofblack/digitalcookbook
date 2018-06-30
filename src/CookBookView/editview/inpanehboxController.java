package CookBookView.editview;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import CookBookEntity.Ingredient;
import javafx.event.ActionEvent;

public class inpanehboxController {
	@FXML
	private TextField Name;
	@FXML
	private TextField Usage;
	@FXML
	private TextField Unit;
	@FXML
	private TextField Description;
	@FXML
	private Button delete;

	// Event Listener on Button[#delete].onAction
	@FXML
	public void deletenode(ActionEvent event) {
		Name.setText("");
		Usage.setText("");
		Unit.setText("");
		Description.setText("");
		
	}
	
	
	public void showingredient(Ingredient ingredient) {
		this.Name.setText(ingredient.getName());
		this.Usage.setText(String.valueOf(ingredient.getAmount()));
		this.Unit.setText(ingredient.getUnit());
		this.Description.setText(ingredient.getDescription());
		
	}
}
