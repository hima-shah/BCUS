package com.bcus.cms.model;

import java.io.Serializable;

/**
 * 
 * @author hima_shah
 *
 */
public class Bag implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6176492141237790835L;
	
	/**
	 * Unique identification of the bag
	 */
	private String bagNum;
	
	/**
	 * Flight gate (Node) from where the traversal is required
	 */
	private String entryGate;
	
	/**
	 * Flight id for which the bag needs to be delivered (The exit node is derived from this)
	 */
	private String flightId;

	public Bag() {
	}

	/**
	 * Initializes the bag object with given attributes
	 * @param bagNum
	 * @param entryGate
	 * @param flightId
	 */
	public Bag(String bagNum, String entryGate, String flightId) {
		this.bagNum = bagNum;
		this.entryGate = entryGate;
		this.flightId = flightId;
	}

	
	/**
	 * Returns the bag number
	 * @return
	 */
	public String getBagNum() {
		return bagNum;
	}

	/**
	 * Returns the entry node
	 * @return
	 */
	public String getEntryGate() {
		return entryGate;
	}

	
	/** 
	 * returns the flight from which end node to be derived
	 * @return
	 */
	public String getFlight() {
		return flightId;
	}

	/**
	 * hashCode of the Bag object is derived from it's unique Bag Number
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bagNum == null) ? 0 : bagNum.hashCode());
		return result;
	}

	/**
	 * Two bag objects are equal if their number are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bag other = (Bag) obj;
		if (bagNum == null) {
			if (other.bagNum != null)
				return false;
		} else if (!bagNum.equals(other.bagNum))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return new StringBuilder().append("\n").append(flightId).append(" ")
				.append(entryGate).append(" ").append(flightId).toString();
	}
}
