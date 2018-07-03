package CookBookDataBaseAcess;

import static org.junit.Assert.assertNotNull;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import com.mysql.cj.exceptions.RSAException;
import com.mysql.cj.jdbc.ha.SequentialBalanceStrategy;
import com.mysql.cj.xdevapi.Result;
import com.sun.glass.ui.TouchInputSupport;

import CookBookEntity.Comment;
import CookBookEntity.Ingredient;
import CookBookEntity.PreparationStep;
import CookBookEntity.Recipe;

import CookBookEntity.User;
import javafx.scene.shape.Line;
public class DatabaselayerObject {

	private Connection con;
	private Statement sql;
	static ResultSet res, res2;
	final private String driver = "com.mysql.jdbc.Driver";
	final private String Databaseuser = "root";
	//final private String driver = "com.mysql.cj.jdbc.Driver";

	//final private String Databasepassword = "heyining";//¿ÉÐÞ¸ÄÏîÄ¿
	final private String Databasepassword = "1989";//¿ÉÐÞ¸ÄÏîÄ¿
	//final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT&autoReconnect=true&failOverReadOnly=false";//¿ÉÐÞ¸ÄÏîÄ¿
	final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306";//¿ÉÐÞ¸ÄÏîÄ¿
	

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
	
	private Connection getConnection() throws Exception {
		Class.forName(this.driver);
		Connection connection = DriverManager.getConnection(this.Databaseurl, this.Databaseuser, this.Databasepassword);
		return connection;
	}
	
	/**
	 * @Description 
	 * @param user
	 * @throws Exception
	 */
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
	
	



	// login·½·¨,·µ»Ø0ÎªÃÜÂë²»Ò»Ñù£¬·µ»Ø1ÔòÎªµÇÂ½³É¹¦£¬-1ÔòÎªÎ´ÕÒµ½ÓÃ»§Ãû


	/**
	 * @Description Check whether the username exists in the datadbase first.If exists,check the password then.If username does not exist,return -1.If password is not correct,return 0.If login successfully,return 1. 
	 * @param username
	 * @param userpassword
	 * @return
	 * @throws SQLException
	 */
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
				System.out.println("Login in Successful"+res.getString("userid"));
				System.out.println("this is userid"+res.getString("userid"));
				this.user.setUserID(res.getString("userid"));
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
	


	


	//锟铰斤拷锟剿伙拷锟斤拷锟斤拷,锟斤拷锟斤拷true为锟缴癸拷锟斤拷锟斤拷锟斤拷false为锟斤拷锟截革拷锟矫伙拷锟斤�?


	/**
	 * @Description Insert the new username and password 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
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

				
		//锟斤拷取锟皆讹拷锟斤拷拥锟絠d锟斤�?		        

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
	 * @Description get a recipe created by a specific user through his userid and the recipe name.
	 * @param userid
	 * @param name
	 * 
	 */
	
	/*
	public Recipe getRecipe(String recipeid) {
	
		Recipe recipe = new Recipe();	
		
		try {
			


			// 锟斤拷取recipe�?锟斤拷锟斤拷息锟斤拷锟斤�?

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
			//String recipeid = res.getString("ID");

			String ss2 = "select * from cookbook.ingredients where RecipeID = " + recipeid;

			ResultSet res = sql.executeQuery(ss2);
			while (res.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setIngredientsID(res.getDouble("ID"));
				ingredient.setName(res.getString("Name"));
				ingredient.setAmount(res.getDouble("Amount"));
				ingredient.setUnit(res.getString("Unit"));
				recipe.addIngredient(ingredient);
			}
			

			// é”Ÿæ�?�¤æ�?�·å�–é�?�Ÿæ�?�¤æ�?�·åº�?�preparationstepsé”Ÿæ�?�¤æ�?�·é�?�Ÿæ�?�¤æ�?��?
			String ss3 = "select * from cookbook.preparationsteps where RecipeID = '" + recipeid+"'";

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
	
	*/


	

	
	/**
	 * @Description 
	 * @param recipename
	 * @return
	 */
	public Recipe searchRecipe(String recipename) {
		Recipe recipe = new Recipe(); 
		
		return recipe; 
	}

