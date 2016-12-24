package service.dataservice.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection getConn(){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/system?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull";
		Connection conn = null;
		try{
//			File file = new File("D:\\connect.txt");
//			InputStreamReader read=new InputStreamReader(new FileInputStream(file));
//			BufferedReader bufferedRead=new BufferedReader(read);
			String username="root";
			String password="123456";
//			read.close();
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
