package com.porschePETFinder.www.emailService;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class contactUs extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		String name = req.getParameter("usr");
		String address = req.getParameter("mail");
		String message = req.getParameter("message");
		
		emailBuilder email = new emailBuilder(address + " /n /n " + message, "Contact Request from " + name, "admin@germanpartshound.com", "Admin");
		email.buildMessage();
		
		try {
			String output = "<html><head></head><body><p>Thanks for contacting us! <a href=\"http://www.thegermanpartshound.appspot.com\">Go back.</a></p>";
			res.getOutputStream().print(output);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
