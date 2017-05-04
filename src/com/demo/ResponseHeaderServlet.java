package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseHeaderServlet
 */
@WebServlet("/ResponseHeaderServlet")
public class ResponseHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseHeaderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setContentType("application/vnd.ms-excel");
//		PrintWriter out = response.getWriter();
//		out.println("\tQ1\tQ2\tQ3\tQ4\tTotal");
//		out.println("Apples\t78\t87\t92\t29\t=SUM(B2:E2)");
//		System.out.println("doGet....");
		
//		response.setContentType("image/jpeg");	
//		InputStream in = this.getClass().getClassLoader().getResourceAsStream("img002.jpg");
//		int len = in.available();
//		byte[] buffer = new byte[len];
//		in.read(buffer);
//		OutputStream out = response.getOutputStream();
//		out.write(buffer);
//		out.flush();
//		out.close();
		
		response.setHeader("refresh", "1");
		PrintWriter out = response.getWriter();
		out.println(new Date().toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
