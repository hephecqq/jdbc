package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 * ResultSet：封装了使用JDBC进行查询的结果
 * 1.调用Statement对象的executeQuery(sql)可以得到结果集
 * 
 * 2.如何处理结果集？
	 * ResultSet返回的实际上就是一张数据表，有一个指针指向数据表的第一页的前面
	 * 调用next()方法检测下一行是否有效,若有效该方法返回true,且指针下移
	 * Iterator对象相当于hasNext()和next()方法的结合体
	 * 3.如何获取每一行每一列的值？
	 * 当指针定位到每一行时，可以通过调用
	 * getXxx(index)或getXxx(columnName)获取每一列的值
	 * 例如：getInt(1).getString("name")
	 * ResultSet需要关闭
 * 
 * 
 * */

public class ResultSetDemo {
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	@Test
	public void test(){
		try{
			conn=JDBCTool.getConnection();
			stmt=conn.createStatement();
			String sql="select id,name,email,birth"
					+ " from customer where id>10";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString("name");
				String email=rs.getString(3);
				Date birth=rs.getDate(4);
				System.out.println(id);
				System.out.println(name);
				System.out.println(email);
				System.out.println(birth);
				System.out.println("-----------");
			}
					
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTool.release(rs, stmt, conn);
		}
	}
	//获取id=10的customer数据表的记录，并打印
	//1.获取Connection
	//2.获取Statement
	//3.准备SQL
	//4.执行查询，得到ResultSet
	//5.处理ResultSet
	//6.关闭ResultSet
	
}
