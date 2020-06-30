<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	
 	<link rel="stylesheet" href="./resources/css/webix.css" type="text/css">
    <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/css/bootstrap.min_original.css">
    <link rel="stylesheet" href="./resources/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/statistic.css">
    
    <script src="./resources/js/angular.min.js" type="text/javascript"></script>
    <script src="./resources/js/angular-ui-router.min.js"></script>
	<script src="./resources/js/index.js" type="text/javascript"></script>
	<script src="./resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
	
	<script src="./resources/angular/controller/statistics.js"></script>
	<script src="./resources/angular/controller/statisticsController.js"></script>
	<script src="./resources/js/webix.js" type="text/javascript"></script>  

<title>Statistics Response</title>
</head>
<title>Insert title here</title>
</head>
<body ng-app="Statistics">

<div ng-controller='statisticsController'>
	

	<div style="margin-left: 40%;margin-top:10%; font-size: 150%;">
	 
		 <c:forEach items="${msg}" var = "i" begin = "0" end = "10">
		         Item <c:out value = "${i}"/><p>
		      </c:forEach>
		
	</div>
	
	
</div>



</body>
</html>