package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 * ResultSet����װ��ʹ��JDBC���в�ѯ�Ľ��
 * 1.����Statement�����executeQuery(sql)���Եõ������
 * 
 * 2.��δ���������
	 * ResultSet���ص�ʵ���Ͼ���һ�����ݱ���һ��ָ��ָ�����ݱ�ĵ�һҳ��ǰ��
	 * ����next()���������һ���Ƿ���Ч,����Ч�÷�������true,��ָ������
	 * Iterator�����൱��hasNext()��next()�����Ľ����
	 * 3.��λ�ȡÿһ��ÿһ�е�ֵ��
	 * ��ָ�붨λ��ÿһ��ʱ������ͨ������
	 * getXxx(index)��getXxx(columnName)��ȡÿһ�е�ֵ
	 * ���磺getInt(1).getString("name")
	 * ResultSet��Ҫ�ر�
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
	//��ȡid=10��customer���ݱ�ļ�¼������ӡ
	//1.��ȡConnection
	//2.��ȡStatement
	//3.׼��SQL
	//4.ִ�в�ѯ���õ�ResultSet
	//5.����ResultSet
	//6.�ر�ResultSet
	
}
