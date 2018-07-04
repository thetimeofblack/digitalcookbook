package CookBookView.detailview;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServingNumberErrorController {
	private Stage stage;

	public void ok(MouseEvent event) {
		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
