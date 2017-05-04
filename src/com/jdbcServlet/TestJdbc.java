package com.jdbcServlet;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class TestJdbc {
	Connection conn;
	@Test
	public void test() {
		try {
			conn = JdbcTools.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conn);
	}

}
