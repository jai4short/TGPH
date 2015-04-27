package com.porschePETFinder.www.parts;

import java.io.IOException;

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

public class getParts extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		String searchName = req.getParameter("searchName");
		System.out.println(searchName);
		
		String partName, description, modelOptionInfo, partNumberText, pos, price, qtyNeeded;
		
		Query query1 = new Query("parts");
		Filter propertyFilter = new FilterPredicate("diagramName", FilterOperator.EQUAL, searchName);
		query1.setFilter(propertyFilter);
		PreparedQuery pq = datastore.prepare(query1);
		System.out.println(pq);
		int i = 0;
		
		String output = "<html> \n" +
		"	<head> \n" +
		"		<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\"> \n" +
		"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> \n" +
		"		<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script> \n" +
		"		<script src=\"addOrder.js\" type=\"text/javascript\"></script> \n"	+
		"	</head> \n" +
		"		<body> \n" + 
		"			<table class=\"table-hover table-bordered\"> \n" +
		"				<thead> \n" +		
		"					<tr> \n" +
		"						<th>Description</th><th>Model Option Info</th><th>Part Number Text</th><th>Position</th><th>Qty Needed</th> \n" +
		"					</tr> \n" +
		"				</thead> \n" +
		"				<tbody> \n";
		
		System.out.println("Query result: ");
		
		for (Entity result : pq.asIterable()) {
			//partName = (String) result.getProperty("ID/Name");
			partName = KeyFactory.keyToString(result.getKey());
			description = (String) result.getProperty("description");
			modelOptionInfo = (String) result.getProperty("modelOptionInfo");
			partNumberText = (String) result.getProperty("partNumberText");
			pos = (String) result.getProperty("pos");
			//price = (String) result.getProperty("price");
			qtyNeeded = (String) result.getProperty("qtyNeeded");
			
			output = output + 	"	<tr id=\"id" + i + "\" onclick=\"addItem(this.firstElementChild.id)\" style=\"cursor:pointer;\"> \n" +
								"		<td id=\"item" + i + "\" name=\"item\">" + description + "</td><td>" + modelOptionInfo + "</td><td>" + partNumberText + "</td><td>" + pos + "</td><td>" + qtyNeeded + "</td> \n" +
								"	</tr> \n";
			i++;
		}
		
		output = output + "</tbody> \n" + 
				"</table> \n" + 
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
