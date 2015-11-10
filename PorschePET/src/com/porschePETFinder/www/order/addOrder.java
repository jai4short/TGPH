package com.porschePETFinder.www.order;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class addOrder extends HttpServlet {
	@SuppressWarnings("unchecked")
	private static final Logger log = Logger.getLogger(addOrder.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		
		ArrayList<String> orderEntry;
		String item, model, partKey;
		
		//define map to store order
		TreeMap<String, ArrayList> order; 
		
		//get the item, model, and item key from request
		item = req.getParameter("item");
		model = req.getParameter("model");
		partKey = req.getParameter("partKey");
		orderEntry = new ArrayList<String>();
		
		System.out.println(item);
		System.out.println(model);
		System.out.println(partKey);
		
		//Initialize datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//create query to fetch the proper vehicle info to assign to part
		Query query1 = new Query("Models");
		Filter filter = new FilterPredicate("ModelID", FilterOperator.EQUAL, model);
		query1.setFilter(filter);
		
		PreparedQuery pq = datastore.prepare(query1);
		
		Entity result = pq.asSingleEntity();
		
		System.out.println(result);
		
		item = result.getProperty("modelName") + " " + item;
		
		//get a session to store order
		HttpSession session = req.getSession();
		
		//if there is no session or there is no data yet stored in the session then create one
		if (session.isNew() || session.getAttribute("order") == null){
			session.setMaxInactiveInterval(1800);
			order = new TreeMap<String, ArrayList>();
			orderEntry.add(partKey);
			orderEntry.add(model);
			orderEntry.add(item);
			order.put(partKey, orderEntry);
			session.setAttribute("order", order);
			log.info("The session was either new or null so created a new session.");
		}
		//if a session already exists grab it so that the order can be added to
		else {
			order = (TreeMap<String, ArrayList>) session.getAttribute("order");
			orderEntry.add(partKey);
			orderEntry.add(model);
			orderEntry.add(item);
			order.put(partKey, orderEntry);
			session.setAttribute("order", order);
			log.info("The session was neither new nor null so added the item " + item);
		}
		
		log.info(order.toString());
		
		try {
			res.getOutputStream().println("Item added to Order. If your order is complete, click COMPLETE. If you want to add more items click CANCEL.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
