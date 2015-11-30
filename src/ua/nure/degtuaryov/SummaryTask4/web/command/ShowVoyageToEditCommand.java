package ua.nure.degtuaryov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.bean.VoyageBean;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Voyage;
import ua.nure.degtuaryov.SummaryTask4.web.Path;


/**
 * ShowVoyageToEditCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class ShowVoyageToEditCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1786346739212645912L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("ShowVoyageToEditCommand starts");
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		String forward = Path.PAGE_ERROR_PAGE;
		Long id = (Long) (request.getAttribute("id"));
		Voyage voyage = daoFactory.getVoyageDAO().findVoyageById(id);
		if (voyage != null) {
			VoyageBean voyageBean = daoFactory.getVoyageDAO().findVoyageBeanByVoyage(voyage);
			request.setAttribute("trains", daoFactory.getTrainDAO().findAllTrains());
			request.setAttribute("voyageBean", voyageBean);
			forward = Path.PAGE_EDIT_VOYAGE;
		}
		LOG.debug("ShowVoyageToEditCommand ends");
		return forward;
	}

}
