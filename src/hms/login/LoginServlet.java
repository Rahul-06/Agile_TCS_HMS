package hms.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import hms.dao.LoginDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		String n=request.getParameter("username");  
		String p=request.getParameter("userpass"); 
		
		HttpSession session = request.getSession(false);
		if(session!=null)
		session.setAttribute("name", n);

		if(LoginDao.validate(n, p)){  
	        if(n.equalsIgnoreCase("Admin")) {
	        	RequestDispatcher rd=request.getRequestDispatcher("welcome_admin.jsp");
	        	rd.forward(request,response);  
			}
	        else if(n.equalsIgnoreCase("Pharmacist")) {
				RequestDispatcher rd=request.getRequestDispatcher("welcome_pharmacist.jsp");
				rd.forward(request,response);  
			}
	        else if(n.equalsIgnoreCase("Diagnostic")) {
				RequestDispatcher rd=request.getRequestDispatcher("welcome_diagnostic.jsp");
				rd.forward(request,response);  
			}
		}  
		else{  
			out.print("<p style=\"color:red\">Sorry username or password error</p>");  
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			rd.include(request,response);  
		}  

		out.close();  
	}  
}
