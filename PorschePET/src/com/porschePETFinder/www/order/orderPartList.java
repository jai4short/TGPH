package com.porschePETFinder.www.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Key;

public class orderPartList extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String orderId = req.getParameter("orderID");
		Key orderKey = KeyFactory.stringToKey(orderId);
		ArrayList partIDs;
		ArrayList orderPartNames;
		String partName, description, modelOptionInfo, partNumberText, pos, price, qtyNeeded, partKey;
		
		Query getPartIDs = new Query("order");
		Query getPart = new Query("parts");
		Key partID;
		
		Filter orderFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, orderKey);
		Filter partFilter;
		
		getPartIDs.setFilter(orderFilter);
		
		PreparedQuery pq = datastore.prepare(getPartIDs);
		PreparedQuery partQuery;
		
		Entity result = pq.asSingleEntity();
		Entity partResult;
		
		partIDs = (ArrayList) result.getProperty("partKeys");
		orderPartNames = (ArrayList) result.getProperty("items");
		
		String output = "<html> \n" +
		"	<head> \n" +
		"		<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\"> \n" +
		"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> \n" +
		"		<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script> \n" +
		"		<script src=\"addOrder.js\" type=\"text/javascript\"></script> \n"	+
		"	</head> \n" +
		"		<body> \n" +
		"			<div class=\"row\"> \n" +
		"				<div class=\"col-md-12\"> \n" +
		"						<h1>" + result.getKey() + " " + result.getProperty("timestamp") + "</h1> \n" +
		"						<h2>" + result.getProperty("contact") + "</h2> \n" +
		"				</div> \n" +
		"			</div> \n" +
		"			<div class=\"row\"> \n" +
		"				<div class=\"col-md-12\">" +
		"					<table class=\"table-hover table-bordered\"> \n" +
		"						<thead> \n" +		
		"							<tr> \n" +
		"								<th>Item</th><th>Description</th><th>Model Option Info</th><th>Part Number Text</th><th>Position</th><th>Price</th><th>Qty Needed</th> \n" +
		"							</tr> \n" +
		"						</thead> \n" +
		"						<tbody> \n";
		
		for (int i = 0; i < partIDs.size(); i++){
			partID = KeyFactory.stringToKey((String) partIDs.get(i));
			partFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, partID);
			getPart.setFilter(partFilter);
			partQuery = datastore.prepare(getPart);
			partResult = partQuery.asSingleEntity();
			
			description = (String) partResult.getProperty("description");
			modelOptionInfo = (String) partResult.getProperty("modelOptionInfo");
			partNumberText = (String) partResult.getProperty("partNumberText");
			pos = (String) partResult.getProperty("pos");
			price = (String) partResult.getProperty("price");
			qtyNeeded = (String) partResult.getProperty("qtyNeeded");
			
			output = output + 	"	<tr> \n" +
					"		<td>" + orderPartNames.get(i) + "</td><td>" + description + "</td><td>" + modelOptionInfo + "</td><td id=\"partNumTxt" + i + "\">" + partNumberText + "</td><td>" + pos + "</td><td>" + price + "</td><td>" + qtyNeeded + "</td> \n" +
					"	</tr> \n";
		}
		
		output = output + "</tbody> \n" +
				"</table> \n" +
				"</div>" +
				"</body> \n" + 
				"</html>";
		try {
			System.out.println(output);
			res.getOutputStream().print(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
