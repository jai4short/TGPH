package com.porschePETFinder.www.order;

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

public class orderList extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		String key = req.getParameter("key");
		
		if (key.equalsIgnoreCase("P@$$w0rd")){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("order");
		PreparedQuery pq = datastore.prepare(query);
		
		String output = "<html> \n" +
		"	<head> \n" +
		"		<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\"> \n" +
		"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> \n" +
		"		<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script> \n" +
		"		<script src=\"addOrder.js\" type=\"text/javascript\"></script> \n"	+
		"	</head> \n" +
		"		<body> \n" +
		"			<form role=\"form\" action=\"/deleteOrder\" method=\"get\" \n" +
		"			<div class=\"row\"> \n" +
		"				<div class=\"col-md-1\">" +
		"					&nbsp; \n" +
		"				</div> \n" +
		"				<div class=\"col-md-10\">" +
		"					<table class=\"table-hover table-bordered\"> \n" +
		"						<thead> \n" +		
		"							<tr> \n" +
		"								<th>Select</th><th>Order ID</th><th>TimeStamp</th><th>Parts</th><th>Contact</th> \n" +
		"							</tr> \n" +
		"						</thead> \n" +
		"						<tbody> \n";
		
		for (Entity result : pq.asIterable()){
			output = output + "<tr> \n" +
								"<td><input type=\"checkbox\" /></td><td><a href=\"http://www.germanpartshound.com/orderPartList?orderID=" + KeyFactory.keyToString(result.getKey()) + "\">" + result.getKey() + "</a></td><td>" + result.getProperty("timestamp") + "</td><td>" + result.getProperty("items") + "</td><td>" + result.getProperty("contact") + "</td> \n" +
								"</tr> \n";
		}
		
		output = output + "</tbody> \n" +
						"</table> \n" +
					"</div> \n" +
	"				<div class=\"col-md-1\">" +
	"					&nbsp; \n" +
	"				</div> \n" +
				"</div> \n" +
	"			<div class=\"row\"> \n" +
	"				<div class=\"col-md-12\">" +
	"					&nbsp; \n" +
	"				</div> \n" +
	"			</div> \n" +
	"			<div class=\"row\"> \n" +
	"				<div class=\"col-md-2\">" +
	"					&nbsp; \n" +
	"				</div> \n" +
	"				<div class=\"col-md-3\">" +
	"					&nbsp;<button type=\"submit\" class=\"btn btn-default\">Remove</button> \n" +
	"				</div> \n" +
	"				<div class=\"col-md-7\">" +
	"					&nbsp; \n" +
	"				</div> \n" +
	"			</div> \n" +
				"</form> \n" +
	"<div class=\"row\">\r\n      \t\t<div class=\"col-lg-12\">\r\n      \t\t\t<form action=\"/queryDS\" method=\"post\" role=\"form\">\r\n      \t\t\t\t<div class=\"form-group\">\r\n      \t\t\t\t\t<label for=\"entity\">Entity</label>\r\n      \t\t\t\t\t<input type=\"text\" name=\"entity\" class=\"form-control\" id=\"entity\" placeholder=\"Enter query entity\"></input>\r\n      \t\t\t\t</div>\r\n      \t\t\t\t<div class=\"form-group\">\r\n      \t\t\t\t\t<label for=\"property\">Property</label>\r\n      \t\t\t\t\t<input type=\"text\" name=\"property\" class=\"form-control\" id=\"property\" placeholder=\"Enter query property to filter on\"></input>\r\n      \t\t\t\t</div>\r\n      \t\t\t\t<div class=\"form-group\">\r\n      \t\t\t\t\t<label for=\"filtervalue\">Filter Value</label>\r\n      \t\t\t\t\t<input type=\"text\" name=\"filtervalue\" class=\"form-control\" id=\"filtervalue\" placeholder=\"Enter query filter value\"></input>\r\n      \t\t\t\t</div>\r\n      \t\t\t\t<button class=\"btn btn-default\">Query</button>\r\n      \t\t\t</form>\r\n      \t\t</div>\r\n      \t</div>\r\n      \t<div class=\"row\">\r\n      \t\t<div class=\"col-lg-12\">\r\n      \t\t\t<form action=\"/deleteEntities\" method=\"post\" role=\"form\">\r\n      \t\t\t\t<div class=\"form-group\">\r\n      \t\t\t\t\t<label for=\"entity\">Entity</label>\r\n      \t\t\t\t\t<input type=\"text\" name=\"entity\" class=\"form-control\" id=\"entity\" placeholder=\"Enter entity to delete\"></input>\r\n      \t\t\t\t</div>\r\n      \t\t\t\t<button class=\"btn btn-default\">Delete</button>\r\n      \t\t\t</form>\r\n      \t\t</div>\r\n      \t</div> \n" +
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
		else{
			try {
				res.getOutputStream().print("incorrect password");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
