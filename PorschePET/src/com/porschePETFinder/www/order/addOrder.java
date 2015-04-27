package com.porschePETFinder.www.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class addOrder extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		ArrayList order;
		String item;
		item = req.getParameter("item");
		
		HttpSession session = req.getSession();
		
		if (session.isNew()){
			session.setMaxInactiveInterval(1800);
			order = new ArrayList();
			order.add(item);
			session.setAttribute("order", order);
		}
		else {
			order = (ArrayList) session.getAttribute("order");
			order.add(item);
		}
		
		System.out.println(order);
		
		try {
			res.getOutputStream().println("Item added to Order. If your order is complete, click OK. If you want to add more items click CANCEL.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
