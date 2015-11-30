package ua.nure.degtuaryov.SummaryTask4.db.dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.Fields;
import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBean;
import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBean.StationTime;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.dao.RouteDAO;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Route;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Station;
import ua.nure.degtuaryov.SummaryTask4.web.command.NoCommand;

public class DerbyRouteDAO implements RouteDAO {

	private static final String SQL_SELECT_ALL_ROUTS = "SELECT * from ROUTE";
	private static final String SQL_SELECT_ALL_STATIONS_BY_ROUTE_ID_SR = "SELECT * from station_route where route_id=? order by position";
	private static final String SQL_SELECT_ROUTE_BY_ID = "SELECT * from ROUTE where id=?";
	private static final String SQL_DELETE_ROUTE_BY_ID = "DELETE FROM ROUTE WHERE id=?";
	private static final String SQL_SELECT_ROUTE_BY_NAME = "SELECT * from ROUTE where name = ?";
	private static final String SQL_INSERT_ROUTE = "INSERT INTO route VALUES (default, ?)";
	private static final String SQL_INSERT_ROUTE_STATION = "INSERT INTO Station_route VALUES (?, ?, ?, ?, ?)";
	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public Route findRouteById(long id) {
		Connection con = null;
		PreparedStatement statement = null;
		Route route = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_ROUTE_BY_ID);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				route = obtainRoute(rs);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return null;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return route;
	}

	@Override
	public List<Route> findAllRoutes() {
		Connection connection = null;
		PreparedStatement statement = null;
		List<Route> result = new ArrayList<Route>();
		ResultSet rs = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_SELECT_ALL_ROUTS);
			rs = statement.executeQuery();
			while (rs.next()) {
				Route route = obtainRoute(rs);
				result.add(route);
			}
		} catch (SQLException e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(connection, statement);
			LOG.error(e.getMessage());
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(connection, statement, rs);
		}
		return result;
	}

	private Route obtainRoute(ResultSet rs) throws SQLException {
		Route route = new Route();
		route.setId(rs.getLong(Fields.ENTITY_ID));
		route.setName(rs.getString(Fields.ROUTE_NAME));
		return route;
	}

	@Override
	public boolean addRoute(RouteStationsBean routeStationsBean) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
			DAOFactory daoFactory = DAOFactory.getInstance();
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_INSERT_ROUTE);
			int k = 1;
			statement.setString(k++, routeStationsBean.getRoute().getName());
			statement.executeUpdate();
			statement.close();
			connection.commit();
			int ind = 0;
			long id = findRouteIdByName(routeStationsBean.getRoute().getName());
			for (StationTime st : routeStationsBean.getStationTimesList()) {
				statement = connection.prepareStatement(SQL_INSERT_ROUTE_STATION);
				k = 1;
				long stId = daoFactory.getStationDAO().findStationByTitle(st.getStation().getTitle());
				statement.setLong(k++, stId);
				statement.setLong(k++, id);
				statement.setInt(k++, st.getTravelTime());
				statement.setInt(k++, st.getStopTime());
				statement.setInt(k, ind++);
				statement.executeUpdate();
				statement.close();
			}
			statement.close();
		} catch (Exception ex) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(connection, statement);
			LOG.error(ex.getMessage());
			return false;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatment(connection, statement);
		}
		return true;
	}

	@Override
	public long findRouteIdByName(String name) {
		long id = 0l;
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_ROUTE_BY_NAME);
			statement.setString(1, name);
			rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getLong(Fields.ENTITY_ID);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return id;
	}

	@Override
	public boolean removeRoute(long id) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_DELETE_ROUTE_BY_ID);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			return false;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatment(con, statement);
		}
		return true;
	}

	@Override
	public List<Station> getStationsByRouteId(long id) {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connection = null;
		PreparedStatement statement = null;
		List<Station> result = new ArrayList<Station>();
		ResultSet rs = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_SELECT_ALL_STATIONS_BY_ROUTE_ID_SR);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long stId = rs.getLong(1);
				Station station = daoFactory.getStationDAO().findStationById(stId);
				result.add(station);
			}
		} catch (SQLException e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(connection, statement);
			LOG.error(e.getMessage());
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(connection, statement, rs);
		}
		return result;
	}

	@Override
	public RouteStationsBean getRouteStationsBeanByRouteId(long id) {
		DAOFactory daoFactory = DAOFactory.getInstance();
		Connection connection = null;
		PreparedStatement statement = null;
		RouteStationsBean bean = new RouteStationsBean();
		ResultSet rs = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_SELECT_ALL_STATIONS_BY_ROUTE_ID_SR);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				long stId = rs.getLong(1);
				Station station = daoFactory.getStationDAO().findStationById(stId);
				int travTime = rs.getInt(3);
				int stopTime = rs.getInt(4);
				bean.setStationTimesList(station, travTime, stopTime);
			}
			bean.setRoute(findRouteById(id));
		} catch (SQLException e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(connection, statement);
			LOG.error(e.getMessage());
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(connection, statement, rs);
		}
		return bean;
	}

}
