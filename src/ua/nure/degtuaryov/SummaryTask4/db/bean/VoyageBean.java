package ua.nure.degtuaryov.SummaryTask4.db.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import ua.nure.degtuaryov.SummaryTask4.db.entity.Train;

/**
 * 
 * VoyageBean is a bean that contains all information from voyage in one object
 * 
 * @author Degtuaryow
 *
 */
public class VoyageBean implements Serializable {

	private static final long serialVersionUID = -1333829633980823904L;

	private long id;

	private Train train;

	private RouteStationsBean routeStationsBean;

	private Timestamp arrivalTime;

	private Timestamp departureTime;

	private int coupeSeat;

	private int reservedSeat;

	private int generalSeat;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Timestamp getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}

	public int getCoupeSeat() {
		return coupeSeat;
	}

	public void setCoupeSeat(int coupeSeat) {
		this.coupeSeat = coupeSeat;
	}

	public int getReservedSeat() {
		return reservedSeat;
	}

	public void setReservedSeat(int reservedSeat) {
		this.reservedSeat = reservedSeat;
	}

	public int getGeneralSeat() {
		return generalSeat;
	}

	public void setGeneralSeat(int generalSeat) {
		this.generalSeat = generalSeat;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public RouteStationsBean getRouteStationsBean() {
		return routeStationsBean;
	}

	public void setRouteStationsBean(RouteStationsBean routeStationsBean) {
		this.routeStationsBean = routeStationsBean;
	}

	@Override
	public String toString() {
		return "VoyageBean [id=" + id + ", train=" + train + ", route=" + routeStationsBean + ", arrivalTime="
				+ arrivalTime + ", departureTime=" + departureTime + ", coupeSeat=" + coupeSeat + ", reservedSeat="
				+ reservedSeat + ", generalSeat=" + generalSeat + "]";
	}

}
