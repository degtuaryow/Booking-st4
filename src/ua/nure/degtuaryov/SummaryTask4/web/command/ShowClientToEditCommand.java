package ua.nure.degtuaryov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Client;
import ua.nure.degtuaryov.SummaryTask4.web.Path;

/**
 * ShowClientToEditCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class ShowClientToEditCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 45902L;
	
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("ShowClientToEditCommand starts");
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		String forward = Path.PAGE_ERROR_PAGE;
		Long id = (Long) (request.getAttribute("id"));
		Client client = daoFactory.getClientDAO().findClientById(id);
		if (client != null) {
			request.setAttribute("client", client);
			forward = Path.PAGE_EDIT_CLIENT;
		}
		LOG.debug("ShowClientToEditCommand ends");
		return forward;
	}

}
