package service.dataservice.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection getConn(){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/system";
		String username = "root";
		String password = "Yyj980226";
		Connection conn = null;
		try{
			Class.forName(driver);//classLoader,加载对应驱动
			conn = (Connection)DriverManager.getConnection(url,username,password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
}
