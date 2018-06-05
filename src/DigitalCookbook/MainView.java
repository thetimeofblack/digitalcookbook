package DigitalCookbook;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class MainView extends GridPane{
	Button searchbutton = new Button("Seach");
	Button listButton = new Button("View all recipes");
	Button favouriteButton = new Button("Favourite Recipes");
	Button createButton = new Button("Create a Recipe");
	HBox buttonBox = new HBox(searchbutton,listButton,favouriteButton,createButton);
	
	
	MainView(){
		this.add(buttonBox,3,2);
		this.add(listButton,3,3);
	}
}
