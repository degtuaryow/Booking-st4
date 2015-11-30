package ua.nure.degtuaryov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Route;
import ua.nure.degtuaryov.SummaryTask4.web.Path;

/**
 * ViewDeleteRoutePageCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class ViewDeleteRoutePageCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3433246711L;

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		LOG.debug("ViewDeleteRoutePageCommand starts");
		DAOFactory daoFactory = DAOFactory.getInstance();
		String forward = Path.PAGE_ERROR_PAGE;
		if (request.getAttribute("id") == null || request.getAttribute("id").toString().isEmpty()) {
			return forward;
		} else {
			Long id = (Long) (request.getAttribute("id"));
			Route route = daoFactory.getRouteDAO().findRouteById(id);
			if (route != null) {
				request.setAttribute("route", route);
				forward = Path.PAGE_DELETE_ROUTE;
			}
		}
		LOG.debug("ViewDeleteRoutePageCommand ends");
		return forward;
	}

}
