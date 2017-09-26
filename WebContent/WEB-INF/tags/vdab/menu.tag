<%@tag description='menu' pageEncoding='UTF-8'%>
<%@attribute name='hideVoorstellingen' type='java.lang.Boolean'%>
<%@attribute name='hideReservatiemandje' type='java.lang.Boolean'%>
<%@attribute name='hideBevestigen' type='java.lang.Boolean'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<a href="<c:url value='/index.htm'/>"
	<c:if test='${hideVoorstellingen}'>hidden</c:if>>Voorstellingen</a>
<c:if test='${not empty sessionScope.mandje}'>
	<a href="<c:url value='/reservatiemandje.htm'/>"
		<c:if test='${hideReservatiemandje}'>hidden</c:if>>Reservatiemandje</a>
	<a href="<c:url value='/bevestigen.htm'/>"
		<c:if test='${hideBevestigen}'>hidden</c:if>>Bevestiging reservatie</a>
</c:if>