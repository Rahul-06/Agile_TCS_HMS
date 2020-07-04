<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

fieldset {
 	display: inline-block;
 	align: center;
 	text-align: center;
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
<title>Registration Page</title>
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

<center><h3>New Patient Registration</h3></center>
<div align="center">
<form action="PatientServelet" method="post">
<fieldset style="width:350px;">
<table align="center">
   <tr>
       <td><label style="font-size: 15px;color:blue;">Patient SSN Id</label> <span style="color:red;">*</span></td>
       <td><input type="number" name="ws_ssn" min="1" pattern="^\d{1,9}$" required></td>
   </tr>
   <tr>
       <td>Patient Name <span style="color:red;">*</span></td>
       <td><input type="text" name="ws_name" required></td>
   </tr>
   <tr>
       <td>Age <span style="color:red;">*</span></td>
       <td><input type="text" name="ws_age" pattern="^(1[89]|[2-9]\d)$" required></td>
   </tr>
   <tr>
       <td>Date of Admit <span style="color:red;">*</span></td>
       <td><input type="text" name="ws_doj" pattern="^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$" placeholder="yyyy-mm-dd" required></td>
   </tr>
   <tr>
       <td>Type of Bed <span style="color:red;">*</span></td>
       <td><select id="ws_tob" name="ws_tob" required>
       		<option value="" disabled selected>Please select your Bed Type</option>
          	<option value="General-Ward">General-Ward</option>
  			<option value="Semi-Share">Semi-Share</option>
 		    <option value="Single-Room">Single-Room</option>
	   </select></td>
   </tr>
   <tr>
       <td>Address <span style="color:red;">*</span></td>
       <td><textarea name="ws_adrs" rows="4" cols="20" required></textarea></td>
   </tr>
   <tr>
       <td>City <span style="color:red;">*</span></td>
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
       <td>State <span style="color:red;">*</span></td>
       <td><select id="ws_state" name="ws_state" required>
       		<option value="" disabled selected>Please select your state</option>
          	<option value="Andhra Pradesh">Andhra Pradesh</option>
  			<option value="Kerala">Kerala</option>
 		    <option value="Tamilnadu">Tamilnadu</option>
  			<option value="Karnataka">Karnataka</option>
	   </select></td>
   </tr>
   
   
</table>
</fieldset><br>
<button type="submit" name="action" value="Submit" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;">Submit <i class="fa fa-plus-circle"></i></button>
<button type="reset" name="Reset" style="border-radius: 12px;background-color: #4CAF50;border: none;color: white;padding: 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;">Reset <i class="fa fa-refresh"></i></button>

</form>
</div>
</body>
</html>