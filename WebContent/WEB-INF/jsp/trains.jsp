<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<c:set var="title" value="Find Train" />

<%@ include file="/WEB-INF/jspf/headContent.jspf"%>

<link rel="stylesheet" type="text/css" media="screen"
	href="css/autoComplete.css">

<script type="text/javascript">
	
<%@ include file="script/changeStations.js" %>
	
<%@ include file="script/auto_complite.js" %>
	
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="viewFindTrains" />
		<c:set var="voyageBeans" value="${requestScope.voyageBeans}"></c:set>
		<c:set var="st1" value="${requestScope.st1}"></c:set>
		<c:set var="st2" value="${requestScope.st2}"></c:set>
		<h2>
			<fmt:message key="find_train_main_page.form_name" />
		</h2>
		<br>

		<hr />
		<table>
			<tr>
				<td><fmt:message key="find_train_main_page.wherefrom" />:</td>
				<td></td>
				<td><fmt:message key="find_train_main_page.whereto" />:</td>
			</tr>
			<tr>
				<td><input type="text" id="station1" name="station1"
					class="viewstations" value="${requestScope.station1}" required
					maxlength="15" /></td>
				<td><input type="button" onclick="return changeStations()"
					value="<=>" /></td>
				<td><input type="text" id="station2" name="station2"
					class="viewstations" value="${requestScope.station2}" required
					maxlength="15" /></td>
			</tr>
			<tr>
				<td colspan="2"><fmt:message
						key="find_train_main_page.date_format" />(YYYY-MM-DD)</td>

				<td><input type="date" name="date" value="${requestScope.date}"
					required maxlength="15" pattern="(\d{4})-(\d{2})-(\d{2})" /></td>
			</tr>
		</table>
		<br> <input type="submit"
			value="<fmt:message key="find_train_main_page.button.find_train"/>" />
		<hr />
	</form>
	<c:if test="${not empty voyageBeans}">
		<table id="t01"
			style="align: center; width: 1200px; text-align: center" border="1"
			cellpadding="20" cellspacing="0">
			<thead style="background: #aaaaff">
				<tr>
					<th><fmt:message key="chose_train_page_success.train_info" /></th>
					<th><fmt:message key="chose_train_page_success.vayage_info" /></th>
					<th><fmt:message key="chose_train_page_success.from_to" /></th>
					<th><fmt:message key="chose_train_page_success.arrival_time" /></th>
					<th><fmt:message key="chose_train_page_success.departure_time" /></th>
					<th><fmt:message key="chose_train_page_success.seats" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.voyageBeans}" var="voyageBean">
					<c:url var="editUrl"
						value="controller?command=editVoyagePage&id=${voyageBean.id}" />
					<c:url var="removeUrl"
						value="controller?command=deleteVoyage.do&id=${voyageBean.id}" />
					<tr bgcolor="#dddddd">
					<tr>
						<td rowspan="3"><c:out value="${voyageBean.train.name}" /></td>
						<td rowspan="3"><a
							href="controller?command=routeInfo&id=${voyageBean.routeStationsBean.route.id}">${voyageBean.routeStationsBean.route.name}</a>
						</td>
						<td rowspan="3"><c:out
								value="${voyageBean.routeStationsBean.stationTimesList.get(0).station.title} - 
								${voyageBean.routeStationsBean.stationTimesList.get
									(voyageBean.routeStationsBean.stationTimesList.size() - 1).station.title}" /></td>
						<td rowspan="3"><c:out value="${voyageBean.arrivalTime}" /></td>
						<td rowspan="3"><c:out value="${voyageBean.departureTime}" /></td>
						<td><input type="button" style="height: 25px; width: 130px"
							onclick="location.href='controller?command=viewBuyTicket&id=${voyageBean.id}&type=coupe&st1=${st1}&st2=${st2}&date=${date}';"
							value="<fmt:message key="chose_train_page_success.button.buy_coupe"/>" />
							<c:out value=": ${voyageBean.coupeSeat}" /></td>
					</tr>
					<tr>
						<td><input type="button" style="height: 25px; width: 130px"
							onclick="location.href='controller?command=viewBuyTicket&id=${voyageBean.id}&type=reserved&st1=${st1}&st2=${st2}&date=${date}';"
							value="<fmt:message key="chose_train_page_success.button.buy_reserved"/>" />
							<c:out value=": ${voyageBean.reservedSeat}" /></td>
					</tr>
					<tr>
						<td><input type="button" style="height: 25px; width: 130px"
							onclick="location.href='controller?command=viewBuyTicket&id=${voyageBean.id}&type=general&st1=${st1}&st2=${st2}&date=${date}';"
							value="<fmt:message key="chose_train_page_success.button.buy_general"/>" />
							<c:out value=":  ${voyageBean.generalSeat}" /></td>
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