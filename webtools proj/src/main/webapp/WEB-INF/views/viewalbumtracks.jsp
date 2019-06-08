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
  		<div style="margin:0% 25%;">
			<ul class="list-group list">
				<c:set var="list" value="${requestScope.tracks}" />

				<c:if test="${list.size()>0 && list != null}">
				<br/><br/>
				<h1 style="margin-left: 20%;">ALBUM-Tracks</h1>
					<li class="list-group-item d-flex justify-content-between align-items-center">
							<label>Track Id</label> &nbsp;<label style="margin-left:30%">Title</label> &nbsp;
							<label style="margin-left:30%">Filename</label> &nbsp;
						</li>
					<c:forEach var="a" items="${list}">
						<li class="list-group-item d-flex justify-content-between align-items-center">
							<label style="margin-left: 2%">${a.track_Id}</label> &nbsp;<label style="margin-left:34%">${a.title}</label> &nbsp;
							<label style="margin-left:30%">${a.fileName}</label> &nbsp;
						</li>
						<br />
					</c:forEach>
				</c:if>
				<c:if test="${list.size()<=0 || list == null}">
  					No Tracks present !!!!
  				</c:if>

			</ul>
			</div>

</body>
</html>