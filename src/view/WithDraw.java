package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.DAOUser;
import model.TransactionHistory;
import model.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JTextField;
import javax.swing.JButton;

public class WithDraw extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User u;
	private JTextField txtInputMoney;
	private JButton btnWithDraw;
	private JButton btnCancel;
	/**
	 * Create the frame.
	 */
	public WithDraw(User u, MainFunctions view) {
		/*truyen user*/
		this.u = u;
		
		setSize(439,319);
		setLocationRelativeTo(view);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		
		JLabel lbWithDraw = new JLabel("RÚT TIỀN");
		lbWithDraw.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbWithDraw.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("Nhập số tiền cần rút");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtInputMoney = new JTextField();
		txtInputMoney.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("(Số tiền cần rút phải là bội của 50,000)");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnWithDraw = new JButton("Rút tiền");
		btnWithDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				withDraw();
			}
		});
		
		btnCancel = new JButton("Huỷ");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainFunctions(u).setVisible(true);
				exit();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addComponent(btnWithDraw)
							.addPreferredGap(ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
							.addComponent(btnCancel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInputMoney, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(104)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)))
					.addGap(176))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(165)
					.addComponent(lbWithDraw, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(290, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addComponent(lbWithDraw, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtInputMoney, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWithDraw)
						.addComponent(btnCancel))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void withDraw()
	{
		BigInteger inp = new BigInteger(txtInputMoney.getText());
		//so tien can rut lon hon so du
		if(inp.compareTo(u.getBalance()) == 1 ){
			JOptionPane.showMessageDialog(this, "Số dư trong tài khoản không đủ");
			txtInputMoney.setText("");
		}else
		{
			if(inp.mod(new BigInteger("50000")).compareTo(new BigInteger("0")) == 0)
			{
				u.setBalance(u.getBalance().subtract(inp));
				DAOUser.updateUserBalance(u);
				DAOUser.insertTransactionHistory(new TransactionHistory(TransactionHistory.getRandomTransactionHistoryId(), 
																		u.getUserId(), 
																		new BigInteger("-"+inp), 
																		null));
				txtInputMoney.setText("");
				JOptionPane.showMessageDialog(this, "Rút tiền thành công");
			}
			else 
			{
				txtInputMoney.setText("");
				JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền là bội của 50,000");
			}
		}
	}
	public void exit()
	{
		this.dispose();
	}
}
