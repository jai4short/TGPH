package com.porschePETFinder.www.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class viewOrder extends HttpServlet {
	private static final Logger log = Logger.getLogger(addOrder.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		HttpSession session = req.getSession();
		
		System.out.println("order: " + session.getAttribute("order"));
		try {
			TreeMap<String, ArrayList> order = (TreeMap<String, ArrayList>) session.getAttribute("order");
			Set<String> keys = order.keySet();
		String output = "<html> \n" +
				"	<head> \n" +
				"		<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\"> \n" +
				"		<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> \n" +
				"		<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script> \n" +
				"		<script src=\"addOrder.js\" type=\"text/javascript\"></script> \n"	+
				"	</head> \n" +
				"	<body> \n" +
				"	<form id=\"removeItems\" role=\"form\" action=\"/deleteOrder\" method=\"get\" \n" +	
				"		<div class=\"container\"> \n" +
				"			<div class=\"row\" style=\"height:33%;\">&nbsp;</div> \n" + 
				"			<div class=\"row\">	\n" +
				"				<div class=\"col-lg-2\"> \n" +
				"					&nbsp; \n" +
				"				</div> \n" +
				"				<div class=\"col-lg-8\"> \n" +
				"					<table class=\"table-hover table-bordered\" > \n" + 
				"						<thead> \n" + 
				"							<tr> \n" +
				" 								<th>Order</th> \n" +
				"							</tr> \n" +
				"						</thead> \n" +
				"						<tbody> \n";
				
				//for (int i = 0; i < keys.size(); i++){
				Iterator i = keys.iterator();
				while (i.hasNext()){
					ArrayList orderEntry = order.get(i.next());
					output = output +
							"				<tr> \n" +
							"					<td> \n" +
							"						<div class=\"checkbox\"> \n" +
							"							<label>&nbsp;<input type=\"checkbox\" name=\"items\" value=\"" + orderEntry.get(0) + "\">&nbsp; " + orderEntry.get(2) + " &nbsp; </label> \n" +
							"						</div> \n" +
							"					</td> \n" +
							"				</tr> \n";
					System.out.println("orderEntry: " + orderEntry);
				}
				
				output = output +
				"	</form> \n" +
				"						</tbody> \n" +
				"					</table> \n" +
				"					<form id=\"completeOrder\" role=\"form\" method=\"post\" action=\"/completeOrder\"> \n" +
				"						<br> <input id=\"contact\" name=\"contact\" type=\"email\" placeholder=\"Enter your email\" required/> <br> \n" +
				"						<br><br><p id=\"validationMsg\" style=\"color:red;\"></p> \n" +
				"					</form> \n" +				
				"					<br> <button type=\"button\" onclick=\"removeItems()\" class=\"btn btn-default\">Remove</button> \n" +
				"					<button type=\"button\" onclick=\"completeOrder()\" class=\"btn btn-default\">Submit Order</button> \n" +
				"					<br><br><p>Press back to navigate to previous page or <a href=\"http://www.thegermanpartshound.appspot.com\">go back to the homepage and continue your order here.</a></p>" +	
				"				</div> \n" +
				"			</div> \n" +
				"		</div> \n" +
				"	</body> \n" +
				"</html>";
				
				try {
					log.info(output);
					res.getOutputStream().print(output);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		} catch (NullPointerException e) {
			e.printStackTrace();
			
			try {
				String output = "<html><head></head><body><p>You have not added any items to your order. <a href=\"http://www.thegermanpartshound.appspot.com\">Go back.</a></p>";
				log.info("");
				res.getOutputStream().print(output);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	
		}
	}

}
