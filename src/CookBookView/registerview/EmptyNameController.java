package CookBookView.registerview;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmptyNameController {
	
	private Stage stage;

	@FXML
	public void closeWindow(MouseEvent event) {
		this.stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
