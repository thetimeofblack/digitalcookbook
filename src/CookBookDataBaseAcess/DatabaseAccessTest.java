package CookBookDataBaseAcess;
import static org.junit.Assert.assertEquals;
import DigitalCookbook.CookBookApp;

import org.junit.*;
import org.junit.Assert.*;
import CookBookEntity.Recipe;
public class DatabaseAccessTest {
	public static void main(String[] args) {
		DatabaseAccessObject dao = new DatabaseAccessObject();
		Recipe recipe = CookBookApp.createGongBaoJiding();
		
		
		assertEquals(dao.createrecipe(0, recipe),true);
	}
}
