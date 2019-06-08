<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
.list{
	margin: 5% 20% 0% 30%;
}
</style>
</head>
<body>

	<jsp:include page="dashboard-sidenav.jsp"></jsp:include>
	<ul class="list-group">
		<c:set var="list" value="${requestScope.subscriberList }" />
		<c:if test="${list.size()>0 && list != null}">
			<c:forEach var="a" items="${list}">
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					<label>${a.fname}</label> &nbsp; <label>${a.lname}</label> 
					<span class="badge badge-primary badge-pill"> 
					<a href="./deletesubscriber?user=${a.id}" class="btn btn-primary btn active"
						role="button" aria-pressed="true">Delete Subscriber</a>
				</span>
				</li>
			</c:forEach>
		</c:if>
		<c:if test="${list.size()<=0 || list == null}">
  				No artist present!!!!!
  </c:if>
	</ul>
</body>
</html>