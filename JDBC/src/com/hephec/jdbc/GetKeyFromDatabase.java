package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class GetKeyFromDatabase {


	static Connection conn=null;
	static ResultSet rs=null;
	static PreparedStatement ps=null;
	//ȡ�����ݿ��Զ����ɵ�����ֵ
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
			//ͨ��PreparedStatement����getGeneratedKeys();
			//��ȡ��ResultSet����,�ö�����ֻ��һ�У����ڴ�Ű�����
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
