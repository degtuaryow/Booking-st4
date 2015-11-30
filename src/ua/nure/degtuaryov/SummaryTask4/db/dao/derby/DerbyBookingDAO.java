package ua.nure.degtuaryov.SummaryTask4.db.dao.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Benefit;
import ua.nure.degtuaryov.SummaryTask4.db.Fields;
import ua.nure.degtuaryov.SummaryTask4.db.Seat;
import ua.nure.degtuaryov.SummaryTask4.db.bean.BookingBean;
import ua.nure.degtuaryov.SummaryTask4.db.dao.BookingDAO;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Booking;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Trip;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Voyage;
import ua.nure.degtuaryov.SummaryTask4.web.command.NoCommand;

public class DerbyBookingDAO implements BookingDAO {

	private static final String SQL_INSERT_BOOKING = "INSERT INTO booking VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_BOOKING_BY_ID = "SELECT * FROM booking WHERE id=?";
	private static final String SQL_SELECT_ALL_BOOKING_BY_CLIENT_ID = "SELECT * FROM booking WHERE client_id=? order by date desc";
	private static final String SQL_SELECT_MAX_SEAT_NUMBER = "SELECT max(seat_number) FROM booking";
	private static final String SQL_SELECT_BOOKING_BY_CL_BOOK_ID = "SELECT * FROM booking WHERE id=? and client_id=?";
	private static final Logger LOG = Logger.getLogger(NoCommand.class);
	private static final String SQL_UPDATE_BOOKING = "UPDATE booking SET client_id=?, voyage_id=?, price=?, benefit_id=?,"
			+ " trip_id=?, seat_id=?,seat_number=?,date=?,arrival_time=?,"
			+ " departure_time=? WHERE id=?";
	
	@Override
	public boolean addBooking(Booking booking) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_INSERT_BOOKING);
			int k = 1;
			statement.setLong(k++, booking.getClientId());
			statement.setLong(k++, booking.getVoyageId());
			statement.setInt(k++, booking.getPrice());
			statement.setLong(k++, booking.getBenefitId());
			statement.setLong(k++, booking.getTripId());
			statement.setLong(k++, booking.getSeatId());
			statement.setInt(k++, booking.getSeatNumber());
			statement.setTimestamp(k++, booking.getDate());
			statement.setTimestamp(k++, booking.getArrivalTime());
			statement.setTimestamp(k++, booking.getDepartureTime());
			statement.executeUpdate();
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
	public boolean updateBooking(Booking booking) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_UPDATE_BOOKING);
			int k = 1;
			statement.setLong(k++, booking.getClientId());
			statement.setLong(k++, booking.getVoyageId());
			statement.setInt(k++, booking.getPrice());
			statement.setLong(k++, booking.getBenefitId());
			statement.setLong(k++, booking.getTripId());
			statement.setLong(k++, booking.getSeatId());
			statement.setInt(k++, booking.getSeatNumber());
			statement.setTimestamp(k++, booking.getDate());
			statement.setTimestamp(k++, booking.getArrivalTime());
			statement.setTimestamp(k++, booking.getDepartureTime());
			statement.setLong(k++, booking.getId());
			statement.executeUpdate();
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
	public boolean removeBooking(long id) {
		return false;
	}

	@Override
	public List<Booking> fingAllBookingByClientId(long id) {
		Connection connection = null;
		PreparedStatement statement = null;
		List<Booking> result = new ArrayList<Booking>();
		ResultSet rs = null;
		try {
			connection = DerbyDAOFactory.getConnection();
			statement = connection.prepareStatement(SQL_SELECT_ALL_BOOKING_BY_CLIENT_ID);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			while (rs.next()) {
				Booking booking = obtainBooking(rs);
				result.add(booking);
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
	public Booking fingBookingById(long id) {
		Connection con = null;
		PreparedStatement statement = null;
		Booking booking = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_BOOKING_BY_ID);
			statement.setLong(1, id);
			rs = statement.executeQuery();
			if (rs.next()) {
				booking = obtainBooking(rs);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return null;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return booking;
	}

	private Booking obtainBooking(ResultSet rs) throws SQLException {
		Booking booking = new Booking();
		booking.setId(rs.getLong(Fields.ENTITY_ID));
		booking.setClientId(rs.getLong(Fields.BOOKING_CLIENT_ID));
		booking.setVoyageId(rs.getLong(Fields.BOOKING_VOYAGE_ID));
		booking.setBenefitId(rs.getLong(Fields.BOOKING_BENEFIT_ID));
		booking.setSeatId(rs.getLong(Fields.BOOKING_SEAT_ID));
		booking.setArrivalTime(rs.getTimestamp(Fields.BOOKING_ARRIVAL_TIME));
		booking.setDate(rs.getTimestamp(Fields.BOOKING_DATE));
		booking.setTripId(rs.getLong(Fields.BOOKING_TRIP_ID));
		booking.setPrice(rs.getInt(Fields.BOOKING_PRICE));
		booking.setDepartureTime(rs.getTimestamp(Fields.BOOKING_DEPARTURE_TIME));
		booking.setSeatNumber(rs.getInt(Fields.BOOKING_SEAT_NUMBER));
		return booking;
	}

	@Override
	public BookingBean fingBookingBeanByBooking(Booking booking) {
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookingBean bookingBean = new BookingBean();
		bookingBean.setBenefit(Benefit.getBenefit(booking));
		bookingBean.setBooking(booking);
		bookingBean.setClient(daoFactory.getClientDAO().findClientById(booking.getClientId()));
		bookingBean.setDate(booking.getDate());
		bookingBean.setId(booking.getId());
		bookingBean.setSeat(Seat.getSeat(booking));
		Trip trip = daoFactory.getTripDAO().findTripById(booking.getTripId());
		bookingBean.setStationBE(trip.getStationBegin(), trip.getStationEnd());
		bookingBean.setTrip(trip);
		Voyage voyage = daoFactory.getVoyageDAO().findVoyageById(booking.getVoyageId());
		bookingBean.setVoyageBean(daoFactory.getVoyageDAO().findVoyageBeanByVoyage(voyage));
		return bookingBean;
	}

	@Override
	public int getMaxseatNumber() {
		Connection con = null;
		PreparedStatement statement = null;
		int result = 0;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_MAX_SEAT_NUMBER);
			rs = statement.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return 0;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return result;
	}

	@Override
	public Booking fingBookingByClientId(long bookingId, long clientId) {
		Connection con = null;
		PreparedStatement statement = null;
		Booking booking = null;
		ResultSet rs = null;
		try {
			con = DerbyDAOFactory.getConnection();
			statement = con.prepareStatement(SQL_SELECT_BOOKING_BY_CL_BOOK_ID);
			statement.setLong(1, bookingId);
			statement.setLong(2, clientId);
			rs = statement.executeQuery();
			if (rs.next()) {
				booking = obtainBooking(rs);
			}
		} catch (Exception e) {
			DerbyDAOFactory.rollbackAndCloseConnectionStatment(con, statement);
			LOG.error(e.getMessage());
			return null;
		} finally {
			DerbyDAOFactory.commitAndCloseConnectionStatmentResSet(con, statement, rs);
		}
		return booking;
	}
}
