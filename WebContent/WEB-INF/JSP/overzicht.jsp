<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<vdab:head title='Overzicht'/>
</head>
<body>
	<h1>Het Cultuurhuis:overzicht
		<img src="<c:url value='/images/bevestig.png'/>" alt='Bevestig'>
	</h1>
	<vdab:menu/>
	<c:if test='${not empty gelukteReservaties}'>
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
					<td><fmt:formatDate value='${gelukteReservatie.voorstelling.datumTijd}' 
						type='both' dateStyle='short' timeStyle='short'/></td>
					<td>${gelukteReservatie.voorstelling.titel}</td>
					<td>${gelukteReservatie.voorstelling.uitvoerders}</td>
					<td>${gelukteReservatie.voorstelling.prijs}</td>
					<td>${gelukteReservatie.aantalPlaatsen}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test='${not empty mislukteReservaties}'>
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
					<td><fmt:formatDate value='${misReservatie.voorstelling.datumTijd}' 
						type='both' dateStyle='short' timeStyle='short'/></td>
					<td>${mislukteReservatie.voorstelling.titel}</td>
					<td>${mislukteReservatie.voorstelling.uitvoerders}</td>
					<td>${mislukteReservatie.voorstelling.prijs}</td>
					<td>${mislukteReservatie.aantalPlaatsen}</td>
					<td>${mislukteReservatie.voorstelling.vrijePlaatsen}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>