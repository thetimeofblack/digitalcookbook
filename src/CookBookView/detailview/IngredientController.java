package CookBookView.detailview;

import java.util.Iterator;
import java.util.LinkedList;

import com.mysql.cj.xdevapi.DatabaseObjectDescription;
import com.sun.javafx.print.Units;
import com.sun.prism.Texture.Usage;

import CookBookEntity.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class IngredientController {
	@FXML
	private Pane ingredientPane;
	
	@FXML
	private VBox vbox; 
	
	@FXML
	private Text No;
	
	@FXML
	private Text Name; 
	
	@FXML
	private Text Usage ; 
	
	@FXML
	private Text Units ; 
	
	@FXML
	private Text Description;
	
	
	// Event Listener on Pane[#ingredientPane].onDragDetected
	@FXML
	public void showIngredients(MouseEvent event) {
		// TODO Autogenerated
	}
	
	public void addingredients(LinkedList<Ingredient> ingredients) throws Exception{
		Iterator<Ingredient> iterator= ingredients.iterator();
		while(iterator.hasNext()) {
			Ingredient ingredient = iterator.next();
			FXMLLoader hboxloader = new FXMLLoader(getClass().getResource("inpanehbox.fxml"));
			HBox hBox = hboxloader.load();
			Text Name = (Text) hBox.getChildren().get(1);
			Text Usage= (Text) hBox.getChildren().get(2);
			Text Unit =(Text)hBox.getChildren().get(3);
			Text Description = (Text)hBox.getChildren().get(4);
			Name.setText(ingredient.getName());
			Usage.setText(String.valueOf(ingredient.getAmount()));
			Unit.setText(ingredient.getUnit());
			Description.setText(ingredient.getDescription());
			vbox.getChildren().add(hBox);
			System.out.println("add Ingredients");
		}
	}
	public VBox getVBox() {
		return this.vbox;
	}
	public void setVBox(VBox vBox) {
		this.vbox =vBox;
	}
}
