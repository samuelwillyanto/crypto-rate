package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import database.Connect;

public class SumOfTransaction {

	static Scanner sc = new Scanner(System.in);
	
	public static void sumCrypto() {
		System.out.println("Sum of transaction from each crypto");

		viewTable();
	}
	
	public static void viewTable() {
		// tampilkan coinid, rp exchange rate, asset sold, asset bought, rp sold, rp bought
		Double buyBtc = null, sellBtc = null, buyETH = null, sellETH = null, buyLTC = null, sellLTC = null;
		System.out.println("");
		System.out.println("=========================================================================================");
		System.out.println("| Name | Rp Exhange Rate | Asset Sold  | Asset Bought |    Rp Sold    |    Rp Bought    |");
		System.out.println("=========================================================================================");
		
		// Query untuk ngambil transaksi
		Connect con = Connect.getConnection();
		String queryBuyBtc = "SELECT SUM(num_of_coins) as sumBuybtc FROM transaction WHERE transaction = 'buy' AND coinId = 'BTC'";
		ResultSet rs = con.executeQuery(queryBuyBtc);
		try {
			while(rs.next()) {
				buyBtc = rs.getDouble("sumBuybtc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String querySellBtc = "SELECT SUM(num_of_coins) as sumSellbtc FROM transaction WHERE transaction = 'sell' AND coinId = 'BTC'";
		ResultSet rsSellBtc = con.executeQuery(querySellBtc);
		try {
			while(rsSellBtc.next()) {
				sellBtc = rsSellBtc.getDouble("sumSellbtc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryBuyETH = "SELECT SUM(num_of_coins) as buyETH FROM transaction WHERE transaction = 'buy' AND coinId = 'ETH'";
		ResultSet rsBuyETH = con.executeQuery(queryBuyETH);
		try {
			while(rsBuyETH.next()) {
				buyETH = rsBuyETH.getDouble("buyETH");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String querySellETH = "SELECT SUM(num_of_coins) as sellETH FROM transaction WHERE transaction = 'sell' AND coinId = 'ETH'";
		ResultSet rsSellETH = con.executeQuery(querySellETH);
		try {
			while(rsSellETH.next()) {
				sellETH = rsSellETH.getDouble("sellETH");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryBuyLTC = "SELECT SUM(num_of_coins) as buyLTC FROM transaction WHERE transaction = 'buy' AND coinId = 'LTC'";
		ResultSet rsBuyLTC = con.executeQuery(queryBuyLTC);
		try {
			while(rsBuyLTC.next()) {
				buyLTC = rsBuyLTC.getDouble("buyLTC");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String querySellLTC = "SELECT SUM(num_of_coins) as sellLTC FROM transaction WHERE transaction = 'sell' AND coinId = 'LTC'";
		ResultSet rsSellLTC = con.executeQuery(querySellLTC);
		try {
			while(rsSellLTC.next()) {
				sellLTC = rsSellLTC.getDouble("sellLTC");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query2 = "SELECT * FROM cryptocurrency";
		Integer rpSold, rpBought;
		
		ResultSet rs2 = con.executeQuery(query2);
		try {
			while(rs2.next()) {
				System.out.printf("| %-5s| %-16.4f|", rs2.getString("coinid"), rs2.getDouble("rp_exchange_rate"));
//				 %-12f| %-13f| %-14f| %-16f\n
				if(rs2.getString("coinid").equalsIgnoreCase("BTC")) {
					rpSold = (int) (sellBtc * 443132052.6);
					rpBought = (int) (buyBtc * 443132052.6);
					System.out.printf("%-13f| %-13f|%-14d| %-16d\n", buyBtc, sellBtc, rpSold, rpBought);
				} else if(rs2.getString("coinid").equalsIgnoreCase("ETH")) {
					rpSold = (int) (sellETH * 26421617.09);
					rpBought = (int) (buyETH * 26421617.09);
					System.out.printf("%-13f| %-13f|%-14d| %-16d\n", buyETH, sellETH, rpSold, rpBought);
				} else if(rs2.getString("coinid").equalsIgnoreCase("LTC")) {
					rpSold = (int) (sellLTC * 904285.2);
					rpBought = (int) (buyLTC * 904285.2);
					System.out.printf("%-13f| %-13f|%-14d| %-16d\n", buyLTC, sellLTC, rpSold, rpBought);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("=========================================================================================");

	}
}
