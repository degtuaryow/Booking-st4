package ua.nure.degtuaryov.SummaryTask4.db;

/**
 * Constants for the database connection.
 * 
 * @author Degtuaryow Dima
 *
 */
public class Constants {

	public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
	public static final String DB_URL_C = "jdbc:derby://localhost:1527/db/bookingDB";
	public static final String DAO_FACTORY = "ua.nure.degtuaryov.SummaryTask4.db.dao.derby.DerbyDAOFactory";
	public static final String DB_DATA_SOURSE = "java:/comp/env/jdbc/db/bookingDB";
	public static final String DB_LOGIN = "root";
	public static final String DB_PASSWORD = "root";

}
