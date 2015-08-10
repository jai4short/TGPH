package com.porschePETFinder.www.admin;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class deleteEntities extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String entity = req.getParameter("entity");
		
		Query query = new Query(entity);
		PreparedQuery pq = datastore.prepare(query);
		
		for (Entity result: pq.asIterable()){
			Key key = result.getKey();
			datastore.delete(key);
		}
		
		System.out.println("All " + entity + " entities have been deleted.");
	}
}
