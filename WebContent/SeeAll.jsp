<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>
<%@ page import="hms.credentials.*" %> 
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

.dropdown-content a:hover {background-color: #f1f1f1;}

.dropdown:hover .dropdown-content {
  display: block;
}

td{
  padding: 15px;
}
table {
  border-collapse: collapse;
  width: 100%;
}
</style>
<meta charset="ISO-8859-1">
<title>View Patient Page</title>
</head>
<body align="center">
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
<center><h3>View Patient Data</h3></center>
<FORM action="CustomerServelet" method="post">
<% response.setContentType("text/html");
Connection conn = null;
PreparedStatement ps=null;

try {
	conn = DbConn.getConnection();
    System.out.println("Connected!");
    // sql query to insert values in the secified table.
    String queryString = "select ws_ssn,ws_pat_id,ws_pat_name,ws_age,ws_doj,ws_rtype,address,ws_city,ws_state from patient where ws_status='Active';";
	ps = conn.prepareStatement(queryString);
    ResultSet rs = ps.executeQuery();%>
	<br>
	<TABLE BORDER="1" align="center">
            <TR>
                <TH>Patient_SSN_Id</TH>
                <TH>Patient_Id</TH>
                <TH>Patient_Name</TH>
                <TH>Age</TH>
                <TH>Date Of Admit</TH>
                <TH>Type of Room</TH>
                <TH>Address</TH>
                <TH>City</TH>
                <TH>State</TH>                
            </TR>
            
            <TR>
            <% while(rs.next()){ 
            %>
            </TR>
            <TR>
            <TD><%= rs.getInt(1)%></TD>
			<td><%= rs.getInt(2)%></td>
			<td><%= rs.getString(3)%></td>
			<td><%=	rs.getInt(4)%></td>
			<td><%= rs.getString(5)%></td>	
            <TD> <%= rs.getString(6) %></TD>
            <td><%= rs.getString(7)%></td> 
            <td><%= rs.getString(8)%></td>
            <td><%= rs.getString(9)%></td>               
            </TR>
            <% } %>
            
            
            </TABLE>
		          <%
                
	                 }
	                 catch (Exception ex) {
	                	 out.println(ex);
	                 out.println("Unable to connect to database.");
	        
	                    }
	                 finally {
	                     // close all the connections.
	                     ps.close();
	                     conn.close();
	                     
	                 }%>
 <br>	 
  </FORM>


<button type="button" name="back" value="Back" onclick="document.location='welcome_admin.jsp'" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  float: middle;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;"><i class="fa fa-chevron-circle-left"></i> Back</button>
</body>
</html>