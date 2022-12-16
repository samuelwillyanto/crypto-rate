package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import database.Connect;

public class UserWealth {

	static Scanner sc = new Scanner(System.in);
	
	public static void showUserWealth() {
		System.out.println("Calculate and show the wealth from each user");
		
		// tampilkan user nam dan assets in rp
		view();
	}
	
	public static void view() {
		System.out.println("");
		System.out.println("USER WEALTH");
		System.out.println("==========================================================");
		System.out.println("| User name | Assets in Rp |");
		System.out.println("==========================================================");
		
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM assets a join user u on a.userId = u.userId join cryptocurrency cr on cr.CoinID = a.CoinID";
		ResultSet rs = con.executeQuery(query);
		Integer assetRp;
		Float sumAsset;
		Double exchange;
		String coinId;
		try {
			while(rs.next()) {
				exchange = rs.getDouble("rp_exchange_rate");
				sumAsset = rs.getFloat("sum_of_assets");
				
				assetRp = (int) (sumAsset * exchange);
				
				System.out.printf("| %-10s| %-12d|\n", rs.getString("user_name"), assetRp);
			}
		} catch (SQLException e) {
			System.out.println("Query Failed");
		}
		System.out.println("==========================================================");

	}
}
