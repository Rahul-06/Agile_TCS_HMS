<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="javax.servlet.http.HttpSession,javax.servlet.http.HttpServletRequest,hms.bean.Pharmacy,hms.bean.Patient" %>
  
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
<meta charset="ISO-8859-1">
<title>Issue Medicine</title>
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
<h3><center>Manage_Medicine</center></h3> 
<div align="center">
<form action="PharmacyServelet" method="post">
<fieldset style="width:350px;">
<table>
	<tr>
		<td>Medicine Name</td>
		<td><input type="text" name="ws_med_name" required></td> 
	</tr>
	<tr>
		<td>Quantity</td>
		<td><input type="number" name="ws_qty" min="1" required></td>
	</tr>
</table>
</fieldset><br>
<button type="submit" name="action" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;" value="issue">Issue <i class="fa fa-plus-circle"></i></button>
</form>
</div>
</body>
</html>