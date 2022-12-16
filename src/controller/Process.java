package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import database.Connect;
import model.Users;

public class Process {

	static Scanner sc = new Scanner(System.in);
	
	public static void users() {
		Connect con = Connect.getConnection();
		String query = "SELECT * FROM user";
		String name, id;
		Integer rupiah;
		ResultSet rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				id = rs.getString("userid");
				name = rs.getString("user_name");
				rupiah = rs.getInt("rupiah_balance");
				Users u = new Users(id, name, rupiah);
				
			}
		} catch (SQLException e) {
			System.out.println("Query Failed");
		}
		
	}
	
	public static void processTransaction() {
		System.out.println("Process transaction table");
		Integer input = null;
		users();
		do {
			System.out.println("1. Buy/deposit coin");
			System.out.println("2. Sell/withdrawal coin");
			System.out.println("3. Exit");
			System.out.print(">> ");
			try {
				input = sc.nextInt();
				sc.nextLine();
				if(input < 1 || input > 3) {
					throw new Exception();
				}
				process(input);
			} catch (Exception e) {
				System.out.println("Please input 1 - 3");
			}
		} while (input != 3);
	}

	public static void process(Integer input) {
		switch (input) {
		case 1:
			buyCoin();
			break;	
		case 2:
			sellCoin();
			break;
		}
	}

	public static void sellCoin() {
		// TODO Auto-generated method stub
		System.out.println("Sell Coin");

		float total;
		Integer assetid;
		Float sum_assets, quantity;
		System.out.print("Enter Asset ID :");
		assetid = sc.nextInt(); sc.nextLine();
		System.out.print("Enter Quantity to Sell : ");
		quantity = sc.nextFloat(); sc.nextLine();
		
		Connect con = Connect.getConnection();
		
		ResultSet rs = con.executeQuery("select * from assets");
		
		try {
			while(rs.next()) {
				sum_assets = rs.getFloat("sum_of_assets");
				if(rs.getInt("assetid") == assetid) {
					if(rs.getFloat("sum_of_assets") < quantity) {
						System.out.println("Your assets < quantity to sell");
					} else {
						total = rs.getFloat("sum_of_assets") - quantity;
						con.executeUpdate("UPDATE assets SET sum_of_assets = "+total+" WHERE assetid = "+assetid+"");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void buyCoin() {
		// TODO Auto-generated method stub
		System.out.println("Buy Coin");
		Float totalPrice = null;
		int rupiah = 0;
		String userID, coinID;
		Float quantity;
		Double exchange = null;
		
		System.out.print("Enter User ID [A1, E2, P0] : ");
		userID = sc.nextLine();
		System.out.print("Enter Coin ID [BTC, ETH, LTC] : ");
		coinID = sc.nextLine();
		System.out.print("Enter Quantity : ");
		quantity = sc.nextFloat(); sc.nextLine();
		
		Connect con = Connect.getConnection();
		
		ResultSet rs = con.executeQuery("select * from cryptocurrency");
		
		try {
			while(rs.next()) {
				if(rs.getString("coindid").equals(coinID)) {
					exchange = rs.getDouble("rp_exchange_rate");
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		totalPrice = (float) (quantity * exchange);
		
		ResultSet rsUser = con.executeQuery("select * from user");
		
		try {
			while(rsUser.next()) {
				if(rsUser.getString("userid").equals(userID)) {
					rupiah = rs.getInt("rupiah_balance");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rupiah < totalPrice) {
			System.out.println("Sorry your balance < total price");
		} else {
			con.executeUpdate("insert into assets (userid, coinid, sum_of_assets) "
					+ "VALUES ('"+userID+"', '"+coinID+"', "+quantity+")");
		}
	}
}
