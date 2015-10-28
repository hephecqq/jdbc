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

			//1.��ȡ���ݿ�����
			//2.׼�������sql���
		String sql="insert into customer (name,email,birth) values('����','zhangsan@sina.com','1992-10-19')";
		String d_sql="delete from customer where name='����'";
		String u_sql="update customer set name='����' where id='1'";
		
			//3.ִ�в���
			stmt=conn.createStatement();
			int count=stmt.executeUpdate(sql);
			int count_d=stmt.executeUpdate(d_sql);
			int count_u=stmt.executeUpdate(u_sql);
			
			System.out.println(count);
			System.out.println(count_d);
			System.out.println(count_u);
			
			//4.��ȡ����SQL����Statement����
			//1.Statement����ִ��sql���Ķ���
			//2.ͨ��Connection��createStatement��������ȡ
			//3.ͨ��executeUpdate(sql)����ִ��sql���
			//4.�����SQL������insert,update,delete��������select���
			
			//5.����Statement�����executeUpdate(sql)ִ��sql���в���
			//6.�ر�Statement����
			
			//7.�ر�����
			//8.conn��statement�Ƕ���Ӧ�ó�������ݿ��������������Դ��ʹ�ú����ر�
			//int i=10/0;
			//��Ҫ��finally�йر���Դ
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTool.release(null,stmt, conn);
		}
	
	
		
	}
	
	//�汾1.0
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
