<%@page contentType='text/html' pageEncoding='UTF-8'%>
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
		<input name='gebruikersnaam' autofocus required
			<c:if test='${not empty gebruikersnaam}'>disabled</c:if>></label>
		<label>Paswoord:
		<input name='paswoord' type='password' required
			<c:if test='${not empty gebruikersnaam}'>disabled</c:if>></label>
		${fout}
		<input name='opzoeken' type='submit' 
			<c:if test='${not empty gebruikersnaam}'>disabled</c:if> value='Zoek me op'>
	</form>
	<form action="<c:url value='/nieuweklant.htm'/>">
		<input name='nieuweklant' type='submit' 
			<c:if test='${not empty gebruikersnaam}'>disabled</c:if> value='Ik ben nieuw'>
	</form>
	<c:if test='${not empty gebruikersnaam}'>
		${klant.voornaam} ${klant.familienaam} ${klant.adres.straat}
		${klant.adres.huisNr} ${klant.adres.postcode} ${klant.adres.gemeente}
	</c:if>
	<form method='post'>
		<h2>Stap 2:Bevestigen</h2>
		<input name='bevestigen' type='submit' value='Bevestigen'
			<c:if test='${empty gebruikersnaam}'>disabled</c:if>>
	</form>
</body>
</html>