package com.carrent.com.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carrent.com.dao.CarDao;
import com.carrent.com.dao.carDaoImpl;
import com.carrent.com.exception.vehicleNotFoundException;

/**
 * Servlet implementation class removeCarServlet
 */
public class removeCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeCarServlet() {
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
		// TODO Auto-generated method stub
		doDelete(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the vehicle number from the form submission
        String vehicleNumber = request.getParameter("vehNumber");
        PrintWriter out = response.getWriter();
        response.setContentType("text.html");

        // Validate the input
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            response.getWriter().write("Vehicle number cannot be empty.");
            return;
        }

        // Call the service method to remove the car
        try {
            CarDao carService = new carDaoImpl();  // Assuming you have a service layer
            carService.removeCar(vehicleNumber);
        } catch (vehicleNotFoundException e) {
            response.getWriter().write("<b style='color:red;'>Error: " + e.getMessage()+"<b/>");  // Handle custom exception
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("Database error occurred.");
        }
    }
}
