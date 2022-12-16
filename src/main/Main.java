package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.Process;
import controller.SumOfTransaction;
import controller.UserTransactions;
import controller.UserWealth;
import database.Connect;

public class Main {

	private Connect con;
	private Scanner sc;
	
	public Main() {
		con = Connect.getConnection();
		sc = new Scanner(System.in);
		
		printMenu();		
	}

	public void printMenu() {
		// TODO Auto-generated method stub
		Integer input = 0; 
		do {
			System.out.printf("\n\nPilih Nomor\n"
								+ "1. Nomor 1\n"
								+ "2. Nomor 2\n"
								+ "3. Nomor 3\n"
								+ "4. Nomor 4\n"
								+ "5. Exit\n"
								+ ">> ");
			try {
				input = sc.nextInt();
				if(input < 1 || input > 5) {
					throw new Exception();
				}
				process(input);
			} catch (Exception e) {
				System.out.println("Please input 1 - 5");
			}
		} while (input != 5);
	}

	public void process(Integer input) {
		switch (input) {
		case 1:
			// ini merupakan facade untuk bagian mempertunjukkan jawaban nomor 1
			// dapat ditemukan pada file UserTransactions.java package controller
			UserTransactions.viewTransaction();
			break;	
		case 2:
			// ini merupakan facade untuk bagian mempertunjukkan jawaban nomor 2
			// dapat ditemukan pada file SumOfTransaction.java package controller
			SumOfTransaction.sumCrypto();
			break;
		case 3:
			// ini merupakan facade untuk bagian mempertunjukkan jawaban nomor 3
			// dapat ditemukan pada file Process.java package controller
			Process.processTransaction();			
			break;
		case 4:
			// ini merupakan facade untuk bagian mempertunjukkan jawaban nomor 4
			// dapat ditemukan pada file UserWealth.java package controller
			UserWealth.showUserWealth();			
			break;
		case 5:
			System.out.println("Thank You!");
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
