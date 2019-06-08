<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!------ Include the above in your HEAD tag ---------->

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="dashboard-sidenav.jsp"></jsp:include>
	<div style="margin-left: 30%; margin-top: 5%">
		<a href="./addNew" class="btn btn-primary btn-lg active"
			role="button" aria-pressed="true">Add New Album</a> &nbsp;&nbsp; <a
			style="margin-left: 10%;" href="./addTrack"
			class="btn btn-primary btn-lg active" role="button"
			aria-pressed="true">Add New Track</a>
		<c:if test="${requestScope.addType == 'ALBUM'}">

			<div style="margin-top: 5%">
				<form action="./add" method="post">
					<label>ALBUM name </label> <input type="text" class="form-control"
						style="width: 41%" name="albumName" placeholder=""> <input
						type="hidden" name="addType" value="ALBUM" /> <br />
					<br /> <input type="submit" class="form-control btn-primary"
						style="width: 20%; margin-left: 10%" name="submit" placeholder="">
				</form>
			</div>
		</c:if>

		<c:if test="${requestScope.addType == 'TRACK'}">

		<form:form commandName="track" enctype="multipart/form-data"
				class="form-horizontal" method="post">
			<br/><br/><br/><br/>
			<c:if test="${requestScope.albums.size()<=0 || requestScope.albums == null }">
				<h1> NO ALBUMS TO ADD TRACKS!!!! </h1>
			</c:if>
				<c:if test="${requestScope.albums.size()>0 && requestScope.albums != null }">
				<h4>Select Album</h4>
				<form:select id="albumId" name="album" path="albumId" style="width:40%">
					<c:forEach items="${requestScope.albums}" var="album">
						<option value="${album.album_id}">${album.albumName}</option>
					</c:forEach>
				</form:select><br/><br/>
				
				<h4> Title</h4>
				<form:input type="text" class="form-control"
						style="width:40%" name="trackName" path="title" placeholder=""></form:input><br/><br/>
				
				<h4> Audio File </h4>
				<form:input type="file" id="track" name="track" required="required" path="music" accept=".mp3,audio/*"></form:input>
				
				<br/>
				<input type="hidden" name="addType" value="TRACK" />
    
    			<br />
    			<input type="submit" class="form-control btn-primary" style="width: 30%; margin-left: 5%" name="submit" >
				</c:if>
    </form:form>
		</c:if>

	</div>
</body>
</html>