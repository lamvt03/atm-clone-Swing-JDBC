package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAOUser;
import model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ChangePin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User u;
	private JPasswordField pfOldPassword;
	private JPasswordField pfNewPassword;
	private JPasswordField pfConfirmPassword;
	private JButton btnChangePin;
	private JButton btnCancel;
	/**
	 * Create the frame.
	 */
	public ChangePin(User u, MainFunctions parent) {
		this.u = u;
		setSize(433,333);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(parent);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("ĐỔI PIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập mã pin cũ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhập mã pin mới");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_2 = new JLabel("Xác nhận mã pin");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		pfOldPassword = new JPasswordField();
		
		pfNewPassword = new JPasswordField();
		
		pfConfirmPassword = new JPasswordField();
		
		btnChangePin = new JButton("Đổi pin");
		btnChangePin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePin();
			}
		});
		
		btnCancel = new JButton("Huỷ");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pfConfirmPassword, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
								.addComponent(pfNewPassword, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
								.addComponent(pfOldPassword, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnChangePin)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCancel))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(166)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(pfOldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(pfNewPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(pfConfirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnChangePin)
						.addComponent(btnCancel))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void changePin()
	{
		//neu mat khau cu dung
		if(u.getPass().equals(new String(pfOldPassword.getPassword())))
		{
			if(new String(pfNewPassword.getPassword()).equals(new String(pfConfirmPassword.getPassword())))
			{
				u.setPass(new String(pfNewPassword.getPassword()));
				DAOUser.updateUserPin(u);
				JOptionPane.showMessageDialog(this, "Đổi pin thành công");
				reset();
			}else {
				JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp");
				reset();
			}
		}else {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng");
			reset();
		}
	}
	public void reset()
	{
		pfOldPassword.setText("");
		pfNewPassword.setText("");
		pfConfirmPassword.setText("");
	}
	public void cancel()
	{
		new MainFunctions(u).setVisible(true);
		this.dispose();
	}
}
