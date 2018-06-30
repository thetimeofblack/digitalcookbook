package CookBookView.editview;

import javafx.scene.control.TextArea;
import java.security.KeyStore.PrivateKeyEntry;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StepHBoxController {
	@FXML
	private TextArea step; 
	public void setStepText(String step) {
		this.step.setText(step);
	}
	public void clean() {
		this.step.setText("");
	}
}
