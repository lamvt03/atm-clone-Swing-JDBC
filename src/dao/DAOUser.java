package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDB;
import model.TransactionHistory;
import model.User;

public class DAOUser {
	public static int insertUser(User u)
	{
		int rs = 0;
		Connection conn = ConnectDB.open();
		
		String sql = "INSERT INTO info (userID, fullName, pass, balance)"
				     + " VALUES (?, ?, ?, ?)";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUserId());
			pst.setString(2, u.getFullName());
			pst.setString(3, u.getPass());
			pst.setString(4, u.getBalance().toString());
			
			System.out.println("ban da thuc thi sql: " + sql);
			
			rs = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		ConnectDB.close(conn);
		return rs;
	}
	public static int updateUserBalance(User u) {
		int rs = 0;
		Connection conn = ConnectDB.open();
		
		String sql = "UPDATE info"
				+ " SET balance = ?"
				+ " WHERE userID = ?;";
			
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getBalance().toString());
			pst.setString(2, u.getUserId());
			rs = pst.executeUpdate();
			
			System.out.println("CO " + rs + " dong bi thay doi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConnectDB.close(conn);
		return rs;
	}
	public static User getUser(String userID, String pass)
	{
		User u = null;
		Connection conn = ConnectDB.open();
		
		String sql = "SELECT *"
				   + " FROM info"
				   + " WHERE userID = ? AND pass = ?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userID);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
			    return null; 
			}else {
				rs.next();
				u = new User(rs.getString("userID")
						    ,rs.getString("fullName")
						    ,rs.getString("pass")
						    ,new BigInteger(rs.getString("balance")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.close(conn);
		}
		return u;		
	}
	public static User getUser(String userID)
	{
		User u = null;
		Connection conn = ConnectDB.open();
		
		String sql = "SELECT *"
				   + " FROM info"
				   + " WHERE userID = ? ;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userID);
			ResultSet rs = pst.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
			    return null; 
			}else {
				rs.next();
				u = new User(rs.getString("userID")
						    ,rs.getString("fullName")
						    ,rs.getString("pass")
						    ,new BigInteger(rs.getString("balance")));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.close(conn);
		}
		return u;		
	}
	public static int updateUserPin(User u) {
		int rs = 0;
		Connection conn = ConnectDB.open();
		
		String sql = "UPDATE info"
				+ " SET pass = ?"
				+ " WHERE userID = ?;";
			
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getPass());
			pst.setString(2, u.getUserId());
			rs = pst.executeUpdate();
			
			System.out.println("CO " + rs + " dong bi thay doi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConnectDB.close(conn);
		return rs;
	}
	public static int insertTransactionHistory(TransactionHistory tsh)
	{
		int rs = 0;
		Connection conn = ConnectDB.open();
		
		String sql = "INSERT INTO transactionHistory (transactionHistoryID,userID, money, targetUserID)"
				     + " VALUES (?,?, ?, ?)";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, tsh.getTransactionHistoryId());
			pst.setString(2, tsh.getUserId());
			pst.setString(3, tsh.getMoney().toString());
			pst.setString(4, tsh.getTargetUserId());
			
			System.out.println("ban da thuc thi sql: " + sql);
			
			rs = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		ConnectDB.close(conn);
		return rs;
	}
	public static ArrayList<TransactionHistory> getTransactionHistory(String userID)
	{
		ArrayList<TransactionHistory> list = new ArrayList<>();
		Connection conn = ConnectDB.open();
		
		String sql = "SELECT *"
				   + " FROM transactionHistory"
				   + " WHERE userID = ?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userID);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				list.add(new TransactionHistory(rs.getString("transactionHistoryID")
										,rs.getString("userID")
										,new BigInteger(rs.getString("money"))
										,rs.getTimestamp("dayOfTransaction")
										,rs.getString("targetUserID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectDB.close(conn);
		}
		return list;
		
	}
	
}
