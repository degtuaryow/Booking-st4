package ua.nure.degtuaryov.SummaryTask4.web.command;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBean;
import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBean.StationTime;
import ua.nure.degtuaryov.SummaryTask4.db.bean.VoyageBean;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Train;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Voyage;
import ua.nure.degtuaryov.SummaryTask4.web.Path;

/**
 * AddVoyageCommand command.
 * 
 * @author Dedtuaryow
 * 
 */
public class AddVoyageCommand extends Command {

	private static final long serialVersionUID = 390361760003934986L;

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
	
	private static final String WRONG = "wrong";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOGGER.debug("AddVoyageCommand starts");
		String routeIdLiteral = "routeId";
		String trainIdLiteral = "trainId";
		String forward = Path.PAGE_ERROR_PAGE;
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		if (request.getParameter(routeIdLiteral) == null || request.getParameter(trainIdLiteral) == null
				|| request.getParameter(trainIdLiteral).isEmpty() || request.getParameter(routeIdLiteral).isEmpty()) {
			request.setAttribute(WRONG, "Complete the fields correctly");
			forward = Path.PAGE_ADD_VOYAGE;
		} else {
			long routeId = Long.valueOf(request.getParameter(routeIdLiteral));
			long trainId = Long.valueOf(request.getParameter(trainIdLiteral));
			StringBuilder stringBuilder = new StringBuilder(request.getParameter("date"));
			int index = stringBuilder.indexOf("T");
			stringBuilder.deleteCharAt(index);
			stringBuilder.insert(index, " ");
			stringBuilder.append(":00");
			Timestamp date = Timestamp.valueOf(stringBuilder.toString());
			Voyage voyage = new Voyage();
			voyage.setArrivalTime(date);
			voyage.setTrainId(trainId);
			voyage.setRouteId(routeId);
			java.util.Date currectDate = new java.util.Date();
			if (date.before(new Timestamp(currectDate.getTime()))) {
				request.setAttribute(WRONG, "Change date!");
				forward = Path.PAGE_ADD_VOYAGE;
			} else {
				if (routeId > 0 && trainId > 0 && date != null) {
					if (!daoFactory.getVoyageDAO().existVoyageIdByName(voyage)) {
						VoyageBean voyageBean = new VoyageBean();
						voyageBean.setArrivalTime(date);
						Train train = daoFactory.getTrainDAO().findTrainById(trainId);
						RouteStationsBean routeStationsBean = daoFactory.getRouteDAO()
								.getRouteStationsBeanByRouteId(routeId);
						int time = 0;
						for (StationTime stationTime : routeStationsBean.getStationTimesList()) {
							time += stationTime.getStopTime() + stationTime.getTravelTime();
						}
						Long timeLong = (long)time * 60000L;
						Timestamp timestampDepartureTime = new Timestamp(date.getTime() + timeLong);
						voyageBean.setDepartureTime(timestampDepartureTime);
						voyageBean.setCoupeSeat(train.getCoupeSeat());
						voyageBean.setGeneralSeat(train.getGeneralSeat());
						voyageBean.setReservedSeat(train.getReservedSeat());
						voyageBean.setRouteStationsBean(routeStationsBean);
						voyageBean.setTrain(train);
						daoFactory.getVoyageDAO().addVoyage(voyageBean);
						request.setAttribute("sucess", "Add!");
						forward = Path.PAGE_ADD_VOYAGE;
					} else {
						request.setAttribute(WRONG, "This vogege is already exist!");
						forward = Path.PAGE_ADD_VOYAGE;
					}
				}
			}
		}
		LOGGER.debug("AddVoyageCommand ends");

		return forward;
	}

}
