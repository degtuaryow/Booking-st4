package ua.nure.degtuaryov.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.degtuaryov.SummaryTask4.db.bean.VoyageBean;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Voyage;

/**
 * Interface dao producing implementations VoyageDAO
 * 
 * @author Degtuaryow
 *
 */
public interface VoyageDAO {
	
	Voyage findVoyageById(long id);

	List<VoyageBean> findAllVoyageBeans();
	
	List<Voyage> findAllVoyages();
	
	VoyageBean findVoyageBeanByVoyage(Voyage voyage);
	
	List<VoyageBean> findVoyageBeanByRouteId(long id, String date);

	boolean addVoyage(VoyageBean voyageBean);

	boolean removeVoyage(long id);
	
	boolean existVoyageIdByName(Voyage voyage);
	
	boolean updateVoyage(VoyageBean voyageBean);
	
	boolean buyTicket(Voyage voyage, String type);

}
