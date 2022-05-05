package com.te.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {
	public static void manager() throws SQLException {
		int employeeId = 0;
		Scanner scanner = new Scanner(System.in);
		String employeeLeaveDate = null;
		String employeeLeaveStatus = null;
		System.out.println("Hii Manager");
		boolean exit = true;
		while (exit) {
			System.out.println(
					"Enter 1 to show all leave request \nEnter 2 to Approve/Reject leave request \nEnter 3 to exit");
			int option = scanner.nextInt();
			if (option == 1) {
				System.out.println("All Leave Requests");
				try {
					Connection connect = DriverManager.getConnection(App.url, "root", "admin");
					Statement stmt = connect.createStatement();
					String sql = "SELECT * FROM employeeleave";

					ResultSet result = stmt.executeQuery(sql);

					while (result.next()) {
						employeeId = result.getInt("employeeId");
						employeeLeaveDate = result.getString("employeeLeaveDate");
						employeeLeaveStatus = result.getString("employeeLeaveStatus");
						System.out.println("Employee id " + employeeId + " requested leave for date "
								+ employeeLeaveDate + " status is " + employeeLeaveStatus);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (option == 2) {
				System.out.println("Enter Employee Id to accept/reject leave");
				int eId = scanner.nextInt();
				try {
					Connection connect = DriverManager.getConnection(App.url, "root", "admin");
					Statement stmt = connect.createStatement();
					String sql1 = "SELECT employeeLeaveDate, employeeLeaveStatus FROM employeeleave where employeeId="
							+ eId;
					ResultSet result1 = stmt.executeQuery(sql1);

					while (result1.next()) {
						employeeLeaveDate = result1.getString("employeeLeaveDate");
						employeeLeaveStatus = result1.getString("employeeLeaveStatus");
						System.out.println(
								"Leave requested for date " + employeeLeaveDate + " status is " + employeeLeaveStatus);
					}
					System.out.println("Enter date to make approve/reject");
					String eLeaveDate = scanner.next();
					System.out.println("Enter 1 for reject or 2 for approve for the date " + eLeaveDate);
					int value = scanner.nextInt();
					String status = null;
					if (value == 1) {
						status = "rejected";
					} else if (value == 2) {
						status = "approved";
					} else {
						System.out.println("Check what option you have entered");
					}
					String sql2 = "UPDATE employeeleave set employeeLeaveStatus='" + status
							+ "' where employeeLeaveDate='" + eLeaveDate + "'";
					int result2 = stmt.executeUpdate(sql2);
					if (result2 > 0) {
						System.out.println(status);
					} else {
						System.out.println(status);
					}
				} catch (InputMismatchException e) {
					System.out.println("check what you have entered");
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
