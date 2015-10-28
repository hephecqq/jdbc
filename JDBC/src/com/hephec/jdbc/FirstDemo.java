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
	 * Driver是一个接口：是数据库厂商必须提供实现的接口，能从其中获取数据库连接
	 * 
	 * 1.加入mysql驱动
	 * 	1)解压
	 * 	2)加入类路径
	 *  	
	 * */
	@Test
	public void testDriver() throws SQLException{
	
		//1.创建一个Driver实现类的对象
		
		Driver driver=new com.mysql.jdbc.Driver();
		
		//2.准备连接数据库的基本信息,url,user,password;
		
		String url="jdbc:mysql://localhost:3306/test";
		
		//jdbc:子协议://ip地址:端口号/数据库名
		
		Properties info=new Properties();
		info.put("user", "root");
		info.put("password", "1230");
		
		//3.调用Driver接口的connect(url,info)获取数据库连接
		
		Connection conn=driver.connect(url, info);
		System.out.println(conn);
		//可以通过Driver的实现类对象获取数据库连接
		conn.close();
		
	}
	/**
	 * 编写一个通用的方法，在不修改源程序的情况下,可以获取任何数据库的连接
	 * 解决方案：
	 *	1.连接数据库连接的基本信息放在配置文件中(Driver实现类类的全类名,url,user,password)
	 *	2.通过修改配置文件的方式实现和具体的数据库解耦
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *
	 * */
	
	/**
	 * 该方法没有跟任何数据库耦合，通用的代码
	 * 
	 * */
	
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, Exception{
		String driverClass=null;
		String jdbcUrl=null;
		String user=null;
		String password=null;
		
		//读取类路径下的info.properties文件
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
