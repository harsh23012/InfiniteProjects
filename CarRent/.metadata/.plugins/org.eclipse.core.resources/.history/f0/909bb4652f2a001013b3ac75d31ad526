package com.carrent.com.serv.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.carrent.com.dao.PaymentDao;
import com.carrent.com.dao.PaymentDaoImpl;
import com.carrent.com.model.Payment;

/**
 * Servlet implementation class ShowPaymentHistoryByCustId
 */
public class ShowPaymentHistoryByCustId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPaymentHistoryByCustId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    int custId = Integer.parseInt(request.getParameter("customerId"));
	    List<Payment> payment;
	    
	    
	    try {
	        PaymentDao dao = new PaymentDaoImpl(); 
	        payment = dao.paymentDetailsCustomer(custId);

	        if (payment.isEmpty()) {
	            out.println("<h3> ***No Payment History Found*** </h3>");
	            return;
	        }
	        out.println("<center><h2> Lease List </h2><table border='1'>");
	        out.println("<tr><th>Lease ID</th><th>Payment ID</th><th>Payment Date</th><th>Amount</th><th>Advance Amount</th><th>Remaining Amount</th></tr>");

	        for (Payment p : payment) {
	        	out.println("<tr>");
	            out.println("<td>" + p.getLeaseId() + "</td>");
	            out.println("<td>" + p.getPaymentId() + "</td>");
	            out.println("<td>" + p.getPaymentDate() + "</td>");
	            out.println("<td>" + p.getAmount() + "</td>");
	            out.println("<td>" + p.getAdvAmount() + "</td>");
	            out.println("<td>" + p.getRemainingAmount() + "</td>");
	            out.println("</tr>");	
			} 

	        out.println("</table></center>");

	    } catch (Exception e) {
	        e.printStackTrace();
	        out.println("<h3>Error fetching vehicles: " + e.getMessage() + "</h3>");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
