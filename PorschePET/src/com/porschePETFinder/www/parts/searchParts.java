package com.porschePETFinder.www.parts;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class searchParts extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		String partName, description, modelOptionInfo, partNumberText, pos, price, qtyNeeded, diagName;
		
		String searchName = "";
		
		String partNum = req.getParameter("partNum");
		 Query query = new Query("parts");
		 
		 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		 
		 Filter filter = new FilterPredicate("partNumberText", FilterOperator.EQUAL, partNum);
		 query.setFilter(filter);
		 
		 PreparedQuery pq = datastore.prepare(query);
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
					"								<th>DiagramName</th><th>Description</th><th>Model Option Info</th><th>Part Number Text</th><th>Position</th><th>Qty Needed</th> \n" +
					"							</tr> \n" +
					"						</thead> \n" +
					"						<tbody> \n";
					
					System.out.println("Query result: ");
					
					for (Entity result : pq.asIterable()) {
						//partName = (String) result.getProperty("ID/Name");
						partName = KeyFactory.keyToString(result.getKey());
						description = (String) result.getProperty("description");
						modelOptionInfo = (String) result.getProperty("modelOptionInfo");
						partNumberText = (String) result.getProperty("partNumberText");
						pos = (String) result.getProperty("pos");
						searchName = (String) result.getProperty("model");
						diagName = (String) result.getProperty("diagramName");
						//price = (String) result.getProperty("price");
						qtyNeeded = (String) result.getProperty("qtyNeeded");
						
						output = output + 	"	<tr id=\"id" + i + "\" onclick=\"getItem(this.children[1].id, this.children[3].id)\" style=\"cursor:pointer;\" data-toggle=\"modal\" data-target=\"#myModal\"> \n" +
											"		<td>" + diagName + "</td><td id=\"item" + i + "\" name=\"item\">" + description + "</td><td name=\"modeOptionInfo\">" + modelOptionInfo + "</td><td id=\"partNumTxt" + i + "\">" + partNumberText + "</td><td>" + pos + "</td><td>" + qtyNeeded + "</td> \n" +
											"	</tr> \n";
						i++;
					}
					
					output = output + "</tbody> \n" +
							"</table> \n" +
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
							      "</div>" +
							      "<div class=\"modal-footer\">" +
							      	"<form action=\"/viewOrder\" method=\"get\"> \n" +
							      		"<button type=\"button\" onclick=\"addItem(document.getElementById('orderResponse').id, '" + searchName + "')\" class=\"btn btn-default\">Yes</button> \n" +
							      		"<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">No</button> \n" +
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
