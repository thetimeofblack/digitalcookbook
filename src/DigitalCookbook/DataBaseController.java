package DigitalCookbook;
import java.io.IOException;
import java.util.*;
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
	
		}
		
		
		public Recipe searchRecipe(String recipename) {
				Recipe recipe = new Recipe(recipename);
				String sql = "select * from recipe where recipename = ";
			    sql = sql +"\'" +recipename + "\'";
			    try {
				ResultSet rs = this.statement.executeQuery(sql);
				if(rs.next()) {
				  recipe.setRecipename(rs.getString("recipename"));	
				  recipe.setCategory(rs.getString("category"));
				  recipe.setCookingTime(rs.getDouble("cooktime"));
				  recipe.setNumber(rs.getInt("recipe"));
				  rs.close();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
			   
			    return recipe;
				
		}
		
		
		public Recipe addingreforRecipe(Recipe recipe) {
			
			return recipe;
		}
		
		
		public void close(){
			
		}
			
}
