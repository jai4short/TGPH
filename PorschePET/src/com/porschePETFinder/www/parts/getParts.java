package com.porschePETFinder.www.parts;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

public class getParts extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String searchName = req.getParameter("model");
		String group = req.getParameter("group");
		System.out.println(searchName);
		
		String partName, description, modelOptionInfo, partNumberText, pos, price, qtyNeeded, partKey;
		
		Key key;
		
		Query query1 = new Query("parts"); 
		Query query2 = new Query("Diagrams");
		
		Filter propertyFilter = new FilterPredicate("model", FilterOperator.EQUAL, searchName);
		Filter propertyFilter2 = new FilterPredicate("section", FilterOperator.EQUAL, group);
		Filter propertyCompFilter = CompositeFilterOperator.and(propertyFilter, propertyFilter2);
		query1.setFilter(propertyCompFilter);
		query1.addSort("partNumberText", SortDirection.ASCENDING);
		query2.setFilter(propertyCompFilter);
		PreparedQuery pq = datastore.prepare(query1);
		PreparedQuery pq2 = datastore.prepare(query2);
		System.out.println(pq);
		System.out.println(pq2);
		
		Entity imgResult = pq2.asSingleEntity();
		String resultImage = (String) imgResult.getProperty("imageUrl");
		resultImage = resultImage.replace("localhost:8888", "www.germanpartshound.com");
		resultImage = resultImage.replace(" ", "_");
		resultImage = resultImage.replace(" ", "_");
		resultImage = resultImage.replace("(", "");
		resultImage = resultImage.replace(")", "");
		
		if (!resultImage.contains(".gif"))
			resultImage = resultImage + ".gif";
		int i = 0;
		
		String output = "<html> \n" +
		"	<head> \n" +
		"		<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\"> \n" +
		"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> \n" +
		"		<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script> \n" +
		"		<script src=\"addOrder.js\" type=\"text/javascript\"></script> \n"	+
		"	</head> \n" +
		"		<body> \n" +
		"			<div class=\"row\"> \n" +
		"				<div class=\"col-md-3\"> \n" +
		"					<form action=\"/viewOrder\" method=\"get\"> \n" +
		"						<h1>To view your current order click the button</h1> \n" +
		"						<button type=\"submit\" onclick=\"sendAjax('/viewOrder')\" class=\"btn btn-default\">View Order</button> \n" +
		"					</form> \n" +
		"				</div> \n" +
		"			</div> \n" +
		"			<div class=\"row\"> \n" +
		"				<div class=\"col-md-6\">" +
		"					<table class=\"table-hover table-bordered\"> \n" +
		"						<thead> \n" +		
		"							<tr> \n" +
		"								<th>Description</th><th>Model Option Info</th><th>Part Number Text</th><th>Position</th><th>Qty Needed</th> \n" +
		"							</tr> \n" +
		"						</thead> \n" +
		"						<tbody> \n";
		
		System.out.println("Query result: ");
		
		for (Entity result : pq.asIterable()) {
			key = result.getKey();
			partKey = KeyFactory.keyToString(key);
			partName = KeyFactory.keyToString(result.getKey());
			description = (String) result.getProperty("description");
			modelOptionInfo = (String) result.getProperty("modelOptionInfo");
			partNumberText = (String) result.getProperty("partNumberText");
			pos = (String) result.getProperty("pos");
			//price = (String) result.getProperty("price");
			qtyNeeded = (String) result.getProperty("qtyNeeded");
			
			output = output + 	"	<tr id=\"id" + i + "\" onclick=\"getItem(this.firstElementChild.id, this.children[2].id, '" + partKey +"')\" style=\"cursor:pointer;\" data-toggle=\"modal\" data-target=\"#myModal\"> \n" +
								"		<td id=\"item" + i + "\" name=\"item\">" + description + "</td><td>" + modelOptionInfo + "</td><td id=\"partNumTxt" + i + "\">" + partNumberText + "</td><td>" + pos + "</td><td>" + qtyNeeded + "</td> \n" +
								"	</tr> \n";
			i++;
		}
		
		output = output + "</tbody> \n" +
				"</table> \n" +
				"</div>" +
				"<div class=\"col-md-6\" >" +
				"<img src=\""+ resultImage + "\" class=\"img-responsive\" />" +
				"</div>" +
				"</div>" +
				"<div id=\"myModal\" class=\"modal fade\" role=\"dialog\">" +
				  "<div class=\"modal-dialog\">" +
				    "<div class=\"modal-content\">" +
				      "<div class=\"modal-header\">" +
				        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>" +
				        "<h4 class=\"modal-title\">Do you want to add this part to your order?</h4>" +
				      "</div>" +
				      "<div class=\"modal-body\">" +
				        "<p id=\"orderResponse\"></p>" +
				        "<p id=\"itemKey\" style=\"display:none;\"></p>" +
				      "</div>" +
				      "<div class=\"modal-footer\">" +
				      	"<form action=\"/viewOrder\" method=\"get\"> \n" +
				      		"<button id=\"addItemBtn\" type=\"button\" onclick=\"addItem(document.getElementById('orderResponse').id, '" + searchName.toString() + "')\" class=\"btn btn-default\">Yes</button> \n" +
				      		"<button id=\"dAddItemBtn\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">No</button> \n" +
				      		"<button type=\"submit\" class=\"btn btn-default\">View Order</button> \n" +
				      	"</form> \n" +
				      "</div>" +
				    "</div>" +"" +
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
