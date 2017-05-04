package com.jdbcServlet;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


public class UserDaoImpl implements UserDao {
	Connection conn;
	UserInfo obj;
	@Override
	public UserInfo login(String username, String password) {
		// TODO Auto-generated method stub
		QueryRunner queryRunner = new QueryRunner();
		String sql ="select username,password from userTbl where username=? and password=?";
		Connection connection = null;
		
		try {
			connection = JdbcTools.getConnection();
			obj = (UserInfo) queryRunner.query(connection, sql, new BeanHandler(UserInfo.class),username,password);
			System.out.println(obj);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcTools.release(connection, null);
		}
		return obj;
	}

}
