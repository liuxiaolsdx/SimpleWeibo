package com.weibo.DB;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.weibo.util.WeiboLogger;

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
			WeiboLogger.exception(e1);
		}

		String drivers = props.getProperty("jdbc.drivers");
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		try {
			Class.forName(drivers);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			WeiboLogger.exception(e);
		}
	}
	
	/**
	 * close Connection, prepareStatement, ResultSet
	 */
	public void closeConn() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				WeiboLogger.exception(e);
			}
		}
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				WeiboLogger.exception(e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				WeiboLogger.exception(e);
			}
		}
	}

	/**
	 * execute query sql
	 * @param strSQL
	 * @param params: prepareStatement parameters
	 * @return
	 */
	public ResultSet executeQuery(final String strSQL, final Object[] params) {
		getConnection();
		try {
			state = conn.prepareStatement(strSQL);
			for (int i = 0; i < params.length; i++) {
				state.setObject(i + 1, params[i]);
			}
			rs = state.executeQuery();
			return rs;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return null;
		}
	}
	
	/**
	 * execute update sql
	 * @param strSQL
	 * @param params: prepareStatement parameters
	 * @return
	 */
	public int executeUpdate(final String strSQL, final Object[] params) {
		getConnection();
		try {
			state = conn.prepareStatement(strSQL);
			for (int i = 0; i < params.length; i++) {
				state.setObject(i + 1, params[i]);
			}
			int updateRow = state.executeUpdate();
			return updateRow;
		} catch (SQLException e) {
			WeiboLogger.exception(e);
			return 0;
		}
	}
}



