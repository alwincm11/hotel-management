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

public class chefs extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private String name;
	private Date joinDate;
	private String ssn;
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
	public static void main(String[] args) {
		JdbcMysql.conectToOracleDatabase();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chefs frame = new chefs();
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
	public chefs() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(30, 33, 46, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(86, 30, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Date of join");
		lblStartDate.setBounds(193, 33, 75, 14);
		panel.add(lblStartDate);
		
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(272, 27, 91, 20);
		panel.add(dateChooser);
		
		JLabel lblRevenuePlanned = new JLabel("SSN");
		lblRevenuePlanned.setBounds(409, 33, 40, 14);
		panel.add(lblRevenuePlanned);
		
		textField_1 = new JTextField();
		textField_1.setBounds(464, 30, 99, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.RED, 2, true));
		panel_1.setBounds(154, 83, 313, 51);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("ADD NEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				name=textField.getText();
				joinDate=dateChooser.getDate();
				
				date=new SimpleDateFormat("yyyy-MM-dd").format(joinDate);
				ssn=textField_1.getText();
				
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
					e.printStackTrace();
				}
				String sql="INSERT INTO chef (name,joinDate,ssn) VALUES ('"+name+"', '"+date+"','"+ssn+"')";
				
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
					joinDate=dateChooser.getDate();
					
					date=new SimpleDateFormat("yyyy-MM-dd").format(joinDate);
					ssn=textField_1.getText();
				
				    
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Please Fill The Data Corectly");
						e1.printStackTrace();
					}
				
				String sql="UPDATE chef SET name='"+name+"',joinDate='"+date+"',ssn='"+ssn+"'WHERE id='"+tablePrimaryKey+"';";	
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
				
				String sql="DELETE FROM chef WHERE id ="+tablePrimaryKey;
				
				try {
					int rowaffrected=JdbcMysql.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Delete Error:Pleae Give Corect Information");
					e1.printStackTrace();
				}
				try {
					loadDatabaseToTable(null,0);
					JOptionPane.showMessageDialog(null, "One Chef Record is Deleted");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnDeletet.setBounds(202, 11, 89, 29);
		panel_1.add(btnDeletet);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 597, 125);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SL","Name", "Join Date", "SSN"
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
     
        	        Object a=table.getModel().getValueAt(row, 2);
        	        String localDateString=a.toString();
    	        	java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(localDateString);
    	        	dateChooser.setDate(date2);
				} catch (Exception e) {
					try {
						loadDatabaseToTable(null, 0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//e.printStackTrace();
				}  
			}
		});
		try {
			loadDatabaseToTable(null,0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
private void loadDatabaseToTable(ResultSet rs1,int flag) throws SQLException {
	ResultSet rs = null;
	model =(DefaultTableModel) table.getModel();
	model.setRowCount(0);
	if(flag==0)
	{
		 rs= JdbcMysql.stmt.executeQuery("select * from chef");
		 while (rs.next())
	      {
	    	   
			// System.out.println(rs.getString("p_name")+" "+rs.getString("street")+" "+rs.getString("city"));
			 Object rowData[]={rs.getInt("id"),rs.getString("name"),rs.getString("joinDate"),rs.getString("ssn")};
			 model.addRow(rowData);
			 
	      }
	      rs.close();
	}

	if(flag==1)
	{
	
  while (rs1.next())
  {
	   
	// System.out.println(rs.getString("p_name")+" "+rs.getString("street")+" "+rs.getString("city"));
	  Object rowData[]={rs1.getInt("id"),rs1.getString("name"),rs1.getString("joinDate"),rs1.getString("ssn")};
	 model.addRow(rowData);
	 
  }
  rs1.close();
}
	}
}
