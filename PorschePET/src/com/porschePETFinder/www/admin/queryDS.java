package com.porschePETFinder.www.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class queryDS extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String entity, filterValue, filterProperty;
		
		entity = req.getParameter("entity");
		filterProperty = req.getParameter("property");
		filterValue = req.getParameter("filtervalue");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query(entity);
		query.addFilter(filterProperty, Query.FilterOperator.EQUAL, filterValue);
		
		System.out.println(query);
		
		PreparedQuery pq = datastore.prepare(query);
		
		for (Entity result: pq.asIterable()){
			PrintWriter out = res.getWriter();
			out.println(result);
		}
	}

}
