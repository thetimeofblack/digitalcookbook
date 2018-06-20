package CookBookView.registerview;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class miniController implements Initializable {
	@FXML
	private Button ok ;
	
	private Stage privatestage;
	
	@FXML
	private Text mainText; 
	

	public void afterOk(ActionEvent e) {
		privatestage.hide();
	}
	public void setStage(Stage stage) {
		this.privatestage = stage; 
	}
	public void setText(String text) {
		this.mainText.setText(text);
	}
	
	public Text getText() {
		return this.mainText;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
}
