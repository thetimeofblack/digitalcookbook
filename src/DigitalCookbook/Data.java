package DigitalCookbook;

import javax.jws.soap.SOAPBinding.Use;

import CookBookEntity.Recipe;
import CookBookEntity.User;

public class Data {
	public static void main(String[] args) throws Exception {
		Recipe recipe1 = CookBookApp.createGongBaoJiding();
		Recipe recipe2 = CookBookApp.createHongShaoRou(); 
		Recipe Recipe3 = CookBookApp.createSuanLaFen(); 
		User user = new User("heyining","heyining");
		
		CookBook cookBook = new CookBook() ; 
		cookBook.saveRecipe(recipe1);
		int userloginrs = cookBook.userLogin(user);
		System.out.println(userloginrs);
		
	}
}
