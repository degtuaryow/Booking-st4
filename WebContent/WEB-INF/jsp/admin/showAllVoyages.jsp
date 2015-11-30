<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<head>
<c:set var="title" value="All voyages" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>


</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<br>
	<input type="button"
		onclick="location.href='controller?command=viewAddVoyagePage';"
		value="<fmt:message key="edit_voyage_main_page.button.add_voyage"/>" />
	<hr />
	<h2>
		<fmt:message key="edit_voyage_main_page.form_name" />
	</h2>
	<br> Different sorting:
	<br>
	<select name="selectVoyageType" id="selectType">
		<option value="GET_ALL" ${type == 'GET_ALL' ? 'selected' : ''}>Get
			all</option>
		<option value="GET_BY_DATE" ${type == 'GET_BY_DATE' ? 'selected' : ''}>Get
			by date</option>
		<option value="SORT_BY_DATE"
			${type == 'SORT_BY_DATE' ? 'selected' : ''}>Sort by add date</option>
	</select>
	<input type="date" name="voyageDate"
		${date eq null ? 'hidden="true"' : ''} id="voyageDate" value="${date}">
	<script>
		$("#selectType")
				.change(
						function() {
							var type = $(this).val();
							if (type == "GET_BY_DATE") {
								$("#voyageDate").show();
							} else {
								$("#voyageDate").hide();
								location.href = 'controller?command=viewAllVoyages&type='
										+ type;
							}
						});
		$("#voyageDate")
				.change(
						function() {
							var date = $(this).val();
							location.href = 'controller?command=viewAllVoyages&type=GET_BY_DATE&date='
									+ date;
						});
	</script>
	<script type="text/javascript">
		$.get('/SummaryTask4/getVoyages');
	</script>
	<hr />
	<div id="table">
		<table id="t01"
			style="align: center; width: 1350px; text-align: center">
			<thead style="background: #227755">
				<tr>
					<th><fmt:message key="edit_voyage_main_page.train_info" /></th>
					<th><fmt:message key="edit_voyage_main_page.vayage_info" /></th>
					<th><fmt:message key="edit_voyage_main_page.from_to" /></th>
					<th><fmt:message key="edit_voyage_main_page.arrival_time" /></th>
					<th><fmt:message key="edit_voyage_main_page.departure_time" /></th>
					<th><fmt:message key="edit_voyage_main_page.coupe_seat" /></th>
					<th><fmt:message key="edit_voyage_main_page.reserved_seat" /></th>
					<th><fmt:message key="edit_voyage_main_page.general_seats" /></th>
					<th colspan="2"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.voyageBeans}" var="voyageBean">
					<c:url var="editUrl"
						value="controller?command=editVoyagePage&id=${voyageBean.id}" />
					<c:url var="removeUrl"
						value="controller?command=deleteVoyage.do&id=${voyageBean.id}" />
					<tr>
						<td><c:out
								value="K-${voyageBean.train.coupeSeat};P-${voyageBean.train.reservedSeat};
					G-${voyageBean.train.generalSeat};" /></td>
						<td>"<a
							href="controller?command=routeInfo&id=${voyageBean.routeStationsBean.route.id}">${voyageBean.routeStationsBean.route.name}</a>/${voyageBean.train.name}"
						</td>
						<td><c:out
								value="${voyageBean.routeStationsBean.stationTimesList.get(0).station.title} - 
								${voyageBean.routeStationsBean.stationTimesList.get
									(voyageBean.routeStationsBean.stationTimesList.size() - 1).station.title}" /></td>
						<td><c:out value="${voyageBean.arrivalTime}" /></td>
						<td><c:out value="${voyageBean.departureTime}" /></td>
						<td><c:out value="${voyageBean.coupeSeat}" /></td>
						<td><c:out value="${voyageBean.reservedSeat}" /></td>
						<td><c:out value="${voyageBean.generalSeat}" /></td>
						<td><a href="${editUrl}"><fmt:message
									key="edit_voyage_main_page.button.edit" /></a></td>
						<td><a href="${removeUrl}"><fmt:message
									key="edit_voyage_main_page.button.delete" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:if test="${not empty error}">
		<div class="msg">${error}</div>
	</c:if>
</body>
</html>