<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <style>
  
    #result {
   position: absolute;
   width: 100%;
   max-width:870px;
   cursor: pointer;
   overflow-y: auto;
   max-height: 400px;
   box-sizing: border-box;
   z-index: 1001;
  }
  .link-class:hover{
   background-color:#f1f1f1;
  }
  </style>

<script type="text/javascript">
function ajaxFunction(param)
{
	var xmlHttp;
   
	try     // Firefox, Opera 8.0+, Safari
	{
		xmlHttp=new XMLHttpRequest();
	}
	catch (e)
	{
		try  // Internet Explorer
		{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				alert("Your browser does not support AJAX!");
				return false;
			}
		}
	}

	xmlHttp.onreadystatechange=function()
	{
		if(xmlHttp.readyState==4)
		{
			var result = JSON.parse(xmlHttp.responseText);

			result.forEach(function(track){
				console.log(track.track_id);
				var html  = "<li class='list-group-item d-flex justify-content-between align-items-center'>";
				html += "<label>" + track.title + "</label>";
				html+="<span class='badge badge-primary' style='background-color: #428BCA;'> <a " ;
				html+="href='./addtrackstoplaylist?trackid="+track.track_Id+"'";
				html+= " class='btn-primary' role='button'>" ;
				html+= "Add to Playlist</a> </span>";
				html += "</li>"	;

				document.getElementById("result").innerHTML += html;
				});
			

			console.log(result);
		}
	}

	document.getElementById("result").innerHTML = "";
	var url = "http://localhost:8080/musicstore/searchTrack?search="+param;
	xmlHttp.open("GET",url,true);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send();
}

function searchTracks(){
	var str = document.getElementById("search").value;
	ajaxFunction(str);
}
</script>

 </head>
 <body>
 <jsp:include page="dashboard-sidenav.jsp"></jsp:include>
  <br /><br />
  <div class="container" style="width:900px;">
   <h2 align="center">Search Tracks</h2>
   <br /><br />
   <div align="center" style="margin-top:2%;">
    <input type="text" name="search" id="search" placeholder="Search Tracks" class="form-control" onchange="searchTracks()"/>
   </div><br/><br/>
   <div style="margin-top:2%;">
   <ul class="list-group" id="result"></ul>
   </div>
   <br />
  </div>
 </body>
</html>
