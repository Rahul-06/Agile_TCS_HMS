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
import hms.bean.Lab;
import hms.bean.Medicine;
import hms.bean.Patient;
import hms.bean.Pharmacy;
import hms.service.PatientService;
import hms.service.DiagnosticService;

@WebServlet("/DiagnosticServelet")
public class DiagnosticServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DiagnosticServelet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("In get");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		PatientService ps = new PatientService();
		DiagnosticService ds = new DiagnosticService();
		String action = request.getParameter("action");
		System.out.println("In post");
		if (action.equalsIgnoreCase("SeeDiag")) {
			String ssn_id = request.getParameter("ws_ssn");
			Patient p = ps.viewPatient(ssn_id);
			List<Diagnostic> d = ds.viewDiagnostic(ssn_id);
			if (p.getSsn_id() == null) {
				out.println("<h1>View Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("FailDiag.jsp");
				rd.forward(request, response);
			} else if (d.isEmpty()) {
				HttpSession session = request.getSession();
				session.setAttribute("patient", p);
				session.setAttribute("diagnostic", d);
				session.setAttribute("data", "no");
				RequestDispatcher rd = request.getRequestDispatcher("See_Diag.jsp");
				rd.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("patient", p);
				session.setAttribute("diagnostic", d);
				session.setAttribute("data", "yes");
				RequestDispatcher rd = request.getRequestDispatcher("See_Diag.jsp");
				// request.setAttribute("patient",p);
				rd.forward(request, response);
			}

		}

		
		if(action.equalsIgnoreCase("issue"))
		{
			String test_name = request.getParameter("ws_test_name");
			List<Lab> l = ds.conductTest(test_name);
			if(l.isEmpty())
			{
				out.println("<h1>Diagnostic Test is not available</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("FailDiag.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
				HttpSession session = request.getSession(false);
				session.setAttribute("lab",l);
				rd.forward(request, response);
			}
		}
		
		if(action.equalsIgnoreCase("update"))
		{
			HttpSession session = request.getSession(false);
			List<Lab> test_list = (List<Lab>)(List)session.getAttribute("lab");
			Patient p = (Patient) session.getAttribute("patient");
			int cnt = ds.updateTest(test_list, p);
			if(cnt > 0)
		    {
				RequestDispatcher rd = request.getRequestDispatcher("TestUpdSuc.jsp");
					//request.setAttribute("patient",p);
				rd.forward(request, response);
		    }
			else
			{
				out.println("<h1>Update Failed</h1>");
			    RequestDispatcher rd = request.getRequestDispatcher("FailTestUpd.jsp");
			    rd.forward(request, response);
			}
				
		}
		
		if(action.equalsIgnoreCase("add"))
		{
			String test_name = request.getParameter("ws_test_name");
			Integer test_rate = Integer.parseInt(request.getParameter("ws_rate"));
			Lab l = new Lab(test_name, test_rate);
			int x = ds.createTest(l);
			if(x > 0)
			{
				out.println("<h1>Updation Success</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("addTest.jsp");
				request.setAttribute("testname",test_name );
				rd.forward(request, response);
			}
			else
			{
				out.println("<h1>Updation Failed</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("addTest.jsp");
				rd.include(request, response);
			}
				
		}
		

	}

}
