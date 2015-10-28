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
	//使用JDBC处理大数据.大对象
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
			 * 读取Blob的数据类似
			 * 关键代码：
			 * Blob pic_Blob=resultSet.getBlob(3);
			 * 得到输入流
			 * InputStream in=pic.getBinaryStream();
			 * 
			 * OutputStream fos=new FileOutputStream("xx.jpg");
			 * byte[] buffer=new byte[1023];
			 * int len=0;
			 * while((len=in.read(buffer))!=-1){
			 * 	//写入输出流
			 * 	write(buffer,0,len);
			 * }
			 *	关闭流
			 * 
			 * */
			
			//使用getBlob()方法读取得到Blob对象
			//调用Blob的getBinaryStream()得到输入流
			//在使用IO操作即可
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTool.release(null, ps, conn);
		}
		
	}
}
