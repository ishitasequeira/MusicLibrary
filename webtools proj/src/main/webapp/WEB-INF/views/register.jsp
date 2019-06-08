<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/resources/css/register.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="dashboard-sidenav.jsp"></jsp:include>


	<div class="container">
		<hr>
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<header class="card-header"> <a href="./login.htm"
						class="float-right btn btn-outline-primary mt-1">Log in</a>
					<h4 class="card-title mt-2">Sign up</h4>
					</header>
					<article class="card-body"> <form:form commandName="user">
						<div class="form-row">
							<div class="col form-group">
								<label>First name </label>
								<form:input type="text" class="form-control" path="fname"
									placeholder=""></form:input>
								<form:errors path="fname" />
								<br />
							</div>

							<!-- form-group end.// -->
							<div class="col form-group">
								<label>Last name</label>
								<form:input type="text" class="form-control" path="lname"
									placeholder=""></form:input>
								<form:errors path="lname" />
								<br />
							</div>
							<!-- form-group end.// -->
						</div>
						<!-- form-row end.// -->
						<div class="form-group">
							<label>Email address</label>
							<form:input type="email" class="form-control" path="email"
								placeholder=""></form:input>
							<form:errors path="email" />
							<c:if test="${requestScope.error != null}">
									<div class="alert alert-danger">${requestScope.error}</div>
								</c:if>
							<br /> <small class="form-text text-muted">We'll never
								share your email with anyone else.</small>
						</div>
						<!-- form-group end.// -->
						<div class="form-group">
							<label>Register As:</label><br> <label
								class="form-check form-check-inline"> <form:radiobutton
									path="role" class="form-check-input" value="SUBSCRIBER"
									checked="checked"></form:radiobutton> <span
								class="form-check-label"> Subscriber </span>
							</label> <label class="form-check form-check-inline"> <form:radiobutton
									path="role" class="form-check-input" value="ARTIST"></form:radiobutton>
								<span class="form-check-label"> Artist</span>
							</label>
						</div>
						<!-- form-row.// -->
						<div class="form-group">
							<label>Create password</label>
							<form:input class="form-control" type="password" path="password"
								placeholder=""></form:input>
							<form:errors path="password" /><br />
						</div>
						<!-- form-group end.// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								Register</button>
						</div>
						<!-- form-group// -->
						<small class="text-muted">By clicking the 'Sign Up'
							button, you confirm that you accept our <br> Terms of use
							and Privacy Policy.
						</small>
					</form:form> </article>
					<!-- card-body end .// -->
					<div class="border-top card-body text-center">
						Have an account? <a href="./login">Log In</a>
					</div>
				</div>
				<!-- card.// -->
			</div>
			<!-- col.//-->

		</div>
		<!-- row.//-->


	</div>
	<br>
	<br>
	</article>
</body>
</html>