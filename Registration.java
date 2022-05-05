package com.te.backend;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Registration {
	 

	public static void register() {
		Scanner scanner = new Scanner(System.in);
		int empId = 0;
		String empName = null;
		String empType = null;
		String empEmail = null;
		String empPass = null;
		try {
			System.out.println("Enter the Employee Id");
			empId = scanner.nextInt();
			System.out.println("Enter the Employee Name");
			empName = scanner.next();
			System.out.println("Enter the Employee Type");
			empType = scanner.next();
			System.out.println("Enter the Employee Email");
			empEmail = scanner.next();
			System.out.println("Enter the Employee Password");
			empPass = scanner.next();
		} catch (InputMismatchException e) {
			System.out.println("Check what you have entered");
			Registration.register();;
		}
//		System.out.println(empId);
//		System.out.println(empName);
//		System.out.println(empType);
//		System.out.println(empEmail);
//		System.out.println(empPass);

		try {
			Connection connect = DriverManager.getConnection(App.url, "root", "admin");
			Statement stmt = connect.createStatement();
			String insertSql = "insert into employeeinfo(employeeId, employeeName, employeeType, email, password) values( '"
					+ empId + "','" + empName + "','" + empType + "','" + empEmail + "','" + empPass + "')";
			int result = stmt.executeUpdate(insertSql);
			if (result > 0) {
				System.out.println("Successfully Registered");
			} else {
				System.err.println("Registration failed");
			}
			connect.close();
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}

}
