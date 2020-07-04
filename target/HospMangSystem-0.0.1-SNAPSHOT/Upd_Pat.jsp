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
fieldset {
 	display: inline-block;
 	align: center;
 	text-align: center;
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
</style>

<% 
Patient c = (Patient) request.getAttribute("cust");
%>
<%
HttpSession session1 = request.getSession();
session1.setAttribute("cust",c);
%>

<meta charset="ISO-8859-1">
<title>Update Patient Page</title>
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
<h3><center>Update Patient Data</center></h3>
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
		<td>Update Customer Name<span style="color:red;">*</span></td>
		<td><input type="text" name="ws_pat_name" required></td>
	</tr>
	<tr>
		<td>Customer Age</td>
		<td><%=c.getAge() %></td>
	</tr>
	<tr>
		<td>Update Customer Age<span style="color:red;">*</span></td>
		<td><input type="text" name="ws_age" pattern="^(1[89]|[2-9]\d)$" required></td>
	</tr>
	<tr>
		<td>Date Of Admit</td>
		<td><%=c.getDoj() %></td>
	</tr>
	<tr>
		<td>Update Date Of Admit<span style="color:red;">*</span></td>
		<td><input type="text" name="ws_doj" pattern="^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$" placeholder="yyyy-mm-dd" required></td>
	</tr>
	<tr>
		<td>Type Of Bed</td>
		<td><%=c.getTob() %></td>
	</tr>
	<tr>
		<td>Update Type Of Bed<span style="color:red;">*</span></td>
		<td><select id="ws_tob" name="ws_tob" required>
       		<option value="" disabled selected>Please select your Bed Type</option>
          	<option value="General-Ward">General-Ward</option>
  			<option value="Semi-Share">Semi-Share</option>
 		    <option value="Single-Room">Single-Room</option>
	   </select></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><%=c.getAddress() %></td>
	</tr>
	<tr>
		<td>Update Address</td>
		<td><textarea name="ws_adrs" rows="4" cols="20" required></textarea></td>
	</tr>
	<tr>
		<td>City</td>
		<td><%=c.getCity() %></td>
	</tr>
	<tr>
		<td>Update City</td>
		<td><select id="ws_city" name="ws_city" required>
       		<option value="" disabled selected>Please select your city</option>
          	<option value="Vijayawada">Vijayawada</option>
  			<option value="Amaravati">Amaravati</option>
 		    <option value="Bengaluru">Bangalore</option>
  			<option value="Mysuru">Mysuru</option>
  			<option value="Thiruvananthapuram">Thiruvananthapuram</option>
  			<option value="Kochi">Kochi</option>
  			<option value="Madurai">Madurai</option>
  			<option value="Trichy">Trichy</option>
  			<option value="Coimbatore">Coimbatore</option>
  			<option value="Chennai">Chennai</option>
	   </select></td>
	</tr>
	<tr>
		<td>State</td>
		<td><%=c.getState() %></td>
	</tr>
	<tr>
		<td>Update State</td>
		<td><select id="ws_state" name="ws_state" required>
       		<option value="" disabled selected>Please select your state</option>
          	<option value="Andhra Pradesh">Andhra Pradesh</option>
  			<option value="Kerala">Kerala</option>
 		    <option value="Tamilnadu">Tamilnadu</option>
  			<option value="Karnataka">Karnataka</option>
	   </select></td>
	</tr>
	<tr><td><span style="color:red;">(*)Fields are compulsory</span></td><td></td></tr>
</table>
</fieldset><br>
	<button type="submit" name="action" value="Update" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;">Update <i class="fa fa-edit"></i></button>
	<button type="button" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" onclick="document.location='UpdPat.jsp'"><i class="fa fa-chevron-circle-left"> </i> Cancel</button>
</form>
</body>
</html>