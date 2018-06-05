package CookBookController;
import DigitalCookbook.Recipe;
import java.io.IOException;
import java.util.*;

import DigitalCookbook.Ingredient;
import DigitalCookbook.Recipe;

import java.sql.Connection; 
import java.sql.DriverManager ; 
import java.sql.ResultSet; 
import java.sql.SQLException ; 
import java.sql.Statement ;


public class DataBaseController  {
	final private String databaseUser = "root";
	final private String databasePassword = "heyining";
	final private String databaseUrl = "jdbc:mysql://localhost:3306/recipedatabase?useSSL=true&serverTimezone=GMT";
	final private String databaseDriver = "com.mysql.cj.jdbc.Driver";
	private LinkedList<Recipe> recipelist = new LinkedList<Recipe>(); 
	private Statement statement;
		
		DataBaseController(){
			try { 
					Class.forName(this.databaseDriver);
					Connection con = DriverManager.getConnection(this.databaseUrl, this.databaseUser, this.databasePassword);
					if(!con.isClosed())
						System.out.println("Succeeded connecting to the Database!");
					this.statement = con.createStatement();//获得数据库session
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void InsertRecipe(Recipe recipe) {
				String name= recipe.getrecipename();
				String category = recipe.getCategory();
				double cooktime =recipe.getCooktime();
				int number = recipe.getNumber();
				LinkedList<String> recipesteps = recipe.getSteps();
				LinkedList<Ingredient> recipeingredients = recipe.getIngredientlist();
				double preparationtime = recipe.getPreparationtime();
				
		}
		
		
		public LinkedList<Recipe> searchRecipe(String recipename) {
				Recipe recipe = new Recipe(recipename);
				LinkedList<Recipe> recipelist = new LinkedList<Recipe>();
 				String sql = "select * from recipe where recipename = ";
			    sql = sql +"\'" +recipename + "\'";
			    try {
				ResultSet rs = this.statement.executeQuery(sql);
				while(rs.next()) {
				  recipe.setRecipename(rs.getString("recipename"));	
				  recipe.setCategory(rs.getString("category"));
				  recipe.setCookingTime(rs.getDouble("cooktime"));
				  recipe.setNumber(rs.getInt("recipe"));
				  recipe.setrecipeid(rs.getString("recipeid"));
				  this.addingreforRecipe(recipe);
				  
				  recipelist.add(recipe);
				}
				  rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			   
			    return recipelist;
				
		}
		
		
		private void addingreforRecipe(Recipe recipe) {
			String sql = "select * from Ingredientrecipes where recipeid = \'"+recipe.getrecipeid()+"\'";
			try {
			ResultSet rs = this.statement.executeQuery(sql);
			LinkedList<Integer> ingredientid = new LinkedList<Integer>();
			
			
	
			while(rs.next()) {
				
				String name = rs.getString("ingredientname");
				double amount = rs.getDouble("ingredientamount");
				String unit = rs.getString("ingredientunit");
				
				Ingredient ingredient = new Ingredient(name,amount,unit);
				
				recipe.addIngredient(ingredient);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		private void addstepsforRecipe(Recipe recipe) {
			String sql = "Select";
			try {
			ResultSet rs = this.statement.executeQuery(sql);
			String step = rs.getString("description");
			recipe.addPreparationStep(step);
		
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		
		public LinkedList<Recipe> getRecipeList() {
			return this.recipelist;
		}
		public void close(){
			
		}
			
}
