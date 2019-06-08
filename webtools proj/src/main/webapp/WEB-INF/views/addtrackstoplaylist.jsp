<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="dashboard-sidenav.jsp"></jsp:include>
	<div style="margin: 5% 20%;">
		<c:if test="${requestScope.playlists != null}">
			<form action="./addToPlylist" method="GET">
			<label>Track:</label><input type="text" name="track" value="${track.title}" disabled="disabled"/><br/><br/>
				<input type="hidden" name="trackid" value="${track.track_Id}"/>
				<br/><br/><label>PLAYLISTS</label>
				<select name="playlist">
				
				<c:forEach var="p" items="${requestScope.playlists}">
					<option value="${p.playlist_Id}">${p.playListName}</option>
				</c:forEach>
				</select><br/><br/>
				<input type="submit" name="submit"
									class="btn btn-primary btn-block" value="Submit">
			</form>
		</c:if>
		<c:if test="${requestScope.playlists == null}">
			<h1>No playlists available to add to. Please create one!!</h1>
		</c:if>
		
	</div>
</body>
</html>