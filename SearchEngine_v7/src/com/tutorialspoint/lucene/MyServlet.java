package com.tutorialspoint.lucene;
//package com.room.sample.servlet

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.room.sample.view.Customer;

public class MyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		//System.out.println("----- InsertCustomerServlet -----");
		try {
			// Get the customer value submitted from Customer.jsp page through HttpServletRequest object
			String searchtxt=request.getParameter("Field3"); 
			String searchtype=request.getParameter("command"); 
			String searchdataset=request.getParameter("dataset");             
			//System.out.println("-----request.getParameter(\"command\") -----"+ request.getParameter("command"));
			//System.out.println("-----request.getParameter(\"dataset\") -----"+ request.getParameter("dataset"));
			//Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
			MainCaller temp =new MainCaller();
			temp.setURLList(searchtxt,searchdataset,searchtype);
			temp.setName(searchtype);

			// RequestDispatcher dispatcher=request.getRequestDispatcher("/Welcome.jsp");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
			//Set the customer instance into request.Then only the customer object 
			//will be available in the Welcome.jsp page
			request.setAttribute("cust",temp);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
