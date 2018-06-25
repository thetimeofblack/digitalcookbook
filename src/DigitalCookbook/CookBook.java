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
		boolean result = this.databaselayerObject.insertRecipe(recipe);
		if(result) return true; 
		return false; 
	}
	
	public Recipe getRecipe(String recipeid) throws Exception {
		Recipe recipe = new Recipe();
		recipe = this.databaselayerObject.getRecipe(recipeid);
		return recipe; 
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
}
