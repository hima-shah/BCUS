package com.bcus.cms.model;

import java.io.Serializable;

/**
 * It's undirected edge between a pair of nodes.
 * It stores two node ends along with the travel time between them (weight)
 * @author hima_shah
 *
 */
public class Edge implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6723582067767379442L;
	
	/**
	 * One node of the pair joined by the edge
	 */
	private String node1;
	
	/**
	 * The other node of the edge. With bi-directional (undirectional) edge the order of nodes doesn't matter.
	 */
	private String node2;
	
	/**
	 * Travel time between two nodes.
	 */
	int weight;
	
	/**
	 * Initializes the edge with the pair of nodes and travel time
	 * @param node1
	 * @param node2
	 * @param weight
	 */
	public Edge(String node1, String node2, int weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}
	
	
	/**
	 * Both node1 and node2 are taken into account while calculating the hascode of the edge
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node2 == null) ? 0 : node2.hashCode());
		result = prime * result
				+ ((node1 == null) ? 0 : node1.hashCode());
		return result;
	}
	
	/**
	 * Node1 and Node2 can appear in any order, but still it's the same edge.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (node2 == null) {
			if (other.node2 != null)
				return false;
		} else if (node1 == null) {
			if (other.node1 != null)
				return false;
		} else if(!(((node1.equals(other.node1))&&(node2.equals(other.node2)))||
				((node1.equals(other.node2))&&(node2.equals(other.node1)))))
		{
			return false;
		}
		return true;
	}

	public String getNodeOne() {
		return node1;
	}

	public String getNodeTwo() {
		return node2;
	}

	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("Edge [").append(node1).append(" <-> ")
				.append(node2).append(", weight=").append(weight)
				.append("]").toString();
	}
	
}
