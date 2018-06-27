
package CookBookView.firstview;


import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.sun.glass.ui.Application;
import com.sun.prism.Image;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstView extends javafx.application.Application {
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fv.fxml"));
			BorderPane root = (BorderPane) loader.load();
			fvController fvc = loader.getController();
			//VBox vbox = new VBox();
			//ox.getChildren().add();
			Scene scene = new Scene(root, 600, 1000);
			fvc.setStageAndScene(primaryStage, scene);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Nice to meet you");
			primaryStage.setResizable(false);
			scene.getStylesheets().add(FirstView.class.getResource("firstview.css").toExternalForm());
			//primaryStage.show();
			// primaryStage.getIcons().add(newImage(getClass().getResourceAsStream("images/earth.png")));
			primaryStage.show();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}


}