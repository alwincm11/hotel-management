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
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AssignChefandFood extends JFrame {

	private JPanel contentPane;
	private String name;
	private String ssn;
	String date;
	DefaultTableModel model;
	private int tablePrimaryKey;
	private String hotel_name="";
	JComboBox comboBox_2,comboBoxHotel,comboBoxFood;
	private String foodItem;
	public static ResultSet rs=null;
	private JTable table;
	
	public static void main(String[] args) {
		JdbcMysql.conectToOracleDatabase();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignChefandFood frame = new AssignChefandFood();
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
	public AssignChefandFood() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHEFS MANAGEMENT");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel.setBounds(214, 0, 247, 28);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE, 3));
		panel.setBounds(10, 39, 617, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hotel");
		lblNewLabel_1.setBounds(10, 33, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblStartDate = new JLabel("Food Items");
		lblStartDate.setBounds(173, 33, 75, 14);
		panel.add(lblStartDate);
		
		JLabel lblRevenuePlanned = new JLabel("SSN");
		lblRevenuePlanned.setBounds(390, 33, 40, 14);
		panel.add(lblRevenuePlanned);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.RED, 2, true));
		panel_1.setBounds(186, 83, 189, 51);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD NEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
					e.printStackTrace();
				}
				String sql="INSERT INTO assigned (hotel_name,foodItem,ssn) VALUES ('"+hotel_name+"', '"+foodItem+"','"+ssn+"')";
				
			try {
						int i=JdbcMysql.stmt.executeUpdate(sql);
						System.out.println(i+" records affected"); 
						ResultSet rs = null;
						loadDatabaseToTable(rs,0);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Database Error");
						e.printStackTrace();
					}  
			}
		});
		btnNewButton.setBounds(42, 11, 89, 29);
		panel_1.add(btnNewButton);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ssn=comboBox_2.getSelectedItem().toString();
			}
		});
		comboBox_2.setBounds(440, 30, 99, 20);
		panel.add(comboBox_2);
		
		comboBoxHotel = new JComboBox();
		comboBoxHotel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				hotel_name=comboBoxHotel.getSelectedItem().toString();
				System.out.println("item changed : "+hotel_name);
				              
			}
		});
		comboBoxHotel.setBounds(66, 30, 86, 20);
		panel.add(comboBoxHotel);
		
		comboBoxFood = new JComboBox();
		comboBoxFood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				foodItem=comboBoxFood.getSelectedItem().toString();
			}
		});
		comboBoxFood.setBounds(238, 30, 113, 20);
		panel.add(comboBoxFood);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadaFoodItemsBasedOnHotel();    
			}
		});
		btnNewButton_1.setBounds(259, 58, 62, 14);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		btnNewButton_2.setBounds(518, 111, 89, 23);
		panel.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.BLUE, 3));
		panel_2.setBounds(10, 214, 617, 147);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 597, 125);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Hotel Name", "Food Item", "SSN"
			}
		));
		try {
			loadDatabaseToTable(null,0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 loadallHotelNames();
		 loadaSSN();
		 
	}
private void loadDatabaseToTable(ResultSet rs1,int flag) throws SQLException {
	ResultSet rs = null;
	model =(DefaultTableModel) table.getModel();
	model.setRowCount(0);
	if(flag==0)
	{
		 rs= JdbcMysql.stmt.executeQuery("select * from assigned");
		 while (rs.next())
	      {
	    	   
		
			 Object rowData[]={rs.getInt("id"),rs.getString("hotel_name"),rs.getString("foodItem"),rs.getString("ssn")};
			 model.addRow(rowData);
			 
	      }
	      rs.close();
	}

	if(flag==1)
	{
	
  while (rs1.next())
  {
	  Object rowData[]={rs1.getInt("id"),rs1.getString("hotel_name"),rs1.getString("foodItem"),rs1.getString("ssn")};
	 model.addRow(rowData);
	 
  }
  rs1.close();
}
	}

private void loadallHotelNames()
{

	try {
		rs=JdbcMysql.stmt.executeQuery("SELECT name from hotels");
		 while (rs.next())
	      {
			// comboBox.addItem(rs3.getString("name"));
			 String value=rs.getString("name");
			 comboBoxHotel.addItem(value);
			 System.out.println("inside : "+value);
	      }
		 System.out.println("outside");
		
		 rs.close();
		
	} catch (Exception e) {

		e.printStackTrace();
	}
}

public void loadaFoodItemsBasedOnHotel() {
	System.out.println("loadaFoodItemsBasedOnHotel");
	try {
		String sql="SELECT fooditem from menuitems WHERE hotel_name='"+hotel_name+"'";
		System.out.println(sql);

		 rs=JdbcMysql.stmt.executeQuery(sql);
		 comboBoxFood.removeAllItems();
		 while (rs.next())
	      {
			 comboBoxFood.addItem(rs.getString("fooditem"));
	      }
		
	 rs.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void loadaSSN() {

	try {
		String sql="SELECT ssn FROM chef";
		System.out.println(sql);

		 rs=JdbcMysql.stmt.executeQuery(sql);
		 comboBoxFood.removeAllItems();
		 while (rs.next())
	      {
			 comboBox_2.addItem(rs.getString("ssn"));
	      }
		
	 rs.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
