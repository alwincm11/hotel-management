package com.hotel.view;

import com.hotel.Jdbc.JdbcMysql;

public class AppRun {

	public static void main(String[] args) {
		Login login=new Login();
		login.frame.setVisible(true);
		JdbcMysql.conectToOracleDatabase();
		}

}
