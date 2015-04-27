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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;



@SuppressWarnings("serial")
public class ParseJSON extends HttpServlet	{


public void doGet(HttpServletRequest req, HttpServletResponse res){
	
	//parameters = "pos=" + pos + "&partNumberText=" + partNumberText + "&description=" + description + "&qtyNeeded=" + qtyNeeded + "&price=" + price + "&modelOptionInfo=" + modelOptionInfo + "&diagramName=" + diagramName;
	/**String pos, partNumberText, description, qtyNeeded, price, modelOptionInfo, diagramName;
	pos = req.getParameter("pos");
	partNumberText = req.getParameter("partNumberText");
	description = req.getParameter("description");
	qtyNeeded = req.getParameter("qtyNeeded");
	price = req.getParameter("price");
	modelOptionInfo = req.getParameter("modelOptionInfo");
	diagramName = req.getParameter("diagramName");
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Entity part = new Entity("parts");
	part.setProperty("diagramName", diagramName);
	part.setProperty("pos", pos);
	part.setProperty("partNumberText", partNumberText);
	part.setProperty("description", description);
	part.setProperty("qtyNeeded", qtyNeeded);
	part.setProperty("price", price);
	part.setProperty("modelOptionInfo", modelOptionInfo);
	datastore.put(part); **/
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	Query query = new Query("Diagrams");
	PreparedQuery pq = datastore.prepare(query);
	
	for (Entity result: pq.asIterable(FetchOptions.Builder.withChunkSize(1000))){
		String oldUrl = (String) result.getProperty("imageUrl");
		String newUrl = oldUrl.replace("localhost:8888", "http://www.thegermanpartshound.appspot.com");
		result.setProperty("imageUrl", newUrl);
		System.out.println("The old url: " + oldUrl + " has been changed to: " + newUrl);
		datastore.put(result);
		System.out.println(result);
		
	}
	
}

}
