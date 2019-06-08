<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<style type="text/css">
.list {
	margin: 5% 20% 0% 30%;
}
</style>
<body>

	<jsp:include page="dashboard-sidenav.jsp"></jsp:include>

		<ul class="list-group list">
			<c:set var="list" value="${requestScope.albumsList}" />
			<h1 style="margin-left: 20%">My Albums</h1>
			<c:if test="${list.size()>0 && list != null}">
			<label>ALBUM NAME</label> &nbsp; <label style="margin-left:25%"> STATUS</label><label style="margin-left:40%">ACTION</label>
				<c:forEach var="a" items="${list}">
					<li class="list-group-item d-flex justify-content-between align-items-center">
						<label>${a.albumName}</label> &nbsp; <label style="margin-left:30%"> ${a.status}</label>
					<c:if test="${a.status=='ACCEPTED'}"> 
						<span class="badge progress-bar-primary" style="background-color: #428BCA;"> 
								<a href="./viewalbumtracks?albumid=${a.album_id}" class="btn-primary " role="button"
									aria-pressed="true">VIEW TRACKS</a>
							</span>
						</c:if>
						<c:if test="${a.status!='ACCEPTED'}">
						<span class="badge progress-bar-secondary">
								<a href="#" class="btn-secondary" role="button"
									aria-pressed="true" disabled>VIEW TRACKS</a>
							</span>
						</c:if>
						

					</li>
					<br />
				</c:forEach>
			</c:if>
			<c:if test="${list.size()<=0 || list == null}">
  					No Albums present !!!!
  				</c:if>

		</ul>
	</div>
</body>
</html>