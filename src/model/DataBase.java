package model;
import java.sql.*;
public class DataBase {
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "yang";
    static final String PASS = "yang";
    
    public static Connection connect(){
    	Connection conn = null;	 
        try {
        	// 注册 JDBC 驱动
			Class.forName("com.mysql.jdbc.Driver");
			 // 打开链接
	        System.out.println("连接数据库...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
        return conn;
    }
    
    public int compareIdPassword(String id, String password)
    {    	
    	int temp = 3;
    	Connection conn = DataBase.connect();
    	String sql = "select password from user where id='"+id+"'";
    	try {
    		
			Statement stat = conn.createStatement();
			ResultSet result = stat.executeQuery(sql);
			/*if(result.next()==false)
				temp = 1;//无此id
			else */if(result.next()==true&&result.getString(1).substring(0,password.length()).equals(password))
				temp = 0;//密码正确
			else{
				temp = 2;//密码错误
				System.out.println(result.getString(1));
			}
			
			result.close();
			stat.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return temp;
    }
}
