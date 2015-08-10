package com.porschePETFinder.dataload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;



@SuppressWarnings("serial")
public class ParseJSON extends HttpServlet	{


public void doGet(HttpServletRequest req, HttpServletResponse res){
	
	//parameters = "pos=" + pos + "&partNumberText=" + partNumberText + "&description=" + description + "&qtyNeeded=" + qtyNeeded + "&price=" + price + "&modelOptionInfo=" + modelOptionInfo + "&diagramName=" + diagramName;
	String pos, partNumberText, description, qtyNeeded, price, modelOptionInfo, diagramName, model, section;
	pos = req.getParameter("pos");
	partNumberText = req.getParameter("partNumberText");
	description = req.getParameter("description");
	qtyNeeded = req.getParameter("qtyNeeded");
	price = req.getParameter("price");
	modelOptionInfo = req.getParameter("modelOptionInfo");
	diagramName = req.getParameter("diagramName");
	model = req.getParameter("dir");
	section = req.getParameter("section");
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Entity part = new Entity("parts");
	part.setProperty("diagramName", diagramName);
	part.setProperty("pos", pos);
	part.setProperty("partNumberText", partNumberText);
	part.setProperty("description", description);
	part.setProperty("qtyNeeded", qtyNeeded);
	part.setProperty("price", price);
	part.setProperty("modelOptionInfo", modelOptionInfo);
	part.setProperty("model", model);
	part.setProperty("section", section);
	datastore.put(part);
	
	/**String model, section, newUrl;
	
	model = req.getParameter("model");
	section = req.getParameter("sec");
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Query query = new Query("Diagrams");
	Filter filter = new FilterPredicate("model", FilterOperator.EQUAL, model);
	Filter filter2 = new FilterPredicate("section", FilterOperator.EQUAL, section);
	CompositeFilter filter3 = CompositeFilterOperator.and(filter, filter2);
	query.setFilter(filter3);
	PreparedQuery pq = datastore.prepare(query);
	try {
		Entity result = pq.asSingleEntity();
		newUrl = "http://www.thegermanpartshound.appspot.com/images/noImage.gif";
		result.setProperty("imageUrl", newUrl);
		datastore.put(result);
	} catch(TooManyResultsException e){
		e.printStackTrace();
		newUrl = "http://www.thegermanpartshound.appspot.com/images/noImage.gif";
		System.out.println(pq);
		for (Entity result:pq.asIterable()){
			System.out.println(result);
			result.setProperty("imageUrl", newUrl);
			datastore.put(result);
		}
	}**/
	}

}
