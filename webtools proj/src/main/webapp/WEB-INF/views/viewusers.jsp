<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Artist</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style type="text/css">
.list {
	margin: -8% 20% 0% 30%;
}
</style>
</head>
<body>

	<jsp:include page="dashboard-sidenav.jsp"></jsp:include>

	<div>

		<c:set var="list" value="${requestScope.userList }" />
		<c:if test="${requestScope.display=='ARTIST'}">
			<h1 style="margin-left:40%">Artists</h1>
			<ul class="list-group list">
				<c:if test="${list.size()>0 && list != null}">
					<c:forEach var="a" items="${list}">
						<li class="list-group-item d-flex justify-content-between align-items-center">
							<label>${a.fname}</label> &nbsp; <label>${a.lname}</label> 
							<span class="badge badge-primary" style="background-color: #428BCA;"> 
							<a href="./deleteartist?user=${a.id}" class="btn-primary" role="button">Delete Artist</a>
						</span>
						</li>
						<br />
					</c:forEach>
				</c:if>
			</ul>
			<c:if test="${list.size()<=0 || list == null}">
  					<h1 style="margin-left:40%">No Artists Found!!</h1>
  				</c:if>
		</c:if>

	</div>

	<div>
		<c:if test="${requestScope.display=='SUBSCRIBER'}">
			<h1 style="margin-left:40%">Subscribers</h1>
			<c:if test="${list.size()>0 && list != null}">
				<ul class="list-group list">
					<c:forEach var="a" items="${list}">
						<li class="list-group-item d-flex justify-content-between align-items-center">
							<label>${a.fname}</label> &nbsp; <label>${a.lname}</label> 
							<span class="badge badge-primary" style="background-color: #428BCA;">
								<a href="./deletesubscriber?user=${a.id}" class="btn-primary " role="button">Delete Subscriber</a>
							</span>
						</li>
					</c:forEach>
				</ul>
			</c:if>

			<c:if test="${list.size()<=0 || list == null}">
  					<h1 style="margin-left:40%">No Subscribers Found!!</h1>
  				</c:if>
		</c:if>

	</div>

</body>
</html>