package com.whispers.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

public class SessionEventController implements HttpSessionListener {
	
	@Autowired
	UserController userController;
	
	private static int totalActiveSessions;
	
	public static int getTotalActiveSession(){
		return totalActiveSessions;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		totalActiveSessions++;
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		totalActiveSessions--;
	}	
	
}