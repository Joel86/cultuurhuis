<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
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
	<dl>
		<dd>Voorstelling:</dd><dt>${voorstelling.titel}</dt>
		<dd>Uitvoerders:</dd><dt>${voorstelling.uitvoerders}</dt>
		<dd>Datum:</dd>
		<dt><fmt:formatDate value='${voorstelling.datumTijd}' 
		type='both' dateStyle='short' timeStyle='short'/></dt>
		<dd>Prijs:</dd><dt>&euro;${voorstelling.prijs}</dt>
		<dd>Vrije plaatsen:</dd><dt>${voorstelling.vrijePlaatsen}</dt>
		<dd>Plaatsen:</dd>
	</dl>
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