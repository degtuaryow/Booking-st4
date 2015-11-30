package ua.nure.degtuaryov.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBean;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Route;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Station;

/**
 * Interface dao producing implementations RouteDAO
 * 
 * @author Degtuaryow
 *
 */
public interface RouteDAO {

	Route findRouteById(long id);

	List<Route> findAllRoutes();

	boolean addRoute(RouteStationsBean routeStationsBean);

	boolean removeRoute(long id);

	List<Station> getStationsByRouteId(long id);

	RouteStationsBean getRouteStationsBeanByRouteId(long id);

	long findRouteIdByName(String name);
}
