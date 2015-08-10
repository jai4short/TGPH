package com.porschePETFinder.dataload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class loadModel extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws TooManyResultsException {
		String model, section, name, diagName, resultName = "", loadKey;
		
		loadKey = req.getParameter("loadKey");
		
		if (loadKey.equalsIgnoreCase("model")){
			//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			String modelYr, modelId;
			
			modelYr = req.getParameter("modelYr");
			modelId = req.getParameter("modelId");
			
			System.out.println(modelYr + " " + modelId);
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			Entity Model = new Entity("Models");
			Model.setProperty("modelName", modelYr);
			Model.setProperty("ModelID", modelId);
			
			datastore.put(Model);
		}
		
		else {
			//Initialize Blobstore 
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
			//model = req.getParameter("dir");
			//section = req.getParameter("section");
			model = req.getParameter("diagModel");
			name = req.getParameter("searchName");
			diagName = req.getParameter("diagName");
			
			Query query1 = new Query("Diagrams");
			Filter propertyFilter = new FilterPredicate("name", FilterOperator.EQUAL, name);
			query1.setFilter(propertyFilter);
			
			System.out.println(query1);
			
			PreparedQuery pq = datastore.prepare(query1);
			
			/**Entity result = pq.asSingleEntity();
				System.out.println(result);
				if (result == null)
					System.out.println("no result");
				else
					resultName = (String) result.getProperty("name");**/
				
			for (Entity result : pq.asIterable()) {
				result.setProperty("model", model);
				datastore.put(result);
				System.out.println(model);
				System.out.println(diagName);
				System.out.println(result);
			}
			
			
			/**System.out.println("model: " + model);
			System.out.println("name: " + name);
			System.out.println("section: " + section);
			System.out.println("query result: " + resultName);**/
		}
		
	}

}
