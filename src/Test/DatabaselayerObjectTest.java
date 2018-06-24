package Test;
import static org.junit.Assert.assertEquals;


import DigitalCookbook.CookBookApp;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import CookBookEntity.User;
import CookBookEntity.Recipe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import CookBookDataBaseAcess.DatabaselayerObject;
class DatabaselayerObjectTest {
	private static DatabaselayerObject dao ; 
	@BeforeAll 
    static void initialize(){
		dao = new DatabaselayerObject();
		
	}
	
	
	@Test
	void testDatabaselayerObject() {
		fail("Not yet implemented");
	}

	@Test
	void testUserLogin() {
		fail("Not yet implemented");
		User user = new User("heyining","heyining");
		
	}

	@Test
	void testUserLogin1() {
		fail("Not yet implemented");
	}

	@Test
	void testUserRegisterUser() {
		fail("Not yet implemented");
	}

	@Test
	void testUserRegister() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUserRecipe() {
		fail("Not yet implemented");
	}

	@Test
	void testGetallrecipelist() {
		fail("Not yet implemented");
	}

	@Test
	void testShowsearchingrecipelist() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertrecipe() throws SQLException {
		fail("Not yet implemented");
		Recipe recipe = CookBookApp.createGongBaoJiding();
		dao.insertRecipe(recipe);	
	}

	@Test
	void testInsertingredients() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertpreparationsteps() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertrecipeuser() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertRecipe() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleterecipe() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteingredients() {
		fail("Not yet implemented");
	}

	@Test
	void testDeletepreparationsteps() {
		fail("Not yet implemented");
	}

	@Test
	void testJudgerecipeuser() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleterecipeuser() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteRecipe() {
		fail("Not yet implemented");
	}

	@Test
	void testEditRecipe() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertfavourite() {
		fail("Not yet implemented");
	}

	@Test
	void testJudgefavourite() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveRecipeDescription() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveCommentandRate() {
		fail("Not yet implemented");
	}

	@Test
	void testClose() {
		fail("Not yet implemented");
	}

}
