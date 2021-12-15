package com.hotel.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import com.hotel.Jdbc.JdbcMysql;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class MenuManagement extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_3;
	private JTable table;
	private String menu_name;
	private String fooditem;
	private double food_price;
	private String hotel_name="";

	DefaultTableModel model;
	JComboBox comboBox;
	private int tablePrimaryKey;
	private JTextField textField_1;
	Map<Integer, String>hotels =new HashMap<>();
	//ArrayList<String>hotels =new ArrayList<>();

	public static void main(String[] args) {
		JdbcMysql.conectToOracleDatabase();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuManagement frame = new MenuManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MENU MANAGEMENT");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel.setBounds(246, 0, 233, 21);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE, 3));
		panel.setBounds(10, 39, 617, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Menu Name");
		lblNewLabel_1.setBounds(29, 11, 78, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(117, 8, 91, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCity = new JLabel("Food Item");
		lblCity.setBounds(29, 42, 78, 14);
		panel.add(lblCity);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(117, 39, 91, 20);
		panel.add(textField_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.RED, 2, true));
		panel_1.setBounds(146, 83, 334, 51);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD NEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				menu_name=textField.getText();
				fooditem=textField_3.getText();
				food_price=Double.parseDouble(textField_1.getText());
			    
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
					e.printStackTrace();
				}
				String sql="INSERT INTO menuitems (menu_name,fooditem,hotel_name,food_price) VALUES ('"+menu_name+"', '"+fooditem+"','"+hotel_name+"','"+food_price+"')";
				
			try {
						int i=JdbcMysql.stmt.executeUpdate(sql);
						System.out.println(i+" records affected"); 
						ResultSet rs = null;
						loadDatabaseToTable(rs,0);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Food Item Name Already Exist");
						e.printStackTrace();
					}  
			}
		});
		btnNewButton.setBounds(10, 11, 89, 29);
		panel_1.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					menu_name=textField.getText();
					fooditem=textField_3.getText();
					food_price=Double.parseDouble(textField_1.getText());
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
						e1.printStackTrace();
					}
				
				String sql="UPDATE menuitems SET menu_name='"+menu_name+"',fooditem='"+fooditem+"',food_price='"+food_price+"',hotel_name='"+hotel_name+"' WHERE id='"+tablePrimaryKey+"';";	
				System.out.println(sql);
				try {
					JdbcMysql.stmt.execute(sql);
					JOptionPane.showMessageDialog(null, "Your Data Is Updated");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Please Fill The Info Corectly");
					e1.printStackTrace();
				}
				try {
					loadDatabaseToTable(null,0);
					
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			
			}
		});
		btnUpdate.setBounds(122, 11, 89, 29);
		panel_1.add(btnUpdate);
		
		JButton btnDeletet = new JButton("DELETET");
		btnDeletet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql="DELETE FROM menuitems WHERE id ="+tablePrimaryKey;
				
				try {
					int rowaffrected=JdbcMysql.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Delete Error:Pleae Give Corect Information");
					e1.printStackTrace();
				}
				try {
					loadDatabaseToTable(null,0);
					JOptionPane.showMessageDialog(null, "One Food Item Record is Deleted");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnDeletet.setBounds(221, 11, 89, 29);
		panel_1.add(btnDeletet);
		
		JLabel lblNewLabel_2 = new JLabel("Select Hotel");
		lblNewLabel_2.setBounds(401, 11, 79, 14);
		panel.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		/*comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hotel_name=comboBox.getSelectedItem().toString();
				System.out.println(hotel_name);
			}
		});*/
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				hotel_name=comboBox.getSelectedItem().toString();
				System.out.println(hotel_name);
			}
		});
		
		
		
		comboBox.setBounds(479, 11, 109, 20);
		panel.add(comboBox);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(401, 45, 79, 14);
		panel.add(lblPrice);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(479, 42, 109, 20);
		panel.add(textField_1);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(518, 111, 89, 23);
		panel.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.BLUE, 3));
		panel_2.setBounds(10, 214, 617, 147);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SL", "Menu Name", "Food Item Name", "Food Item Price","Hotel Name"
			}
		));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
    	        try {
    	        	int row = table.getSelectedRow();
        	        tablePrimaryKey=(int)table.getModel().getValueAt(row, 0);
        	        System.out.println(tablePrimaryKey);
        	        textField.setText((String) table.getModel().getValueAt(row, 1));
        	        textField_3.setText((String) table.getModel().getValueAt(row, 2));
        	        textField_1.setText(""+table.getModel().getValueAt(row, 4));
        	        System.out.println(table.getModel().getValueAt(row, 3).toString());
        	       
				} catch (Exception e) {
					try {
						loadDatabaseToTable(null, 0);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}  
			}
		});
		table.setBounds(10, 11, 597, 125);
		panel_2.add(table);
		try {
			loadDatabaseToTable(null,0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		loadallHotelNames();
	}
	
	private void loadallHotelNames()
	{
		try {
			ResultSet rs =JdbcMysql.stmt.executeQuery("SELECT id ,name from hotels");
			 while (rs.next())
		      {
				 comboBox.addItem(rs.getString("name"));
				 hotels.put(rs.getInt("id"), rs.getString("name"));
		      }
			
			 rs.close();
			 hotel_name=comboBox.getItemAt(0).toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
private void loadDatabaseToTable(ResultSet rs1,int flag) throws SQLException {
		ResultSet rs = null;
		model =(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		if(flag==0)
		{
			 rs= JdbcMysql.stmt.executeQuery("select * from menuitems");
			 while (rs.next())
		      {
		    	   
				// System.out.println(rs.getString("p_name")+" "+rs.getString("street")+" "+rs.getString("city"));
				 Object rowData[]={rs.getInt("id"),rs.getString("menu_name"),rs.getString("fooditem"),rs.getString("hotel_name"),rs.getDouble("food_price")};
				 model.addRow(rowData);
				 
		      }
		      rs.close();
		}
	
		if(flag==1)
		{
		
      while (rs1.next())
      {
    	   
		// System.out.println(rs.getString("p_name")+" "+rs.getString("street")+" "+rs.getString("city"));
		 Object rowData[]={rs1.getInt("id"),rs1.getString("menu_name"),rs1.getString("fooditem"),rs1.getString("hotel_name"),rs1.getDouble("food_price")};
		 model.addRow(rowData);
		 
      }
      rs1.close();
	}
}
}
