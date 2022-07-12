package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.DAOUser;
import model.User;
import view.Login;
import view.MainFunctions;

public class LoginController implements ActionListener {
	Login view;
	public LoginController(Login view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		User u = DAOUser.getUser(view.getAccountNumber(), view.getPassword());
		if(u != null)
		{
			System.out.println("Dang nhap thanh cong");
			new MainFunctions(u).setVisible(true);
			view.exit();
		}
		else {
			System.out.println("Dang nhap that bai");
		}
	}

}
