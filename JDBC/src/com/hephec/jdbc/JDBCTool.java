package com.hephec.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;

/**
 * 
 * 操作JDBC的工具类，其中封装了一些工具方法
 * 版本1
 * 1.获取数据库连接方法
 * 	读取配置文件从数据库服务器获取一个连接
 * 2.
 * 
 * 
 * */
public class JDBCTool {
	public static void main(String[] args) {
		Connection conn=getConnection();
		System.out.println(conn);
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			if(stmt!=null)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null)
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection conn = null;
		
		//1.准备连接数据库的四个字符串
				//1创建Properties对象
				Properties prop=new Properties();
				InputStream in=DriverManagerTest.class.getClassLoader().getResourceAsStream("info.properties");
				try {
					prop.load(in);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String password=prop.getProperty("password");
				String url=prop.getProperty("jdbcUrl");
				String driver=prop.getProperty("driver");
				String user=prop.getProperty("user");
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String encoding="useUnicode=true&characterEncoding=UTF-8";
				try {
					conn=DriverManager.getConnection(url+"?"+encoding,user,password);
				} catch (SQLException e) {
				}
				return conn;
	}
}
