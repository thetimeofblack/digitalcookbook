package DigitalCookbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import CookBookEntity.Recipe;


public class DatabaseController {
	
	static Connection con;
	static Statement sql;
	static ResultSet res;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");// 使用forName方法加载jdbc驱动程序
			System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 使用Drivemanager中getConnection的方法得到数据库连接，三个参数依次指定路径，用户名和密码
			con = DriverManager.getConnection("jdbc:mysql:" + "//127.0.0.1:3306/", "root", "1989");
			System.out.println("数据库连接成功"); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	//login方法
	public boolean login(String s1,String s2) throws SQLException{
		con = this.getConnection();
		String s = "select * from cookbook.user where UserName = '"+s1+"' and UserPassword = "+s2;
		sql = con.createStatement();
		res = sql.executeQuery(s);// SQL语句对大小写不敏感
		return res.first(); 
	}
	
	//create account 方法
	public boolean create(String s1,String s2) throws SQLException{
		con = this.getConnection();
		String s = "insert into cookbook.user values(5, '"+s1+"',"+s2+")";
		sql = con.createStatement();
		res = sql.executeQuery(s);
		return true;		
	}
	
	//show recipe detail 方法
	public Recipe showrecipedetail(String s){				
		con = this.getConnection();
		Recipe recipe = new Recipe();
		try {
			sql = con.createStatement();
			String ss = "select * from cookbook.recipe where name = '"+s+"'";
			res = sql.executeQuery(ss);// SQL语句对大小写不敏感			
			if (res.next()) {			
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getDouble("PrepareTime"));
				recipe.setCookTime(res.getDouble("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipe;
	}
	
	
	//show all recipe list 方法	
	public LinkedList<Recipe> showallrecipelist(String name) {				
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select * from cookbook.recipe");//sql对大小写不敏感
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getDouble("PrepareTime"));
				recipe.setCookTime(res.getDouble("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);							
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	//show searching recipe list 方法
	public LinkedList<Recipe> showsearchingrecipelist(String s) {
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		String ss = "select * from recipe where name is like '%"+s+"%'";
		try {
			sql = con.createStatement();
			res = sql.executeQuery(ss);//sql对大小写不敏感
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getDouble("PrepareTime"));
				recipe.setCookTime(res.getDouble("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	//create recipe 方法
	public Recipe createrecipe(String name,String preparetime,String cooktime,String category){
		con = this.getConnection();
		Recipe recipe = new Recipe();
		try{
			sql = con.createStatement();
			res = sql.executeQuery();//sql对大小写不敏感
		}
	}

}