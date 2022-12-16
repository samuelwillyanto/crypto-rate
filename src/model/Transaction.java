package model;

import database.Connect;

public class Transaction {

	private String userID, coinID;
	private float sum_of_assets;
	
	public Transaction(String userID, String coinID, float sum_of_assets) {
		super();
		this.userID = userID;
		this.coinID = coinID;
		this.sum_of_assets = sum_of_assets;
	}

	public String getUserID() {
		return userID;
	}

	public String getCoinID() {
		return coinID;
	}

	public float getSum_of_assets() {
		return sum_of_assets;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setCoinID(String coinID) {
		this.coinID = coinID;
	}

	public void setSum_of_assets(float sum_of_assets) {
		this.sum_of_assets = sum_of_assets;
	}
	
	public void insert() {
		Connect con = Connect.getConnection();
	
		String query = String.format("INSERT INTO assets (userID, coinID, sum_of_assets) "
									+ "VALUES (%s, %s, %d)", userID, coinID, sum_of_assets);
		
		con.executeUpdate(query);
	}
}
