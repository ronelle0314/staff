
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>


<style>
.customers {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

.customers td, .customers th {
	border: 1px solid #ddd;
	padding: 8px;
}

.customers tr:nth-child(even) {
	background-color: #f2f2f2;
}

.customers tr:hover {
	background-color: #ddd;
}

.customers th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #4CAF50;
	color: white;
}
</style>



</head>
<body >
	
	<form action="" method="">

		<center>
			<font size="3.5"
				style="font-family: Calibri, Garamond, Serif; color: #D2691E;">User List </font>
		</center>
		<br> <br>
		<center>
			<table class="customers">
				<tr>
					<th>Id</th>
					<th>User Name</th>
					<th>Password</th>
					<th>User Type</th>
				</tr>

				<c:forEach var="recordItem" items="${userList}">
					<tr>
						<td>${recordItem.userId}</td>
						<td>${recordItem.userName}</td>
						<td>${recordItem.password}</td>
						<td>${recordItem.userType}</td>
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
			<font size="1.5"> TCF Java - Client </font>
		</P>
	</div>
	<!-- Footer END -->
</body>
<!--<p>${response}</p>  -->
</html>