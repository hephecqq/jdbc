package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

public class JDBCMetaData {
	
	static Connection conn=null;
	static ResultSet rs=null;
	//�õ����ݿ�Ļ�����Ϣ
	//DatabaseMetaData,ResultSetMetaData
	@Test
	public void testDatabaseMetaData(){
		//�������ݿ��Ԫ���ݶ���,����Connection����õ�
		DatabaseMetaData data=null;
		conn=JDBCTool.getConnection();
		
		try{
			
			data=conn.getMetaData();
			System.out.println("���ݿ�İ汾��:"+data.getDatabaseMajorVersion());
			System.out.println("���ݿ��URL"+data.getURL());
			System.out.println("���ݿ���û���"+data.getUserName());
			System.out.println("���ݿ�����Щ���ݿ�:");
			rs=data.getCatalogs();
			while(rs.next()){
				System.out.println(rs.getString(1));
			}
			
			//ResultSetMetaData����������еĻ�����Ϣ
			//�����������Щ�У��������еı���,����Ϣ
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
