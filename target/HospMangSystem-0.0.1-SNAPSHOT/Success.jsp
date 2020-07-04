<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Success</title>

</head>
<body>
<%
int id = (Integer)request.getAttribute("ssnid");
%>
</body>
<script type="text/javascript">
confirm("Patient creation with SSN_ID <%=id%> was done successfully."); 
window.location.href = "RegPat.jsp";
</script>
</html>