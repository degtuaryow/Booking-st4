package ua.nure.degtuaryov.SummaryTask4.db;

import ua.nure.degtuaryov.SummaryTask4.db.entity.Booking;

/**
 * Enum for seat entity from db to get seat type and coefficient for ticket
 * price
 * 
 * @author Degtuaryow
 *
 */
public enum Seat {
	COUPE(1), RESERVED(3), GENERAL(2);

	public String getType() {
		return name().toLowerCase();
	}

	private int coefficient;

	private Seat(int constant) {
		this.setCoefficient(constant);
	}

	public int getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}

	public static Seat getSeat(Booking booking) {
		long seatId = booking.getSeatId();
		return Seat.values()[(int) seatId];
	}
}
