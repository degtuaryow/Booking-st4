package ua.nure.degtuaryov.SummaryTask4.db;

import ua.nure.degtuaryov.SummaryTask4.db.entity.Client;

/**
 * Enum for role entity from db to get role identifier
 * 
 * @author Degtuaryow
 *
 */
public enum Role {
	ADMIN, CLIENT;
	
	public static Role getRole(Client client) {
		long roleId = client.getRoleId();
		return Role.values()[(int) roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}	
}
