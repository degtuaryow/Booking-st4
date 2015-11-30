<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<head>
<c:set var="title" value="My tickets" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<c:if test="${empty wrong}">
		<h2><fmt:message key="my_tickets_main_page.form_name"/></h2>
		<hr />
		<br>
		<table id="t01"
			style="align: center; width: 1350px; text-align: center"
			cellpadding="10" cellspacing="0">
			<thead style="background: #aaaaff">
				<tr>
					<th><fmt:message key="my_tickets_main_page.info"/></th>
					<th><fmt:message key="my_tickets_main_page.from_to"/></th>
					<th><fmt:message key="my_tickets_main_page.arrival_time"/></th>
					<th><fmt:message key="my_tickets_main_page.departure_time"/></th>
					<th><fmt:message key="my_tickets_main_page.seat_number"/></th>
					<th><fmt:message key="my_tickets_main_page.date"/></th>
					<th><fmt:message key="my_tickets_main_page.benefit"/></th>
					<th><fmt:message key="my_tickets_main_page.price"/></th>
					<th colspan="1"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.bookingBeans}" var="bookingBean">
					<c:url var="saveUrl"
						value="controller?command=editVoyagePage&id=${bookingBean.id}" />
					<c:url var="infoUrl"
						value="controller?command=ticketInfo&id=${bookingBean.id}" />
					<tr>
						<td><a
							href="controller?command=routeInfo&id=${bookingBean.voyageBean.routeStationsBean.route.id}">${bookingBean.voyageBean.routeStationsBean.route.name}</a>/${bookingBean.voyageBean.train.name}
						</td>
						<td><c:out
								value="${bookingBean.stationBE.station1.title} - 
								${bookingBean.stationBE.station2.title}" /></td>
						<td><c:out value="${bookingBean.booking.arrivalTime}" /></td>
						<td><c:out value="${bookingBean.booking.departureTime}" /></td>
						<td><c:out
								value="${bookingBean.booking.seatNumber}-${bookingBean.seat.getType()}" /></td>
						<td><c:out value="${bookingBean.booking.date}" /></td>
						<td><c:out value="${bookingBean.benefit.getReason()}" /></td>
						<td><c:out value="${bookingBean.booking.price/100}" /></td>						
						<td><a href="${infoUrl}"><fmt:message key="my_tickets_main_page.button.show_ticket"/></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${not empty wrong}">
		<div class="msg">${wrong}</div>
	</c:if>
</body>
</html>