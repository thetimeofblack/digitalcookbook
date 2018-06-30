package Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import CookBookEntity.Comment;
import CookBookEntity.Recipe;
import CookBookEntity.User;
import DigitalCookbook.CookBook;

class CookBookTest {
	
	private static CookBook cookbook = new CookBook();
	private Recipe recipe1 = null;
	private Recipe recipe2 = null;
	
	private User newuser = null; 
	/**
	 * create two recipe objects for testing
	 * create one user object for testing
	 */
	@Before
	public void setUp() throws Exception{
		//create recipes for insertion
		recipe1 = new Recipe("test1","test1",1);
		recipe1.setCookingTime(20);
		recipe1.setPreparationTime(20);
		
		recipe2 = new Recipe("test2","test2",1);
		recipe2.setCookingTime(20);
		recipe2.setPreparationTime(21);
		
		
		
		
		//create user for insertion
		newuser = new User("testuser","123456");
	
	}
	

	
	/**
	 * Test whether add function is valid
	 */

	@Test
	void testAdd() {
		cookbook.getRecipelist().add(recipe1);
		assertNotNull(cookbook.getRecipelist());
	}
/*
 * test showallrecipe function
 */
	//@Test
	//public void testShowallrecipe() throws Exception{
	//assertNotNull(cookbook.showallrecipe());
		
		
	//}

	@Test
	void testCookBookString() {
		fail("Not yet implemented");
	}

	@Test
	void testSetDataBase() {
		fail("Not yet implemented");
	}

	@Test
	void testGetRecipelist() {
		assertNotNull(cookbook.getallrecipelist());
	}

	/*
	 * test whether saverecipe function is valid
	 */
	@Test
	void testSaveRecipe() throws Exception {
		assertTrue(cookbook.saveRecipe(recipe1));
		
	}

	@Test
	void testSearchRecipe() throws Exception {
		assertNotNull(cookbook.searchRecipe("test1"));
	}
	
	/**
	 * Test whether commentrecipe function is valid
	 * @throws Exception
	 */
	@Test
	void testCommentRecipe() throws Exception {
		
		assertNotNull(cookbook.getRecipeComment("1"));
	}

	@Test
	void testUserLogin() throws Exception {
		assertNotNull(cookbook.userLogin(newuser));
	}

	@Test
	void testGetRecipeComment() throws Exception {
		assertNotNull(cookbook.getRecipeComment("1"));

	}

	@Test
	void testGetallrecipelist() {
		assertNotNull(cookbook.getallrecipelist());

		
		
	}

	@Test
	void testGetVegrecipelist() {
		assertNotNull(cookbook.getVegrecipelist());
	}

	@Test
	void testGetEggrecipelist() {
		assertNotNull(cookbook.getEggrecipelist());
	}
	/**
	 * 
	 */
	@Test
	void testGetMeatrecipelist() {
		assertNotNull(cookbook.getMeatrecipelist());
	}

	@Test
	void testGetUserRecipe() throws Exception {
		assertNotNull(cookbook.getUserRecipe());
	}

	/**@Test
	void testSaveRecipelist() throws Exception {
		
		cookbook.saveRecipelist(recipe2);
		assertNotNull(cookbook.searchRecipe("test2"));

	}
	*/

	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	void testSaveUserRecipe() throws Exception {
		
	}

	@Test
	void testDeleteUserRecipe() {
		
	}

	/**
	 * test whether userregister function is valid
	 * @throws Exception
	 */
	@Test
	void testUserRegister() throws Exception {
		assertTrue(cookbook.userRegister(newuser));
	}

	/**
	 * test whether savecomment function is valid
	 * @throws Exception
	 */
	@Test
	void testSaveComment() throws Exception {
		String userid = "1";
		int grade = 1;
		String comment = "test";
		Comment commentandgrade = new Comment(grade,comment);
		assertTrue(cookbook.saveComment(commentandgrade,userid));

	}
	
	/**
	 * test whether getfavouriterecipe function is valid
	 * @throws Exception
	 */
	@Test
	void testGetfavouriterecipe() throws Exception {
		assertNotNull(cookbook.getfavouriterecipe());
	}
	
	/**
	 * test whether setfavourite function is valid
	 * @throws Exception
	 */
	@Test
	void testSetFavourite() throws Exception {
		assertTrue(cookbook.setFavourite("1"));	
		}

}
