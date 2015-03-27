<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
    response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires", 0);           //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");        //HTTP 1.0 backward compatibility
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>COHO</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-theme.min.css">
<!-- Bootstrap Calendar CSS -->
<link rel="stylesheet" type="text/css" href="resources/css/datepicker.css">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="resources/css/style.css">

</head>
<body>
    <!-- Top navigation bar -->
    <tiles:insertAttribute name="header-basic" />

	<div class="container-fluid">
		<div class="row">
			<!-- Main content of the page -->
			<tiles:insertAttribute name="main-content" />
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/bootstrap-datepicker.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="resources/js/ie10-viewport-bug-workaround.js"></script>
    
    <!-- Custom Javascript -->
    <script src="resources/js/script.js"></script>
</body>
</html>