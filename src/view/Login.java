package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LoginController;
import dao.DAOUser;
import model.TransactionHistory;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbLogo;
	private JLabel lbPinCode;
	private JLabel lbAccountNumber;
	private JTextField txtAccountNumber;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnExit;
	private JLabel lbUnderLogo;

	/**
	 * Launch the application.
	 */
	
	public Login() {
		setSize(645,391);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#ffffff"));
		contentPane.setOpaque(true);
		setContentPane(contentPane);
		
		lbLogo = new JLabel("");
		lbLogo.setHorizontalAlignment(SwingConstants.LEFT);
		Image im = null;
		try {
			im = ImageIO.read(new File("images/logo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lbLogo.setIcon(new ImageIcon(im));
		
		lbUnderLogo = new JLabel("");
		lbUnderLogo.setBackground(Color.decode("#ED1B24"));
		lbUnderLogo.setOpaque(true);
		
		lbPinCode = new JLabel("Mã pin");
		
		lbAccountNumber = new JLabel("Số tài khoản");
		
		txtAccountNumber = new JTextField();
		txtAccountNumber.setColumns(10);
		
		txtPassword = new JPasswordField();
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(Color.decode("#000000"));
		btnLogin.setForeground(Color.decode("#ffffff"));
		btnLogin.addActionListener(new LoginController(this));
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(lbLogo, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
				.addComponent(lbUnderLogo, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnExit))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(124)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lbPinCode, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbAccountNumber, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtPassword)
								.addComponent(txtAccountNumber, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))))
					.addGap(38))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbUnderLogo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAccountNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbAccountNumber, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbPinCode, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnLogin)
					.addGap(18)
					.addComponent(btnExit)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void exit()
	{
		this.dispose();
	}
	public String getAccountNumber() {
		return txtAccountNumber.getText();
	}
	public String getPassword() {
		return new String(txtPassword.getPassword());
	}
	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
