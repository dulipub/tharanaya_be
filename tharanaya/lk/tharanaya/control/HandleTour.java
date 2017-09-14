package tharanaya.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.Response;

import tharanaya.dto.Tour;
import tharanaya.logic.TourDB;

/**
 * Servlet implementation class HandleTour
 */
@WebServlet("/HandleTour")
public class HandleTour extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** The variable that holds the search/select result set */
	private ArrayList<Tour> results = new ArrayList<Tour>(); 
	/** All the database functions are done through this object */
    private TourDB tourDb = new TourDB();   
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleTour() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /**
     * parameters:
     * tour - tourid to create new tour select tour row
     * selectall - select all from tour table
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String select = request.getParameter("tours"); //search all tour buttons name must match this
		if(!(select==null||select.equals(""))){
			selectAll(request, response);
		}else{
			System.out.println("incorrect request sent");
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String insert = request.getParameter("tourInsert"); //this is the save button name of the tourtable.jsp
		String update = request.getParameter("tourUpdate"); //this is the update button name of the tourtable.jsp
		String delete = request.getParameter("tourDelete"); //this is the delete button name of the tourtable.jsp
		
		if(!(insert==null||insert.equals(""))){
			setInsert(request,response);
		}else if(!(update==null||update.equals(""))){
			setUpdate(request,response);
		}else if(!(delete==null||delete.equals(""))){
			System.out.println("not delete tour");
		}else{
			System.out.println("incorrect request sent");
		}
	}
	
	
	private void selectAll(HttpServletRequest request,HttpServletResponse response){
		try {
			results = tourDb.selectAll();
			request.setAttribute("resultset", results);
			forwardToPage(request, response);
		} catch (SQLException e) {
			request.setAttribute("error", "Sorry database error! couldn't select table");
			e.printStackTrace();
			errorPage(request, response);
		}
	}

	private void setInsert(HttpServletRequest request,HttpServletResponse response) {
			boolean isSet = false;
			String error = "";
			String tourID = "0"; //setting an arbitrary tour id as tour id is auto increment
			String tName = request.getParameter("tName");
			String ndays = request.getParameter("nDays");
			String location = request.getParameter("location");
			
			Tour tour = new Tour();
			//set all data and return true if all is ok
			isSet = tour.setAll(tourID, tName, ndays, location);
			
			if(isSet){
				//if all is ok then this will be true
				isSet = tourDb.insert(tour);	
				if(isSet){
					selectAll(request, response); //select the updated tour table 
				}else{
					error = "Sorry row was not inserted";
					request.setAttribute("error", error);
					errorPage(request, response);
				}	
			}else{
				error = tour.getErrorMsg();
				request.setAttribute("error", error);
				errorPage(request, response);
			}
		}
	
	private void setUpdate(HttpServletRequest request, HttpServletResponse response) {
		boolean isSet = false;
		String error = "error";
		String tourID = request.getParameter("tourID");
		String tName = request.getParameter("tName");
		String ndays = request.getParameter("nDays");
		String location = request.getParameter("location");
		
		Tour tour = new Tour();
		isSet = tour.setAll(tourID, tName, ndays, location);
		
		if(isSet){
			//if all is ok then this will be true
			isSet = tourDb.update(tour);	
			if(isSet){
				selectAll(request, response); //select the updated tour table 
			}else{
				error = "Sorry database was not updated";
				request.setAttribute("error", error);
				errorPage(request, response);
			}	
		}else{
			error = tour.getErrorMsg();
			request.setAttribute("error", error);
			errorPage(request, response);
		}
	}
	//forward to default page
	private void forwardToPage(HttpServletRequest request,HttpServletResponse response){
		RequestDispatcher view = request.getRequestDispatcher("/views/tourtable.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//forward to the error page
	private void errorPage(HttpServletRequest request,HttpServletResponse response){
		RequestDispatcher view = request.getRequestDispatcher("/views/errormessage.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}


