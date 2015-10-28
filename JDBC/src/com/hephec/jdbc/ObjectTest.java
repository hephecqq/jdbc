package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

public class ObjectTest {
	
	@Test
	public void getStudent() throws SQLException{
		//�����û�������
		//a:׼��֤��
		//b:���֤��
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		String type=scan.nextLine();
		if(type.equals("׼��֤��")){
			System.out.println("������׼��֤��");
			String line=scan.nextLine();
			//����sql
			@SuppressWarnings("unused")
			String sql="select * from examstudent"
					+"where examCard=?";
			ps.setString(4, line);
			//ִ�в�ѯ
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt(1);
				//...ʡ��
				//��װ�ɶ���
				
			}
			
		}
		if(type.equals("���֤��")){
			System.out.println("���������֤��");
			String line=scan.nextLine();
			//����sql
			String sql="select * from examstudent"
					+"where idCard=?";
		}else{
			System.out.println("��������...");
		}
		
	}
	
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	java.sql.PreparedStatement ps=null;
	/**
	 * �����ݿ����һ��ѧ����¼
	 * */
	
	@Test
	public void test(){
		
//		String sql="INSERT INTO examstudent(flowId,TYPE,IdCard,ExamCard,StudentName,Location,Grade) "
//				+"VALUES (10,1000021,'121413','3142141','hepeng','grage',30);";
//	
		
		try {
			conn=JDBCTool.getConnection();
			//����Student����
			Student stu=new Student(10021,1204121,"43243","243414","hephec","gg",100);
			
			stmt=conn.createStatement();
			String add_sql="insert into examstudent(flowId,TYPE,IdCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(add_sql);
			ps.setInt(1, stu.getFlowID());
			ps.setInt(2, stu.getType());
			ps.setString(3, stu.getIdCard());
			ps.setString(4, stu.getExamCard());
			ps.setString(5, stu.getStudentName());
			ps.setString(6, stu.getLocation());
			ps.setInt(7, stu.getGrade());
			int count=ps.executeUpdate();
			//System.out.println(add_sql);
			//int count=stmt.executeUpdate(add_sql);
			System.out.println(count);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTool.release(rs, stmt, conn);
		}
		
		
	}
}	
