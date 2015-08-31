<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PeopleFinder</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
h1 {
	text-align: center;
	color: red;
	font-size: 80px;
	font-family: "Bookman Old Style";
}

body {
	font-family: "Bookman Old Style";
	color: red;
	background-image: url('people.jpg');
}

nav {
	font-family: "Bookman Old Style";
	color: red;
}
</style>
</head>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-brand">Customers</div>
	<div>
		<ul class="nav navbar-nav">
			<li><a href="FindPeople.jsp" style="color: white"><b>Home</b></a></li>
		</ul>
	</div>
</div>
</nav>
<body>
<br>
<br>
	<h1>
		<b>People Finder tool</b>
	</h1>
	<br>
	<br>
	<div class="panel-group col-sm-6 col-sm-offset-3" style=width:50%>
		<div class="panel-primary panel" style=text-align:center>
			<div class="panel-heading">Enter text to search</div>

		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<form action="PeopleFinder" method=post>
					<label style="font-size: 20px">Search</label> <input type="text"
						name="search"></input><br></br> <input type="submit"
						class="btn pull-left btn-primary btn-lg" value="Search"></input><br></br>
				</form>
			</div>
		</div>


	</div>

</body>
</html>