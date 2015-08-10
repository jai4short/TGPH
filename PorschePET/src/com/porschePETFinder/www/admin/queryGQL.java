package com.porschePETFinder.www.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;




public class queryGQL extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		String kind = req.getParameter("kind");
		String output = "";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query query = new Query(kind);
		query.addProjection(new PropertyProjection("model", String.class));
		query.addProjection(new PropertyProjection("section", String.class));
		query.setDistinct(true);
		List<Entity> results = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10000));
		
		for (Entity result: results){
			output = output + result.getProperty("model") + ", " + result.getProperty("section") + "\n";
			System.out.println(result);
		}
		
		try {
			res.getOutputStream().print(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
