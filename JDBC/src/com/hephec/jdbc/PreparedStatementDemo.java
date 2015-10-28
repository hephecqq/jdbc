package com.hephec.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PreparedStatementDemo {
	
	@Test
	public void testResultSetMetaData() throws SQLException{
		//ResultSetMetadata�Ǳ���ResultSet��Ԫ���ݶ��󣬴��п���
		//��ȡ����������ж�����,����ʱʲô����Ϣ
		//ʹ�÷�ʽ:�õ�ResultSetMetaData����
		java.sql.ResultSetMetaData meta=rs.getMetaData();
		//��ȡ������Щ��
		int count=meta.getColumnCount();
		
		//��ȡָ���еı������������д�1��ʼ
		String label=meta.getColumnLabel(1);
		System.out.println(label);
		
	}
	
	public <T> T get(Class<T> clazz,String sql,Object ...args) throws Exception, ReflectiveOperationException{
		T entity=null;
		
		conn=JDBCTool.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			
			java.sql.ResultSetMetaData rsmd=rs.getMetaData();
			Map<String, Object> hashMap=new HashMap<String, Object>();
			while(rs.next()){
				//��ӡÿһ�е�����
				for (int i = 0; i <rsmd.getColumnCount(); i++) {
					String label=rsmd.getColumnLabel(i+1);
					Object value=rs.getObject(label);
					hashMap.put(label, value);
					Class clazz1=Student.class;
					Object obj=clazz1.newInstance();
					
					for(Map.Entry<String, Object> entry:hashMap.entrySet()){
						String fieldName=entry.getKey();
						Object fieldValue=entry.getValue();
						System.out.println(fieldName);
						System.out.println(fieldValue);
					}
				}
				
				
				//���÷��䴴������
				try {
					entity=clazz.newInstance();
					//ͨ������sql������жϵ���ѡ������Щ�У��Լ���ҪΪentity�������Щ���Ը�ֵ
					//��ȡJDBC��Ԫ����ResultSetMetaData
				
					
					
				} catch (InstantiationException e) {
							e.printStackTrace();
				} catch (IllegalAccessException e) {
								e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JDBCTool.release(rs, ps, conn);
		}
		return entity;
		
	}
	public Customer getCustomer(String sql,Object ...args){
		Customer customer=new Customer();
		conn=JDBCTool.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
			rs=ps.executeQuery();
			
			while(rs.next()){
				customer.setId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setBirth(rs.getDate(4));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JDBCTool.release(rs, ps, conn);
		}
		return customer;
		
	}
	public Student getStudent(String sql,Object ...args) throws Exception{
		
		conn=JDBCTool.getConnection();
		String sql1="insert into customer values(?,?,?,?)";
		Student stu=new Student();
		
		ps=conn.prepareStatement(sql1);
		ps.setInt(1, 100);
		ps.setString(2, "xxx");
		ps.setString(3, "yyyy");
		ps.setDate(4, new Date(new java.util.Date().getTime()));
		int count=ps.executeUpdate();
		System.out.println(count);
		
		JDBCTool.release(rs, ps, conn);
		
		return stu;
		
	}
	//ʹ��PreparedStatement��Ч���sqlע������
	static Connection conn=null;
	static java.sql.PreparedStatement ps=null;
	static ResultSet rs=null;
	
	public void addNewStudent(Student stu){
		String sql="insert into examstudent values(?,?,?,?,?,?,?)";
		System.out.println(sql);
		//update("",stu.getExamCard());
		
		
	}
	public static void update(String sql,Object ...args) throws SQLException{
		//args��ʾռλ����ֵ
		
		conn=JDBCTool.getConnection();
		ps=conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i+1, args[i]);
		}
		//ִ��sql���
		int count=ps.executeUpdate(sql);
		System.out.println(count);
		
		JDBCTool.release(rs, null, conn);
		
	}
	//1.ʹ��preparedStatement����
	//1,�����ö���
	//PreparedStatement ps=conn.prepareStatement(sql)
	//�˴����봫��sql���
	//2.����PreparedStatement�����setXxx()����Ϊÿ��ռλ��λ�ø�ֵ
	//3.ִ��SQL���,executeQuery()��������executeUpdate()����
	//ע��ִ��ʱ������Ҫ����sql��䣬����ֵ��1��ʼ
	

	/**
	 * sqlע��
	 * @throws Exception 
	 * 
	 * */
	@Test
	public void testSQLInject() throws Exception{
		conn=JDBCTool.getConnection();
		String sql="select * from test where id=101 or 1=1";
		
		java.sql.Statement stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			System.out.println(rs.getInt(1));
			
		}
		JDBCTool.release(rs, stmt, conn);
	
	}

	@Test
	public void test() throws SQLException{
		conn=JDBCTool.getConnection();
		String sql="insert into customer values(?,?,?,?)";
		
		ps=conn.prepareStatement(sql);
		ps.setInt(1, 100);
		ps.setString(2, "xxx");
		ps.setString(3, "yyyy");
		ps.setDate(4, new Date(new java.util.Date().getTime()));
		int count=ps.executeUpdate();
		System.out.println(count);
		
		JDBCTool.release(rs, ps, conn);
		
	}
	
}	
