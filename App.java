package com.te.backend;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
	static int choice;
	static final String url = "jdbc:mysql://localhost:3306/backend";

	public static int option() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Employee Login");
		System.out.println("Enter 1 to Register Employee \nEnter 2 to Login \nEnter 3 to exit");
		try {
			choice = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Check what you have entered");
		}
		return choice;
	}

	public static void main(String[] args) {
		App.option();
		boolean exit = true;
		while (exit) {
			switch (choice) {
			case 1:
				Registration.register();
				exit = false;
				break;
			case 2:
				Login.log();
				exit = false;
				break;
			case 3:
				System.out.println("Bye...");
				exit = false;
				break;
			default:
				System.out.println("Invalid options check what you have entered");
				App.option();
			}
			 
		}
	}
}
