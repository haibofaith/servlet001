package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
//		logic HttpServletRequest请求,HttpServletResponse 响应
//		PrintWriter out = resp.getWriter();
//		out.print("hello servlet!");
//		out.close();
//		
//		String str = this.getServletContext().getInitParameter("servlet_name");
//		System.out.println(str);
//		resp.sendRedirect("http://www.baidu.com");
		PrintWriter out = resp.getWriter();
		out.print("hello servlet!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		doGet(req, resp);
	}

}
