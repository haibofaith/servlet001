package com.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MyhttpSessionListener
 *
 */
public class MyhttpSessionListener implements HttpSessionListener {
	int count;
	HttpSession session;
    /**
     * Default constructor. 
     */
    public MyhttpSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	session = arg0.getSession();
    	count = (int) session.getServletContext().getAttribute("onLineCount");
    	count++;
    	System.out.println("online people:"+count);
    	session.getServletContext().setAttribute("onLineCount", count);
    	System.out.println("sessionCreated...");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	count--;
    	System.out.println("online people:"+count);
    	session.getServletContext().setAttribute("onLineCount", count);
    }
	
}
