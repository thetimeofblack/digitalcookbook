package DigitalCookbook;
import java.sql.Connection; 
import java.sql.DriverManager ; 
import java.sql.ResultSet; 
import java.sql.SQLException ; 
import java.sql.Statement ;


public class Database_test {
	public static void main(String[] args) throws Exception {
		Connection con ; 
		String driver = "com.mysql.cj.jdbc.Driver";  //driver信息
		String url = "jdbc:mysql://localhost:3306/recipedatabase?useSSL=true&serverTimezone=GMT";//根据网上的资料这里mysql密码如果不加ssl会警告，
		//建议加一下，同时时域问题也是这样
		String user = "root";//用户名一般本地账户都是root，你也可以自己在本地数据库端设置账户
		String password = "heyining";//密码
		try {
			Class.forName(driver);//首先都搜索到驱动
			con = DriverManager.getConnection(url, user, password);//根据驱动创建数据库连接
			if(!con.isClosed())
				System.out.println("Succeeded connecting to the Database!");
			Statement statement = con.createStatement();//获得数据库session
			String sql = "select * from recipe";//这里我自己建了一个表
			/*
			 * 、、、
			 */
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {     //注意rs.next()能够索取数据库查询出来的一行数据
				System.out.println(rs.getString("recipename"));//根据列名查询
			}
			System.out.println(rs.next());
			rs.close();
			con.close();
			//记得关闭连接，还有所有关于连接的语句都放在try中
		}catch(ClassNotFoundException e) {
			System.out.println("Sorry, can't find the Driver");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("DataBase Connection sucessful");
		}
	}

}
