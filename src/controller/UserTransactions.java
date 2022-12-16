package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import database.Connect;

public class UserTransactions {

	static Scanner sc = new Scanner(System.in);
	
	public static void viewTransaction() {
		System.out.println("Show all user transaction");
		// show user name, transaction, coin name, number of coins
		viewAll();
	}
	
	public static void viewAll() {
		System.out.println("");
		System.out.println("ALL TRANSACTIONS");
		System.out.println("==========================================================");
		System.out.println("| User name | Transaction | Coin Name  | Number of Coins |");
		System.out.println("==========================================================");
		
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM transaction t join user u on t.userId = u.userId join cryptocurrency cr on cr.CoinID = t.CoinID";
		
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				System.out.printf("| %-10s| %-12s| %-11s| %-16f|\n", rs.getString("user_name"), rs.getString("transaction"), rs.getString("coin_name"), rs.getFloat("num_of_coins"));
			}
		} catch (SQLException e) {
			System.out.println("Query Failed");
		}
		System.out.println("==========================================================");

	}
}
