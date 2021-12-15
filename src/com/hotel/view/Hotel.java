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

public class Hotel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JTextField textField_5;
	private String name;
	private Date startDate;
	private double revenueplanned;
	private double expenceExpected;
	private String city;
	private String state;
	JDateChooser dateChooser;
	String date;
	DefaultTableModel model;
	private int tablePrimaryKey;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		JdbcMysql.conectToOracleDatabase();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hotel frame = new Hotel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Hotel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOTEL DETAILS");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel.setBounds(246, 0, 163, 21);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE, 3));
		panel.setBounds(10, 39, 617, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(29, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(85, 8, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(195, 11, 75, 14);
		panel.add(lblStartDate);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(267, 5, 91, 20);
		panel.add(dateChooser);
		
		JLabel lblRevenuePlanned = new JLabel("Revenue Planned");
		lblRevenuePlanned.setBounds(368, 11, 99, 14);
		panel.add(lblRevenuePlanned);
		
		textField_1 = new JTextField();
		textField_1.setBounds(489, 8, 99, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblExpExpected = new JLabel("Exp : expected");
		lblExpExpected.setBounds(10, 42, 75, 14);
		panel.add(lblExpExpected);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(85, 39, 86, 20);
		panel.add(textField_2);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(197, 39, 75, 14);
		panel.add(lblCity);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(267, 36, 91, 20);
		panel.add(textField_3);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(404, 36, 75, 14);
		panel.add(lblState);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(489, 36, 99, 20);
		panel.add(textField_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.RED, 2, true));
		panel_1.setBounds(39, 83, 549, 51);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD NEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				name=textField.getText();
				startDate=dateChooser.getDate();
				
				date=new SimpleDateFormat("yyyy-MM-dd").format(startDate);
				revenueplanned=Double.parseDouble(textField_1.getText());
				expenceExpected=Double.parseDouble(textField_2.getText());
				city=textField_3.getText();
			    state=textField_4.getText();
			    
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
					e.printStackTrace();
				}
				String sql="INSERT INTO hotels (name,startDate,revenueplanned,expenceExpected,city,state) VALUES ('"+name+"', '"+date+"','"+revenueplanned+"','"+expenceExpected+"','"+city+"', '"+state+"')";
				
			try {
						int i=JdbcMysql.stmt.executeUpdate(sql);
						System.out.println(i+" records affected"); 
						ResultSet rs = null;
						loadDatabaseToTable(rs,0);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Hotel Name Already Exist");
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
					name=textField.getText();
					startDate=dateChooser.getDate();
					
					date=new SimpleDateFormat("yyyy-MM-dd").format(startDate);
					revenueplanned=Double.parseDouble(textField_1.getText());
					expenceExpected=Double.parseDouble(textField_2.getText());
					city=textField_3.getText();
				    state=textField_4.getText();
				    
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
						e1.printStackTrace();
					}
				
				String sql="UPDATE hotels SET name='"+name+"',startDate='"+date+"',revenueplanned='"+revenueplanned+"',expenceExpected='"+expenceExpected+"',city='"+city+"',state='"+state+"' WHERE id='"+tablePrimaryKey+"';";	
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
		btnUpdate.setBounds(103, 11, 89, 29);
		panel_1.add(btnUpdate);
		
		JButton btnDeletet = new JButton("DELETET");
		btnDeletet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql="DELETE FROM hotels WHERE id ="+tablePrimaryKey;
				
				try {
					int rowaffrected=JdbcMysql.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Delete Error:Pleae Give Corect Information");
					e1.printStackTrace();
				}
				try {
					loadDatabaseToTable(null,0);
					JOptionPane.showMessageDialog(null, "One Hotel Record is Deleted");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnDeletet.setBounds(202, 11, 89, 29);
		panel_1.add(btnDeletet);
		
		textField_5 = new JTextField();
		textField_5.setBounds(301, 11, 112, 29);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnSearchByName = new JButton("Search By Name");
		btnSearchByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name=textField_5.getText();
				String sql ="SELECT * FROM hotels WHERE name LIKE '"+name+"%'";
				try {
					ResultSet rs =JdbcMysql.stmt.executeQuery(sql);
					loadDatabaseToTable(rs, 1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearchByName.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnSearchByName.setBounds(423, 11, 116, 29);
		panel_1.add(btnSearchByName);
		
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
				"SL","Name", "Start Date", "Revenue Planned", "Expence Expected", "city", "State"
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
        	        textField_1.setText(""+table.getModel().getValueAt(row, 3));
        	        textField_2.setText(""+table.getModel().getValueAt(row, 4));
        	        textField_3.setText((String) table.getModel().getValueAt(row, 5));
        	        textField_4.setText((String) table.getModel().getValueAt(row, 6));
        	        Object a=table.getModel().getValueAt(row, 2);
        	        String localDateString=a.toString();
    	        	 java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(localDateString);
    	        	 dateChooser.setDate(date2);
				} catch (Exception e) {
					try {
						loadDatabaseToTable(null, 0);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					//e.printStackTrace();
				}  
			}
		});
		table.setBounds(10, 11, 597, 125);
		panel_2.add(table);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(523, 190, 104, 23);
		contentPane.add(btnNewButton_1);
		try {
			loadDatabaseToTable(null,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void loadDatabaseToTable(ResultSet rs1,int flag) throws SQLException {
		ResultSet rs = null;
		if(flag==0)
		{
			 rs= JdbcMysql.stmt.executeQuery("select * from hotels");
		}
		if(flag==1)
		{
			rs=rs1;
		}
		
		model =(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
      while (rs.next())
      {
		// System.out.println(rs.getString("p_name")+" "+rs.getString("street")+" "+rs.getString("city"));
		 Object rowData[]={rs.getInt("id"),rs.getString("name"),rs.getString("startDate"),rs.getDouble("revenueplanned"),rs.getDouble("expenceExpected"),rs.getString("city"),rs.getString("state")};
		 model.addRow(rowData);
		 
      }
      rs.close();
	}
}
