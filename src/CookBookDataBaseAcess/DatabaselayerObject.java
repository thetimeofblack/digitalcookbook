package CookBookDataBaseAcess;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;


import CookBookEntity.Comment;
import CookBookEntity.Ingredient;
import CookBookEntity.PreparationStep;
import CookBookEntity.Recipe;

import CookBookEntity.User;
public class DatabaselayerObject {

	private Connection con;
	private Statement sql;
	static ResultSet res, res2;
	final private String driver = "com.mysql.jdbc.Driver";
	final private String Databaseuser = "root";
<<<<<<< HEAD
	//final private String Databasepassword = "heyining";//可修改项目
	final private String Databasepassword = "258000";//可修改项目
	//final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT";//可修改项目
	final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306";//可修改项目
	
=======

	final private String Databasepassword = "root";
	final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT";
	//final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306";
	//final private String Databaseurl = "jdbc:mysql://localhost:3306/recipedatabase?useSSL=true&serverTimezone=GMT";

>>>>>>> refs/remotes/origin/master
	private User user; 
	
	public DatabaselayerObject(){
		try {
		this.user= new User();
		Class.forName(this.driver);
		
		System.out.println("the driver for database has been initialized");
		this.con = DriverManager.getConnection(this.Databaseurl, this.Databaseuser, this.Databasepassword);
		System.out.println("database access sucessful!");
		this.sql = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UserLogin(User user) throws Exception{
		userLogin(user.getUserName(),user.getUserPassword());
		this.user = user; 
	}
	
	private void setUserid(int userid) {
		this.user.setUserID(String.valueOf(userid));
	}
	
	private void setUserid(String userid) {
		this.user.setUserID(userid);
	}
	
	
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/master
	
<<<<<<< HEAD
	// login方法,返回0为密码不一样，返回1则为登陆成功，-1则为未找到用户名
=======
	// login閿熸枻鎷烽敓鏂ゆ嫹,閿熸枻鎷烽敓鏂ゆ嫹0涓洪敓鏂ゆ嫹閿熻涓嶄竴閿熸枻鎷烽敓鏂ゆ嫹1閿熸枻鎷蜂负閿熸枻鎷烽檰閿熺即鐧告嫹閿熸枻鎷�-1閿熸枻鎷蜂负鏈敓鎻鎷烽敓鐭紮鎷烽敓鏂ゆ嫹

>>>>>>> refs/remotes/origin/master
	public int userLogin(String username, String userpassword) throws SQLException {
			
		String s1 = "select * from cookbook.user where UserName = '"+ username +"'";
		res = this.sql.executeQuery(s1);
		if(res.first()){
			String s2 = "select * from cookbook.user where UserName = '" + username + "' and UserPassword = '" + userpassword+"'";
			
			res = this.sql.executeQuery(s2);
			res.next();
			String databasepassword = res.getString("UserPassword");
			System.out.println(databasepassword);
			if (userpassword.equals(databasepassword)) {
				System.out.println("Login in Successful");
				this.setUserid(res.getString("userid"));
				return 1;
			} else {
				return 0 ; 
			}
		}
		return -1; 
		//String s = "select * from cookbook.user where UserName = ? and UserPassword = ?";
		//PreparedStatement ps = (PreparedStatement) con.prepareStatement(s);
		//ps.setString(arg0, arg1);	
	}
	

<<<<<<< HEAD
	

	//新建账户方法,返回true为成功，返回false为有重复用户名
=======

	

	//閿熼摪鏂ゆ嫹閿熷壙浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹,閿熸枻鎷烽敓鏂ゆ嫹true涓洪敓缂寸櫢鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹false涓洪敓鏂ゆ嫹閿熸埅闈╂嫹閿熺煫浼欐嫹閿熸枻鎷�

>>>>>>> refs/remotes/origin/master
	public boolean userRegister(User user) throws SQLException {
		String sqlstr1 = "select * from `cookbook`.`user` where username = '"+user.getUserName()+"'";
		res = this.sql.executeQuery(sqlstr1);
		if(res.first()) {
			return false; 
		}
		String sqlstr2 = "insert into `cookbook`.`user` (`UserName`,`UserPassword`) values('" 
				+ user.getUserName() + "','"
				+ user.getUserPassword() + "')";
		System.out.println(sqlstr2);
		//int res1 = this.sql.executeUpdate(sqlstr2);
		PreparedStatement pstmt = this.con.prepareStatement(sqlstr2,Statement.RETURN_GENERATED_KEYS);
		int res1 = pstmt.executeUpdate();
		System.out.println(res1);
        ResultSet rs = pstmt.getGeneratedKeys();
				
<<<<<<< HEAD
		//获取自动增加的id号		        
=======
		//閿熸枻鎷峰彇閿熺殕璁规嫹閿熸枻鎷锋嫢閿熺禒d閿熸枻鎷�		        
>>>>>>> refs/remotes/origin/master
        String id = rs.getString(1);
        user.setUserID(id);            
        this.user = user;
		return true; 	
	}
	
	
	public boolean userRegister() throws SQLException {
		
		String sqlstr = "insert into `cookbook`.`user` (`UserName`,`UserPassword`) values('" 
				+ this.user.getUserName() + "',"
				+ this.user.getUserPassword() + ")";
		
		int res1 = this.sql.executeUpdate(sqlstr);
		System.out.println(res1);
		
		if (res1 > 0) {
			return true;
		} else {
			return false;
		}
	
	}
	

	/**
	 * get a complete recipe detail
	 * @param userid
	 * @param name
	 * 
	 */
	public Recipe getUserRecipe(String recipeid) {
	
		Recipe recipe = new Recipe();	
		
		try {
			
<<<<<<< HEAD
			// 提取recipe一般信息部分
			String ss1 = "select * from cookbook.recipe where id = '"+recipeid+"'";
=======

			// 閿熸枻鎷峰彇recipe涓�閿熸枻鎷烽敓鏂ゆ嫹鎭敓鏂ゆ嫹閿熸枻鎷�
			String ss1 = "select * from cookbook.recipe where id = '"+recipeid+"'";

>>>>>>> refs/remotes/origin/master
			res = this.sql.executeQuery(ss1);
			
			if (res.next()) {
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
			}

<<<<<<< HEAD
			
			// 提取对应ingredients部分
			String ss2 = "select * from cookbook.ingredients where RecipeID = " + recipeid;
=======

			// 閿熸枻鎷峰彇閿熸枻鎷峰簲ingredients閿熸枻鎷烽敓鏂ゆ嫹
			String recipeid1 = res.getString("ID");

			String ss2 = "select * from cookbook.ingredients where RecipeID = " + recipeid1;
>>>>>>> refs/remotes/origin/master
			res = sql.executeQuery(ss2);
			while (res.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setIngredientsID(res.getDouble("ID"));
				ingredient.setName(res.getString("Name"));
				ingredient.setAmount(res.getDouble("Amount"));
				ingredient.setUnit(res.getString("Unit"));
				recipe.addIngredient(ingredient);
			}
			

			// 閿熸枻鎷峰彇閿熸枻鎷峰簲preparationsteps閿熸枻鎷烽敓鏂ゆ嫹
			String ss3 = "select * from cookbook.preparationsteps where RecipeID = " + recipeid1;
			res = sql.executeQuery(ss3);
			while (res.next()) {
				PreparationStep preparationsteps = new PreparationStep();
				preparationsteps.setStepsID(res.getDouble("ID"));
				preparationsteps.setDescription(res.getString("Description"));
				preparationsteps.setOrder(res.getDouble("preparationstepsorder"));
				recipe.addPreparationStep(preparationsteps);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipe;
	}
	
<<<<<<< HEAD
	//未实现部分
=======
	//鏈疄閿熻璇ф嫹閿熸枻鎷�
>>>>>>> refs/remotes/origin/master
	/*
	public Recipe getRecipe(ResultSet res) throws Exception{
		Recipe recipe = new Recipe();
		if(res.next()) {
		recipe.setRecipeID(res.getInt("ID"));
		
			
		}
		return recipe;
	}
	
	public Recipe searchRecipe(String recipename) {
		Recipe recipe = new Recipe(); 
		
		return recipe; 
	}
	*/

	
	// 
	/*
	 * @description show all recipe list method
	 * 
	 */
	public LinkedList<Recipe> getallrecipelist() {		
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {

<<<<<<< HEAD
			// 显示出所有的recipe list		
			res = this.sql.executeQuery("select * from cookbook.recipe ");
=======

			// 閿熸枻鎷风ず閿熸枻鎷烽敓鏂ゆ嫹閿熷彨纰夋嫹recipe list		
			res = this.sql.executeQuery("select * from cookbook.recipe ");

>>>>>>> refs/remotes/origin/master
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);
			}

<<<<<<< HEAD
			// 显示出私有部分recipe
			/* 
			 * 此方法已不用实现
=======

			// 閿熸枻鎷风ず閿熸枻鎷风閿熷彨璇ф嫹閿熸枻鎷穜ecipe
			/* 
			 * 閿熷壙鍑ゆ嫹閿熸枻鎷烽敓绐栬鎷烽敓鏂ゆ嫹瀹為敓鏂ゆ嫹

>>>>>>> refs/remotes/origin/master
			String ss = "select * from `cookbook`.`user-recipe` where userid = " + userid;
			res = sql.executeQuery(ss);
			String s1;
			while (res.next()) {
				s1 = "select * from `cookbook`.`recipe` where recipeid = " + Integer.toString(res.getInt("recipeid"));
				res2 = sql.executeQuery(s1);
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res2.getInt("ID"));
				recipe.setName(res2.getString("Name"));
				recipe.setServeNumber(res2.getInt("serveNumber"));
				recipe.setPrepareTime(res2.getInt("PrepareTime"));
				recipe.setCookTime(res2.getInt("cookTime"));
				recipe.setCategory(res2.getString("Category"));
				recipe.setDescription(res2.getString("Description"));
				ls.add(recipe);
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

<<<<<<< HEAD
	
	// 用户输入菜名，搜索recipe 方法
	public LinkedList<Recipe> showsearchingrecipelist(String s) {
		
=======

	
	// 閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓绲〆cipe 閿熸枻鎷烽敓鏂ゆ嫹
	public LinkedList<Recipe> showsearchingrecipelist(String s) {
		


>>>>>>> refs/remotes/origin/master
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {			
			String ss = "select * from cookbook.recipe where name is like '%" + s + "%'";
			res = this.sql.executeQuery(ss);
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getString("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	// create recipe method
	
	
<<<<<<< HEAD
	//recipe表 插入方法	
	public Recipe insertrecipe(Recipe recipe) {//此recipe无recipeid
=======
	//recipe閿熸枻鎷� 閿熸枻鎷烽敓璇柟閿熸枻鎷�	
	public Recipe insertrecipe(Recipe recipe) {//閿熸枻鎷穜ecipe閿熸枻鎷穜ecipeid
>>>>>>> refs/remotes/origin/master
		String recipeid = "null";
		int res1 = 0;
		String ss1 = "INSERT INTO cookbook.recipe (Name, ServeNumber, Privacy, PrepareTime, Category, Description, CookTime) values(\'"
				+ recipe.getName() + "\','" 
				+ recipe.getServeNumber() + "\','" 
				+ recipe.getPrivacy() + "\','"
				+ recipe.getPrepareTime() + "\',\'" 
				+ recipe.getCategory() + "\',\'" 
				+ recipe.getDescription() + "\',\'"
				+ recipe.getCookTime()+"')";
		System.out.println(ss1);
		PreparedStatement pstmt;
		try {
<<<<<<< HEAD
			pstmt = this.con.prepareStatement(ss1,Statement.RETURN_GENERATED_KEYS);
			res1 = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
	        recipeid = rs.getString(1);
		} catch (SQLException e) {
=======

			pstmt = this.con.prepareStatement(ss1,Statement.RETURN_GENERATED_KEYS);
			res1 = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
	        recipeid = rs.getString(1);
		} catch (SQLException e) {

>>>>>>> refs/remotes/origin/master
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		recipe.setRecipeID(recipeid);
		return recipe;
	}
	
	
<<<<<<< HEAD
	//ingredients表插入方法
=======
	//ingredients閿熸枻鎷烽敓鏂ゆ嫹鏁曟枻鎷烽敓锟�
>>>>>>> refs/remotes/origin/master
	public void insertingredients(Recipe recipe) throws SQLException {
		int res1 = 0;
		LinkedList<Ingredient> ls1 = recipe.getIngredientlist();
		for (int x = 0; x < ls1.size(); x++) {
			ls1.get(x);
			String sqlstr2 = "INSERT INTO `cookbook`.`ingredient` (`Name`, `RecipeID`, `Amount`, `Unit`, `Description`) "
					+ "values('" 
					+ ls1.get(x).getName() + "','" 
					+ recipe.getRecipeID() + "','" 
					+ ls1.get(x).getAmount() + "','"
					+ ls1.get(x).getUnit() + "','" 
					+ ls1.get(x).getDescription() + "')";
			System.out.println(sqlstr2);
			res1 = sql.executeUpdate(sqlstr2);
		}
		
	}
	
	
<<<<<<< HEAD
	//preparationsteps表插入方法
	public void insertpreparationsteps(Recipe recipe) throws SQLException {
		int res1 = 0;
		LinkedList<PreparationStep> ls2 = recipe.getPreparationSteps();
		for (int y = 0; y < ls2.size(); y++) {
			ls2.get(y);
			String sqlstr3 = "INSERT INTO `cookbook`.`preparationstep` (`Description`,`preparationstepsorder`,`RecipeID`)"
					+ "values('" 
					+ ls2.get(y).getDescription() + "','" 
					+ ls2.get(y).getOrder() + "','"
					+ recipe.getRecipeID()+ "')";
			System.out.println(sqlstr3);
			res1 = sql.executeUpdate(sqlstr3);
		}
		
	}
	
	
	//recipe-user表插入方法
	public void insertrecipeuser(Recipe recipe) throws SQLException{
		int res1 = 0;
		String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values('" 
				+ Integer.parseInt(this.user.getUserID()) + "','" 
				+ recipe.getRecipeID()+ "')";
		res1 = sql.executeUpdate(ss4);
		
	}
	
	//总：recipe插入方法	
	public boolean insertRecipe(Recipe recipe) throws SQLException {
		Recipe newrecipe = new Recipe();
		newrecipe = insertrecipe(recipe);
		insertingredients(newrecipe);
		insertpreparationsteps(newrecipe);
		insertrecipeuser(newrecipe);
		return true;
	}
	
	
	
	//recipe表删除方法
	public void deleterecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}		
	//ingredients表删除方法
	public void deleteingredients(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`ingredients` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}	
	//preparationsteps表删除方法
	public void deletepreparationsteps(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`preparationsteps` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}
	//recipe-user表判断方法，返回true为用户私有项（可删改），false为公共项（不可删改）
=======
	//preparationsteps閿熸枻鎷烽敓鏂ゆ嫹鏁曟枻鎷烽敓锟�
	public void insertpreparationsteps(Recipe recipe) throws SQLException {
		int res1 = 0;
		LinkedList<PreparationStep> ls2 = recipe.getPreparationSteps();
		for (int y = 0; y < ls2.size(); y++) {
			ls2.get(y);
			String sqlstr3 = "INSERT INTO `cookbook`.`preparationstep` (`Description`,`preparationstepsorder`,`RecipeID`)"
					+ "values('" 
					+ ls2.get(y).getDescription() + "','" 
					+ ls2.get(y).getOrder() + "','"
					+ recipe.getRecipeID()+ "')";
			System.out.println(sqlstr3);
			res1 = sql.executeUpdate(sqlstr3);
		}
		
	}
	
	
	//recipe-user閿熸枻鎷烽敓鏂ゆ嫹鏁曟枻鎷烽敓锟�
	public void insertrecipeuser(Recipe recipe) throws SQLException{
		int res1 = 0;
		String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values('" 
				+ Integer.parseInt(this.user.getUserID()) + "','" 
				+ recipe.getRecipeID()+ "')";
		res1 = sql.executeUpdate(ss4);
		
	}
	
	//閿熸澃锝忔嫹recipe閿熸枻鎷烽敓璇柟閿熸枻鎷�	
	public boolean insertRecipe(int i, Recipe recipe) throws SQLException {
		Recipe newrecipe = new Recipe();
		newrecipe = insertrecipe(recipe);
		insertingredients(newrecipe);
		insertpreparationsteps(newrecipe);
		insertrecipeuser(newrecipe);
		return true;
	}
	
	
	
	//recipe閿熸枻鎷峰垹閿熸枻鎷�
	public void deleterecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();

		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}		
	//ingredients閿熸枻鎷峰垹閿熸枻鎷�
	public void deleteingredients(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`ingredients` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}	
	//preparationsteps閿熸枻鎷峰垹閿熸枻鎷�
	public void deletepreparationsteps(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`preparationsteps` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}
	//recipe-user閿熸枻鎷烽敓鍙柇鍑ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹true涓洪敓鐭紮鎷风閿熸枻鎷烽敓绛嬶紙閿熸枻鎷峰垹閿熶茎锝忔嫹閿熸枻鎷穎alse涓洪敓鏂ゆ嫹閿熸枻鎷烽敓绛嬶紙閿熸枻鎷烽敓鏂ゆ嫹鍒犻敓渚ワ綇鎷�
>>>>>>> refs/remotes/origin/master
	public boolean judgerecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss1 = "select * from `cookbook`.`user-recipe` where userid = '"+this.user.getUserID()+"' and recipeid = '"+recipeid+"'";
		res = this.sql.executeQuery(ss1);
		if(res.first()) {
			return true;
		}
		return false;
	}
<<<<<<< HEAD
	//recipe-user表删除方法
=======
	//recipe-user閿熸枻鎷峰垹閿熸枻鎷�
>>>>>>> refs/remotes/origin/master
	public void deleterecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss2 = "delete from `cookbook`.`user-recipe` "
				+ "where recipeid = '" + recipeid + "' and userid = '"+this.user.getUserID();
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss2);
				
	}
		
	//总：recipe删除方法,返回true成功删除，false为不可删除
	public boolean deleteRecipe(Recipe recipe) throws SQLException {
		if(judgerecipeuser(recipe)) {
			deleterecipe(recipe);
			deleteingredients(recipe);
			deletepreparationsteps(recipe);
			return true;
		}else {
			return false;
		}	
	}
	
	
	
	//recipe修改方法（ingredients和preparationsteps部分先删再插）,true为删除成功，false为不可删
	public boolean editRecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		if(judgerecipeuser(recipe)) {
			String ss2 = "update `cookbook`.`recipe` "
					+ "set `name` = '" + recipe.getName() + 
					"', `servenumber` = '"+ recipe.getServeNumber() + 
					"', `preparetime` = '" + recipe.getPrepareTime() + 
					"', `category` = '" + recipe.getCategory() + 
					"', `description` = '" + recipe.getDescription() + 
					"', `cooktime` = '" + recipe.getCookTime() + 
					"' where (`id` = " + recipe.getRecipeID() + ")";
			int res1 = this.sql.executeUpdate(ss2);
			deleteingredients(recipe);
			deletepreparationsteps(recipe);
			insertingredients(recipe);
			insertpreparationsteps(recipe);
			return true;
		}	
		else {
			return false;
		}
	}
	

		
	//閿熸澃锝忔嫹recipe鍒犻敓鏂ゆ嫹,閿熸枻鎷烽敓鏂ゆ嫹true閿熺即鐧告嫹鍒犻敓鏂ゆ嫹false涓洪敓鏂ゆ嫹閿熸枻鎷峰垹閿熸枻鎷�
	public boolean deleteRecipe(Recipe recipe) throws SQLException {
		if(judgerecipeuser(recipe)) {
			deleterecipe(recipe);
			deleteingredients(recipe);
			deletepreparationsteps(recipe);
			return true;
		}else {
			return false;
		}	
	}
	
	
	
	//recipe閿熺潾鏀瑰嚖鎷烽敓鏂ゆ嫹閿熸枻鎷穒ngredients閿熸枻鎷穚reparationsteps閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷峰垹閿熷姭鎻掞級,true涓哄垹閿熸枻鎷锋檼閿熸枻鎷烽敓绲漚lse涓洪敓鏂ゆ嫹閿熸枻鎷峰垹
	public boolean editRecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		if(judgerecipeuser(recipe)) {
			String ss2 = "update `cookbook`.`recipe` "
					+ "set `name` = '" + recipe.getName() + 
					"', `servenumber` = '"+ recipe.getServeNumber() + 
					"', `preparetime` = '" + recipe.getPrepareTime() + 
					"', `category` = '" + recipe.getCategory() + 
					"', `description` = '" + recipe.getDescription() + 
					"', `cooktime` = '" + recipe.getCookTime() + 
					"' where (`id` = " + recipe.getRecipeID() + ")";
			int res1 = this.sql.executeUpdate(ss2);
			deleteingredients(recipe);
			deletepreparationsteps(recipe);
			insertingredients(recipe);
			insertpreparationsteps(recipe);
			return true;
		}	
		else {
			return false;
		}
	}
	


	// UPDATE `cookbook`.`recipe` SET `Name` = 'qiezi', `ServeNumber` = '2',
	// `Category` = 'meat' WHERE (`ID` = '4');
<<<<<<< HEAD
	// 修改recipe方法，recipe1为想要修改的recipe，recipe2为修改之后的recipe
	
	
	// favourite表增加方法
	public void insertfavourite(Recipe recipe) throws SQLException {		
		String recipeid = recipe.getRecipeID();
=======
	// 閿熺潾闈╂嫹recipe閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷穜ecipe1涓洪敓鏂ゆ嫹瑕侀敓鐫敼纰夋嫹recipe閿熸枻鎷穜ecipe2涓洪敓鐫潻鎷蜂箣閿熸枻鎷烽敓绲〆cipe
	
	
	// favourite閿熸枻鎷烽敓鏂ゆ嫹鑷冮敓鏂ゆ嫹閿燂拷
	public void insertfavourite(Recipe recipe) throws SQLException {		
		String recipeid = recipe.getRecipeID();

>>>>>>> refs/remotes/origin/master
		int res1 = 0;
		String ss = "insert into `cookbook`.`favourite` (`recipeid`,`userid`) values('" 
				+ recipeid + "','" 
				+ this.user.getUserID() + "')";
		res1 = this.sql.executeUpdate(ss);		
	}
	
<<<<<<< HEAD
	//favourite表显示方法,true为喜欢，false为不喜欢
=======
	//favourite閿熸枻鎷烽敓鏂ゆ嫹绀洪敓鏂ゆ嫹閿熸枻鎷�,true涓哄枩閿熸枻鎷烽敓鏂ゆ嫹false涓洪敓鏂ゆ嫹鍠滈敓鏂ゆ嫹
>>>>>>> refs/remotes/origin/master
	public boolean judgefavourite(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "select * from `cookbook`.`favourite` where recipeid = '"+recipeid+"' and userid = "+this.user.getUserID();
		res = this.sql.executeQuery(ss);
		if(res.first()) {
			return true;
		}else {
			return false;
		}
	}
	

	// rate閿熸枻鎷穋omments閿熸枻鎷烽敓鏂ゆ嫹,true涓洪敓缂寸櫢鎷�
	/*
	public boolean addRateandComments(int userid, int recipeid, int rate, String comments) {
		
		
		int res1 = 0;
	
		try {
			String insertrateandcomments = "insert into `cookbook`.`rateandcomments (`recipeid`,`userid`,`rate`,`comments`) values(" 
					+ recipeid+ "," 
					+ userid + "," 
					+ rate + ",'" 
					+ comments + "')";
			res1 = sql.executeUpdate(insertrateandcomments);
			if (res1 >0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	
	}
	*/
	private void getbasicRecipe(Recipe recipe , ResultSet res) throws Exception{
		
		recipe.setRecipeID(res.getString("ID"));
		recipe.setName(res.getString("Name"));
		recipe.setServeNumber(res.getInt("serveNumber"));
		recipe.setPrepareTime(res.getInt("PrepareTime"));
		recipe.setCookTime(res.getInt("cookTime"));
		recipe.setCategory(res.getString("Category"));
		recipe.setDescription(res.getString("Description"));
		
	}
	
	
	/*
<<<<<<< HEAD
	 * 以下两个功能已经实现过
=======
	 * 閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷蜂斧閿熺粸纰夋嫹姝㈤敓锟�
>>>>>>> refs/remotes/origin/master
	 */
	private void saveIngredients(Ingredient ingredients , ResultSet res) throws Exception{
		
	}
	
	private void savePreparationSteps(PreparationStep preparationSteps, ResultSet res) throws Exception{
		
	}
	
	
	
	public String saveRecipeDescription(int recipeid)throws Exception {
       
        //int recipeid = recipe.getRecipeID();
        
        String ss = "select description from cookbook.recipe where recipeid = " + recipeid;
        res = sql.executeQuery(ss);
        return res.getString("description");
       
    }
    
	
    public boolean saveCommentandRate(Comment comment , int recipeid)throws Exception{
        int userid = Integer.parseInt(this.user.getUserID());
        String sqlstr = "insert into cookbook.rateandcomments (recipeid,userid,rate,comments) values('"+
        		recipeid+"','"+
        		this.user.getUserID()+"','"+
        		comment.getGrade()+"','"+
        		comment.getComment()+"')";
        
        PreparedStatement pstmt = this.con.prepareStatement(sqlstr,Statement.RETURN_GENERATED_KEYS);//閿熸枻鎷峰彇閿熺殕璁规嫹閿熸枻鎷烽敓鎺ョ鎷穒d閿熸枻鎷�
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();

        if(rs.next())

        {
        
         int enterInfoId = rs.getInt(1);
         comment.setCommentid(String.valueOf(enterInfoId));
         System.out.println(enterInfoId);
         return true;
        }
     
        return false;
         
    }
    
    
    
    public void close() throws Exception{
    	this.sql.close();
    	this.con.close();
    	
    }


}
