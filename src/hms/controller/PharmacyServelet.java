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

import hms.bean.Medicine;
import hms.bean.Patient;
import hms.bean.Pharmacy;
import hms.service.PatientService;
import hms.service.PharmacyService;


@WebServlet("/PharmacyServelet")
public class PharmacyServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmacyServelet() {
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
		String action = request.getParameter("action");
		System.out.println("In post");
		if(action.equalsIgnoreCase("SeePhar"))
		{
		    String ssn_id=request.getParameter("ws_ssn");
		    Patient p = ps.viewPatient(ssn_id);
		    List<Pharmacy> ph = phs.viewPharmacy(ssn_id);
		    if(p.getSsn_id() == null )
		    {
		    	out.println("<h1>View Failed</h1>");
		    	RequestDispatcher rd = request.getRequestDispatcher("FailPhar.jsp");
		    	rd.forward(request, response);
		    }
		    else if(ph.isEmpty())
		    {
		    	HttpSession session = request.getSession();
		    	session.setAttribute("patient", p);
		    	session.setAttribute("pharmacy", ph);
		    	session.setAttribute("data", "no");
		    	RequestDispatcher rd = request.getRequestDispatcher("See_Phar.jsp");
				rd.forward(request, response);
		    }
		    else 
			{
		    	HttpSession session = request.getSession();
		    	session.setAttribute("patient", p);
		    	session.setAttribute("pharmacy", ph);
		    	session.setAttribute("data", "yes");
				RequestDispatcher rd = request.getRequestDispatcher("See_Phar.jsp");
				//request.setAttribute("patient",p);
				//request.setAttribute("pharmacy", ph);
				rd.forward(request, response);
			}
			
		}
		
		if(action.equalsIgnoreCase("issue"))
		{
			String med_name = request.getParameter("ws_med_name");
			Integer qty = Integer.parseInt(request.getParameter("ws_qty"));
			List<Medicine> m = phs.issueMedicine(med_name, qty);
			if(m.isEmpty())
			{
				out.println("<h1>Medicine is not available</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("FailMed.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("Medicine.jsp");
				//request.setAttribute("medicine",m);
				HttpSession session = request.getSession(false);
				session.setAttribute("medicine",m);
				//request.setAttribute("qty", qty);
				rd.forward(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("update"))
		{
			HttpSession session = request.getSession(false);
			List<Medicine> med_list = (List<Medicine>)(List)session.getAttribute("medicine");
			Patient p = (Patient) session.getAttribute("patient");
			int cnt = phs.updateMedicine(med_list, p);
			if(cnt > 0)
		    {
				RequestDispatcher rd = request.getRequestDispatcher("MedUpdSuc.jsp");
					//request.setAttribute("patient",p);
					//request.setAttribute("pharmacy", ph);
				rd.forward(request, response);
		    }
			else
			{
				out.println("<h1>Update Failed</h1>");
			    RequestDispatcher rd = request.getRequestDispatcher("FailUpdMed.jsp");
			    rd.forward(request, response);
			}
				
		}
		
		if(action.equalsIgnoreCase("add"))
		{
			String med_name = request.getParameter("ws_med_name");
			Integer med_qty = Integer.parseInt(request.getParameter("ws_qty"));
			Integer med_rate = Integer.parseInt(request.getParameter("ws_rate"));
			
			Medicine m = new Medicine(med_name, med_qty, med_rate);
			int x = phs.createMedicine(m);
			if(x > 0)
			{
				out.println("<h1>Updation Success</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("addMed.jsp");
				request.setAttribute("medname",med_name );
				rd.forward(request, response);
			}
			else
			{
				out.println("<h1>Updation Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("addMed.jsp");
				rd.include(request, response);
			}
				
		}
		
	}

}
