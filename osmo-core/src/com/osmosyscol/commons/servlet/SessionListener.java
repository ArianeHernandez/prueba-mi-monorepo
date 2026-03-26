package com.osmosyscol.commons.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	private static List<HttpSession> sessions = new ArrayList<HttpSession>();

	public void sessionCreated(HttpSessionEvent event) {
		synchronized (this) {
			sessions.add(event.getSession());
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (this) {
			sessions.remove(event.getSession());
		}
	}

	public static long getSession() {

		long sessionCount = 0;

		for (HttpSession session : sessions) {
			try {

				if (session.getAttribute("login") != null) {
					sessionCount++;
				}

			} catch (Exception e) {
			}
		}

		return sessionCount;
	}

}
