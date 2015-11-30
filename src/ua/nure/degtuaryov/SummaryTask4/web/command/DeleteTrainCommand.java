package ua.nure.degtuaryov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.web.Path;

/**
 * DeleteTrainCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class DeleteTrainCommand extends Command {

	private static final long serialVersionUID = 20897214978036708L;

	private static final Logger LOGER = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOGER.debug("DeleteTrainCommand starts");
		String forward = Path.PAGE_ERROR_PAGE;
		if (request.getAttribute("id") == null || request.getAttribute("id").toString().isEmpty()) {
			return forward;
		} else {
			Long id = (Long) request.getAttribute("id");
			DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
			DAOFactory daoFactory = DAOFactory.getInstance();
			if (daoFactory.getTrainDAO().removeTrain(id)) {
				forward = Path.COMMAND_VIEW_ALL_TRAINS;
				request.setAttribute("success", "You delete train!");
			}
		}
		LOGER.debug("DeleteTrainCommand ends");
		return forward;
	}

}
