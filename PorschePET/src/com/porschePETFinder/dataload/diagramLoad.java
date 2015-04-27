package com.porschePETFinder.dataload;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;



@SuppressWarnings("serial")

public class diagramLoad extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String diagramName = req.getParameter("diagName");
		String diagramURL = req.getParameter("diagUrl");
		String diagramModel = req.getParameter("diagModel");
		String diagramSection = req.getParameter("diagSec");
		String diagramGroup = req.getParameter("groupName");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity diagram = new Entity("Diagrams");
		diagram.setProperty("name", diagramName);
		diagram.setProperty("imageUrl", diagramURL);
		diagram.setProperty("model", diagramModel);
		diagram.setProperty("section", diagramSection);
		diagram.setProperty("group", diagramGroup);
		datastore.put(diagram);
		
		res.getOutputStream().print(diagramName + " " + diagramURL + " Have been uploaded successfully!");
		
	      }
		
}
		
		
		
	


