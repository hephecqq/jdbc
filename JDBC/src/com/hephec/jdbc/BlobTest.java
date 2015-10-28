package com.hephec.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class BlobTest {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	//ʹ��JDBC���������.�����
	@Test
	public void test(){
		try{
			String sql="insert into customer(name,email,birth,picture) values(?,?,?,?)";
			conn=JDBCTool.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, "11");
			ps.setString(2, "xxx");
			ps.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			InputStream in=BlobTest.class.getClassLoader().getResourceAsStream("bg1.jpg");
			ps.setBlob(4,in);
			
			ps.executeUpdate();
			/**
			 * ��ȡBlob����������
			 * �ؼ����룺
			 * Blob pic_Blob=resultSet.getBlob(3);
			 * �õ�������
			 * InputStream in=pic.getBinaryStream();
			 * 
			 * OutputStream fos=new FileOutputStream("xx.jpg");
			 * byte[] buffer=new byte[1023];
			 * int len=0;
			 * while((len=in.read(buffer))!=-1){
			 * 	//д�������
			 * 	write(buffer,0,len);
			 * }
			 *	�ر���
			 * 
			 * */
			
			//ʹ��getBlob()������ȡ�õ�Blob����
			//����Blob��getBinaryStream()�õ�������
			//��ʹ��IO��������
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, ps, conn);
		}
		
	}
}
