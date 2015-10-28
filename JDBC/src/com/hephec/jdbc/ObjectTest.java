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
		//接收用户的输入
		//a:准考证号
		//b:身份证号
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		String type=scan.nextLine();
		if(type.equals("准考证号")){
			System.out.println("请输入准考证号");
			String line=scan.nextLine();
			//构造sql
			@SuppressWarnings("unused")
			String sql="select * from examstudent"
					+"where examCard=?";
			ps.setString(4, line);
			//执行查询
			rs=ps.executeQuery();
			while(rs.next()){
				int id=rs.getInt(1);
				//...省略
				//封装成对象
				
			}
			
		}
		if(type.equals("身份证号")){
			System.out.println("请输入身份证号");
			String line=scan.nextLine();
			//构造sql
			String sql="select * from examstudent"
					+"where idCard=?";
		}else{
			System.out.println("重新输入...");
		}
		
	}
	
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	java.sql.PreparedStatement ps=null;
	/**
	 * 向数据库插入一条学生记录
	 * */
	
	@Test
	public void test(){
		
//		String sql="INSERT INTO examstudent(flowId,TYPE,IdCard,ExamCard,StudentName,Location,Grade) "
//				+"VALUES (10,1000021,'121413','3142141','hepeng','grage',30);";
//	
		
		try {
			conn=JDBCTool.getConnection();
			//创建Student对象
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
