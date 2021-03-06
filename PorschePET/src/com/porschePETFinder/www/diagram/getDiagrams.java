package com.porschePETFinder.www.diagram;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class getDiagrams extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		String partGroup = req.getParameter("group");
		
		if (partGroup == null){
			String model = req.getParameter("model");
			
			String output = "<html> \n" +
					"	<head> \n" +
					"		<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\"> \n" +
					"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> \n" +
					"		<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script> \n" +
					"		<script src=\"addOrder.js\" type=\"text/javascript\"></script> \n"	+
					"	</head> \n" +
					"	<body> \n" +
					"		<div class=\"container\"> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Engine&model=" + model + "\"><h1>Engine</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Fuel/Exhaust&model=" + model + "\"><h1>Fuel\\Exhaust</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Body&model=" + model + "\"><h1>Body</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Electric&model=" + model + "\"><h1>Electrical</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Brakes/Wheels&model=" + model + "\"><h1>Brakes\\Wheels</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Transmission&model=" + model + "\"><h1>Transmission</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Pedal%20System/Levers&model=" + model + "\"><h1>Pedal System\\Levers</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"			<div class=\"row\">	\n" +
					"				<div class=\"col-lg-12\"> \n" +
					"					<a href=\"http://www.germanpartshound.com\\getDiagrams?group=Front%20Axle/Steering&model=" + model + "\"><h1>Front Axle\\Steering</h1></a> \n" +
					"				</div> \n" +
					"			</div> \n" +
					"		</div> \n" +
					"	</body> \n" +
					"</html>";
			try {
				System.out.println(output);
				res.getOutputStream().print(output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		String model = req.getParameter("model");
		System.out.println(model);
		
		String resultName, resultImage, resultModel, resultSection, output;
		
		output = "<html> \n" +
		"<head> \n" + 
		"	</head> \n" +
		"		<body> \n" + 
		"			<table border=\"1\"> \n";
		
		ArrayList diagrams = new ArrayList();
		ArrayList images = new ArrayList();
		ArrayList sections = new ArrayList();
		ArrayList models = new ArrayList();
		
		Query query1 = new Query("Diagrams");
		Filter propertyFilter = new FilterPredicate("model", FilterOperator.EQUAL, model);
		Filter propertyFilter2 = new FilterPredicate("group", FilterOperator.EQUAL, partGroup);
		Filter filters = CompositeFilterOperator.and(propertyFilter, propertyFilter2);
		query1.setFilter(filters);
		
		System.out.println(query1);
		
		PreparedQuery pq = datastore.prepare(query1);
		
		for (Entity result : pq.asIterable()) {
			resultName = (String) result.getProperty("name");
			resultModel = (String) result.getProperty("model");
			resultSection = (String) result.getProperty("section");
			resultImage = (String) result.getProperty("imageUrl");
			resultImage = resultImage.replace("localhost:8888", "www.germanpartshound.com");
			resultImage = resultImage.replace(" ", "_");
			resultImage = resultImage.replace("(", "");
			resultImage = resultImage.replace(")", "");
			
			if (!resultImage.contains(".gif"))
				resultImage = resultImage + ".gif";
			
			//resultImage = resultImage.replace("http://www.germanpartshound.com|images|", "http://www.germanpartshound.com/images/");
			images.add(resultImage);
			diagrams.add(resultName);
			sections.add(resultSection);
			models.add(resultModel);
		}
		
		for (int i = 0; i < images.size(); i++){
			output = output + "<tr> \n" + "" +
							"	<th> \n" + 
									diagrams.get(i) + 
							"	</th> \n" +
							"</tr> \n" +
							"<tr> \n" +
							"	<td> \n" + 
							"		<a href=\"http://www.germanpartshound.com/getParts?model=" + models.get(i) + "&group=" + sections.get(i) + "\"><img src=\"" + images.get(i) + "\" /></a> \n" +
							//"		<a href=\"http://www.germanpartshound.com/getParts?searchName=" + diagrams.get(i) + "\"><img src=\"" + images.get(i) + "\" /></a> \n" +
							"	</td> \n" +
							"</tr> \n";
		}
		
		output = output + "</table> \n" + 
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

}
