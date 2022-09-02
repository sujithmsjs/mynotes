package com.suji.lsnr;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCheck
 *
 */
public class SessionCheck implements HttpSessionListener {

	
	private int count;
    /**
     * Default constructor. 
     */
    public SessionCheck() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	count++;
    	System.out.println("Session no."+count+" Created.");
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	System.out.println("Session no."+count+" Destroyed.");
    }
	
}
