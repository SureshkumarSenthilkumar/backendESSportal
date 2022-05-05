package com.te.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeLeave {
	public static void employeeLeave(int empId) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String employeeLeaveDate = null;
		String employeeLeaveStatus = null;
		System.out.println("Hii Employee");
		boolean exit = true;
		while (exit) {
			System.out.println(
					"Enter 1 to show all status of leave requests \nEnter 2 to request for leave \nEnter 3 to exit");
			int option = scanner.nextInt();
			if (option == 1) {
				System.out.println("all status of leave requests");
				try {
					System.out.println("HEllo all");
					Connection connect = DriverManager.getConnection(App.url, "root", "admin");
					Statement stmt = connect.createStatement();
					String sql = "SELECT employeeLeaveDate, employeeLeaveStatus FROM employeeleave where employeeId="
							+ empId;
					ResultSet result = stmt.executeQuery(sql);

					while (result.next()) {
						employeeLeaveDate = result.getString("employeeLeaveDate");
						employeeLeaveStatus = result.getString("employeeLeaveStatus");
						System.out.println(
								"Leave request for date " + employeeLeaveDate + " status is " + employeeLeaveStatus);
					}
				} catch (InputMismatchException e) {
					System.out.println("Check what you have entered");
				}

			} else if (option == 2) {
				try {
					System.out.println("Enter the date to request leave");
					String leaveDate = scanner.next();
					Connection connect = DriverManager.getConnection(App.url, "root", "admin");
					Statement stmt = connect.createStatement();
					String sql = "insert into employeeleave(employeeId, employeeLeaveDate, employeeLeaveStatus) values( '"
							+ empId + "','" + leaveDate + "','pending')";
					int result = stmt.executeUpdate(sql);
					if (result > 0) {
						System.out.println("requested leave ");
					}else {
						System.out.println("can't able to request");
					}
				} catch (InputMismatchException e) {
					System.out.println("Check what you have entered");
				}

			} else if (option == 3) {
				System.out.println("Bye...");
				exit = false;
			} else {
				System.out.println("Check what you have entered");
			}

		}
	}
}
