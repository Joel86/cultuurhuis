<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang='nl'>
<head>
	<c:import url='/WEB-INF/JSP/head.jsp'>
		<c:param name='title' value='Nieuwe klant'/>
	</c:import>
</head>
<body>
	<h1>Het Cultuurhuis:nieuwe klant
		<img src="<c:url value='/images/nieuweklant.png'/>" alt='Nieuwe klant'>
	</h1>
	<form method='post'>
		<label>Voornaam:
			<input name='voornaam' autofocus required>
		</label>
		<label>Familienaam:
			<input name='familienaam' required>
		</label>
		<label>Straat:
			<input name='straat' required>
		</label>
		<label>Huisnr.:
			<input name='huisnr' required>
		</label>
		<label>Postcode:
			<input name='postcode' required>
		</label>
		<label>Gemeente:
			<input name='gemeente' required>
		</label>
		<label>Gebruikersnaam:
			<input name='gebruikersnaam' required>
		</label>
		<label>Paswoord:
			<input name='paswoord' required>
		</label>
		<label>Herhaal paswoord:
			<input name='herhaalpaswoord' required>
		</label>
		<input type='submit' value='OK'>
	</form>
</body>
</html>