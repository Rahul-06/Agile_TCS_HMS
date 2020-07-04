package hms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hms.bean.Diagnostic;
import hms.bean.Patient;
import hms.bean.Pharmacy;
import hms.service.BillService;
import hms.service.DiagnosticService;
import hms.service.PatientService;
import hms.service.PharmacyService;


@WebServlet("/BillServelet")
public class BillServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillServelet() {
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
		PharmacyService phs = new PharmacyService();
		DiagnosticService ds = new DiagnosticService();
		String action = request.getParameter("action");
		System.out.println("In post");		
		if(action.equalsIgnoreCase("SeeBill"))
		{
		    String ssn_id=request.getParameter("ws_ssn");
		    Patient p = ps.viewPatient(ssn_id);
		    List<Pharmacy> ph = phs.viewPharmacy(ssn_id);
		    List<Diagnostic> d = ds.viewDiagnostic(ssn_id);
		    if (p.getSsn_id() == null) {
		    	 System.out.println("hi4");
				out.println("<h1>View Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("FailBill.jsp");
				rd.forward(request, response);
			}
		    else if( (ph.isEmpty()) && (d.isEmpty()) )
	    	{			    	
		    	HttpSession session = request.getSession();
		    	session.setAttribute("patient", p);
		    	session.setAttribute("pharmacy", ph);
		    	session.setAttribute("data", "no");
		    	session.setAttribute("diagnostic", d);
		    	session.setAttribute("data1", "no");
		    	RequestDispatcher rd = request.getRequestDispatcher("ViewBill.jsp");
				rd.forward(request, response);
	    	}
	    	else if( (ph.isEmpty()) && (!(d.isEmpty())) )
		    {
	    		HttpSession session = request.getSession();
		    	session.setAttribute("patient", p);
		    	session.setAttribute("pharmacy", ph);
		    	session.setAttribute("data", "no");
		    	session.setAttribute("diagnostic", d);
		    	session.setAttribute("data1", "yes");
		    	RequestDispatcher rd = request.getRequestDispatcher("ViewBill.jsp");
				rd.forward(request, response);
		    }
	    	else if( (d.isEmpty()) && (!(ph.isEmpty())) )
	    	{
	    		HttpSession session = request.getSession();
		    	session.setAttribute("patient", p);
				session.setAttribute("pharmacy", ph);
		    	session.setAttribute("data", "yes");
		    	session.setAttribute("diagnostic", d);
				session.setAttribute("data1", "no");
				RequestDispatcher rd = request.getRequestDispatcher("ViewBill.jsp");
				rd.forward(request, response);
	    	}
	    	else
	    	{
	    		HttpSession session = request.getSession();
		    	session.setAttribute("patient", p);
		    	session.setAttribute("pharmacy", ph);
		    	session.setAttribute("data", "yes");
		    	session.setAttribute("diagnostic", d);
		    	session.setAttribute("data1", "yes");
				RequestDispatcher rd = request.getRequestDispatcher("ViewBill.jsp");
				rd.forward(request, response);
	    	}			
		}
		
		if(action.equalsIgnoreCase("Confirm"))
		{
			HttpSession session = request.getSession(false);
			Patient p = (Patient) session.getAttribute("patient");
			BillService bs = new BillService();
			int cnt = bs.dischargePatient(p);
			if(cnt > 0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("BillSuc.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("FailUpdBill.jsp");
				rd.forward(request, response);
			}
		}
		
	}

}
