package CookBookDataBaseAcess;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.Assert.*;
import CookBookEntity.Recipe;
public class DatabaseAccessTest {
	public static void main(String[] args) {
		DatabaseAccessObject dao = new DatabaseAccessObject();
		Recipe recipe = new Recipe("hello" , "happy",4);
		assertEquals(dao.createrecipe(0, recipe),recipe);
	}
}
