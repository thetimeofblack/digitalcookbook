package CookBookDataBaseAcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;



import CookBookEntity.Ingredient;
import CookBookEntity.PreparationSteps;
import CookBookEntity.Recipe;

import CookBookEntity.User;
public class DatabaseAccessObject {

	private Connection con;
	private Statement sql;
	static ResultSet res, res2;
	final private String driver = "com.mysql.jdbc.Driver";
	final private String Databaseuser = "root";
	final private String Databasepassword = "heyining";
	//final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT";
	final private String Databaseurl = "jdbc:mysql://127.0.0.1:3306";
	private User user; 
	public DatabaseAccessObject(){
		try {
		Class.forName(driver);
		System.out.println("the driver for database has been initialized");
		this.con = DriverManager.getConnection(this.Databaseurl, this.Databaseuser, this.Databasepassword);
		System.out.println("database access sucessful!");
		this.sql = con.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UserLogin(User user) throws Exception{
		login(user.getUserName(),user.getUserPassword());
		this.user = user; 
	}
	
	public Connection getConnection() {
	
		try {
			Class.forName("com.mysql.jdbc.Driver");// ʹ��forName��������jdbc��������
			System.out.println("the driver for database has been initialized");
			// ʹ��Drivemanager��getConnection�ķ����õ����ݿ����ӣ�������������ָ��·�����û���������
			this.con = DriverManager.getConnection("jdbc:mysql:" + "//127.0.0.1:3306/?characterEncoding=utf8&useSSL=true&serverTimezone=GMT", "root", "258000");
			System.out.println("database access sucessful!");
			this.sql = this.con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.con;
	}
	
	// login����,����0Ϊ���벻һ������Ϊ0��Ϊuserid��-1��Ϊδ�ҵ��û���
	public int login(String username, String userpassword) throws SQLException {
		int i = 1;
	
		String s1 = "select * from cookbook.user where UserName = '"+ username +"'";
		res = this.sql.executeQuery(s1);
		if(res.first()){
			String s2 = "select * from cookbook.user where UserName = '" + username + "' and UserPassword = '" + userpassword+"'";
			sql = con.createStatement();
			res = sql.executeQuery(s2);
			if (res.first()) {
				i = res.getInt("userid");
			} else {
				i = 0;
			}
		}else{
				i = -1;
		}
		//String s = "select * from cookbook.user where UserName = ? and UserPassword = ?";
		//PreparedStatement ps = (PreparedStatement) con.prepareStatement(s);
		//ps.setString(arg0, arg1);
		return i;
	}

	// INSERT INTO `cookbook`.`user` (`UserName`, `UserPassword`) VALUES
	// ('Xiyuan', '12345'); ɵ�Ʒ��Ų�����
	// create account ����,�ѳɹ����
	public boolean UserRegister(User user) throws SQLException {
		
		String sqlstr = "insert into `cookbook`.`user` (`UserName`,`UserPassword`) values('" 
				+ user.getUserName() + "','"
				+ user.getUserPassword() + "')";
		System.out.println(sqlstr);
		int res1 = this.sql.executeUpdate(sqlstr);
		System.out.println(res1);
		
		if (res1 > 0) {
			return true; 
		} else {
			return false; 
		}
		
	}
	
	
	public boolean UserRegister() throws SQLException {
		
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
	 * get a complete recipe
	 * @param userid
	 * @param name
	 * 
	 */
	public Recipe getUserRecipe(int userid, String name) {
	
		Recipe recipe = new Recipe();
		LinkedList<Ingredient> ls1 = new LinkedList<Ingredient>();
		LinkedList<PreparationSteps> ls2 = new LinkedList<PreparationSteps>();
		try {
			
			// ��ȡrecipeһ����Ϣ����
			String ss1 = "select * from cookbook.recipe where name = '" + name + "'";
			res = this.sql.executeQuery(ss1);
			if (res.next()) {
				recipe.setRecipeID(res.getInt("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
			}

			// ��ȡ��Ӧingredients����
			String recipeid = res.getString("ID");
			String ss2 = "select * from cookbook.ingredients where RecipeID = " + recipeid;
			res = sql.executeQuery(ss2);
			while (res.next()) {
				Ingredient ingredients = new Ingredient();
				ingredients.setIngredientsID(res.getDouble("ID"));
				ingredients.setName(res.getString("Name"));
				ingredients.setAmount(res.getDouble("Amount"));
				ingredients.setUnit(res.getString("Unit"));
				ls1.add(ingredients);
			}
			recipe.setIngredientlist(ls1);

			// ��ȡ��Ӧpreparationsteps����
			String ss3 = "select * from cookbook.preparationsteps where RecipeID = " + recipeid;
			res = sql.executeQuery(ss3);
			while (res.next()) {
				PreparationSteps preparationsteps = new PreparationSteps();
				preparationsteps.setStepsID(res.getDouble("ID"));
				preparationsteps.setDescription(res.getString("Description"));
				preparationsteps.setOrder(res.getDouble("preparationstepsorder"));
				ls2.add(preparationsteps);
			}
			recipe.setPreparationSteps(ls2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recipe;
	}
	
	
	public Recipe getRecipe(ResultSet res) throws Exception{
		Recipe recipe = new Recipe();
		if(res.next()) {
		recipe.setRecipeID(res.getInt("ID"));
		
			
		}
		return recipe;
	}

	// show all recipe list method
	/*
	 * @description
	 * 
	 */
	public LinkedList<Recipe> getPublicRecipe(int userid, String name) {
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {

			// ��ʾ�����й�������recipe
			sql = con.createStatement();
			res = sql.executeQuery("select * from cookbook.recipe where `privacy` = 0");
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getInt("ID"));
				recipe.setName(res.getString("Name"));
				recipe.setServeNumber(res.getInt("serveNumber"));
				recipe.setPrepareTime(res.getInt("PrepareTime"));
				recipe.setCookTime(res.getInt("cookTime"));
				recipe.setCategory(res.getString("Category"));
				recipe.setDescription(res.getString("Description"));
				ls.add(recipe);
			}

			// ��ʾ��˽�в���recipe
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	// show searching recipe list ����.�Ҳ�û��ʵ��A�û�����Ĳ�������B�û��Լ�create�Ĳ���ͬ���������
	public LinkedList<Recipe> showsearchingrecipelist(int userid, String s) {
		con = this.getConnection();
		LinkedList<Recipe> ls = new LinkedList<Recipe>();
		try {
			sql = con.createStatement();
			String ss = "select * from cookbook.recipe where name is like '%" + s + "%'";
			res = sql.executeQuery(ss);
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeID(res.getInt("ID"));
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
	
	public boolean insertRecipe(int userid, Recipe recipe) {
		
		
		boolean j = false;
		int successflag = 0, res1 = 0;
	
		try {

			// ���뵽recipe����
			String ss1 = "INSERT INTO cookbook.recipe (Name, ServeNumber, Privacy, PrepareTime, Category, Description, CookTime) values(\'"
					+ recipe.getName() + "\'," 
					+ recipe.getServeNumber() + "," 
					+ recipe.getPrivacy() + ","
					+ recipe.getPrepareTime() + ",\'" 
					+ recipe.getCategory() + "\',\'" 
					+ recipe.getDescription() + "\',"
					+ recipe.getCookTime()+")";
			System.out.println(ss1);
			res1 = this.sql.executeUpdate(ss1);
			if (res1 >= 1) {
				successflag = successflag++;
			}

			// ����recipeid
			String ss = "select * from `cookbook`.`recipe` where Name = '" + recipe.getName() + "'";
			System.out.println(ss);
		
			res = sql.executeQuery(ss);
			
			res.next();
			
			int recipeid = res.getInt("ID");
			System.out.println("insert recipe successfully");
			
			// ���뵽Ingredients����
			LinkedList<Ingredient> ls1 = recipe.getIngredientlist();
			// Iterator iter1 = ls1.iterator();
			for (int x = 0; x < ls1.size(); x++) {
				ls1.get(x);
				String sqlstr2 = "INSERT INTO `cookbook`.`ingredient` (`Name`, `RecipeID`, `Amount`, `Unit`, `Description`) "
						+ "values('" 
						+ ls1.get(x).getName() + "','" 
						+ recipeid + "','" 
						+ ls1.get(x).getAmount() + "','"
						+ ls1.get(x).getUnit() + "','" 
						+ ls1.get(x).getDescription() + "')";
				System.out.println(sqlstr2);
				res1 = sql.executeUpdate(sqlstr2);
			}
			
			if (res1 >= 1) {
				successflag = successflag++;
			}

			// ���뵽preparationsteps����
			LinkedList<PreparationSteps> ls2 = recipe.getPreparationSteps();
			for (int y = 0; y < ls2.size(); y++) {
				ls2.get(y);
				String sqlstr3 = "INSERT INTO `cookbook`.`preparationstep` (`Description`,`RecipeID`)"
						+ "values('" 
						+ ls2.get(y).getDescription() + "'," 
					
						+ recipeid+ ")";
				System.out.println(sqlstr3);
				res1 = sql.executeUpdate(sqlstr3);
			}
			if (res1 >= 1) {
				successflag = successflag++;
			}

			// ���뵽recipe-user����
			String ss4 = "INSERT INTO `cookbook`.`UserRecipe` (`userid`,`recipeid`) values(" 
			+ userid + "," 
			+ recipeid+ ")";
			res1 = sql.executeUpdate(ss4);
			if (res1 >= 1) {
				successflag = successflag++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (successflag >= 3) {
			return true;
		}
		return false;
	}

	// ɾ��recipe����������trueΪ�ɹ�������falseΪʧ��
	public boolean deleteRecipe(Recipe recipe) {
		con = this.getConnection();
		boolean i = false;
		int recipeid = recipe.getRecipeID();
		String ss = "delete from `cookbook`.`recipe` "
				+ "where id = '" + recipeid + "' and privacy = 1";
		try {
			sql = con.createStatement();
			int res1 = sql.executeUpdate(ss);
			if (res1 == 1) {
				i = true;
			} else {
				i = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	// UPDATE `cookbook`.`recipe` SET `Name` = 'qiezi', `ServeNumber` = '2',
	// `Category` = 'meat' WHERE (`ID` = '4');
	// �޸�recipe������recipe1Ϊ��Ҫ�޸ĵ�recipe��recipe2Ϊ�޸�֮���recipe
	public boolean editRecipe(int userid, Recipe recipe1, Recipe recipe2) {
		con = this.getConnection();
		boolean i = false;
		int recipe1id = recipe1.getRecipeID();
		int recipe2id = recipe2.getRecipeID();
		try {
			String ss1 = "update `cookbook`.`recipe` "
					+ "set `name` = '" + recipe2.getName() + 
					"', `servenumber` = '"+ recipe2.getServeNumber() + 
					"', `preparetime` = '" + recipe2.getPrepareTime() + 
					"', `category` = '" + recipe2.getCategory() + 
					"', `description` = '" + recipe2.getDescription() + 
					"', `cooktime` = '" + recipe2.getCookTime() + 
					"' where (`id` = " + recipe1id + ")";
			res = sql.executeQuery(ss1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// ��Ϊfavourite recipe����,trueΪ�ɹ�
	public boolean favouriteRecipe(int userid, Recipe recipe) {
		con = this.getConnection();
		int recipeid = recipe.getRecipeID();
		int res1 = 0;
		boolean i = false;
		try {
			String ss = "insert into `cookbook`.`favourite` (`recipeid`,`userid`) values('" 
		+ recipeid + "','" 
		+ userid + "')";
			res1 = sql.executeUpdate(ss);
			if (res1 >= 1) {
				i = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	// rate��comments����,trueΪ�ɹ�
	public boolean addRateandComments(int userid, Recipe recipe, int rate, String comments) {
		con = this.getConnection();
		int recipeid = recipe.getRecipeID();
		int res1 = 0;
		boolean i = false;
		try {
			String ss = "insert into `cookbook`.`rate (`recipeid`,`userid`,`rate`,`comments`) values('" 
					+ recipeid+ "','" 
					+ userid + "','" 
					+ rate + "','" 
					+ comments + "')";
			res1 = sql.executeUpdate(ss);
			if (res1 >= 1) {
				i = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	private void getbasicRecipe(Recipe recipe , ResultSet res) throws Exception{
		
		recipe.setRecipeID(res.getInt("ID"));
		recipe.setName(res.getString("Name"));
		recipe.setServeNumber(res.getInt("serveNumber"));
		recipe.setPrepareTime(res.getInt("PrepareTime"));
		recipe.setCookTime(res.getInt("cookTime"));
		recipe.setCategory(res.getString("Category"));
		recipe.setDescription(res.getString("Description"));
		
	}
	
	private void saveIngredients(Ingredient ingredients , ResultSet res) throws Exception{
		
	}
	
	private void savePreparationSteps(PreparationSteps preparationSteps, ResultSet res) throws Exception{
		
	}
	
	public String saveRecipeDescription(int recipeid)throws Exception {
        con = this.getConnection();
        //int recipeid = recipe.getRecipeID();
        
        String ss = "select description from cookbook.recipe where recipeid = " + recipeid;
        res = sql.executeQuery(ss);
        return res.getString("description");
       
    }
    
    public String saveRecipeRate(int recipeid,int userid)throws Exception{
        this.con = this.getConnection();
        String ss = "insert into values()";
        res = sql.executeQuery(ss);
        return res.getString("rate");
    }
    
    
    
    public void close() throws Exception{
    	this.sql.close();
    	this.con.close();
    	
    }


}
