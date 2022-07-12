package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.ChangePin;
import view.Login;
import view.MainFunctions;
import view.TransactionHistoryList;
import view.Transfer;
import view.WithDraw;

public class MainFunctionsController implements ActionListener{
	MainFunctions view;
	public MainFunctionsController(MainFunctions view)
	{
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String nameBtn = e.getActionCommand();
		if(nameBtn == "Rút tiền")
		{
			new WithDraw(view.getUser(), view).setVisible(true);
			view.dispose();
		}else if(nameBtn == "Số dư")
		{
			JOptionPane.showMessageDialog(view, "Số dư của bạn là " + String.format("%,d", view.getUser().getBalance()) + " VND", "SỐ DƯ", 1);
		}else if(nameBtn == "Đổi pin")
		{
			new ChangePin(view.getUser(), view).setVisible(true);
			view.dispose();
		}else if(nameBtn == "Chuyển khoản")
		{
			new Transfer(view.getUser(), view).setVisible(true);
			view.dispose();
		}else if(nameBtn == "Lịch sử giao dịch")
		{
			new TransactionHistoryList(view.getUser()).setVisible(true);
			view.dispose();
		}else if(nameBtn == "Đăng xuất")
		{
			new Login().setVisible(true);
			view.dispose();
		}
	}

}
