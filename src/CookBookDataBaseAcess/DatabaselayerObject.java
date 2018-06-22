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
	//final private String Databasepassword = "heyining";//¿ÉĞŞ¸ÄÏîÄ¿
	final private String Databasepassword = "258000";//¿ÉĞŞ¸ÄÏîÄ¿
	//final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT";//¿ÉĞŞ¸ÄÏîÄ¿
	final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306";//¿ÉĞŞ¸ÄÏîÄ¿
	
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
	// login·½·¨,·µ»Ø0ÎªÃÜÂë²»Ò»Ñù£¬·µ»Ø1ÔòÎªµÇÂ½³É¹¦£¬-1ÔòÎªÎ´ÕÒµ½ÓÃ»§Ãû
=======
	// loginé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·,é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·0ä¸ºé”Ÿæ–¤æ‹·é”Ÿè¯«ä¸ä¸€é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·1é”Ÿæ–¤æ‹·ä¸ºé”Ÿæ–¤æ‹·é™†é”Ÿç¼´ç™¸æ‹·é”Ÿæ–¤æ‹·-1é”Ÿæ–¤æ‹·ä¸ºæœªé”Ÿæ­ç¢‰æ‹·é”ŸçŸ«ä¼™æ‹·é”Ÿæ–¤æ‹·

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
	

	//ĞÂ½¨ÕË»§·½·¨,·µ»ØtrueÎª³É¹¦£¬·µ»ØfalseÎªÓĞÖØ¸´ÓÃ»§Ãû
=======

	

	//é”Ÿé“°æ–¤æ‹·é”Ÿå‰¿ä¼™æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·,é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·trueä¸ºé”Ÿç¼´ç™¸æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·falseä¸ºé”Ÿæ–¤æ‹·é”Ÿæˆªé©æ‹·é”ŸçŸ«ä¼™æ‹·é”Ÿæ–¤æ‹·

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
		//»ñÈ¡×Ô¶¯Ôö¼ÓµÄidºÅ		        
=======
		//é”Ÿæ–¤æ‹·å–é”Ÿçš†è®¹æ‹·é”Ÿæ–¤æ‹·æ‹¥é”Ÿçµ dé”Ÿæ–¤æ‹·		        
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
			// ÌáÈ¡recipeÒ»°ãĞÅÏ¢²¿·Ö
			String ss1 = "select * from cookbook.recipe where id = '"+recipeid+"'";
=======

			// é”Ÿæ–¤æ‹·å–recipeä¸€é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·æ¯é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·
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
			
			// ÌáÈ¡¶ÔÓ¦ingredients²¿·Ö
			String ss2 = "select * from cookbook.ingredients where RecipeID = " + recipeid;
=======

			// é”Ÿæ–¤æ‹·å–é”Ÿæ–¤æ‹·åº”ingredientsé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·
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
			

			// é”Ÿæ–¤æ‹·å–é”Ÿæ–¤æ‹·åº”preparationstepsé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·
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
	//Î´ÊµÏÖ²¿·Ö
=======
	//æœªå®é”Ÿè¡—è¯§æ‹·é”Ÿæ–¤æ‹·
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
			// ÏÔÊ¾³öËùÓĞµÄrecipe list		
			res = this.sql.executeQuery("select * from cookbook.recipe ");
