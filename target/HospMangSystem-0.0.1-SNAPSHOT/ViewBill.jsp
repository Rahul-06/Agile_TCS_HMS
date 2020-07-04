<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.servlet.http.HttpSession,javax.servlet.http.HttpServletRequest,hms.bean.Patient,hms.bean.Pharmacy,hms.bean.Diagnostic,java.util.List" %>
    <%@ page import="java.time.format.DateTimeFormatter,java.time.LocalDateTime, java.time.LocalDateTime, java.util.Date, java.text.SimpleDateFormat" %>
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
long millis=System.currentTimeMillis();  
java.sql.Date date=new java.sql.Date(millis);  
//System.out.println(date);  

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date1 = c.getDoj();
Date d1 = (Date) sdf.parse(date1);
Date d2 = date;
int day = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
//System.out.println(day);

String type_room = c.getTob();
int bill_room;
if(type_room.equalsIgnoreCase("Single-Room")) { 
       	bill_room = day * 8000;
    }
else if(type_room.equalsIgnoreCase("Semi-Share")) {
    	bill_room = day * 4000;
    }
else {
		bill_room = day * 2000; 
	} 
int bill_pharmacy = 0;
int bill_diagnostics = 0;
%>

<meta charset="ISO-8859-1">
<title>Billing Page</title>
</head>
<body>
<h1><center>Hospital_Management_System</center></h1>
<ul>
  <li><a href="welcome_admin.jsp">Home</a></li>
  <li class="dropdown"><a href="#" class="dropbtn">Patient</a>
  <div class="dropdown-content">
      <a href="RegPat.jsp">Patient Registration</a>
      <a href="UpdPat.jsp">Update Patient</a>
      <a href="DelPat.jsp">Delete Patient</a>
      <a href="SeePat.jsp">Search Patient</a>
      <a href="SeeAll.jsp">View All Patient</a>
    </div>
  </li>
  <li class="dropdown"><a class ="active" href="#" class="dropbtn">Bill Payment</a>
  <div class="dropdown-content">
      <a href="Bill.jsp">View Patient Bill</a>
    </div>
  </li>
  <li><a href="Logout.jsp">Logout</a></li>
</ul>
<h3><center>Patient Billing</center></h3>
<form action="BillServelet" method="post" align="center">
<table border="1" align="center">
	<tr>
	  <TH>Patient SSN Id</TH>
      <TH>Patient Id</TH>
      <TH>Patient Name</TH>
      <TH>Patient Age</TH>
      <TH>Date Of Admit</TH>
      <TH>Date of Discharge</TH>
      <TH>Type of Room</TH>
	</tr>
	<tr>
		<td><%=c.getSsn_id() %></td>
		<td><%=c.getPat_id() %></td>
		<td><%=c.getPat_name() %></td>
		<td><%=c.getAge() %></td>
		<td><%=c.getDoj() %></td>
		<td><%= date %></td>
		<td><%=c.getTob() %></td>
	</tr>
</table>
<table>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td style=" font-weight : bold;">No. of days</td>
		<td><%= day %></td>
		<td style = "font-weight : bold;">Bill for Room</td>
	    <td>Rs.<%= bill_room %></td>
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
//int bill_pharmacy = 0;
%>
<h3><center>Pharmacy Charges</center></h3>
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
		<% bill_pharmacy = bill_pharmacy + (itr.getWs_med_qty()*itr.getWs_med_rate()); %>
	</tr>
	<% } %>
</table>
<table>	
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td style = "font-weight : bold;">Bill for Pharmacy</td>
		<td>Rs.<%= bill_pharmacy %></td>
	</tr>
</table>

<% }
else { %>
   <h3>No Medicines Issued</h3>
<% } %>

<%
String data1 = (String) session.getAttribute("data1");
if(data1.equalsIgnoreCase("yes")){
%>
<% 
List<Diagnostic> d = (List<Diagnostic>)(List)session.getAttribute("diagnostic");
//Pharmacy ph = (Pharmacy) session1.getAttribute("pharmacy");
%>
<%
HttpSession session2 = request.getSession(false);
session2.setAttribute("patient", c);
session2.setAttribute("diagnostic",d);
//int bill_diagnostics = 0;
%>
<h3><center>Diagnostics Charges</center></h3>
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
		<% bill_diagnostics = bill_diagnostics + itr.getWs_test_amt(); %>
	</tr>
	<% } %>
</table>
<table>
	<tr>
	<td></td>
	<td></td>
	<td></td>
		<td style = "font-weight : bold;">Bill for Diagnostic</td>
		<td>Rs.<%= bill_diagnostics %></td>
	</tr>
</table>

<% }
else { %>
   <h3>No Diagnostics Conducted</h3>
<% } %>

<%
int total = bill_room + bill_pharmacy + bill_diagnostics;
%>
<table>
	<tr>
	<td><input type="submit" name="action" value="Confirm" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  	text-align: center;
  	text-decoration: none;
  	display: inline-block;
  	font-size: 16px;
  	margin: 4px 2px;
  	cursor: pointer;" onclick="BillServelet.java"></td>
    <td style = "font-weight : bold;">Grant Total</td>
    <td>Rs.<%= total %></td>
 	</tr> 
</table>

	<button type="button" name="back" value="Back" href="Bill.jsp" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" onclick="document.location='Bill.jsp'"><i class="fa fa-chevron-circle-left"></i> Back</button>
</form>
</body>
</html>