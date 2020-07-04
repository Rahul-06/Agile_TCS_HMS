package hms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hms.bean.Patient;
import hms.credentials.DbConn;
import hms.service.PatientService;


@WebServlet("/PatientServelet")
public class PatientServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In get");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		PatientService ps = new PatientService();
		String action = request.getParameter("action");
		System.out.println("In post");
		if(action.equalsIgnoreCase("Submit"))
		{
			Integer ssn_id = Integer.parseInt(request.getParameter("ws_ssn"));
			String pat_name = request.getParameter("ws_name");
			Integer age = Integer.parseInt(request.getParameter("ws_age"));
			String address = request.getParameter("ws_adrs");
			String city = request.getParameter("ws_city");
			String state = request.getParameter("ws_state");
			String doj = request.getParameter("ws_doj");
			String tob = request.getParameter("ws_tob");
			
			Patient p = new Patient(ssn_id,pat_name,age,doj,tob,address,city,state);
			int x = ps.createPatient(p);
			if(x > 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				request.setAttribute("ssnid",ssn_id );
				rd.forward(request, response);
			}
			else
			{
				out.println("<h1>Registration Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("RegPat.jsp");
				rd.include(request, response);
			}			
		}
		
		else if(action.equalsIgnoreCase("Get"))
		{
			Integer pat_id = Integer.parseInt(request.getParameter("ws_ssn"));
			Patient p1 = ps.searchId(pat_id);
			
			if (p1.getPat_id() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("FailUpd.jsp");
            	rd.forward(request, response);
			}
			else if(action.equalsIgnoreCase("Get"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("Upd_Pat.jsp");
				request.setAttribute("cust",p1);
				rd.forward(request, response);
			}            
		}

		else if(action.equalsIgnoreCase("Update"))
		{
			HttpSession session = request.getSession(false);
			Patient c = (Patient)session.getAttribute("cust");
			String pat_name = request.getParameter("ws_pat_name");
			String address = request.getParameter("ws_adrs");
			Integer age = Integer.parseInt(request.getParameter("ws_age"));   
			String doj = request.getParameter("ws_doj");
			String tob = request.getParameter("ws_tob");
			String city = request.getParameter("ws_city");
			String state = request.getParameter("ws_state");
			
			Patient c1 = new Patient(c.getSsn_id(),pat_name,age,doj,tob,address,city,state);
			int x = ps.updatePatient(c1);				
			//String details="update";
			if(x > 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("UpdSuc.jsp");
				request.setAttribute("custid",c.getPat_id());
				//request.setAttribute("detail", details);
				rd.forward(request, response);
			}
			else
			{
				out.println("<h1>Updation Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("UpdPat.jsp");
				rd.include(request, response);
			}			
			//session.setAttribute("cust_details",c1);
		}
		
		else if(action.equalsIgnoreCase("Search"))
		{
			Integer ssn_id = Integer.parseInt(request.getParameter("ws_ssn"));
		    Patient p = ps.searchId(ssn_id);
	
		    if(p.getSsn_id() == null)
		    {
		    	out.println("<h1>View Failed</h1>");
		    	RequestDispatcher rd = request.getRequestDispatcher("FailView.jsp");
		    	rd.forward(request, response);
		    }
		    else 
			{
				RequestDispatcher rd = request.getRequestDispatcher("ViewSuc.jsp");
				request.setAttribute("cust",p);
				rd.forward(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("Trash"))
		{
			Integer pat_id = Integer.parseInt(request.getParameter("ws_ssn"));
			Patient p1 = ps.searchId(pat_id);
			if (p1.getPat_id() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("FailDel.jsp");
            	rd.forward(request, response);
			}
			else if(action.equalsIgnoreCase("Trash"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("Del_Pat.jsp");
				request.setAttribute("cust",p1);
				rd.forward(request, response);
			}            
		}
		else if(action.equalsIgnoreCase("delete"))
		{
			HttpSession session = request.getSession(false);
			Patient p = (Patient)session.getAttribute("cust");
		
			int x = ps.deletePatient(p.getSsn_id());
			if(x > 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("DelSuc.jsp");
				request.setAttribute("custid",p.getSsn_id());
				//request.setAttribute("detail", details);
				rd.forward(request, response);
			}
			else
			{
				out.println("<h1>Deletion Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("FailDel.jsp");
				rd.include(request, response);
			}
			
			//session.setAttribute("cust_details",c1);
		}		
	}
}
