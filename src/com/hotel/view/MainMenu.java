package com.hotel.view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.border.LineBorder;

public class MainMenu {

	public JFrame frame;
	String name,pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 705, 441);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(69, 33, 539, 35);
		frame.getContentPane().add(panel);
		
		JLabel lblInventoryManagementSyatem = new JLabel("HOTEL MANAGEMENT SYSTEM");
		lblInventoryManagementSyatem.setForeground(Color.RED);
		lblInventoryManagementSyatem.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblInventoryManagementSyatem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.BLUE, 4));
		panel_1.setForeground(Color.RED);
		panel_1.setBounds(69, 74, 201, 268);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Hotel Mngmnt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hotel hotel=new Hotel();
				hotel.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 21, 181, 49);
		panel_1.add(btnNewButton);
		
		JButton btnMenuMngmnt = new JButton("Menu Mngmnt");
		btnMenuMngmnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuManagement management=new MenuManagement();
				management.setVisible(true);
			}
		});
		btnMenuMngmnt.setBounds(10, 81, 181, 49);
		panel_1.add(btnMenuMngmnt);
		
		JButton btnChefsMngmnt = new JButton("Chefs Mngmnt");
		btnChefsMngmnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chefs chefs=new chefs();
				chefs.setVisible(true);
			}
		});
		btnChefsMngmnt.setBounds(10, 141, 181, 49);
		panel_1.add(btnChefsMngmnt);
		
		JButton btnAssignAChef = new JButton("Assign a Chef");
		btnAssignAChef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignChefandFood food=new AssignChefandFood();
				food.setVisible(true);
			}
		});
		btnAssignAChef.setBounds(10, 201, 181, 49);
		panel_1.add(btnAssignAChef);
	}
}
