package DigitalCookbook;

import javax.jws.soap.SOAPBinding.Use;

import CookBookEntity.Recipe;
import CookBookEntity.User;

public class Data {
	public static void main(String[] args) throws Exception {
		Recipe recipe1 = CookBookApp.createGongBaoJiding();
		Recipe recipe2 = CookBookApp.createHongShaoRou(); 
		Recipe recipe3 = CookBookApp.createSuanLaFen(); 
		//User user = new User("heyining","heyining");
		
		CookBook cookBook = new CookBook() ; 
		
		cookBook.saveRecipe(recipe1);
		cookBook.saveRecipe(recipe2);
		cookBook.saveRecipe(recipe3);

		//int userloginrs = cookBook.userLogin(user);
		//cookBook.saveUserRecipe(user, recipe2);
		//System.out.println(userloginrs);
		
	}
}
