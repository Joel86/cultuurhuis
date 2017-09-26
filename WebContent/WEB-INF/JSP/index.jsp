<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<vdab:head title='Het Cultuurhuis'/>
</head>
<body>
	<h1>Het Cultuurhuis:voorstellingen
		<img src= <c:url value='/images/voorstellingen.png'/> alt='voorstellingen'>
	</h1>
	<vdab:menu hideVoorstellingen='true'/>
	<vdab:menuGenres/>
	<c:if test='${not empty genreVoorstellingen}'>
		<table>
		<caption>${genreVoorstellingen[1].genre.naam} voorstellingen</caption>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Vrije plaatsen</th>
				<th>Reserveren</th>
			</tr>
			<c:forEach var='genreVoorstelling' items='${genreVoorstellingen}'>
				<tr>
					<td>${genreVoorstelling.datumTijd}</td>
					<td>${genreVoorstelling.titel}</td> 
					<td>${genreVoorstelling.uitvoerders}</td>
					<td>&euro;${genreVoorstelling.prijs}</td> 
					<td>${genreVoorstelling.vrijePlaatsen}</td>
					<td>
						<c:if test='${genreVoorstelling.vrijePlaatsen > 0}'>
							<c:url value='/reserveren.htm' var='reserveringsURL'>
								<c:param name='id' value='${genreVoorstelling.id}'/>
							</c:url>
							<a href='${reserveringsURL}'>Reserveren</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test='${empty genreVoorstellingen and not empty param.id}'>
		Geen voorstellingen gevonden.
	</c:if>
</body>
</html>