	// show all recipe list method
	/*
	 * @description show all recipe list method
	 * 
	 */
	/**
	 * @Description 
	 * @param userid
	 * @param name
	 * @return
	 */
	public LinkedList<Recipe> getallrecipelist() {		
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {
			Connection connection = this.getConnection(); 
			Statement state = connection.createStatement(); 
			ResultSet res = state.executeQuery("select ID from cookbook.recipe ");
			
			while (res.next()) {
				
				Recipe recipe = this.getRecipe(res.getString(1),res);
				
				ls.add(recipe);
				
				System.out.println(recipe.getName());
			}

			
		connection.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}





	public LinkedList<Recipe> showsearchingrecipelist(String s) {
		

		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {			
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			String ss = "select * from cookbook.recipe where name like '%" + s + "%'";
			ResultSet res = statement.executeQuery(ss);
			while (res.next()) {
				Recipe recipe = this.getRecipe(res.getString("ID"),res);
				ls.add(recipe);
				System.out.println(recipe.toString());
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	// create recipe method
	
	

	public boolean insertrecipe(Recipe recipe) throws Exception {//锟斤拷recipe锟斤拷recipeid

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
			System.out.print(rs.next());
	        recipeid = rs.getString(1);
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		recipe.setRecipeID(recipeid);
		insertingredients(recipe);
		insertpreparationsteps(recipe);
		
		if(this.user.isExist()) insertrecipeuser(recipe);
		
		
		return true;
	}
	


	private void insertingredients(Recipe recipe) throws Exception {
		int res1 = 0;
		this.con = this.getConnection();
		this.sql = this.con.createStatement();
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
	
	

	//preparationsteps锟斤拷锟斤拷敕斤拷锟�?


	private void insertpreparationsteps(Recipe recipe) throws Exception {
		int res1 = 0;
		this.con = this.getConnection();
		this.sql = this.con.createStatement(); 
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
	
	

	//recipe-user锟斤拷锟斤拷敕斤拷锟�?

	private void insertrecipeuser(Recipe recipe) throws SQLException{
		int res1 = 0;
		System.out.println("This is recipe id "+recipe.getRecipeID());
		String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values(" 
				+ Integer.parseInt(this.user.getUserID())+ "," 
				+ Integer.parseInt(recipe.getRecipeID())+ ")";
				
		res1 = sql.executeUpdate(ss4);
		
	}
	


	private  void deleterecipe(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();

		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}		

	//ingredients锟斤拷删锟斤�?

	private void deleteingredients(Recipe recipe) throws Exception {
		String recipeid = recipe.getRecipeID();
		this.con = this.getConnection();
		this.sql = this.con.createStatement();
		String ss = "delete from `cookbook`.`ingredient` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}	

	//preparationsteps锟斤拷删锟斤�?

	private void deletepreparationsteps(Recipe recipe) throws Exception {
		String recipeid = recipe.getRecipeID();
		this.con =this.getConnection();
		this.sql = this.con.createStatement();
		String ss = "delete from `cookbook`.`preparationstep` "
				+ "where recipeid = '" + recipeid + "'";
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss);
		
	}

	//recipe-user锟斤拷锟叫断凤拷锟斤拷锟斤拷锟斤拷锟斤拷true为锟矫伙拷私锟斤拷锟筋（锟斤拷删锟侥ｏ拷锟斤拷false为锟斤拷锟斤拷锟筋（锟斤拷锟斤拷删锟侥ｏ�?

	private boolean judgerecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss1 = "select * from `cookbook`.`user-recipe` where userid = '"+this.user.getUserID()+"' and recipeid = '"+recipeid+"'";
		res = this.sql.executeQuery(ss1);
		if(res.first()) {
			return true;
		}
		return false;
	}

	//recipe-user锟斤拷删锟斤�?

	public void deleterecipeuser(Recipe recipe) throws SQLException {
		String recipeid = recipe.getRecipeID();
		String ss2 = "delete from `cookbook`.`user-recipe` "
				+ "where recipeid = '" + recipeid + "' and userid = '"+this.user.getUserID();
		int res1 = 0;
		res1 = this.sql.executeUpdate(ss2);
				
	}
		
	

		

	//锟杰ｏ拷recipe删锟斤拷,锟斤拷锟斤拷true锟缴癸拷删锟斤拷false为锟斤拷锟斤拷删锟斤�?

	public boolean deleteRecipe(Recipe recipe) throws Exception {
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

	public boolean editRecipe(Recipe recipe) throws Exception {
			this.con = this.getConnection();
			this.sql = this.con.createStatement();
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
	


	// UPDATE `cookbook`.`recipe` SET `Name` = 'qiezi', `ServeNumber` = '2',
	// `Category` = 'meat' WHERE (`ID` = '4');

	// 锟睫革拷recipe锟斤拷锟斤拷锟斤拷recipe1为锟斤拷要锟睫改碉拷recipe锟斤拷recipe2为锟睫革拷之锟斤拷锟絩ecipe
	
	
	// favourite锟斤拷锟斤拷臃锟斤拷锟�

	public void insertfavourite(Recipe recipe) throws Exception {		
		String recipeid = recipe.getRecipeID();
		this.con = this.getConnection(); 
		this.sql = this.con.createStatement(); 

		int res1 = 0;
		String ss = "insert into `cookbook`.`favourite` (`recipeid`,`userid`) values('" 
				+ recipeid + "','" 
				+ this.user.getUserID() + "')";
		res1 = this.sql.executeUpdate(ss);		
	}
	

	//favourite锟斤拷锟斤拷示锟斤拷锟斤�?,true为喜锟斤拷锟斤拷false为锟斤拷喜锟斤拷

	public boolean judgefavourite(String recipeid) throws Exception {
		
		String ss = "select * from `cookbook`.`favourite` where recipeid = '"+recipeid+"' and userid = "+this.user.getUserID();
		this.con = this.getConnection();
		this.sql = this.con.createStatement();
		this.res = this.sql.executeQuery(ss);
		boolean result = res.first(); 
		this.con.close();
		if(result) {
			return true;
		}
			return false;
		
		
	}
	


	// rate锟斤拷comments锟斤拷锟斤拷,true为锟缴癸�?

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
	
	





	
	
	
	public String saveRecipeDescription(String recipeid)throws Exception {
       
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
        

        PreparedStatement pstmt = this.con.prepareStatement(sqlstr,Statement.RETURN_GENERATED_KEYS);//锟斤拷取锟皆讹拷锟斤拷锟接碉拷id锟斤�?

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
    
    //筛�?�肉和素方法,meat为肉，vegetarian为素
    public LinkedList<Recipe> choosecategory(String description) throws SQLException{
    	String  ss = "select * from `cookbook`.`recipe` where `Description` = '"+description+"'";
    	LinkedList<Recipe> ls = new LinkedList<Recipe>();
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
    	return ls;
    	
    }
    
    public LinkedList<String> getRecipeid(String name) throws Exception{
    	String sqlstr = "select recipeid from recipe where name= '"+name+"'";
    	LinkedList<String> recipeidlist = new LinkedList<String>();
    	this.res = this.sql.executeQuery(sqlstr);
    	while (res.next()) {
    		recipeidlist.add(res.getString(1));
    		
    	}
    	return recipeidlist;
    }
    
    public void setUser(User user) {
    	this.user = user; 
    }
    
    public User getUser() {
    	return this.user;
    }
    
    public boolean getRecipeComment(LinkedList<Comment> comments ,String recipeid) throws Exception{
    	String sqlstr = "select * from cookbook.rateandcomments"
    			+ " where recipeid= "+recipeid;
    	Connection connection = this.getConnection();
    	this.sql = connection.createStatement();
    	ResultSet res = this.sql.executeQuery(sqlstr);
        comments = new LinkedList<Comment>();
    	while(res.next()) {
    	Comment comment = new Comment(res.getInt("rate"), res.getString("comments"));
        comment.setUserid(res.getString("userid"));
        comment.setCommentid(res.getString("ID"));
        comments.add(comment);
    	}
    	connection.close();
    	if(comments.isEmpty()) return false; 
    	return true;
    }
    
    public LinkedList<Recipe> getUserallRecipe(String userid) throws Exception{
    	String sqlstr ="select * from `cookbook`.`user-recipe` , `cookbook`.`recipe` "+
    	" where `cookbook`.`user-recipe`.`userid` = "+userid+ 
    	" and `cookbook`.`user-recipe`.`recipeid`=`cookbook`.`recipe`.`id` ";
    	Connection connection = this.getConnection(); 
    	Statement statement = connection.createStatement();
    	ResultSet resultSet  = statement.executeQuery(sqlstr);
    	LinkedList<Recipe> recipelist = new LinkedList<Recipe>();
    	while(resultSet.next()) {
    		
    		Recipe recipe = this.getRecipe(resultSet.getString("ID"),resultSet);
    		recipelist.add(recipe);
    	}
    	connection.close();    	
    	return recipelist ; 
    	
    	
    }
    
    public LinkedList<Recipe> getfavouriterecipelist(String userid) throws Exception {
    	LinkedList<Recipe> recipelist = new LinkedList<Recipe>();
    	Connection connection = this.getConnection() ;
    	Statement statement= connection.createStatement(); 
    	String sqlstr = "select * from cookbook.favourite, cookbook.recipe "+
    	"where cookbook.recipe.id = cookbook.favourite.recipeid and cookbook.favourite.userid="+userid; 
    	
    	ResultSet resultSet = statement.executeQuery(sqlstr) ; 
    	while(resultSet.next()) {
    		Recipe recipe = this.getRecipe(resultSet.getString("recipeid"), resultSet); 
    		recipelist.add(recipe);    	
    	}
    	
    	connection.close();
    	return recipelist; 
    }
    
    private Recipe getRecipe(String recipeid,ResultSet res) throws Exception {
    	Recipe recipe = new Recipe();
    	Connection connection = this.getConnection();
    	Statement statement = connection.createStatement(); 
    	res = statement.executeQuery("select * from cookbook.recipe where id="+recipeid);
    	res.next();
    	recipe.setRecipeID(res.getString("ID"));
		recipe.setName(res.getString("Name"));
		recipe.setServeNumber(res.getInt("serveNumber"));
		recipe.setPrepareTime(res.getInt("PrepareTime"));
		recipe.setCookTime(res.getInt("cookTime"));
		recipe.setCategory(res.getString("Category"));
		recipe.setDescription(res.getString("Description"));
		recipe.setIngredientlist(this.getIngredient(recipeid));
		recipe.setPreparationSteps(this.getPreparationSteps(recipeid));
		connection.close();
		return recipe; 
    }
    
    private LinkedList<Ingredient> getIngredient(String recipeid) throws Exception{
    	LinkedList<Ingredient> ingredientlist = new LinkedList<Ingredient>();
    	String ss2 = "select * from cookbook.ingredient where RecipeID = " + recipeid;
    	Connection connection = this.getConnection(); 
    	Statement statement = connection.createStatement(); 
		ResultSet res = statement.executeQuery(ss2);
		while (res.next()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setIngredientsID(res.getDouble("ID"));
			ingredient.setName(res.getString("Name"));
			ingredient.setAmount(res.getDouble("Amount"));
			ingredient.setUnit(res.getString("Unit"));
			ingredientlist.add(ingredient);
		}
		connection.close();
    	return ingredientlist ; 
    }
    
    private LinkedList<PreparationStep> getPreparationSteps(String recipeid) throws Exception{
    	LinkedList<PreparationStep> preparationSteps= new LinkedList<PreparationStep>();
    	String sqlstr = "select * from cookbook.preparationstep where RecipeID = '" + recipeid+"'";
    	this.con = this.getConnection();
    	this.sql = this.con.createStatement();
		ResultSet result = sql.executeQuery(sqlstr);
		while (result.next()) {
			PreparationStep preparationstep = new PreparationStep();
			
			preparationstep.setStepsID(result.getDouble("ID"));
			preparationstep.setDescription(result.getString("Description"));
			preparationstep.setOrder(result.getDouble("preparationstepsorder"));
			preparationSteps.add(preparationstep);
		}
		this.con.close();
		
    	return preparationSteps;
    }

    public boolean setfavourite(String userid , String recipeid) throws Exception {
    	Connection connection = this.getConnection() ;
    	this.sql = connection.createStatement(); 
    	String firststr = "select * from cookbook.favourite where userid= "+userid + " and recipeid="+recipeid;
    	ResultSet res= this.sql.executeQuery(firststr);
    	if(!res.first()) {
    	String sqlstr = "insert into cookbook.favourite(userid,recipeid) values ("+
    	userid+","+recipeid+ ")";
    	int result = this.sql.executeUpdate(sqlstr);
    	return true;
    	}
    	this.sql.close();
    	connection.close();
    	if(res.first()) return true; 
    	return false; 
    	
    }
    
    public LinkedList<Recipe> getRankedlist(String userid) throws Exception{
    	Connection connection = this.getConnection(); 
    	Statement statement = connection.createStatement() ;
    	LinkedList<Recipe> recipelist = new LinkedList<Recipe>();
    	String sql = "select * from cookbook.rateandcomments "+
    	"where userid = "+ userid+" "; 
    	System.out.println("database get userid "+userid);
    	//select * from rateandcomments where userid = 1
    	System.out.println(sql);
    	ResultSet resultSet =statement.executeQuery(sql);
    	while(resultSet.next()) {
    		System.out.println("This is comments and rate");
    		Recipe recipe = new Recipe() ;
    		//System.out.println(res.findColumn("comments"));
    	//	System.out.println(resultSetgetInt(1));
    		//System.out.println(res.getString(2));
    		//System.out.println(res.getString(3));
    		recipe.setComments(resultSet.getString("comments"));
    		recipe.setRate(resultSet.getInt("rate"));
    		recipe.setRecipeID(resultSet.getString("recipeid"));
    		this.getRecipe(recipe.getRecipeID(), resultSet);
    		recipelist.add(recipe);
    	}
    	connection.close();
    	return recipelist;
    }
    
    
    public void setRecipecr(Recipe recipe,String userid) throws Exception {
    	Connection connection = this.getConnection(); 
    	String recipeid = recipe.getRecipeID(); 
    	Statement statement  = connection.createStatement(); 
    	String sql = "select * from cookbook.rateandcomments " + 
    	" where recipeid = " +recipeid+" and userid = "+userid;
    	ResultSet resultSet = statement.executeQuery(sql);
    	while(resultSet.next()) {
    		recipe.setRate(resultSet.getInt("rate"));
    		recipe.setComments(resultSet.getString("comments"));
    	}
    	statement.close();
    	connection.close();
    }
    
    public boolean isReciperanked(String recipeid) throws Exception{
    	Connection connection  = this.getConnection() ; 
    	Statement statement = connection.createStatement(); 
    	String sql = " select * from cookbook.rateandcomments " + 
    	    	" where recipei = " +recipeid;
    	int result = statement.executeUpdate(sql);
    	if(result>0) return true; 
    	return false;
    	
    }
    
  public void setUserID(User user) throws Exception{
	  Connection connection = this.getConnection();
	  Statement statement = connection.createStatement() ;
	  String sql = "select * from cookbook.user where UserName = '" + user.getUserName()+"'";
	  ResultSet resultSet = statement.executeQuery(sql);
	  if(resultSet.first()) {
		  System.out.println("User exists");
		  while(resultSet.next()) {
			  user.setUserID(resultSet.getString("userid"));
		  }
	  }else {
		  System.out.println("User does not exist");
			
		}
	  }
  
  public Comment getComment(String recipeid, String userid) throws Exception{
	  Comment comment = new Comment() ; 
	  Connection connection = this.getConnection(); 
	  Statement statement = connection.createStatement(); 
	  String sql = "select * from cookbook.rateandcomments where userid="+userid+" and recipeid="+recipeid;
	  ResultSet resultSet = statement.executeQuery(sql);
	  while(resultSet.next()) {
		  comment.setComment(resultSet.getString("comments"));
		  comment.setCommentid(resultSet.getString("id"));
		  comment.setGrade(resultSet.getInt("rate"));
		  
	  }
	  connection.close();
	  return comment; 
  }
  
  public boolean deleteComment(String recipeid) throws Exception{
	  String userid = this.user.getUserID(); 
	  Connection connection = this.getConnection(); 
	  Statement statement = connection.createStatement(); 
	  String sql = "delete from cookbook.rateandcomments where userid="+userid+" and recipeid="+recipeid;
	  int result = statement.executeUpdate(sql);
	  if(result>0) return true; 
	  else return false; 
	
  }
  
  public boolean setRate(String userid, String recipeid ,int grade) throws Exception{
	  this.con = this.getConnection(); 
	  this.sql = this.con.createStatement(); 
	  String sql1 = "select * from cookbook.rateandcomments where userid=" + userid + " and recipeid= "+recipeid;
	  int result = this.sql.executeUpdate(sql1);
	  int finalresult ; 
	  if(result>0) {
		  String sql2 = "update cookbook.rateandcomments set rate =" +grade +"where userid = " +userid +" and recipeid = "+recipeid; 
		  finalresult = this.sql.executeUpdate(sql2);
		 
	  }else {
		  String sql2 = "insert into rateandcomments(recipeid, userid,rate) values("+recipeid+","+userid+","+grade+")";
		  finalresult = this.sql.executeUpdate(sql2);
	  }
	  if(finalresult>0) return true; 
	  else return false;
	  
  }
  
  public boolean deleteFavourite(String recipeid , String userid) throws Exception {
	  this.con= this.getConnection(); 
	  this.sql = this.con.createStatement(); 
	  String first = "set SQL_SAFE_UPDATES=0";
	  this.sql.executeUpdate(first);
	  String sqlstr = "delete from cookbook.favourite where recipeid = "+ recipeid +" and  userid = "+userid;
	  int res= this.sql.executeUpdate(sqlstr);
	  if(res>0) return true;
	  return false; 
  }
 }

