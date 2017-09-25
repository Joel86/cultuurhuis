<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang='nl'>
<head>
	<c:import url='/WEB-INF/JSP/head.jsp'>
		<c:param name='title' value='Overzicht'/>
	</c:import>
</head>
<body>
	<h1>Het Cultuurhuis:overzicht
		<img src="<c:url value='/images/bevestig.png'/>" alt='Bevestig'>
	</h1>
	<h2>Gelukte reserveringen</h2>
	<table>
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Plaatsen</th>
		</tr>
		<c:forEach var='gelukteReservatie' items='${gelukteReservaties}'>
			<tr>
				<td>${gelukteReservatie.voorstelling.datumTijd}</td>
				<td>${gelukteReservatie.voorstelling.titel}</td>
				<td>${gelukteReservatie.voorstelling.uitvoerders}</td>
				<td>${gelukteReservatie.voorstelling.prijs}</td>
				<td>${gelukteReservatie.aantalPlaatsen}</td>
			</tr>
		</c:forEach>
	</table>
	<h2>Mislukte reserveringen</h2>
	<table>
		<tr>
			<th>Datum</th>
			<th>Titel</th>
			<th>Uitvoerders</th>
			<th>Prijs</th>
			<th>Plaatsen</th>
			<th>Vrije plaatsen</th>
		</tr>
		<c:forEach var='mislukteReservatie' items='${mislukteReservaties}'>
			<tr>
				<td>${mislukteReservatie.voorstelling.datumTijd}</td>
				<td>${mislukteReservatie.voorstelling.titel}</td>
				<td>${mislukteReservatie.voorstelling.uitvoerders}</td>
				<td>${mislukteReservatie.voorstelling.prijs}</td>
				<td>${mislukteReservatie.aantalPlaatsen}</td>
				<td>${mislukteReservatie.voorstelling.vrijePlaatsen}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>