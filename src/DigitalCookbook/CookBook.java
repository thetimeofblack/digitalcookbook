package DigitalCookbook;
import java.util.LinkedList;
import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Comment;
import CookBookEntity.Recipe;
import CookBookEntity.*;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.sql.SQLException;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.*;


public class CookBook {
	private String cookbookname;
	private LinkedList<Recipe> recipelist;
	private DatabaselayerObject databaselayerObject ;
	private User user ;
	
	
	public CookBook() {
		this.databaselayerObject = new DatabaselayerObject();
	}
	public void add(Recipe recipe) {
		recipelist.add(recipe);
	}
	
	public void showallrecipe() {
		Iterator<Recipe> it = recipelist.iterator();
		System.out.println("Show all recipes"); 
		while(it.hasNext()) {
			
			Recipe recipeitr = it.next();
			
		}
	}
	
	CookBook(String cookbookname){
		this.cookbookname = cookbookname; 
		recipelist = new LinkedList<Recipe>();
		
	}
	

	
	public void setDataBase(DatabaselayerObject databaselayerObject) {
		this.databaselayerObject = databaselayerObject ;
	}
	
	public LinkedList<Recipe> getRecipelist(){
		return this.recipelist ; 
	}
	
	public boolean saveRecipe(Recipe recipe ) throws Exception {
		boolean result = this.databaselayerObject.insertrecipe(recipe);
		if(result) return true; 
		return false; 
	}
	

	
	public LinkedList<Recipe> searchRecipe(String recipename)throws Exception{
		//LinkedList<String> recipeidlist = new LinkedList<String>();
		this.recipelist=this.databaselayerObject.showsearchingrecipelist(recipename);
		return this.recipelist;
	}
	
	public void commentRecipe(String recipeid , Comment comment) throws  Exception {
		this.databaselayerObject.saveCommentandRate(comment, Integer.parseInt(recipeid));
		
	}
	
	public int userLogin(User user ) throws Exception {
		this.user = user ;
		int result = this.databaselayerObject.userLogin(user.getUserName(), user.getUserPassword());
		return result; 
		
	}
	
	public LinkedList<Comment> getRecipeComment (String recipeid) throws Exception {
		LinkedList<Comment> comments= new LinkedList<Comment>();
		boolean result = databaselayerObject.getRecipeComment(comments, recipeid);
		return comments;
	}
	
	public LinkedList<Recipe> getallrecipelist(){
		this.recipelist = this.databaselayerObject.getallrecipelist();
		return this.recipelist;
	}
	
	public LinkedList<Recipe> getVegrecipelist(){
		LinkedList<Recipe> vegrecipelist = new LinkedList<Recipe>();
		Iterator<Recipe> iterator = this.recipelist.iterator(); 
		while(iterator.hasNext()) {
			Recipe recipe = (Recipe)iterator.next();
			if(recipe.getDescription().equals("vegetable")) {
				vegrecipelist.add(recipe);
			}
			
		}
		return vegrecipelist;
	}
	
	public LinkedList<Recipe> getEggrecipelist(){
		LinkedList<Recipe> vegrecipelist = new LinkedList<Recipe>();
		Iterator<Recipe> iterator = this.recipelist.iterator(); 
		while(iterator.hasNext()) {
			Recipe recipe = (Recipe)iterator.next();
			if(recipe.getDescription().equals("Egg")) {
				vegrecipelist.add(recipe);
			}
			
		}
		return vegrecipelist;
	}
	
	public LinkedList<Recipe> getMeatrecipelist(){
		LinkedList<Recipe> vegrecipelist = new LinkedList<Recipe>();
		Iterator<Recipe> iterator = this.recipelist.iterator(); 
		while(iterator.hasNext()) {
			Recipe recipe = (Recipe)iterator.next();
			if(recipe.getDescription().equals("Meat")) {
				vegrecipelist.add(recipe);
			}
			
		}
		return vegrecipelist;
	}
	
	public LinkedList<Recipe> getUserRecipe()throws Exception{
		String userid = this.user.getUserID(); 
		LinkedList<Recipe> recipelist = this.databaselayerObject.getUserallRecipe(userid);
		return recipelist;
	}
	
	public void saveRecipelist(LinkedList<Recipe> recipelist) throws Exception{
		Iterator<Recipe> iterator= recipelist.iterator(); 
		while(iterator.hasNext()) {
			Recipe recipe = iterator.next(); 
			this.databaselayerObject.insertrecipe(recipe);
		}
	}
	
	
	public void saveUserRecipe(User user, Recipe recipe) throws Exception{
		this.databaselayerObject.setUser(user);
		this.databaselayerObject.insertrecipe(recipe);
	}
	
	public void deleteUserRecipe(User user, String recipeid)  throws Exception{
		this.user = user; 
		Recipe recipe = new Recipe(); 
		recipe.setRecipeID(recipeid);
		this.databaselayerObject.setUser(user);
		this.databaselayerObject.deleterecipeuser(recipe);
	}
	
	public boolean userRegister(User user) throws Exception{
		return this.databaselayerObject.userRegister(user);
	}
	

	
}
