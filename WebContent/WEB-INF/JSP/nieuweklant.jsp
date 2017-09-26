<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<vdab:head title='Nieuwe klant'/>
</head>
<body>
	<h1>Het Cultuurhuis:nieuwe klant
		<img src="<c:url value='/images/nieuweklant.png'/>" alt='Nieuwe klant'>
	</h1>
	<vdab:menu/>
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
			<input name='paswoord' type='password' required>
		</label>
		<label>Herhaal paswoord:
			<input name='herhaalpaswoord' type='password' required>
		</label>
		<input type='submit' value='OK'>
	</form>
	<c:forEach var='fout' items='${fouten}'>
			${fout.value}
	</c:forEach>
</body>
</html>