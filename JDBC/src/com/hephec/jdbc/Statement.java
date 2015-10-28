package com.hephec.jdbc;

import java.sql.Connection;



import java.sql.SQLException;

import org.junit.Test;

public class Statement {
	
	static Connection conn=null;
	static java.sql.Statement stmt=null;
	
	@Test
	public void test(){
		try {
			conn=DriverManagerTest.getConnection();

			//1.获取数据库连接
			//2.准备插入的sql语句
		String sql="insert into customer (name,email,birth) values('张三','zhangsan@sina.com','1992-10-19')";
		String d_sql="delete from customer where name='何鹏'";
		String u_sql="update customer set name='李四' where id='1'";
		
			//3.执行插入
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			int count_d=stmt.executeUpdate(d_sql);
			int count_u=stmt.executeUpdate(u_sql);
			
			System.out.println(count);
			System.out.println(count_d);
			System.out.println(count_u);
			
			//4.获取操作SQL语句的Statement对象
			//1.Statement用于执行sql语句的对象
			//2.通过Connection的createStatement方法来获取
			//3.通过executeUpdate(sql)可以执行sql语句
			//4.传入的SQL可以是insert,update,delete但不能是select语句
			
			//5.调用Statement对象的executeUpdate(sql)执行sql进行插入
			//6.关闭Statement对象
			
			//7.关闭连接
			//8.conn和statement是都是应用程序和数据库服务器的连接资源，使用后必须关闭
			//int i=10/0;
			//需要在finally中关闭资源
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTool.release(null,stmt, conn);
		}
	
	
		
	}
	
	//版本1.0
	public static int  update(String sql){
		int count=0;
		try {
			conn=DriverManagerTest.getConnection();
			stmt=conn.createStatement();
			count=stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return count;
			
	}
}
