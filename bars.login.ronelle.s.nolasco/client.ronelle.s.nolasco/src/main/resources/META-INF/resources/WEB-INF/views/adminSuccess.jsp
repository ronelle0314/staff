
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    
    <!-- Custom styles for this template -->
    <link href="bootstrap-Navbar-fixedToTop.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>


<style>
.customers {
	font-family: "Century Gothic;
	border-collapse: collapse;
	width: 100%;
}

.customers td, .customers th {
	border: 1px solid #ddd;
	text-align: center;
	padding: 8px;
}

.customers tr:nth-child(even) {
	background-color: #f2f2f2;
	text-align: center;
}

.customers tr:hover {
	background-color: #ddd;
}

.customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background: linear-gradient(to bottom, #ffcccc 0%, #ff9966 100%);
	color: white;
	text-align: center;
}
.btn-success{
	width: 10%;
	background-color: #ff884d;
}
.btn-success:hover{
	background-color: #ff661a;
	}
.btn-delete{
background: url(../../image/delete.jpg) ;
 background-repeat: no-repeat;
 background-size: 100% 100%;
 border-outline: none;
 cursor: pointer;
 border: none;
 width: 30%;
 height: 40px;;
 

}
.btn-update{
background: url(../../image/edit.png) ;
 background-repeat: no-repeat;
 background-size: 100% 100%;
 border-outline: none;
 cursor: pointer;
 border: none;
 width: 30%;
 height: 40px;;
 

}
.messageStyle{
	color: #cc6600;
	font-family: "Century Gothic";
}
.text-muted{
	font-size: 40px;
	color: white;
}
</style>



</head>
<body >
	<div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
      <form method="POST" action="/insertPage"><button class="btn btn-success">Add User</button>
      
      </form>
        <form method="POST" action="/logout"><button class="btn btn-success">Logout</button>
      
      </form>
      
      <br>
       <br>
      
      <span class="text-muted">Welcome: ${currentUser}</span>
    </div>
  </div>
  <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
</div>

		

	
	<form action="" method="">

		<center>
			<font size="10"
				style="font-family: Century Gothic; color: #D2691E;">List of Users</font>
				<br>
				<br>
				<p class="messageStyle">${insertedUser}</p>
		</center>
		<br> <br>
		<center>
	
			<table class="customers">
				<tr>
					<th>Id</th>
					<th>User Name</th>
					<th>Password</th>
					<th colspan= "2">Action</th>
				</tr>

				<c:forEach var="recordItem" items="${userList}">
					<tr>
					
						<td>${recordItem.userId}</td>
						<td>${recordItem.userName}</td>
						<td>${recordItem.userType}</td>
						<td>
						<form action="/deleteUser" method="POST">
						<button type="" class="btn-delete" value="${recordItem.userName}" name=userName onclick="myFunction()" title="Delete"></button>
						</form>
						</td>
						<form action="/updatePage" method="POST">
						<td><button type="" value="${recordItem.userName}" name=userName class="btn-update" title="Update"></button></td>
						</form>
					</tr>
				</c:forEach>
			</table>

		</center>

	</form>

	<br>
	<br>
	<br>
	<br>

	<!-- Footer -->
	<style>
#footer {
	background-color: white;
	color: black;
	clear: both;
	text-align: center;
	padding: 5px;
	border: 1px solid #999999
}
</style>
	<div id="footer">

		<P align=center style="font-family: Calibri, Garamond, Serif;">
			<font size="5"> TCF Java - Admin </font>
		</P>
	</div>
	<!-- Footer END -->
	 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
  
<script>
function myFunction() {
  var txt;
  var r = confirm("Do you want do delete this record!");
  if (r == true) {
	  alert("You've Successfully deleted a User");
    txt = "You pressed Cancel!";
  }
  document.getElementById("demo").innerHTML = txt;
}
</script>
</body>
<!--<p>${response}</p>  -->
</html>