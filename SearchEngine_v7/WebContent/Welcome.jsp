<%@page import="com.tutorialspoint.lucene.MainCaller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
<%
    MainCaller MainCaller=(MainCaller)request.getAttribute("cust");
%>
<table align="center" bgcolor="#FFFFCC" border="1" width="70%">
    <tr>
        <td colspan="2" align="center"><%="Search Results:" %></td>
    </tr>
        <%String str[]=MainCaller.getURLList();%>
        <%for(String s : str) { %>
        <tr>
        	<td><a href=<%=s%>> <%=s%> </a></td>
        </tr>
    	<%}%>
    </table>
</body>
</html>