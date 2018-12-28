<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>

</head>
<style>
.tablecon{
	
	-webkit-box-shadow: 0px 0px 54px -7px rgba(0,0,0,0.75); 
	-moz-box-shadow: 0px 0px 54px -7px rgba(0,0,0,0.75); 
	box-shadow: 0px 0px 54px -7px rgba(0,0,0,0.75);
	
	
}
.tr:nth-child(even) {background: #EEEEEE}
.tr:nth-child(odd) {background: #FFFFFF}
.th{
background: #FF9898}
.td
{ 
width: 10%;
 
	
}
</style>
<body>

<table id="content"
	style="min-height: 80px; height: 80px; width: 100%; border: 0px; margin: 0; padding: 0; bordercollapse: collapse; background-color: #800000;">
	<tr style="height: 60px;">
		<td><img src="resources/images/acn_logo.PNG" alt="Banner Image"></td>
		<td align=right>
			<p style="font-family: Calibri, Garamond, Serif;">
				<font color="white"; ><B> Accenture Philippines Delivery Center </B></font>
			</p>
			<p style="font-family: Calibri, Garamond, Serif;">
				<font color="white";>Technology  Capability  Fundamentals for Java</font>
			</p>
		</td>
	</tr>
</table>
<br><br>
	<form action="" method="GET">

<center>
		<table class ="tablecon" border-radius: 20px;>
		<tr>
		<th class="th">Billing Cycle </th>
		<th class="th">Start Date</th>
		<th class="th">End Date</th>
		<th class="th">First Name</th>
		<th class="th">Last name</th>
		<th class="th">Amount</th>
		
		</tr>
		
		
		<c:forEach var="recordItem" items = "${contents}">
		<tr class="tr">
		<td class="td">${recordItem.billngCycle}</td>
		<td class="td">${recordItem.startDate}</td>
		<td class="td">${recordItem.endDate}</td>
		<td class="td">${recordItem.customerLastName}</td>
		<td class="td">${recordItem.customerFirstName}</td>
		<td class="td">${recordItem.amount}</td>
		
		</tr>
		</c:forEach>
		</table>
			<font size="3.5" style="font-family: Calibri, Garamond, Serif; color: #D2691E;">Billing Automated Request System</font>
			</center>
			<br><br>

			<center>
			<font size="5" style="font-family: Calibri, Garamond, Serif; color: green;">Successfully processed Request File.</font>
			</center>
			<br><br>
        <hr>
        <hr>
		<table align= "Center" style="font-family: Calibri, Garamond, Serif; ">
			<tr>
			<td colspan=125><img src="resources/images/Computer_training2_jpg.jpg" height=200 width=200></td>
				<td valign="Center">
					<table align= "center" >
					<!-- 	<tr><td font size="15" style="font-family: Calibri, Garamond, Serif; color: #D2691E;">Request Successfuly Processed!!!</td></tr> -->
						<tr><td><a href="/tcf">Process another Request</a></td></tr>
					</table>
				</td>
	<!-- 			<td colspan=50><img src="./images/Computer_training2_jpg.jpg" height=200 width=150></td>
				<td colspan=50><img src="./images/10876_jpg.jpg" height=200 width=250></td>  -->

			</tr>
		</table>
	</form>

	<br><br><br><br>

<!-- Footer -->
<style>
#footer {
    background-color:white;
    color:black;
    clear:both;
    text-align:center;
    padding:5px;
    border:1px solid #999999
}
</style>
<div id="footer">

				<P align=center style="font-family: Calibri, Garamond, Serif;">
					<font size="1.5">
						TCF Java - Machine Problem
					</font>
				</P>
</div>
<!-- Footer END -->
</body>
<!--<p>${response}</p>  -->
</html>