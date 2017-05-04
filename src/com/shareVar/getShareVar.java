package com.shareVar;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class getShareVar
 */
@WebServlet("/getShareVar")
public class getShareVar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getShareVar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext ctx = this.getServletContext();
		String ctxValue = (String) ctx.getAttribute("ctx_name");
		HttpSession session = request.getSession();
		String sessionValue = (String)session.getAttribute("session_name");
		String requestValue = (String)request.getAttribute("request_name");
		
		System.out.println("ctxValue:"+ctxValue);
		System.out.println("sessionValue:"+sessionValue);
		System.out.println("requestValue:"+requestValue);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
