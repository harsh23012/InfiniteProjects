package com.carrent.com.serv.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carrent.com.bal.PaymentBal;

/**
 * Servlet implementation class PayDuesServlet
 */
public class PayDuesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayDuesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter  out = response.getWriter();
		response.setContentType("text/html");
		Double dues = Double.parseDouble(request.getParameter("duesAmount"));
		int leaseId = Integer.parseInt(request.getParameter("leaseId"));
		
		PaymentBal bal = new PaymentBal();
		try {
			bal.payDuesBal(dues, leaseId);
			out.println("<b>Vehicle Returned and dues is paid<b/>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
		}
	}

}
