<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login Page</title>
</head>
<style>


* {
  box-sizing: border-box;
}


.column {
  float: left;
  width: 33.33%;
  padding: 30px;
  height: 300px; 
}


.row:after {
  content: "";
  display: table;
  clear: both;
}


.row:after {
  content: "";
  display: table;
  clear: both;
}

</style>


<body>
<div class="row">
<div class="column left">
</div>
<div class="column middle">
<h1><center>Hospital_Management_System</center></h1>
	<form  action="LoginServlet" method="post">
			<fieldset style="width: 300px">
				<legend><h3>Login </h3></legend>
				<table align="center">
					<tr>
						<td>User ID</td>
						<td><input type="text" name="username" required="required" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="userpass" required="required" /></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
				<center><input type="submit"  value="Login" /></center>
			</fieldset>
	</form>
</div>
</div>

<div class="row">
  <div class="column left">
    <h3>Hint</h3>
	<fieldset style="width: 300px;">
	To Sign_IN as Pharmacist<br>
	User ID : Pharmacist<br>
	Password : 1234@pharmacist
	</fieldset>
  </div>
  
  <div class="column middle">
	<h3>Hint</h3>
	<fieldset style="width: 300px">
	To Sign_IN as Admin<br>
	User ID : Admin<br>
	Password : 1234@admin
	</fieldset>
  </div>
  
  
  <div class="column right">
    <h3>Hint</h3>
	<fieldset style="width: 300px;">
	To Sign_IN as Diagnist<br>
	User ID : Diagnostic<br>
	Password : 1234@diagnostic
	</fieldset>
  </div>
</div>




</body>
</html>
