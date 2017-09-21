<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
	<c:import url='/WEB-INF/JSP/head.jsp'>
		<c:param name='title' value='Reserveren'/>
	</c:import>
</head>
<body>
	<h1>Het Cultuurhuis:reserveren
		<img src= <c:url value='/images/reserveren.png'/> alt='reserveren'>
	</h1>
	<a href='<c:url value="/index.htm"/>'>Voorstellingen</a><br>
	<p>Voorstelling:<br>
	${voorstelling.titel}</p>
	<p>Uitvoerders:<br>
	${voorstelling.uitvoerders}</p>
	<p>Datum:<br>
	${voorstelling.datumTijd}</p>
	<p>Prijs:<br>
	${voorstelling.prijs}&euro;</p>
	<p>Vrije plaatsen:<br>
	${voorstelling.vrijePlaatsen}</p>
	Plaatsen:
	<form method='post'>
		<input name='aantal' type='number' min='1' max='${voorstelling.vrijePlaatsen}' required/>
		<input type='submit' value='Reserveren'/>
	</form>
	
</body>
</html>