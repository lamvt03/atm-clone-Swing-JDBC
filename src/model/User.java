package model;

import java.math.BigInteger;

public class User {
	private String userId;
	private String fullName;
	private String pass;
	private BigInteger balance;
	public User() {}
	public User(String userId, String fullName, String pass, BigInteger balance) {
		this.userId = userId;
		this.fullName = fullName;
		this.balance = balance;
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", pass=" + pass + ", balance=" + balance + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public BigInteger getBalance() {
		return balance;
	}
	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
