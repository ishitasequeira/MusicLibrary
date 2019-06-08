<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
<jsp:include page="dashboard-sidenav.jsp"></jsp:include>
	<div id="login">
		<h3 class="text-center text-white pt-5">Login form</h3>
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
					<c:if test="${requestScope.registration_success != null}">
						<div class="alert alert-success">${requestScope.registration_success}</div>
					</c:if>
						<form id="login-form" class="form" action="home" method="post">
							<h3 class="text-center text-info">Login</h3>
							<div class="form-group">
								<c:if test="${requestScope.login_error!= null}">
									<div class="alert alert-danger">${requestScope.login_error}</div>
								</c:if>
								<label for="username" class="text-info">EmailID:</label><br>
								<input type="email" name="email" id="username"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Password:</label><br>
								<input type="password" name="password" id="password"
									class="form-control" required>
							</div>
							<br />
							<div class="form-group">

								<input type="submit" name="submit"
									class="btn btn-primary btn-block" value="Submit">

							</div>

						</form>
						<div class="border-top card-body text-center">
							Not a User? <a href="./register">Register</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
