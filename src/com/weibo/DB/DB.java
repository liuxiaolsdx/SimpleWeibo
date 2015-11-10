package com.weibo.DB;

import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class DB {
	private Connection conn = null;
	private PreparedStatement state = null;
	private ResultSet rs = null;
	
	/**
	 * get property from file database.properties 
	 * and create connection with MySQL
	 */
	public void getConnection() {
		
		Properties props = new Properties();
		try (InputStream in = DB.class.getResourceAsStream("database.properties")) {
			props.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 String drivers = props.getProperty("jdbc.drivers");
		// if (drivers != null)
		// System.setProperty("jdbc.drivers", drivers);

		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		try {
			Class.forName(drivers);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		// String drivers = "com.mysql.jdbc.Driver";
		// if (drivers != null)
		// System.setProperty("jdbc.drivers", drivers);
		//
		// String username = "root";
		// String password = "root";
		// String url = "jdbc:mysql://localhost:3306/weibo";
		//
		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// System.out.println("not found class!");
		// }
		//
		// return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * close Connection, prepareStatement, ResultSet
	 */
	public void closeConn(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(state!=null){
			try {
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * execute query sql
	 * @param strSQL
	 * @param params: prepareStatement parameters
	 * @return
	 */
	public ResultSet executeQuery(final String strSQL,final Object[] params){
		getConnection();
		try {
			state = conn.prepareStatement(strSQL);
			for(int i=0; i< params.length ;i++){
				state.setObject(i+1, params[i]);
			}		
			rs = state.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * execute update sql
	 * @param strSQL
	 * @param params: prepareStatement parameters
	 * @return
	 */
	public int executeUpdate(final String strSQL,final Object[] params){
		getConnection();
		try {
			state = conn.prepareStatement(strSQL);
			for(int i=0; i< params.length ;i++){
				state.setObject(i+1, params[i]);
			}		
			int updateRow = state.executeUpdate();
			return updateRow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} 
	}
//	public int executeUpdateTimestamp(final String strSQL,final Object[] params){
//		getConnection();
//		try {
//			state = conn.prepareStatement(strSQL);
//			int i;
//			for(i=0; i< params.length-1 ;i++){
//				state.setObject(i+1, params[i]);
//			}
//				state.setTimestamp(i+1, (Timestamp) params[i]);
//			int updateRow = state.executeUpdate();
//			return updateRow;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return 0;
//		} 
//	}
//
}



