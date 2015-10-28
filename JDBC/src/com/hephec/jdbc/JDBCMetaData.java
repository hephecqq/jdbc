package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

public class JDBCMetaData {
	
	static Connection conn=null;
	static ResultSet rs=null;
	//得到数据库的基本信息
	//DatabaseMetaData,ResultSetMetaData
	@Test
	public void testDatabaseMetaData(){
		//描述数据库的元数据对象,可由Connection对象得到
		DatabaseMetaData data=null;
		conn=JDBCTool.getConnection();
		
		try{
			
			data=conn.getMetaData();
			System.out.println("数据库的版本号:"+data.getDatabaseMajorVersion());
			System.out.println("数据库的URL"+data.getURL());
			System.out.println("数据库的用户名"+data.getUserName());
			System.out.println("数据库有哪些数据库:");
			rs=data.getCatalogs();
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
			//ResultSetMetaData描述结果集中的基本信息
			//结果集中有哪些列，列名，列的别名,等信息
			ResultSetMetaData rsmd=rs.getMetaData();
			System.out.println(rsmd.getCatalogName(1));
			System.out.println(rsmd.getColumnType(1));
			System.out.println(rsmd.getColumnCount());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, null, conn);
		}
		
	}
}
