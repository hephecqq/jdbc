package com.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
	
	public void getEmployees(){


		Connection con=null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			// step1 load the driver class
			Class.forName("com.mysql.jdbc.Driver");

			// step2 create the connection object
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			// step3 create the statement object
			stmt = con.createStatement();

			// step4 execute query
			rs = stmt.executeQuery("select * from employee");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getDate(2)/* + "  "
						+ rs.getString(3) + "  " + rs.getDouble(4) + " "
						+ rs.getString(5)*/);

		} catch (Exception e) {
			System.out.println(e);
		}finally{
			// step5 close the connection object
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	public void getEmployeeById(int a){
		Connection con=null;
		Statement stmt = null;
		ResultSet rs =null;
		try {
			// step1 load the driver class
			Class.forName("com.mysql.jdbc.Driver");

			// step2 create the connection object
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			// step3 create the statement object
			stmt = con.createStatement();

			// step4 execute query
			rs = stmt.executeQuery("select * from employee where id="+a);
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) /*+ "  "
						+ rs.getString(3) + "  " + rs.getDouble(4) + " "
						+ rs.getString(5)*/);

		} catch (Exception e) {
			System.out.println(e);
		}finally{
			// step5 close the connection object
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public int saveEmployee(int empId, String empName){
		Connection con=null;
		Statement stmt = null;
		//ResultSet rs =null;
		int result=0;
		try {
			// step1 load the driver class
			Class.forName("com.mysql.jdbc.Driver");

			// step2 create the connection object
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");

			// step3 create the statement object
			stmt = con.createStatement();
			String insertSql="insert into employee(id,NAME)  values("+empId+",'"+empName+"')";
			// step4 execute query
			 result= stmt.executeUpdate(insertSql);
	
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			// step5 close the connection object
			try {
				//rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}


}
/*INSERT INTO `test`.`employee` 
(`id`, 
`JOINING_DATE`, 
`NAME`, 
`SALARY`, 
`SSN`
)
VALUES
('id', 
'JOINING_DATE', 
'NAME', 
'SALARY', 
'SSN'
);
*/