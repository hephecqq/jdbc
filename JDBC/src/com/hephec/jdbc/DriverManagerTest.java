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
		//1.׼���������ݿ���ĸ��ַ���
		//1����Properties����
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
	 * DriverManager�������Ĺ�����
	 * 
	 * 1.����ͨ�����ص�getConnection()������ȡ���ݿ�����
	 * 2.����ͬʱ��������������
	 * 
	 * 
	 * */
	@Test
	public void testDriverManager() throws IOException, Exception{
		String driverClass="com.mysql.jdbc.Driver";
		String jdbcUrl="jdbc:mysql:///test";
		String user="root";
		String password="1230";
		
		//��ȡ��·���µ�info.properties�ļ�
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
