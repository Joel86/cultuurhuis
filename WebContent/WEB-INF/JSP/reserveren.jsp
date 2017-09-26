<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<vdab:head title='Reserveren'/>
</head>
<body>
	<h1>Het Cultuurhuis:reserveren
		<img src= <c:url value='/images/reserveren.png'/> alt='reserveren'>
	</h1>
	<vdab:menu/>
	<p>Voorstelling:<br>
	${voorstelling.titel}</p>
	<p>Uitvoerders:<br>
	${voorstelling.uitvoerders}</p>
	<p>Datum:<br>
	${voorstelling.datumTijd}</p>
	<p>Prijs:<br>
	&euro;${voorstelling.prijs}</p>
	<p>Vrije plaatsen:<br>
	${voorstelling.vrijePlaatsen}</p>
	Plaatsen:
	<form method='post'>
		<input name='aantal' type='number' 
			value='${aantalPlaatsenGereserveerd}' required/>
		<c:if test='${not empty fouten}'>
			${fouten.aantal}${voorstelling.vrijePlaatsen}
		</c:if>
		<input type='submit' value='Reserveren'/>
	</form>
	
</body>
</html>