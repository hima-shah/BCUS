package com.bcus.cms.model;

public class Flight {
	private String flightId;
	private String flightGate;
	private String destination;
	private String flighttime;

	public Flight() {
	}

	public Flight(String flightId, String fligthGate, String destination,
			String flighttime) {
		this.flightId = flightId;
		this.flightGate = fligthGate;
		this.destination = destination;
		this.flighttime = flighttime;
	}

	public String getFlightId() {
		return flightId;
	}

	public String getFlightGate() {
		return flightGate;
	}

	public String getDestination() {
		return destination;
	}

	public String getFlighttime() {
		return flighttime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flightId == null) ? 0 : flightId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder("\n").append(flightId).append(" ")
				.append(flightGate).append(" ").append(destination)
				.append(flighttime).toString();
	}
}
