package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class GetKeyFromDatabase {


	static Connection conn=null;
	static ResultSet rs=null;
	static PreparedStatement ps=null;
	//取得数据库自动生成的主键值
	@Test
	public void test(){

		try{
			conn=JDBCTool.getConnection();
			String sql="insert into customer(name,email,birth) values(?,?,?)";
			//ps=conn.prepareStatement(sql);
			ps=conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, "xx");
			ps.setString(2, "xx");
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			ps.executeUpdate();
			//通过PreparedStatement方法getGeneratedKeys();
			//获取的ResultSet对象,该对象中只有一列，用于存放阿胡建
			rs=ps.getGeneratedKeys();
			if(rs.next()){
				System.out.println("key:"+rs.getObject(1));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, null, conn);
		}
	}
}
