package ua.nure.degtuaryov.SummaryTask4.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ua.nure.degtuaryov.SummaryTask4.db.Constants;
import ua.nure.degtuaryov.SummaryTask4.db.dao.DAOFactory;
import ua.nure.degtuaryov.SummaryTask4.db.entity.Station;
import ua.nure.degtuaryov.SummaryTask4.web.command.LoginCommand;

/**
 * API for getting all the stations from my data base
 * 
 * @author Degtuaryow
 *
 */
@WebServlet("/getStations")
public class GetStationsAPI extends HttpServlet {

	private static final long serialVersionUID = 3903617600039343228L;

	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LOGGER.debug("GetStationsAPI starts");
		DAOFactory.setDaoFactoryFCN(Constants.DAO_FACTORY);
		DAOFactory daoFactory = DAOFactory.getInstance();
		List<String> names = new ArrayList<>();
		List<Station> stations = daoFactory.getStationDAO().findAllStations();
		for (Station station : stations) {
			names.add(station.getTitle());
		}
		String json = new Gson().toJson(names);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		LOGGER.debug("GetStationsAPI ends");
	}

}
