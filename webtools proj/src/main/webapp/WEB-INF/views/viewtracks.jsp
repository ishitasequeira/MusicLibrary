<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>

	<jsp:include page="dashboard-sidenav.jsp"></jsp:include>
  		<div style="margin:5% 30%;">
  		<h2>PLAYLIST-Tracks</h2>
			<ul class="list-group list" style="margin-top: 5%">
				<c:set var="list" value="${requestScope.tracks}" />

				<c:if test="${list.size()>0 && list != null}">
				<br/><br/>
				<b>
					<li class="list-group-item d-flex justify-content-between align-items-center">
						<label>Track Name</label><label style="margin-left: 20%">Album Name</label> &nbsp;
						<label style="margin-left: 26%">Artist Name</label>
					</li>
				</b>

					<c:forEach var="a" items="${list}">
						<li class="list-group-item d-flex justify-content-between align-items-center">
							<label>${a.title}</label><label style="margin-left: 30%">${a.album.albumName}</label> &nbsp;
							<label style="margin-left: 30%">${a.album.artist.fname}</label>&nbsp;&nbsp;<label>${a.album.artist.lname}</label>
						</li>
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${list.size()<=0 || list == null}">
  					<h1>No Tracks present !!!!</h1>
  				</c:if>

			</ul>
			</div>

</body>
</html>