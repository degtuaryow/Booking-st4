package ua.nure.degtuaryov.SummaryTask4.db.dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.dao.BookingDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.ClientDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.dao.RouteDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.StationDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.TrainDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.TripDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.VoyageDAO;
import ua.nure.degtuaryov.SummaryTask4.web.command.NoCommand;

public class DerbyDAOFactory extends DAOFactory {

	private static final Logger LOG = Logger.getLogger(NoCommand.class);

	@Override
	public BookingDAO getBookingDAO() {
		return new DerbyBookingDAO();
	}

	@Override
	public ClientDAO getClientDAO() {
		return new DerbyClientDAO();
	}

	@Override
	public RouteDAO getRouteDAO() {
		return new DerbyRouteDAO();
	}

	@Override
	public StationDAO getStationDAO() {
		return new DerbyStationDAO();
	}

	@Override
	public TrainDAO getTrainDAO() {
		return new DerbyTrainDAO();
	}

	@Override
	public VoyageDAO getVoyageDAO() {
		return new DerbyVoyageDAO();
	}

	@Override
	public TripDAO getTripDAO() {
		return new DerbyTripDAO();
	}

	public static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			LOG.debug("Connection~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			DataSource ds = (DataSource) envContext.lookup("jdbc/db/bookingDB");
			con = ds.getConnection();
		} catch (NamingException ex) {

			System.out.println("Connection error");
			LOG.error(ex.getMessage());
		}
		return con;
	}

	/**
	 * Commits and close the given connection.
	 * 
	 * @param con
	 *            Connection to be committed and closed.
	 */
	public static void commitAndCloseConnectionStatment(Connection con, PreparedStatement preparedStatemen) {
		close(preparedStatemen);
		closeCommit(con);
	}

	public static void commitAndCloseConnectionStatmentResSet(Connection con, PreparedStatement preparedStatemen,
			ResultSet resultSet) {
		close(resultSet);
		close(preparedStatemen);
		closeCommit(con);
	}

	private static void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	private static void closeCommit(Connection con) {
		try {
			if (con != null) {
				con.commit();
				con.close();
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	/**
	 * Rollbacks and close the given connection.
	 * 
	 * @param con
	 *            Connection to be rollbacked and closed.
	 */
	public static void rollbackAndCloseConnectionStatment(Connection con, PreparedStatement preparedStatement) {
		close(preparedStatement);
		closeRollback(con);
	}

	private static void close(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}

	private static void closeRollback(Connection con) {
		try {
			if (con != null) {
				con.rollback();
				con.close();
			}
		} catch (SQLException ex) {
			LOG.error(ex.getMessage());
		}
	}
}
