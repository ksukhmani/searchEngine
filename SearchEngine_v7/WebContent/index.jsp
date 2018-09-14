<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.tutorialspoint.lucene.MainCaller"%>
<!DOCTYPE html>
<html>
<head>

<title>
Search Engine JSP 
</title>

<!-- Meta Tags -->
<meta charset="utf-8">
<meta name="generator" content="Wufoo">
<meta name="robots" content="index, follow">

<!-- CSS -->
<link href="css/structure.css" rel="stylesheet">
<link href="css/form.css" rel="stylesheet">
<link href="css/theme.css" rel="stylesheet">

<!-- JavaScript -->
<script src="scripts/wufoo.js"></script>	

</head>

<body id="public">
<div id="container" class="ltr">

<h1 id="logo">
<a></a>
</h1>

<form id="form1" name="form1" action="doSearch" method = "post">
  
<header id="header" class="info">
<h2 class="centerFormProp">Search Engine</h2>
<div class="0"></div>
</header><ul>
<li id="foli3" class="notranslate      ">
<label class="desc" id="title3" for="Field3">
<div>
Data Set: 
<select name="dataset">
    <option value="WindsorStar">WindsorStar</option>
    <option value="CNN">CNN</option>
    <option value="BBC" >BBC</option>
  </select>
</div> 
<div class="0"></div>

<INPUT TYPE="radio" name="command" checked="true" value="Pattern Search">Pattern Search <INPUT TYPE="radio" NAME="command" VALUE="RegEx">RegEx <INPUT TYPE="radio" NAME="command" VALUE="Top N">Top N<BR>


Enter Text :
<span id="req_3" class="req">*</span>
</label>
<div>
<input id="Field3" name="Field3" type="text" class="field text medium" value="" maxlength="255" tabindex="1" onkeyup=""       required placeholder="" />
</div>
</li> 
<li class="buttons ">
<div>
<!--<input id="saveForm" name="saveForm" class="btImg submit" type="image"  src="" alt="Submit"/>-->

<input type="submit" value="Submit">
</div>
</li>
</ul>
</form>
<%
    MainCaller MainCaller=(MainCaller)request.getAttribute("cust");
%>
<table align="center" bgcolor="#FFFFCC" border="1" width="70%">
    <tr>
        <td colspan="2" align="center"><%="Search Results:" %></td>
    </tr>
     	<%if(MainCaller!=null){ %>
        <%String str[]=MainCaller.getURLList();%>
        <%for(String s : str) { %>
	        <tr>	
	        		<td> <%=s%> </td>
	        </tr>
	    	<%}%>
   		<%}%>
</table>
</div><!--container-->
</body>
</html>
