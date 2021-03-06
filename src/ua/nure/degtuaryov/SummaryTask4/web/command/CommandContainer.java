package ua.nure.degtuaryov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

/**
 * Holder for all commands.<br/>
 * 
 * @author Degtuaryow
 * 
 */
public class CommandContainer {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login.do", new LoginCommand());
		commands.put("logout.do", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("viewLoginPage", new ViewLoginPageCommand());
		commands.put("viewRegisterPage", new ViewRegisterPageCommand());
		commands.put("register", new RegisterCommand());
		commands.put("viewAllClients", new ViewClientsListCommand());
		commands.put("changeClient", new EditClientCommand());
		commands.put("editClientPage", new ShowClientToEditCommand());
		commands.put("deleteClient.do", new DeleteClientCommand());
		commands.put("deleteClientPage", new ViewDeleteClientPageCommand());
		commands.put("viewAllTrains", new ViewTrainsListCommand());
		commands.put("editTrainPage", new ShowTrainToEditCommand());
		commands.put("editTrain", new EditTrainCommand());
		commands.put("addTrain.do", new AddTrainCommand());
		commands.put("viewAddTrainPage", new ViewAddTrainPageCommand());
		commands.put("deleteTrainPage", new ViewDeleteTrainPageCommand());
		commands.put("deleteTrain.do", new DeleteTrainCommand());
		commands.put("viewAllStations", new ViewStationListCommand());
		commands.put("viewAllRoutes", new ViewRoutesListCommand());
		commands.put("routeInfo", new ViewAllStattionsFromSTRDCommand());
		commands.put("addStation.do", new AddStationCommand());
		commands.put("addStationPage", new ViewAddStationPageCommand());
		commands.put("deleteRoutePage", new ViewDeleteRoutePageCommand());
		commands.put("deleteRoute.do", new DeleteRouteCommand());
		commands.put("viewAddRoutesPage", new ViewAddRoutePageCommand());
		commands.put("deleteStation.do", new DeleteStationCommand());
		commands.put("addRoute.do", new AddRouteCommand());
		commands.put("viewAllVoyages", new ViewVoyagesListCommand());
		commands.put("viewAddVoyagePage", new ViewAddVoyagePageCommand());
		commands.put("addVoyage.do", new AddVoyageCommand());
		commands.put("deleteVoyage.do", new DeleteVoyageCommand());
		commands.put("editVoyagePage", new ShowVoyageToEditCommand());
		commands.put("editVoyage.do", new EditVoyageCommand());
		commands.put("viewFindTrains", new ViewTrainsCommand());
		commands.put("viewBuyTicket", new ViewBuyTicketPageCommand());
		commands.put("buyTicket.do", new BuyTicketCommand());
		commands.put("viewTickets", new ViewAllTicketsListCommand());
		commands.put("ticketInfo", new ShowTicketInfoCommand());
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}