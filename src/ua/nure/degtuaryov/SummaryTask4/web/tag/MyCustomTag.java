package ua.nure.degtuaryov.SummaryTask4.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBean;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Route;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Train;

/**
 * Custom tag for creating 2 table's rows for voyagePage. Trains and routes
 * 
 * @author Degtuaryow
 *
 */
public class MyCustomTag extends SimpleTagSupport {

	private List<Train> trains;

	private List<RouteStationsBean> routeStationsBeans;

	public void doTag() throws JspException, IOException {

		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();

		trains = daoFactory.getTrainDAO().findAllTrains();
		List<Route> routes = daoFactory.getRouteDAO().findAllRoutes();
		routeStationsBeans = new ArrayList<>();

		for (Route route : routes) {
			RouteStationsBean bean = daoFactory.getRouteDAO().getRouteStationsBeanByRouteId(route.getId());
			routeStationsBeans.add(bean);
		}

		JspWriter out = getJspContext().getOut();
		out.append("<td>" + getAllRoutesList() + "</td>");
		out.append("<td>" + getAllTrainsList() + "</td>");
	}

	private String getAllRoutesList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<select name=\"routeId\">+" + " <option disabled>Choose route</option>");

		for (RouteStationsBean stationsBean : routeStationsBeans) {
			stringBuilder.append(
					" <option value=\"" + stationsBean.getRoute().getId() + "\">" + stationsBean.getRoute().getName()
							+ " (" + stationsBean.getStationTimesList().get(0).getStation().getTitle()
							+ " - " + stationsBean.getStationTimesList()
									.get(stationsBean.getStationTimesList().size() - 1).getStation().getTitle()
							+ ")" + "</option>");
		}
		stringBuilder.append("<//select>");
		return stringBuilder.toString();
	}

	private String getAllTrainsList() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<select name=\"trainId\">+" + " <option disabled>Choose Train</option>");

		for (Train train : trains) {
			stringBuilder.append(
					" <option value=\"" + train.getId() + "\">" + train.getName() + " (K -" + train.getCoupeSeat()
							+ ");(P - " + train.getReservedSeat() + ");(G - " + train.getGeneralSeat() + ")</option>");
		}
		stringBuilder.append("<//select>");
		return stringBuilder.toString();
	}

}
