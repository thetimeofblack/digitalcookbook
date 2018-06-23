package CookBookView.searchview;

import static org.junit.Assert.assertEquals;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Comment;
import CookBookEntity.Recipe;
import CookBookEntity.User;
import CookBookView.loginview.LoginView;
import DigitalCookbook.CookBookApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SearchView extends javafx.application.Application {
	public void start(Stage primaryStage) throws Exception {
		try {
			DatabaselayerObject dao = new DatabaselayerObject();
			Recipe recipe = CookBookApp.createGongBaoJiding();
			Recipe recipe2 = CookBookApp.createHongShaoRou();

			User user = new User("heyining", "heyining");
			dao.userLogin(user.getUserName(), user.getUserPassword());
			Comment comment = new Comment(1, "hello");
			//dao.saveCommentandRate(comment, 1);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("searchView.fxml"));
			GridPane root = (GridPane) loader.load();

			SearchViewController svc = loader.getController();
			
			Scene scene = new Scene(root, 290, 470);
			svc.setDatabaselayerObject(dao);
			svc.setScene(scene);
			svc.setStage(primaryStage);

			primaryStage.setTitle("Welcome back");

			// primaryStage.setResizable(false);

			primaryStage.setScene(scene);
			// scene.getStylesheets().add(LoginView.class.getResource("firstview.css").toExternalForm());
			primaryStage.show();
			// primaryStage.getIcons().add(new
			// Image(getClass().getResourceAsStream("images/earth.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