=======

			// é”Ÿæ–¤æ‹·ç¤ºé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿå«ç¢‰æ‹·recipe list		
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
			// ÏÔÊ¾³öË½ÓĞ²¿·Örecipe
			/* 
			 * ´Ë·½·¨ÒÑ²»ÓÃÊµÏÖ
=======

			// é”Ÿæ–¤æ‹·ç¤ºé”Ÿæ–¤æ‹·ç§é”Ÿå«è¯§æ‹·é”Ÿæ–¤æ‹·recipe
			/* 
			 * é”Ÿå‰¿å‡¤æ‹·é”Ÿæ–¤æ‹·é”Ÿçª–è¯§æ‹·é”Ÿæ–¤æ‹·å®é”Ÿæ–¤æ‹·

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
	
	// ÓÃ»§ÊäÈë²ËÃû£¬ËÑË÷recipe ·½·¨
	public LinkedList<Recipe> showsearchingrecipelist(String s) {
		
=======

	
	// é”ŸçŸ«ä¼™æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿçµ©ecipe é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·
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
	//recipe±í ²åÈë·½·¨	
	public Recipe insertrecipe(Recipe recipe) {//´ËrecipeÎŞrecipeid
=======
	//recipeé”Ÿæ–¤æ‹· é”Ÿæ–¤æ‹·é”Ÿè¯«æ–¹é”Ÿæ–¤æ‹·	
	public Recipe insertrecipe(Recipe recipe) {//é”Ÿæ–¤æ‹·recipeé”Ÿæ–¤æ‹·recipeid
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
	//ingredients±í²åÈë·½·¨
=======
	//ingredientsé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·æ••æ–¤æ‹·é”Ÿï¿½
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
	//preparationsteps±í²åÈë·½·¨
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
	
	
	//recipe-user±í²åÈë·½·¨
	public void insertrecipeuser(Recipe recipe) throws SQLException{
		int res1 = 0;
		String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values('" 
				+ Integer.parseInt(this.user.getUserID()) + "','" 
				+ recipe.getRecipeID()+ "')";
		res1 = sql.executeUpdate(ss4);
		
	}
	
	//×Ü£ºrecipe²åÈë·½·¨	
	public boolean insertRecipe(Recipe recipe) throws SQLException {
		Recipe newrecipe = new Recipe();
		newrecipe = insertrecipe(recipe);
		insertingredients(newrecipe);
		insertpreparationsteps(newrecipe);
		insertrecipeuser(newrecipe);
		return true;
	}
	
	
	
	//recipe±íÉ¾³ı·½·¨
	public void deleterecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}		
	//ingredients±íÉ¾³ı·½·¨
	public void deleteingredients(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`ingredients` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}	
	//preparationsteps±íÉ¾³ı·½·¨
	public void deletepreparationsteps(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`preparationsteps` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}
	//recipe-user±íÅĞ¶Ï·½·¨£¬·µ»ØtrueÎªÓÃ»§Ë½ÓĞÏî£¨¿ÉÉ¾¸Ä£©£¬falseÎª¹«¹²Ïî£¨²»¿ÉÉ¾¸Ä£©
=======
	//preparationstepsé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·æ••æ–¤æ‹·é”Ÿï¿½
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
	
	
	//recipe-useré”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·æ••æ–¤æ‹·é”Ÿï¿½
	public void insertrecipeuser(Recipe recipe) throws SQLException{
		int res1 = 0;
		String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values('" 
				+ Integer.parseInt(this.user.getUserID()) + "','" 
				+ recipe.getRecipeID()+ "')";
		res1 = sql.executeUpdate(ss4);
		
	}
	
	//é”Ÿæ°ï½æ‹·recipeé”Ÿæ–¤æ‹·é”Ÿè¯«æ–¹é”Ÿæ–¤æ‹·	
	public boolean insertRecipe(int i, Recipe recipe) throws SQLException {
		Recipe newrecipe = new Recipe();
		newrecipe = insertrecipe(recipe);
		insertingredients(newrecipe);
		insertpreparationsteps(newrecipe);
		insertrecipeuser(newrecipe);
		return true;
	}
	
	
	
	//recipeé”Ÿæ–¤æ‹·åˆ é”Ÿæ–¤æ‹·
	public void deleterecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();

		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}		
	//ingredientsé”Ÿæ–¤æ‹·åˆ é”Ÿæ–¤æ‹·
	public void deleteingredients(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`ingredients` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}	
	//preparationstepsé”Ÿæ–¤æ‹·åˆ é”Ÿæ–¤æ‹·
	public void deletepreparationsteps(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`preparationsteps` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}
	//recipe-useré”Ÿæ–¤æ‹·é”Ÿå«æ–­å‡¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·trueä¸ºé”ŸçŸ«ä¼™æ‹·ç§é”Ÿæ–¤æ‹·é”Ÿç­‹ï¼ˆé”Ÿæ–¤æ‹·åˆ é”Ÿä¾¥ï½æ‹·é”Ÿæ–¤æ‹·falseä¸ºé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿç­‹ï¼ˆé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·åˆ é”Ÿä¾¥ï½æ‹·
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
	//recipe-user±íÉ¾³ı·½·¨
=======
	//recipe-useré”Ÿæ–¤æ‹·åˆ é”Ÿæ–¤æ‹·
>>>>>>> refs/remotes/origin/master
	public void deleterecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss2 = "delete from `cookbook`.`user-recipe` "
				+ "where recipeid = '" + recipeid + "' and userid = '"+this.user.getUserID();
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss2);
				
	}
		
	//×Ü£ºrecipeÉ¾³ı·½·¨,·µ»Øtrue³É¹¦É¾³ı£¬falseÎª²»¿ÉÉ¾³ı
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
	
	
	
	//recipeĞŞ¸Ä·½·¨£¨ingredientsºÍpreparationsteps²¿·ÖÏÈÉ¾ÔÙ²å£©,trueÎªÉ¾³ı³É¹¦£¬falseÎª²»¿ÉÉ¾
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
	

		
	//é”Ÿæ°ï½æ‹·recipeåˆ é”Ÿæ–¤æ‹·,é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·trueé”Ÿç¼´ç™¸æ‹·åˆ é”Ÿæ–¤æ‹·falseä¸ºé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·åˆ é”Ÿæ–¤æ‹·
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
	
	
	
	//recipeé”Ÿç«æ”¹å‡¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·ingredientsé”Ÿæ–¤æ‹·preparationstepsé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·åˆ é”ŸåŠ«æ’ï¼‰,trueä¸ºåˆ é”Ÿæ–¤æ‹·æ™’é”Ÿæ–¤æ‹·é”Ÿçµalseä¸ºé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·åˆ 
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
	// ĞŞ¸Ärecipe·½·¨£¬recipe1ÎªÏëÒªĞŞ¸ÄµÄrecipe£¬recipe2ÎªĞŞ¸ÄÖ®ºóµÄrecipe
	
	
	// favourite±íÔö¼Ó·½·¨
	public void insertfavourite(Recipe recipe) throws SQLException {		
		String recipeid = recipe.getRecipeID();
=======
	// é”Ÿç«é©æ‹·recipeé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·recipe1ä¸ºé”Ÿæ–¤æ‹·è¦é”Ÿç«æ”¹ç¢‰æ‹·recipeé”Ÿæ–¤æ‹·recipe2ä¸ºé”Ÿç«é©æ‹·ä¹‹é”Ÿæ–¤æ‹·é”Ÿçµ©ecipe
	
	
	// favouriteé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·è‡ƒé”Ÿæ–¤æ‹·é”Ÿï¿½
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
	//favourite±íÏÔÊ¾·½·¨,trueÎªÏ²»¶£¬falseÎª²»Ï²»¶
=======
	//favouriteé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·ç¤ºé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·,trueä¸ºå–œé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·falseä¸ºé”Ÿæ–¤æ‹·å–œé”Ÿæ–¤æ‹·
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
	

	// rateé”Ÿæ–¤æ‹·commentsé”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·,trueä¸ºé”Ÿç¼´ç™¸æ‹·
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
	 * ÒÔÏÂÁ½¸ö¹¦ÄÜÒÑ¾­ÊµÏÖ¹ı
=======
	 * é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·é”Ÿæ–¤æ‹·ä¸«é”Ÿç»ç¢‰æ‹·æ­¢é”Ÿï¿½
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
        
        PreparedStatement pstmt = this.con.prepareStatement(sqlstr,Statement.RETURN_GENERATED_KEYS);//é”Ÿæ–¤æ‹·å–é”Ÿçš†è®¹æ‹·é”Ÿæ–¤æ‹·é”Ÿæ¥ç¢‰æ‹·idé”Ÿæ–¤æ‹·
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
