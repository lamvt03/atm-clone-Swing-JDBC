/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import dao.DAOUser;
import model.TransactionHistory;
import model.User;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author ADMIN
 */
public class TransactionHistoryList extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistoryTransaction;
    private JButton btnBack;
    private User u;
	/**
     * Creates new form TransactionHistory
     */
    public TransactionHistoryList(User u) {
    	this.u = u;
        initComponents();
        getHistoryTransaction(u);
    }
    
    public void getHistoryTransaction(User u){
        DefaultTableModel tableModelTran = (DefaultTableModel)tblHistoryTransaction.getModel();
        tableModelTran.setRowCount(0);
        TableColumnModel tableColumnModel = tblHistoryTransaction.getColumnModel();
        tableColumnModel.getColumn(0).setPreferredWidth(60);
        tableColumnModel.getColumn(1).setPreferredWidth(60);
        tableColumnModel.getColumn(2).setPreferredWidth(60);
        tableColumnModel.getColumn(3).setPreferredWidth(60);
        
        tblHistoryTransaction.setRowHeight(30);
        
        ArrayList<TransactionHistory> listTran = DAOUser.getTransactionHistory(u.getUserId());
        for (int i = 0; i < listTran.size(); i++) {
            TransactionHistory t = listTran.get(i);
            Vector<Object> row = new Vector<>();
            row.add(t.getTransactionHistoryId());
            row.add(String.format("%,d", t.getMoney()));
            row.add(new SimpleDateFormat("HH:mm:ss dd/MM/yyy").format(t.getDayOfTransaction()));
            row.add((t.getTargetUserId()==null)?"Rút tiền":"Chuyển khoản");
            tableModelTran.addRow(row);
        }
    }

                             
    private void initComponents() {
    	setResizable(false);
    	setExtendedState(JFrame.MAXIMIZED_BOTH); 
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistoryTransaction = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LỊCH SỬ GIAO DỊCH");

        tblHistoryTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã giao dịch", "Số tiền", "Thời gian", "Hình thức"
            }
        ));
        jScrollPane1.setViewportView(tblHistoryTransaction);
        
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				back();
				
			}
		});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBack))
        				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(37)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(btnBack))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
        			.addGap(36))
        );
        getContentPane().setLayout(layout);
    }                      
    public void back()
    {
    	new MainFunctions(u).setVisible(true);
    	this.dispose();
    }
}
