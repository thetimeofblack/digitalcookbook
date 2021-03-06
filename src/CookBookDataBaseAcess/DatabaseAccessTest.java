package CookBookDataBaseAcess;
import static org.junit.Assert.assertEquals;
import CookBookEntity.Comment;
import CookBookEntity.User;
import DigitalCookbook.CookBookApp;
import org.junit.Assert.*;
import org.junit.*;
import org.junit.Assert.*;
import CookBookEntity.Recipe;
public class DatabaseAccessTest {
	
	
	public static void main(String[] args) throws Exception {
		DatabaselayerObject dao = new DatabaselayerObject();
		Recipe recipe = CookBookApp.createGongBaoJiding();
		Recipe recipe2 = CookBookApp.createHongShaoRou();
		//heloo
		User user = new User("heyining","heyining");
		User user2 = new User("heyining","Heyining");
		User user3 = new User("heyinin","heyining");
		
		assertEquals(dao.userRegister(user),false);
		assertEquals(dao.userLogin(user.getUserName(),user.getUserPassword()),1);
		assertEquals(dao.userLogin(user2.getUserName(),user2.getUserPassword()),0);
		assertEquals(dao.userLogin(user3.getUserName(),user3.getUserPassword()),-1);
		System.out.println("This is user id"+dao.getUser().getUserID());
		assertEquals(dao.getUser().getUserID(),"1");
	
		//assertEquals(dao.)
		//assertEquals(dao.getUserRecipe(, name))
		//Recipe recipeget = new Recipe();
		//recipeget = dao.getUserRecipe(0, "Gong Bao Jiding");
		//recipeget.show();
		Comment comment = new Comment(1,"hello");
		
		
		//assertEquals(dao.insertRecipe(0, recipe),true);
		
		dao.close();
	}
}
