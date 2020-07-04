<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.servlet.http.HttpSession,javax.servlet.http.HttpServletRequest,hms.bean.Patient" %>
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

td{
  padding: 15px;
}
table {
  border-collapse: collapse;
  width: 100%;
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
</style>

<% 
Patient c = (Patient) request.getAttribute("cust");
%>
<%
HttpSession session1 = request.getSession();
session1.setAttribute("cust",c);
%>

<meta charset="ISO-8859-1">
<title>Delete Patient Page</title>
</head>
<body>
<h1><center>Hospital Management System</center></h1>
<ul>
  <li><a href="welcome_admin.jsp">Home</a></li>
  <li class="dropdown"><a class="active" href="#" class="dropbtn">Patient</a>
  <div class="dropdown-content">
      <a href="RegPat.jsp">Patient Registration</a>
      <a href="UpdPat.jsp">Update Patient</a>
      <a href="DelPat.jsp">Delete Patient</a>
      <a href="SeePat.jsp">Search Patient</a>
      <a href="SeeAll.jsp">View All Patient</a>
    </div>
  </li>
  <li class="dropdown"><a href="#" class="dropbtn">Bill Payment</a>
  <div class="dropdown-content">
      <a href="Bill.jsp">View Patient Bill</a>
    </div>
  </li>
  <li><a href="Logout.jsp">Logout</a></li>
</ul>
<h3><center>Delete Patient Data</center></h3>
<form action="PatientServelet" method="post" align="center">
<fieldset style="width:350px;">
<table align="center">
	<tr>
		<td>Patient SSN ID</td>
		<td><%=c.getSsn_id() %></td>
	</tr>
	<tr>
		<td>Customer ID</td>
		<td><%=c.getPat_id() %></td>
	</tr>
	<tr>
		<td>Customer Name</td>
		<td><%=c.getPat_name() %></td>
	</tr>
	<tr>
		<td>Customer Age</td>
		<td><%=c.getAge() %></td>
	</tr>
	<tr>
		<td>Date Of Admit</td>
		<td><%=c.getDoj() %></td>
	</tr>
	<tr>
		<td>Type Of Bed</td>
		<td><%=c.getTob() %></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><%=c.getAddress() %></td>
	</tr>
	<tr>
		<td>City</td>
		<td><%=c.getCity() %></td>
	</tr>
	<tr>
		<td>State</td>
		<td><%=c.getState() %></td>
	</tr>
</table>
</fieldset><br>
	<button type="submit" name="action" value="Delete" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;">Delete <i class="fa fa-trash"></i></button>
	<button type="button" name="cancel" value="Cancel" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" onclick="window.location.href='DelPat.jsp'"><i class="fa fa-chevron-circle-left"></i> Cancel</button>
</form>
</body>
</html>