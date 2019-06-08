<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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


	<div style="margin: 2% 25%">

		<a href="./addNewPlaylist" class="btn btn-primary btn-lg active" role="button">Add New Playlist</a>

		<c:if test="${requestScope.addType == 'PLAYLIST'}">
			<div style="margin-top: 5%">
				
				<form action="./addNewPlaylist" method="post">
				<c:if test="${requestScope.errormessage != null}">
					<label class="alert-danger">${errormessage}</label>
				</c:if>
				<br/>
					<label>ALBUM name </label> <input type="text" class="form-control"
						style="width: 41%" name="playlistName" placeholder=""> <input
						type="hidden" name="addType" value="ALBUM" /> <br /> <br /> <input
						type="submit" class="form-control btn-primary"
						style="width: 20%; margin-left: 10%" name="submit" placeholder="">
				</form>
			</div>
		</c:if>

		<c:if test="${requestScope.addType == 'VIEW_PLAYLIST'}">
			<ul class="list-group list">
				<c:set var="list" value="${requestScope.playlists}" />

				<c:if test="${list.size()>0 && list != null}">
				<br/><br/>
				<h3>PLAYLISTS</h3>
					<c:forEach var="a" items="${list}">
						<li class="list-group-item d-flex justify-content-between align-items-center">
							<label style="margin-left:5%">${a.playListName}</label> &nbsp;
						<span class="badge progress-bar-primary" style="background-color: #428BCA;"> 
								<a href="./playlisttracks?playlistid=${a.playlist_Id}" class="btn-primary" role="button">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;View&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
							</span>
<!-- 							<span class="badge progress-bar-danger">  -->
<%-- 								<a href="./deletePlaylist?playlistid=${a.playlist_Id}" class="btn btn-danger btn" role="button" --%>
<!-- 									aria-pressed="true">Remove</a> -->
<!-- 							</span> -->
							</li>
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${list.size()<=0 || list == null}">
  					No PlayList present !!!!
  				</c:if>

			</ul>
		</c:if>

	</div>


</body>
</html>