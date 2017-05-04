package com.jdbcServlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcTools {
	
	public static void beginTx(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commitTx(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void rollback(Connection connection){
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setTransactionLevel(Connection conn){
		try {
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(String sql){
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			release(connection, statement);
		}
		
	}
	
	public static void release(Connection connection,Statement statement){
		if (statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void release(ResultSet resultSet,Connection connection,Statement statement){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() throws Exception{
		String driverClass;
		String url;
		String user;
		String password;
		InputStream in = JdbcTools.class.getClassLoader().getResourceAsStream("jdbc.property");
		
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		url = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		Class.forName(driverClass);
	
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
	
	public static void update(String sql,Object ...args){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0 ;i<args.length;i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			release(connection, preparedStatement);
		}	
	}
	
}
