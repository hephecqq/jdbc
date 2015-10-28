package com.hephec.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.junit.Test;

public class DriverManagerTest {
	static Connection conn;
	
	public static void main(String[] args) throws Exception {
		conn=getConnection();
		System.out.println(conn);
	}
	
	public static Connection getConnection() throws Exception{
		//1.准备连接数据库的四个字符串
		//1创建Properties对象
		Properties prop=new Properties();
		InputStream in=DriverManagerTest.class.getClassLoader().getResourceAsStream("info.properties");
		prop.load(in);
		String password=prop.getProperty("password");
		//System.out.println(password);
		String url=prop.getProperty("jdbcUrl");
		//System.out.println(url);
		String driver=prop.getProperty("driver");
		//System.out.println(driver);
		String user=prop.getProperty("user");
		//System.out.println(user);
		Class.forName(driver);
		String encoding="useUnicode=true&characterEncoding=UTF-8";
		conn=DriverManager.getConnection(url+"?"+encoding,user,password);
		System.out.println(conn);
		return conn;
	
		
	}
	/**
	 * DriverManager是驱动的管理类
	 * 
	 * 1.可以通过重载的getConnection()方法获取数据库连接
	 * 2.可以同时管理多个驱动程序
	 * 
	 * 
	 * */
	@Test
	public void testDriverManager() throws IOException, Exception{
		String driverClass="com.mysql.jdbc.Driver";
		String jdbcUrl="jdbc:mysql:///test";
		String user="root";
		String password="1230";
		
		//读取类路径下的info.properties文件
//		InputStream in=
//				getClass().getClassLoader().getResourceAsStream("info.properties");
//		Properties info=new Properties();
//		info.load(in);
//		
//		driverClass=info.getProperty("driver");
//		
//		jdbcUrl=info.getProperty("jdbcUrl");
//		
//		user=info.getProperty("user");
//		
//		password=info.getProperty("password");
		Class.forName(driverClass);
		
		Connection conn=DriverManager.getConnection(jdbcUrl,user,password);
		
		System.out.println(conn);
		
	}
}
