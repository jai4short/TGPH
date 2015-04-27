package com.porschePETFinder.www.order;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class completeOrder extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		String key = "P@$$w0rd";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String inputKey = req.getParameter("key");
		
		if (inputKey.equals(key)){
			HttpSession session = req.getSession();
			
			Entity order = new Entity("order");
			order.setProperty("items", session.getAttribute("order"));
			datastore.put(order);
			session.invalidate();
			
			try {
				res.getOutputStream().println("Order submitted.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else{
			System.out.print("unauthorized request.");
		}
		

	}

}
