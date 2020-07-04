<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.servlet.http.HttpSession,javax.servlet.http.HttpServletRequest,hms.bean.Patient,hms.bean.Pharmacy,hms.bean.Medicine,java.util.List" %>
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

<title>Patient Pharmacy Page</title>
</head>
<body>
<h1><center>Hospital Management System</center></h1>
<ul>
<li><a href="welcome_pharmacist.jsp">Home</a></li>
  <li class="dropdown"><a class="active" href="#" class="dropbtn">Pharmacy</a>
  <div class="dropdown-content">
      <a href="Phar.jsp">View Pharmacy Unit</a>
      <a href="addMed.jsp">Manage_Medicine</a>
    </div>
  </li>
  <li><a href="Logout.jsp">Logout</a></li>
</ul>

<h3><center>Pharmacy - Patient Data</center></h3>
<table border="1" align="center">
	<tr>
	  <TH>Patient SSN Id</TH>
      <TH>Patient Id</TH>
      <TH>Patient Name</TH>
      <TH>Age</TH>
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
<%
String data = (String) session.getAttribute("data");
if(data.equalsIgnoreCase("yes")){
%>
<% 
List<Pharmacy> ph = (List<Pharmacy>)(List)session.getAttribute("pharmacy");
//Pharmacy ph = (Pharmacy) session1.getAttribute("pharmacy");
%>
<%
HttpSession session2 = request.getSession(false);
session2.setAttribute("patient", c);
session2.setAttribute("pharmacy",ph);
%>
<h3><center>Medicines Issued</center></h3>
<table border="1" align="center">
	<tr>
	  <TH>Patient SSN Id</TH>
      <TH>Patient Id</TH>
      <TH>Medicine Id</TH>
      <TH>Medicine Name</TH>
      <TH>Quantity</TH>
      <TH>Rate</TH>
      <TH>Amount</TH>
	</tr>
	<%for(Pharmacy itr : ph) { %>
	<tr>
		<td><%=itr.getWs_ssn() %></td>
		<td><%=itr.getWs_pat_id()%></td>
		<td><%=itr.getWs_med_id() %></td>
		<td><%=itr.getWs_med_name() %></td>
		<td><%=itr.getWs_med_qty() %></td>
		<td><%=itr.getWs_med_rate() %></td>
		<td><%=itr.getWs_med_qty()*itr.getWs_med_rate() %></td>
	</tr>
	<% } %>
</table>

<% }
else { %>
   <h3><center>No Medicines Issued</center></h3>
<% } %>

<% 
List<Medicine> m = (List<Medicine>)(List)session.getAttribute("medicine");
//List<Medicine> m = (List<Medicine>)(List)request.getAttribute("medicine");
//Integer qty = (Integer) request.getAttribute("qty");
%>
<h3><center>Issue Medicines</center></h3>
<form action="PharmacyServelet" method="post" align="center">
<table border="1" align="center">
	<tr>
	  <TH>Medicine Id</TH>
      <TH>Medicine Name</TH>
      <TH>Quantity</TH>
      <TH>Rate</TH>
      <TH>Amount</TH>
	</tr>
	<%for(Medicine itr : m) { %>
	<tr>
		<td><%=itr.getWs_med_id() %></td>
		<td><%=itr.getWs_med_name() %></td>
		<td><%=itr.getWs_med_qty()%></td>
		<td><%=itr.getWs_med_rate()%></td>
		<td><%=itr.getWs_med_qty()*itr.getWs_med_rate() %></td>
	</tr>
	<% } %>
</table>
 <button type="button" name="issue_medicine" value="Add" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" onclick="document.location='issue_medicine.jsp'">Add <i class="fa fa-plus-circle"></i></button>
 <button type="submit" name="action" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" value="update">Update <i class="fa fa-edit"></i></button>
</form>

</body>
</html>