package ua.nure.degtuaryov.SummaryTask4.db.dao;

import ua.nure.degtuaryov.SummaryTask4.db.entity.Trip;

/**
 * Interface dao producing implementations TripDAO
 * 
 * @author Degtuaryow
 *
 */
public interface TripDAO {
	
	boolean addTrip(Trip trip);
	
	Trip findTripById(long id);
	
	long findTripByStations(long st1Id, long st2Id);

}
