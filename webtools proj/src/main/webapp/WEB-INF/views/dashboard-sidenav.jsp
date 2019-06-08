<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
<style>
#sidebar {
	width: 220px;
	position: fixed;
	top: 0;
	left: 0;
	height: 100vh;
	z-index: 999;
	background: #007BBD;
	color: #fff;
}

h1{
	margin: 10%;
	font-family: 'Brush Script MT',cursive;
}

.active{
	height: 60px;
	background-color: #007BBB !important;
	border: none;
}
</style>
</head>
<body>
	<nav id="sidebar">
	<div class="sidebar-header">
		<h1 class="text-center">Music Store</h1>
	</div>
	<br/><br/>
	<div class="list-group text-center">
		<b>
			<c:if test="${sessionScope.user == null}">
				<a href="./" class="list-group-item list-group-item-action active">Login &#10148;</a> 
				<a href="./register" class="list-group-item list-group-item-action active">Register &#10148;</a>
			</c:if>
			<c:if test="${sessionScope.user.role == 'ADMIN'}">
				<a href="./homepage" class="list-group-item list-group-item-action active">Home</a> 
				<a href="./viewartists" class="list-group-item list-group-item-action active">View Artists</a>
				<a href="./viewsubscribers" class="list-group-item list-group-item-action active">View Subscribers</a>
				<a href="./viewalbumrequests" class="list-group-item list-group-item-action active">Requests</a>
				<a href="./report" class="list-group-item list-group-item-action active">View Report</a>
				<a href="./logout" class="list-group-item list-group-item-action active">Logout</a>
			</c:if>
			<c:if test="${sessionScope.user.role == 'ARTIST'}">
				<a href="./homepage" class="list-group-item list-group-item-action active">Home</a> 
				<a href="./viewalbums" class="list-group-item list-group-item-action active">Albums</a>
				<a href="./add" class="list-group-item list-group-item-action active">New Track/Album</a>
				<a href="./requeststatus" class="list-group-item list-group-item-action active">My Requests</a>
				<a href="./logout" class="list-group-item list-group-item-action active">Logout</a>
			</c:if>
			<c:if test="${sessionScope.user.role == 'SUBSCRIBER'}">
				<a href="./homepage" class="list-group-item list-group-item-action active">Home</a> 
				<a href="./search" class="list-group-item list-group-item-action active">Search</a>
				<a href="./playlist" class="list-group-item list-group-item-action active">My Playlists</a>
				<a href="./logout" class="list-group-item list-group-item-action active">Logout</a>
			</c:if>
		</b>
	</div>
	</nav>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
		integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
		crossorigin="anonymous"></script>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
		integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>

</body>
</html>