package model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class TransactionHistory {
	private String transactionHistoryId;
	private String userId;
	private BigInteger money;
	private Timestamp dayOfTransaction;
	private String targetUserId;
	
	public TransactionHistory(String transactionHistoryId, String userId, BigInteger money, String targetuserId) {
		this.transactionHistoryId = transactionHistoryId;
		this.userId = userId;
		this.money = money;
		this.targetUserId = targetuserId;
	}

	public TransactionHistory(String transactionHistoryId, String userId, BigInteger money, Timestamp dayOfTransaction,
			String targetuserId) {
		this.transactionHistoryId = transactionHistoryId;
		this.userId = userId;
		this.money = money;
		this.dayOfTransaction = dayOfTransaction;
		this.targetUserId = targetuserId;
	}

	public String getTransactionHistoryId() {
		return transactionHistoryId;
	}

	public void setTransactionHistoryId(String transactionHistoryId) {
		this.transactionHistoryId = transactionHistoryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigInteger getMoney() {
		return money;
	}

	public void setMoney(BigInteger money) {
		this.money = money;
	}

	public Timestamp getDayOfTransaction() {
		return dayOfTransaction;
	}

	public void setDayOfTransaction(Timestamp dayOfTransaction) {
		this.dayOfTransaction = dayOfTransaction;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetuserId) {
		this.targetUserId = targetuserId;
	}
	public static String getRandomTransactionHistoryId()
	{
		String rs = "";
		String lower = "abcdefghijklmnopwrstuvwxyz";
		String upper = "ABCDEFGHIJKLMNOPWRSTUVWXYZ";
		String number = "0123456789";
		String data = lower + upper + number;
		int length = 16;
		for(int i = 0; i < length; i++)
		{
			int index = (int)(Math.random()*data.length());
			rs += data.charAt(index);
		}
			
		return rs;
	}
	@Override
	public String toString() {
		return "TransactionHistory [transactionHistoryId=" + transactionHistoryId + ", userId=" + userId + ", money="
				+ money + ", dayOfTransaction=" + dayOfTransaction + ", targetUserId=" + targetUserId + "]";
	}
	
}