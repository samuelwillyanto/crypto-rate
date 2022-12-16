package model;

public class Users {

	private String userId, userName;
	private static Integer rupiah_balance;
	public Users(String userId, String userName, Integer rupiah_balance) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.rupiah_balance = rupiah_balance;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public static Integer getRupiah_balance() {
		return rupiah_balance;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setRupiah_balance(Integer rupiah_balance) {
		this.rupiah_balance = rupiah_balance;
	}
	
	
}
