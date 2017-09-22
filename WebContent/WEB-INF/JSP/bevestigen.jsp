<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
	<c:import url='/WEB-INF/JSP/head.jsp'>
		<c:param name='title' value='Reservatie bevestigen'/>
	</c:import>
</head>
<body>
	<h1>Het Cultuurhuis:bevestiging reservaties
		<img src="<c:url value='/images/bevestig.png'/>" alt='Bevestig'>
	</h1>
	<form method='post'>
		<h2>Stap 1:Wie ben je?</h2>
		<label>Gebruikersnaam:
		<input name='gebruikersnaam' autofocus required></label>
		<label>Paswoord:
		<input name='paswoord' type='password' required></label>
		<input name='opzoeken' type='submit' value='Zoek me op'>
		<input name='nieuweklant' type='submit' value='Ik ben nieuw'>
		<h2>Stap 2:Bevestigen</h2>
		<input name='bevestigen' type='submit' value='Bevestigen'>
	</form>
</body>
</html>