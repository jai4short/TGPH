package com.porschePETFinder.www.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deleteOrder extends HttpServlet {
	/**
	 * @param args
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		System.out.println("Delete request received.");
		String[] items = req.getParameterValues("items");
		HttpSession session = req.getSession();
		
		TreeMap<String, ArrayList> order = (TreeMap<String, ArrayList>) session.getAttribute("order");

		
		for (int i = 0; i < ((items.length)); i++){
			try {
				System.out.println("removing: " + items[i]);
				order.remove(items[i]);
			}
			catch (IndexOutOfBoundsException e){
				e.printStackTrace();
			}
			System.out.println("Item deleted");
		}

		session.setAttribute("order", order);
		
		try {
			System.out.println("sending redirect...");
			res.sendRedirect("http://www.germanpartshound.com/viewOrder");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printArray(ArrayList input){
		for (int i = 0; i < input.size(); i++){
			System.out.println(input.get(i));
		}
	}
}
