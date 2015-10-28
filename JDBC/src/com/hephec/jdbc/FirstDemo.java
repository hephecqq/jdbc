package com.hephec.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

public class FirstDemo {
	
	public static void main(String[] args) throws Exception {
		Connection conn=new FirstDemo().getConnection();
		System.out.println(conn);
		
	}
	
	/**
	 * Driver��һ���ӿڣ������ݿ⳧�̱����ṩʵ�ֵĽӿڣ��ܴ����л�ȡ���ݿ�����
	 * 
	 * 1.����mysql����
	 * 	1)��ѹ
	 * 	2)������·��
	 *  	
	 * */
	@Test
	public void testDriver() throws SQLException{
	
		//1.����һ��Driverʵ����Ķ���
		
		Driver driver=new com.mysql.jdbc.Driver();
		
		//2.׼���������ݿ�Ļ�����Ϣ,url,user,password;
		
		String url="jdbc:mysql://localhost:3306/test";
		
		//jdbc:��Э��://ip��ַ:�˿ں�/���ݿ���
		
		Properties info=new Properties();
		info.put("user", "root");
		info.put("password", "1230");
		
		//3.����Driver�ӿڵ�connect(url,info)��ȡ���ݿ�����
		
		Connection conn=driver.connect(url, info);
		System.out.println(conn);
		//����ͨ��Driver��ʵ��������ȡ���ݿ�����
		conn.close();
		
	}
	/**
	 * ��дһ��ͨ�õķ������ڲ��޸�Դ����������,���Ի�ȡ�κ����ݿ������
	 * ���������
	 *	1.�������ݿ����ӵĻ�����Ϣ���������ļ���(Driverʵ�������ȫ����,url,user,password)
	 *	2.ͨ���޸������ļ��ķ�ʽʵ�ֺ;�������ݿ����
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *
	 * */
	
	/**
	 * �÷���û�и��κ����ݿ���ϣ�ͨ�õĴ���
	 * 
	 * */
	
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, Exception{
		String driverClass=null;
		String jdbcUrl=null;
		String user=null;
		String password=null;
		
		//��ȡ��·���µ�info.properties�ļ�
		InputStream in=
				getClass().getClassLoader().getResourceAsStream("info.properties");
		Properties info=new Properties();
		
		info.load(in);
		
		driverClass=info.getProperty("driver");
		jdbcUrl=info.getProperty("jdbcUrl");
//		user=info.getProperty("user");
//		password=info.getProperty("password");
		
		Driver driver=(Driver) Class.forName(driverClass).newInstance();
		
		//info.put("user",user);
		//info.put("password",password);
		
		Connection conn=driver.connect("jdbc:mysql://localhost:3306/test", info);
		System.out.println(conn);
		return conn;
	}
}
