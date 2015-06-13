package com.jdbc.sample.test;

import java.sql.SQLException;

import com.jdbc.sample.EmployeeDAO;

public class JDBCTest {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EmployeeDAO jdbcTest=new EmployeeDAO();
		System.out.println("n:::Get All emplyess:::\n");
		jdbcTest.getEmployees();
		System.out.println("\n:::Get All emplyees by id:::\n");
		jdbcTest.getEmployeeById(1);
		System.out.println("\n:::Saving Employee:::\n");
		int insertRes=jdbcTest.saveEmployee(125, "empName");
		
		System.out.println("RESSSSS::::"+insertRes);
		
	}

}
