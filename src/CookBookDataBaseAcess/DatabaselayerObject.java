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

	final private String Databasepassword = "root";
	final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT";
	//final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306";
	//final private String Databaseurl = "jdbc:mysql://localhost:3306/recipedatabase?useSSL=true&serverTimezone=GMT";

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
	
	

	
	// login锟斤拷锟斤拷,锟斤拷锟斤拷0为锟斤拷锟诫不一锟斤拷锟斤拷1锟斤拷为锟斤拷陆锟缴癸拷锟斤拷-1锟斤拷为未锟揭碉拷锟矫伙拷锟斤拷

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
	


	

	//锟铰斤拷锟剿伙拷锟斤拷锟斤拷,锟斤拷锟斤拷true为锟缴癸拷锟斤拷锟斤拷锟斤拷false为锟斤拷锟截革拷锟矫伙拷锟斤拷

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
				
		//锟斤拷取锟皆讹拷锟斤拷拥锟絠d锟斤拷		        
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
			

			// 锟斤拷取recipe一锟斤拷锟斤拷息锟斤拷锟斤拷
			String ss1 = "select * from cookbook.recipe where id = '"+recipeid+"'";

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


			// 锟斤拷取锟斤拷应ingredients锟斤拷锟斤拷
			String recipeid1 = res.getString("ID");

			String ss2 = "select * from cookbook.ingredients where RecipeID = " + recipeid1;
			res = sql.executeQuery(ss2);
			while (res.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setIngredientsID(res.getDouble("ID"));
				ingredient.setName(res.getString("Name"));
				ingredient.setAmount(res.getDouble("Amount"));
				ingredient.setUnit(res.getString("Unit"));
				recipe.addIngredient(ingredient);
			}
			

			// 锟斤拷取锟斤拷应preparationsteps锟斤拷锟斤拷
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
	
	//未实锟街诧拷锟斤拷
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


			// 锟斤拷示锟斤拷锟斤拷锟叫碉拷recipe list		
			res = this.sql.executeQuery("select * from cookbook.recipe ");

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


			// 锟斤拷示锟斤拷私锟叫诧拷锟斤拷recipe
			/* 
			 * 锟剿凤拷锟斤拷锟窖诧拷锟斤拷实锟斤拷

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


	
	// 锟矫伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟絩ecipe 锟斤拷锟斤拷
	public LinkedList<Recipe> showsearchingrecipelist(String s) {
		


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
	
	
	//recipe锟斤拷 锟斤拷锟诫方锟斤拷	
	public Recipe insertrecipe(Recipe recipe) {//锟斤拷recipe锟斤拷recipeid
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

			pstmt = this.con.prepareStatement(ss1,Statement.RETURN_GENERATED_KEYS);
			res1 = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
	        recipeid = rs.getString(1);
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		recipe.setRecipeID(recipeid);
		return recipe;
	}
	
	
	//ingredients锟斤拷锟斤拷敕斤拷锟�
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
	
	
	//preparationsteps锟斤拷锟斤拷敕斤拷锟�
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
	
	
	//recipe-user锟斤拷锟斤拷敕斤拷锟�
	public void insertrecipeuser(Recipe recipe) throws SQLException{
		int res1 = 0;
		String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values('" 
				+ Integer.parseInt(this.user.getUserID()) + "','" 
				+ recipe.getRecipeID()+ "')";
		res1 = sql.executeUpdate(ss4);
		
	}
	
	//锟杰ｏ拷recipe锟斤拷锟诫方锟斤拷	
	public boolean insertRecipe(int i, Recipe recipe) throws SQLException {
		Recipe newrecipe = new Recipe();
		newrecipe = insertrecipe(recipe);
		insertingredients(newrecipe);
		insertpreparationsteps(newrecipe);
		insertrecipeuser(newrecipe);
		return true;
	}
	
	
	
	//recipe锟斤拷删锟斤拷
	public void deleterecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();

		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}		
	//ingredients锟斤拷删锟斤拷
	public void deleteingredients(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`ingredients` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}	
	//preparationsteps锟斤拷删锟斤拷
	public void deletepreparationsteps(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`preparationsteps` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}
	//recipe-user锟斤拷锟叫断凤拷锟斤拷锟斤拷锟斤拷锟斤拷true为锟矫伙拷私锟斤拷锟筋（锟斤拷删锟侥ｏ拷锟斤拷false为锟斤拷锟斤拷锟筋（锟斤拷锟斤拷删锟侥ｏ拷
	public boolean judgerecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss1 = "select * from `cookbook`.`user-recipe` where userid = '"+this.user.getUserID()+"' and recipeid = '"+recipeid+"'";
		res = this.sql.executeQuery(ss1);
		if(res.first()) {
			return true;
		}
		return false;
	}
	//recipe-user锟斤拷删锟斤拷
	public void deleterecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss2 = "delete from `cookbook`.`user-recipe` "
				+ "where recipeid = '" + recipeid + "' and userid = '"+this.user.getUserID();
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss2);
				
	}

		
	//锟杰ｏ拷recipe删锟斤拷,锟斤拷锟斤拷true锟缴癸拷删锟斤拷false为锟斤拷锟斤拷删锟斤拷
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
	
	
	
	//recipe锟睫改凤拷锟斤拷锟斤拷ingredients锟斤拷preparationsteps锟斤拷锟斤拷锟斤拷删锟劫插）,true为删锟斤拷晒锟斤拷锟絝alse为锟斤拷锟斤拷删
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
	// 锟睫革拷recipe锟斤拷锟斤拷锟斤拷recipe1为锟斤拷要锟睫改碉拷recipe锟斤拷recipe2为锟睫革拷之锟斤拷锟絩ecipe
	
	
	// favourite锟斤拷锟斤拷臃锟斤拷锟�
	public void insertfavourite(Recipe recipe) throws SQLException {		
		String recipeid = recipe.getRecipeID();

		int res1 = 0;
		String ss = "insert into `cookbook`.`favourite` (`recipeid`,`userid`) values('" 
				+ recipeid + "','" 
				+ this.user.getUserID() + "')";
		res1 = this.sql.executeUpdate(ss);		
	}
	
	//favourite锟斤拷锟斤拷示锟斤拷锟斤拷,true为喜锟斤拷锟斤拷false为锟斤拷喜锟斤拷
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
	

	// rate锟斤拷comments锟斤拷锟斤拷,true为锟缴癸拷
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
	 * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷丫锟绞碉拷止锟�
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
        
        PreparedStatement pstmt = this.con.prepareStatement(sqlstr,Statement.RETURN_GENERATED_KEYS);//锟斤拷取锟皆讹拷锟斤拷锟接碉拷id锟斤拷
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
