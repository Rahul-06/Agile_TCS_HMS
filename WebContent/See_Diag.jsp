<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.servlet.http.HttpSession,javax.servlet.http.HttpServletRequest,hms.bean.Patient,hms.bean.Diagnostic,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  border: 1px solid #e7e7e7;
  background-color: #f3f3f3;
  text-align: center;
}

li {
  display: inline-block;
  border-right: 1px solid #bbb;
}

li a, .dropbtn {
  display: block;
  color: #666;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
  background-color: white;
}

li.dropdown {
  display: inline-block;
}

.active {
  background-color: #4CAF50;
}

td{
  padding: 15px;
}
table {
  border-collapse: collapse;
  width: 100%;
}


.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}
fieldset {
 	display: inline-block;
 	align: center;
 	text-align: center;
 }

.dropdown-content a:hover {background-color: #f1f1f1;}

.dropdown:hover .dropdown-content {
  display: block;
}

td, th, tr{
  padding: 15px;
}
table {
  border-collapse: collapse;
  width: 100%;
}
</style>

<% 
HttpSession session1 = request.getSession(false);
Patient c = (Patient) session1.getAttribute("patient");
%>


<meta charset="ISO-8859-1">
<title>Patient Diagnostic Page</title>
</head>
<body>
<h1><center>Hospital Management System</center></h1>
<ul>
<li><a  href="welcome_diagnostic.jsp">Home</a></li>
  <li class="dropdown"><a class="active" href="#" class="dropbtn">Diagnostics</a>
  <div class="dropdown-content">
      <a href="Diag.jsp">View Diagnostics Unit</a>
      <a href="addTest.jsp">Manage_Diagnostics</a>
    </div>
  </li>
  <li><a href="Logout.jsp">Logout</a></li>
</ul>
<h3><center>Diagnostics - Patient Data</center></h3>
<form action="Diag.jsp" method="post" align="center">
<table border="1" align="center">
	<tr>
	  <TH>Patient SSN Id</TH>
      <TH>Patient Id</TH>
      <TH>Patient Name</TH>
      <TH>Patient Age</TH>
      <TH>Date Of Admit</TH>
      <TH>Type of Room</TH>
      <TH>Address</TH>
      <TH>City</TH>
      <TH>State</TH> 
	</tr>
	<tr>
		<td><%=c.getSsn_id() %></td>
		<td><%=c.getPat_id() %></td>
		<td><%=c.getPat_name() %></td>
		<td><%=c.getAge() %></td>
		<td><%=c.getDoj() %></td>
		<td><%=c.getTob() %></td>
		<td><%=c.getAddress() %></td>
		<td><%=c.getCity() %></td>
		<td><%=c.getState() %></td>
	</tr>
</table>
	<button type="button" name="back" value="Back" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" onclick="document.location='Diag.jsp'"><i class="fa fa-chevron-circle-left"></i> Back</button>
<%
String data = (String) session.getAttribute("data");
if(data.equalsIgnoreCase("yes")){
%>
<% 
List<Diagnostic> d = (List<Diagnostic>)(List)session.getAttribute("diagnostic");
//Pharmacy ph = (Pharmacy) session1.getAttribute("pharmacy");
%>
<%
HttpSession session2 = request.getSession(false);
session2.setAttribute("patient", c);
session2.setAttribute("diagnostic",d);
%>
<h3><center>Diagnostics Conducted</center></h3>
<table border="1" align="center">
	<tr>
	  <TH>Patient SSN Id</TH>
      <TH>Patient Id</TH>
      <TH>Test Id</TH>
      <TH>Test Name</TH>
      <TH>Amount</TH>
	</tr>
	<%for(Diagnostic itr : d) { %>
	<tr>
		<td><%=itr.getWs_ssn() %></td>
		<td><%=itr.getWs_pat_id()%></td>
		<td><%=itr.getWs_test_id() %></td>
		<td><%=itr.getWs_test_name() %></td>
		<td><%=itr.getWs_test_amt() %></td>
	</tr>
	<% } %>
</table>

<% }
else { %>
   <h3><center>No Diagnostics Conducted</center></h3>
<% } %>

    <button type="button" name="issue" value="issue" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" onclick="document.location='cond_diag.jsp'">Conduct Diagnostics <i class="fa fa-plus-circle"></i></button>
</form>

</body>
</html>