package DigitalCookbook;
import java.util.LinkedList;

import com.sun.glass.ui.TouchInputSupport;

import CookBookDataBaseAcess.DatabaselayerObject;
import CookBookEntity.Comment;
import CookBookEntity.Recipe;
import CookBookView.createview.CommentController;
import javafx.scene.shape.Line;
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
	
	/**
	 * @Description add one recipe to the recipelist
	 * @param recipe
	 */
	public void add(Recipe recipe) {
		recipelist.add(recipe);
	}
	
	/**
	 * @Description show all the recipes in the recipelist
	 */
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
	
	/**
	 * @Description get all the recipes from database and put them into a list
	 * @return 
	 */
	public LinkedList<Recipe> getRecipelist(){
		return this.recipelist ; 
	}
	
	
	/**
	 * @Description save recipe to the database
	 * @param recipe
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveRecipe(Recipe recipe ) throws Exception {
		boolean result = this.databaselayerObject.insertrecipe(recipe);
		if(result) return true; 
		return false; 
	}
	

	
	/**
	 * @Description search a specific recipe through name
	 * @param recipename
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Recipe> searchRecipe(String recipename)throws Exception{
		//LinkedList<String> recipeidlist = new LinkedList<String>();
		this.recipelist = new LinkedList<Recipe>();
		System.out.println("Cookbook searching");
		this.recipelist=this.databaselayerObject.showsearchingrecipelist(recipename);
		return this.recipelist;
	}
	
	
	
	/**
	 * @Description 
	 * @param recipeid
	 * @param comment
	 * @throws Exception
	 */
	public void commentRecipe(String recipeid , Comment comment) throws  Exception {
		this.databaselayerObject.saveCommentandRate(comment, Integer.parseInt(recipeid),this.user.getUserID());
		
	}
	
	
	/**
	 * @Description user login through using name and password
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int userLogin(User user ) throws Exception {
		this.user = user ;
		int result = this.databaselayerObject.userLogin(user.getUserName(), user.getUserPassword());
		this.user.setUserID(this.databaselayerObject.getUser().getUserID());
		return result; 
		
	}
	
	
	/**
	 * @Description get comments for a specific recipe
	 * @param recipeid
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Comment> getRecipeComment (String recipeid) throws Exception {
		return this.databaselayerObject.getRecipeComment(recipeid);
	}
	
	
	/**
	 * @Description get all the recipes from database and put them into a list
	 * @return
	 */
	public LinkedList<Recipe> getallrecipelist(){
		this.recipelist = this.databaselayerObject.getallrecipelist();
		return this.recipelist;
	}
	
	/**
	 * @Description get all the vegan recipes from database and put them into a list
	 * @return
	 */
	public LinkedList<Recipe> getVegrecipelist(){
		LinkedList<Recipe> vegrecipelist = new LinkedList<Recipe>();
		Iterator<Recipe> iterator = this.recipelist.iterator(); 
		while(iterator.hasNext()) {
			Recipe recipe = (Recipe)iterator.next();
			if(recipe.getDescription().equals("Vegetable")) {
				vegrecipelist.add(recipe);
				System.out.println("add vegetable");
			}
			
		}
		return vegrecipelist;
	}
	
	
	/**
	 * @Description get all the vegetarian recipes from database and put them into a list
	 * @return
	 */
	public LinkedList<Recipe> getEggrecipelist(){
		LinkedList<Recipe> vegrecipelist = new LinkedList<Recipe>();
		Iterator<Recipe> iterator = this.recipelist.iterator(); 
		while(iterator.hasNext()) {
			Recipe recipe = (Recipe)iterator.next();
			if(recipe.getDescription().equals("Egg")) {
				vegrecipelist.add(recipe);
				System.out.println("Add Egg");
			}
			
		}
		return vegrecipelist;
	}
	
	
	/**
	 * @Description get all the meat recipes from database and put them into a list
	 * @return
	 */
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
	
	
	/**
	 * @Description get all the recipes created by a specific user
	 * @return
	 * @throws Exception
	 */
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
	
	
	/**
	 * @Description save the recipe which is created by a specific user
	 * @param user
	 * @param recipe
	 * @throws Exception
	 */
	public void saveUserRecipe(User user, Recipe recipe) throws Exception{
		this.databaselayerObject.setUser(user);
		this.databaselayerObject.insertrecipe(recipe);
	}
	
	/**
	 * @Description delete the recipe which is created by a specific user
	 * @param recipeid
	 * @throws Exception
	 */
	public void deleteUserRecipe(String recipeid)  throws Exception{
		
		Recipe recipe = new Recipe(); 
		recipe.setRecipeID(recipeid);
		this.databaselayerObject.setUser(this.user);
		this.databaselayerObject.deleterecipeuser(recipe);
	}
	
	/**
	 * @Description create a new account 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean userRegister(User user) throws Exception{
		return this.databaselayerObject.userRegister(user);
	}
	
	
	/**
	 * @Description 
	 * @param comment
	 * @param recipeid
	 * @return
	 * @throws Exception
	 */
	public boolean saveComment(Comment comment,String recipeid ) throws Exception {
		System.out.println(recipeid);
		boolean result =this.databaselayerObject.saveCommentandRate(comment, Integer.parseInt(recipeid),this.user.getUserID());
		return result; 
	}
	
	/**
	 * @Description get all the recipes have been set as favorite by a specific user
	 * @return
	 * @throws Exception
	 */
	public LinkedList<Recipe> getfavouriterecipe() throws Exception{
		return this.databaselayerObject.getfavouriterecipelist(this.user.getUserID());
	}
	
	
	
	/**
	 * @Description user set a specific recipe as favorite
	 * @param recipeid
	 * @return
	 * @throws Exception
	 */
	public boolean setFavourite(String recipeid) throws Exception{
	
		return this.databaselayerObject.setfavourite(this.user.getUserID(), recipeid);
	
				
	}
	
	
	/**
	 * @Description set rate and comment for a specific recipe by user
	 * @param recipe
	 * @throws Exception
	 */
	public void setRateComment(Recipe recipe) throws Exception{
		LinkedList<Comment> comments = new LinkedList<Comment>();
		this.databaselayerObject.setUser(this.user);
		this.databaselayerObject.setRecipecr(recipe, user.getUserID());
		
	}
	
	
	public void setUser(User user) throws Exception{
		this.databaselayerObject.UserLogin(user);
		this.databaselayerObject.setUserID(user);
		this.user = user;
		
	}
	
	public LinkedList<Recipe> getRankedRecipeList() throws Exception{
		LinkedList<Recipe> recipelist = new LinkedList<Recipe>();
		recipelist = this.databaselayerObject.getRankedlist(this.user.getUserID());
		if(!recipelist.isEmpty()) this.recipelist = recipelist;
		
		return recipelist;
	}
	
	public LinkedList<Comment> getComments(String recipeid) throws Exception{
		return this.databaselayerObject.getRecipeComment(recipeid);
	}
	
	/**
	 * @Description get the comment of user for a specific recipe
	 * @param recipeid
	 * @return
	 * @throws Exception
	 */
	public Comment getComment(String recipeid) throws Exception {
		Comment comment = new Comment() ; 
		comment = this.databaselayerObject.getComment(recipeid,this.user.getUserID());
		return comment; 
	}	
	
	public void deleteUserComment(String recipeid)throws Exception {
		this.databaselayerObject.setUser(this.user);
		boolean result = this.databaselayerObject.deleteComment(recipeid);
		if(result == false) System.out.println("there is no comment for recipe which can be deleted");
		else System.out.println("Comment for recipe is deleted");
	} 
	
	public boolean editRecipe(Recipe recipe) throws Exception{
		return this.databaselayerObject.editRecipe(recipe);
	}
	public boolean isFavourite(Recipe recipe) throws Exception{
		boolean result = this.databaselayerObject.judgefavourite(recipe.getRecipeID());
		return result;
	}
	
	public void setRate(int rate , String recipeid)throws Exception{
		boolean result = this.databaselayerObject.setRate(this.user.getUserID(), recipeid, rate);
		if(result) System.out.println("set rate for recipe successfully");
		else System.out.println("We can not set rate for the recipe");
	}
	
	public void deleteFavourite(Recipe recipe) throws Exception{
		String recipeid = recipe.getRecipeID(); 
		String userid = this.user.getUserID(); 
		this.databaselayerObject.deleteFavourite(recipeid, userid);
		
	}
	
	public void editComment(String recipeid,String comment) throws Exception{
		this.databaselayerObject.editComment(this.user.getUserID(), recipeid,comment);
	}

}
