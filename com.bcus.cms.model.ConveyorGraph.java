package com.bcus.cms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
 

/**
 * This class holds the Conveyer system as a graph.
 * It contains list of nodes, set of edges and the adjacency matrix with optimal path between pair of nodes along with the least travel time
 * @author hima_shah
 *
 */
public class ConveyorGraph implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7294920499456067824L;

	/**
	 * Set of all edges in the conveyor graph
	 */
	private Set<Edge> edgeSet = new HashSet<Edge>();
	
	/**
	 * List of all nodes within the graph. Its order relates to the indexes of the adjacency matrix.
	 */
	private List<String> nodeNameList = new ArrayList<String>();
	
	/**
	 * Adjacency matrix is of size (n**2).
	 * It gets initialized with direct edge route between nodes and their weight (travel time)
	 * Subsequently stores the optimal time between pair of node and the path for that
	 * If no path exists between two nodes the time is Infinity and path is empty.
	 */
	private MatrixCell adjacencyMatrix[][] ;

	
	public Set<Edge> getEdgeSet(){
		return edgeSet;
	}
	
	public MatrixCell[][] getAdjacencyMatrix(){
		return adjacencyMatrix;
	}
	
	public List<String> getNodeNameList(){
		return nodeNameList;
	}
	public void setAdjacencyMatrix(MatrixCell[][] adjacencyMatrix){
		this.adjacencyMatrix = adjacencyMatrix;
	}
	
	/**
	 * This inner class represents a cell structure of the adjacency matrix
	 * @author hima_shah
	 *
	 */
	public class MatrixCell {
		/**
		 * travel time between two nodes either directly or via other nodes
		 */
		private double travelTime ;

		/**
		 * the optimal path between two nodes 
		 */
		private List<String> nodeList;
		
		/**
		 * The cell is initialized with travel time as Infinity and node path and empty list
		 */
		public MatrixCell(){
			this.travelTime=Double.POSITIVE_INFINITY;
			this.nodeList  = new LinkedList<String>();
		}
	
		
		/**
		 * Private method to add two nodes of the edge in the given order
		 * @param node1
		 * @param node2
		 */
		private void addNodes(String node1, String node2){
			this.nodeList.add(node1);
			this.nodeList.add(node2);
		}
		
		/**
		 * Initializes the path for the direct edges
		 * @param edge
		 * @param startNode
		 */
		public void addPath(Edge edge, String startNode) {
			nodeList.clear();
			String node1 = edge.getNodeOne();
			String node2 = edge.getNodeTwo();
				if(startNode.equalsIgnoreCase(node1)){
					addNodes(node1,node2);
				}else{
					addNodes(node2,node1);
				}
				travelTime = edge.getWeight();
		}
		
		public List<String> getPath(){
			return nodeList;
		}
		public void setPath(List<String> nodeList){
			this.nodeList = nodeList;
		}
		
		public double getTravelTime() {
			return travelTime;
		}
		public void setTravelTime(double travelTime){
			this.travelTime = travelTime;
		}
	}
}
