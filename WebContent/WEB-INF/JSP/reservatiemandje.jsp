<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@taglib prefix='vdab' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<vdab:head title='Reservatiemandje'/>
</head>
<body>
	<h1>Het Cultuurhuis:reservatiemandje
		<img src="<c:url value='/images/mandje.png'/>" alt='mandje'>
	</h1>
	<vdab:menu reservatiemandje='hidden'/>
	<c:set var='totaal' value='0'/>
	<form method='post'>
		<table>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Plaatsen</th>
				<th><input type='submit' value='Verwijderen'/></th>
			</tr>
			<c:forEach var='voorstellingInMandje' items='${voorstellingenInMandje}'>
				<c:set var='subtotaal' value='${voorstellingInMandje.prijs * mandje[voorstellingInMandje.id]}'/>
				<tr>
					<td><fmt:formatDate value='${voorstellingInMandje.datumTijd}' 
						type='both' dateStyle='short' timeStyle='short'/></td>
					<td>${voorstellingInMandje.titel}</td>
					<td>${voorstellingInMandje.uitvoerders}</td>
					<td>&euro;${voorstellingInMandje.prijs}</td>
					<td>${mandje[voorstellingInMandje.id]}</td>
					<td>
						<input type='checkbox' name='id' value='${voorstellingInMandje.id}'>	
					</td>
				</tr>
				<c:set var='totaal' value='${totaal + subtotaal}'/>
			</c:forEach>
		</table>
	</form>
	Te betalen: &euro;${totaal}
</body>
</html>