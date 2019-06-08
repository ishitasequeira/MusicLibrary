<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><h3>Music Library</h3></a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><h3><a href="#">Home</a></h3></li>&nbsp;
      <li><h3><a href="./searchtrack">Search Track</a></h3></li>&nbsp;
      <li><h3><a href="./searchartist">Search Artist</a></h3></li>&nbsp;
      <li><h3><a href="./playlist">Playlist</a></h3></li>&nbsp;
      <li><h3><a href="./album">Album</a></h3></li>&nbsp;
      <li><h3><a href="./accountsettings">Account Settings</a></h3></li>&nbsp;
    </ul>
    <form class="navbar-form navbar-left" action="/action_page.php">
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Search">
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
  </div>
</nav>
</body>
</html>