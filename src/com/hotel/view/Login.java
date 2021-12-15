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

public class Login extends JPanel{

	public JFrame frame;
	private JTextField textField;
	String name,pass;
	private JPasswordField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		initialize();
	}


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
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setForeground(Color.BLUE);
		panel_1.setBounds(111, 118, 420, 257);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(49, 76, 96, 31);
		panel_1.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.setBounds(210, 76, 200, 31);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(49, 118, 104, 35);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(210, 122, 200, 31);
		panel_1.add(textField_1);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(149, 187, 157, 31);
		panel_1.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name=textField.getText();
				pass=textField_1.getText();
				if(name.equals("admin"))
				{
					if(pass.equals("admin"))
					{
						JOptionPane.showMessageDialog(null, "LOGIN SUCCESS");
						MainMenu menu=new MainMenu();
						menu.frame.setVisible(true);
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, " YOU ENTERED WRONG PASSWORD ");

					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "YOU ENTERED WRONG USER NAME ");

				}
				
				
			}
		});
		//btnLogin.setIcon(new ImageIcon("icons/login.png"));
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.RED);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogin.setBackground(Color.RED);
		lblLogin.setBounds(180, 11, 68, 20);
		panel_1.add(lblLogin);
	}
}
