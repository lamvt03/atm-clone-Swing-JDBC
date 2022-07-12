package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOUser;
import model.TransactionHistory;
import model.User;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Transfer extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User u;
	private JTextField tfInputAN;
	private JTextField tfInputMoney;
	private JButton btnCheck;
	private JLabel lbTargetANName;
	private JButton btnCancel;
	private JButton btnTransfer;
	/**
	 * Create the frame.
	 */
	public Transfer(User u, MainFunctions parent) {
		this.u = u;
		
		setSize(449,267);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(parent);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("CHUYỂN KHOẢN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Số tài khoản cần chuyển");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfInputAN = new JTextField();
		tfInputAN.setColumns(10);
		
		btnCheck = new JButton("Kiểm tra");
		btnCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
				
			}
		});
		JLabel lblNewLabel_2 = new JLabel("Số tiền");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfInputMoney = new JTextField();
		tfInputMoney.setColumns(10);
		
		btnTransfer = new JButton("Chuyển");
		btnTransfer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				transfer();
			}
		});
		
		btnCancel = new JButton("Huỷ");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		
		lbTargetANName = new JLabel("New label");
		lbTargetANName.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(165, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(151))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(328, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnTransfer))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(tfInputMoney, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
									.addComponent(tfInputAN, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbTargetANName, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCheck)))
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfInputAN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCheck)
						.addComponent(lbTargetANName))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(tfInputMoney, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTransfer)
						.addComponent(btnCancel))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void check() {
		String inp = tfInputAN.getText();
		User targetUser = DAOUser.getUser(inp);
		if(targetUser == null) {
			JOptionPane.showMessageDialog(this, "Không tồn tại số tài khoản này");
			reset();
		}else {
			lbTargetANName.setText(targetUser.getFullName());
		}
	}
	public void transfer()
	{
		//neu so tien can chuyen nhieu hon so tien trong tai khoan
		if(new BigInteger(tfInputMoney.getText()).compareTo(u.getBalance()) == 1)
		{
			JOptionPane.showMessageDialog(this, "Số tiền trong tài khoản không đủ");
			reset();
		}else {
			User targetUser = DAOUser.getUser(tfInputAN.getText());
			if(targetUser != null) {
				String inp = tfInputMoney.getText();
				u.setBalance(u.getBalance().subtract(new BigInteger(inp)));
				DAOUser.updateUserBalance(u);
				
				targetUser = DAOUser.getUser(tfInputAN.getText());
				targetUser.setBalance(targetUser.getBalance().add(new BigInteger(inp)));
				DAOUser.updateUserBalance(targetUser);
				
				//update tai khoan nguon
				DAOUser.insertTransactionHistory(new TransactionHistory(TransactionHistory.getRandomTransactionHistoryId(), 
																		u.getUserId(), 
																		new BigInteger("-"+inp), 
																		targetUser.getUserId()));
				
				//update tai khoan dich
				DAOUser.insertTransactionHistory(new TransactionHistory(TransactionHistory.getRandomTransactionHistoryId(), 
						targetUser.getUserId(), 
						new BigInteger("+"+inp), 
						u.getUserId()));

				JOptionPane.showMessageDialog(this, "Chuyển thành công");
				reset();
			}
		}
		
	}
	
	public void cancel() {
		new MainFunctions(u).setVisible(true);
		this.dispose();
	}
	public void reset()
	{
		tfInputAN.setText("");
		tfInputMoney.setText("");
		lbTargetANName.setText("");
	}
}
