package com.porschePETFinder.www.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class completeOrder extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		//String key = "P@$$w0rd";
		
		try {
			String contact = req.getParameter("contact");
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			String inputKey = req.getParameter("key");
			ArrayList orderItems = new ArrayList();
			ArrayList orderKeys = new ArrayList();
			
			//if (inputKey.equals(key)){
				HttpSession session = req.getSession();
				TreeMap<String, ArrayList> items = (TreeMap<String, ArrayList>) session.getAttribute("order");
				Set<String> keys = items.keySet();
				Iterator i = keys.iterator();
				while (i.hasNext()){
					ArrayList entries = items.get(i.next());
					orderItems.add(entries.get(2));
					orderKeys.add(entries.get(0));
				}
				
				Entity order = new Entity("order");
				order.setProperty("items", orderItems);
				order.setProperty("partKeys", orderKeys);
				order.setProperty("contact", contact);
				Date date = new Date();
				order.setProperty("timestamp", date);
				datastore.put(order);
				session.invalidate();
				
				try {
					res.setContentType("text/plain");
					res.getWriter().write("Order Submitted");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			/**}
			
			else{
				System.out.print("unauthorized request.");
			}**/
		}
		catch (NullPointerException e){
			
		}

	}

}
