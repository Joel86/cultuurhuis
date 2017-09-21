<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
	<c:import url='/WEB-INF/JSP/head.jsp'>
		<c:param name='title' value='Reservatiemandje'/>
	</c:import>
</head>
<body>
	<h1>Het Cultuurhuis:reservatiemandje
		<img src='/images/mandje.png' alt='mandje'>
	</h1>
	<a href='<c:url value="/index.htm"/>'>Voorstellingen</a>
	<table>
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Plaatsen</th>
			<th><input type='submit' value='Verwijderen'/></th>
		</tr>
		<c:forEach var='gereserveerdeVoorstelling' items='${gereserveerdeVoorstellingen}'>
			<tr>
				<th>${gereserveerdeVoorstelling.datumTijd}</th>
				<th>${gereserveerdeVoorstelling.titel}</th>
				<th>${gereserveerdeVoorstelling.uitvoerders}</th>
				<th>${gereserveerdeVoorstelling.prijs}</th>
				<th></th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>