package com.jdbcServlet;

public interface UserDao {
	public UserInfo login(String username,String password);
}
