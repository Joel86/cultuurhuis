<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
	<c:import url='/WEB-INF/JSP/head.jsp'>
		<c:param name='title' value='Het Cultuurhuis'/>
	</c:import>
</head>
<body>
	<h1>Het Cultuurhuis:voorstellingen
		<img src= <c:url value='/images/voorstellingen.png'/> alt='voorstellingen'>
	</h1>
	<h2>Genres</h2>
	<ul id='genrelijst'>
		<c:forEach var='genre' items='${genres}'>
			<c:url value='/index.htm' var='voorstellingenGenreURL'>
				<c:param name='id' value="${genre.id}"/>
			</c:url>
			<li><a href='${voorstellingenGenreURL}'>${genre.naam}</a></li>
		</c:forEach>
	</ul>
	<c:if test='${not empty genreVoorstellingen}'>
		<table>
		<caption>test</caption>
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
</body>
</html>