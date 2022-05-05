package com.te.backend;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import net.bytebuddy.asm.Advice.Exit;

public class Login {
	static Scanner scanner = new Scanner(System.in);
	static int empId = 0;
	static int employeeId = 0;
	static String empPass = null;
	static String employeePassword = null;
	static String employeeType = null;
	public static void loginVerification() throws SQLException {
		if (empPass.contentEquals(employeePassword) && empId == employeeId) {
			System.out.println("Logged in successfully");
			// manager 
			if (employeeType.contentEquals("manager")) {
				Manager.manager();
			}
			// employee
			else {
				EmployeeLeave.employeeLeave(empId);
			}
		} else if (empId == employeeId) {
			System.out.println("You entered wrong password");
		} else {
			System.out.println("No user found");
		}
	}
	public static void log() {
		
		try {
			System.out.println("Enter the EmployeeId");
			empId = scanner.nextInt();
			System.out.println("Enter the password");
			empPass = scanner.next();
		} catch (InputMismatchException e) {
			System.out.println("Check what you have entered"); 
			Login.log();
		}
		try {
			Connection connect = DriverManager.getConnection(App.url, "root", "admin");
			Statement stmt = connect.createStatement();
			String sql = "SELECT employeeId,employeeType, password FROM employeeinfo where employeeId=" + empId;
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				employeeId = result.getInt("employeeId"); 
				employeePassword = result.getString("password"); 
				employeeType = result.getString("employeeType");
			}

			Login.loginVerification();
		
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("No user found");
		} catch (InputMismatchException e) {
			System.out.println("Check what you have entered");
		}
	}
}
