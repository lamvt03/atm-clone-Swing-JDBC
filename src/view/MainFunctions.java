package view;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import controller.MainFunctionsController;
import model.User;

import java.awt.Font;

public class MainFunctions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbLogo;
	private JLabel lbUnderLogo;
	private JButton btnWithDraw;
	private JButton btnChangePin;
	private JButton btnBalance;
	private JButton btnBanking;
	private JButton btnTransactionHistory;
	private JButton btnLogout;
	private User u;
	private JLabel lbWelcome;

	/**
	 * Create the frame.
	 */
	public MainFunctions(User u) {
		/*Dang nhap thanh cong*/
		this.u = u;
		lbWelcome = new JLabel("Xin chào, " + u.getFullName());
		
		/* frame */
		setSize(680, 430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("images/icon-app.png").getImage());
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#ffffff"));
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
		
		btnWithDraw = new JButton("Rút tiền");
		btnWithDraw.setBorder(new LineBorder(Color.decode("#ED1B24"), 1));
		btnWithDraw.addActionListener(new MainFunctionsController(this));
		
		btnChangePin = new JButton("Đổi pin");
		btnChangePin.setBorder(new LineBorder(Color.decode("#ED1B24"), 1));
		btnChangePin.addActionListener(new MainFunctionsController(this));
		
		btnBalance = new JButton("Số dư");
		btnBalance.setBorder(new LineBorder(Color.decode("#ED1B24"), 1));
		btnBalance.addActionListener(new MainFunctionsController(this));
		
		btnBanking = new JButton("Chuyển khoản");
		btnBanking.setBorder(new LineBorder(Color.decode("#ED1B24"), 1));
		btnBanking.addActionListener(new MainFunctionsController(this));
		
		btnTransactionHistory = new JButton("Lịch sử giao dịch");
		btnTransactionHistory.setBorder(new LineBorder(Color.decode("#ED1B24"), 1));
		btnTransactionHistory.addActionListener(new MainFunctionsController(this));
		
		btnLogout = new JButton("Đăng xuất");
		btnLogout.setBorder(new LineBorder(Color.decode("#ED1B24"), 1));
		btnLogout.addActionListener(new MainFunctionsController(this));
		
		lbWelcome.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnTransactionHistory, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBalance, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnWithDraw, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnBanking, 0, 0, Short.MAX_VALUE)
								.addComponent(btnChangePin, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
								.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(lbUnderLogo, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, 664, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(242)
							.addComponent(lbWelcome)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbUnderLogo, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnBalance)
								.addComponent(btnBanking))
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTransactionHistory)
								.addComponent(btnLogout)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(lbWelcome)
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnWithDraw)
								.addComponent(btnChangePin, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public User getUser()
	{
		return u;
	}
}
