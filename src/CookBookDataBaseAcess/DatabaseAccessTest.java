package CookBookDataBaseAcess;
import static org.junit.Assert.assertEquals;
import CookBookEntity.User;
import DigitalCookbook.CookBookApp;
import org.junit.Assert.*;
import org.junit.*;
import org.junit.Assert.*;
import CookBookEntity.Recipe;
public class DatabaseAccessTest {
	public static void main(String[] args) throws Exception {
		DatabaseAccessObject dao = new DatabaseAccessObject();
		Recipe recipe = CookBookApp.createGongBaoJiding();
		User user = new User("heyining","heyining");
		assertEquals(dao.UserRegister(user),true);
		
		assertEquals(dao.insertRecipe(0, recipe),true);
	}
}
