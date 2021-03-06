package ua.nure.degtuaryov.SummaryTask4.db;

/**
 * All fields from date base in constants
 * 
 * @author Degtuaryow
 *
 */
public class Fields {

	public static final String ENTITY_ID = "id";

	public static final String ROLE_NAME = "name";
	
	public static final String SEAT_TYPE = "type";
	
	public static final String ROUTE_NAME = "name";
	
	public static final String BENEFIT_REASON = "reason";

	public static final String CLIENT_LOGIN = "login";
	public static final String CLIENT_PASSWORD = "password";
	public static final String CLIENT_FIRSTNAME = "firstname";
	public static final String CLIENT_SECONDNAME = "secondname";
	public static final String CLIENT_SURNAME = "surname";
	public static final String CLIENT_EMAIL = "email";
	public static final String CLIENT_ROLE_ID = "role_id";

	public static final String BOOKING_CLIENT_ID = "client_id";
	public static final String BOOKING_VOYAGE_ID = "voyage_id";
	public static final String BOOKING_TRIP_ID = "trip_id";
	public static final String BOOKING_PRICE = "price";
	public static final String BOOKING_BENEFIT_ID = "benefit_id";
	public static final String BOOKING_DATE = "date";
	public static final String BOOKING_SEAT_ID = "seat_id";
	public static final String BOOKING_ARRIVAL_TIME = "arrival_time";
	public static final String BOOKING_DEPARTURE_TIME = "departure_time";
	public static final String BOOKING_SEAT_NUMBER = "seat_number";

	public static final String VOYAGE_ROUTE_ID = "route_id";
	public static final String VOYAGE_TRAIN_ID = "train_id";
	public static final String VOYAGE_COUPE_SEAT = "coupe_seat";
	public static final String VOYAGE_RESERVED_SEAT = "reserved_seat";
	public static final String VOYAGE_GENERAL_SEAT = "general_seat";
	public static final String VOYAGE_ARRIVAL_TIME = "arrival_time";
	public static final String VOYAGE_DEPARTURE_TIME = "departure_time";
	
	public static final String ST_RD_STATION_ID = "station_id";
	public static final String ST_RD_ROUTE_ID = "route_id";
	public static final String ST_RD_TRAVEL_TIME = "travel_time";
	public static final String ST_RD_STOP_TIME = "stop_time";
	public static final String ST_RD_POSITION = "position";
	
	public static final String TRAIN_NAME = "name";
	public static final String TRAIN_COUPE_SEAT = "coupe_seat";
	public static final String TRAIN_RESERVED_SEAT = "reserved_seat";
	public static final String TRAIN_GENERAL_SEAT = "general_seat";
	
	public static final String STATION_TITLE = "title";
	
	public static final String TRIP_STATION_BEGIN_ID = "station_begin_id";
	public static final String TRIP_STATION_END_ID = "station_end_id";
}
