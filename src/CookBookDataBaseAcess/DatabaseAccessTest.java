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
		Recipe recipe2 = CookBookApp.createHongShaoRou();
		
		User user = new User("heyining","heyining");
		User user2 = new User("heyining","Heyining");
		User user3 = new User("heyinin","heyining");
		assertEquals(dao.UserRegister(user),true);
		assertEquals(dao.login(user.getUserName(),user.getUserPassword()),1);
		assertEquals(dao.login(user2.getUserName(),user2.getUserPassword()),0);
		assertEquals(dao.login(user3.getUserName(),user3.getUserPassword()),-1);
		
		
		
		assertEquals(dao.insertRecipe(0, recipe),true);
		dao.close();
	}
}
