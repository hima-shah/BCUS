package com.bcus.cms.processor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Edge;

/**
 * This class uses Floyd Warshall algorithm to find the optimal path between all possible pairs of nodes.
 * The time complexity to build the adjacency matrix is O(n**3).
 * But once the matrix is built the time complexity to get the optimal node is O(1)
 * @author hima_shah
 *
 */
public class FloydWarshallGraphrProcessor implements GraphProcessor {

	
	/**
	 * First initializes the adjacency matrix with the direct edge given between pair of nodes and travel time using method prepareAdjacencyMatrix.
	 * After which finds the optimal path between pair of nodes directly or via other nodes and alter the adjacency matrix accordingly.
	 */
	@Override
	public void buildGraph(ConveyorGraph graph) {
		// TODO Auto-generated method stub
		prepareAdjacencyMatrix(graph);
		buildPath(graph);

	}

	/**
	 * Takes the graph as an input. The set of edges and node list is already assigned to the graph while reading the input file.
	 * The method uses the nodelist and edges and initializes the adjacency matrix for the direct edge and given travel time between pair of nodes.
	 * The index of nodeList is associated with the index of adjacency matrix.
	 * @param conveyorGraph
	 */
	private void buildPath(ConveyorGraph conveyorGraph) {
		int nodes = conveyorGraph.getNodeNameList().size();
		ConveyorGraph.MatrixCell[][] adjacencyMatrix = conveyorGraph
				.getAdjacencyMatrix();

		double currTime, newTime = Double.POSITIVE_INFINITY;
		List<String> path1, path2;
		for (int k = 0; k < nodes; k++) {
			for (int i = 0; i < nodes; i++) {
				for (int j = 0; j < nodes; j++) {
					newTime = adjacencyMatrix[i][k].getTravelTime()
							+ adjacencyMatrix[k][j].getTravelTime();
					currTime = adjacencyMatrix[i][j].getTravelTime();
					//If travelTime(i->j) > travelTime(i->j->k) then alter the adjacency matrix for less travel time and more efficient path.
					if (currTime > newTime) {
						adjacencyMatrix[i][j].setTravelTime(newTime);
						path1 = new LinkedList<String>(
								adjacencyMatrix[i][k].getPath());
						path2 = new LinkedList<String>(
								adjacencyMatrix[k][j].getPath());
						//the last element of path1 and first element of path2 are the same.
						((LinkedList<String>) path1).removeLast();
						path1.addAll(path2);
						adjacencyMatrix[i][j].setPath(path1);
					}
				}
			}
		}
	}

	
	/**
	 * It initializes the adjacency matrix for direct edges given in input.
	 * If direct edge doesn't exist between nodes the travel time is Infinity and path between them is empty list.
	 * @param conveyorGraph
	 */
	private void prepareAdjacencyMatrix(ConveyorGraph conveyorGraph) {
		List<String> nodeNameList = (ArrayList<String>) conveyorGraph
				.getNodeNameList();
		int totalNodes = nodeNameList.size();
		int startIndex = 0, endIndex = 0;
		String startNodeName, endNodeName;
		ConveyorGraph.MatrixCell[][] adjMatrix = new ConveyorGraph.MatrixCell[totalNodes][totalNodes];

		List<String> oneNodeList;
		//Initialies all matrix cells with empty path and travel time as Infinity.
		//For i==j i.e. when source and target nodes are same travel time =0
		for (int i = 0; i < totalNodes; i++) {
			for (int j = 0; j < totalNodes; j++) {
				adjMatrix[i][j] = (new ConveyorGraph()).new MatrixCell();
				if (i == j) {
					adjMatrix[i][j].setTravelTime(0);
					oneNodeList = new ArrayList<String>();
					oneNodeList.add(nodeNameList.get(i));
					adjMatrix[i][j].setPath(oneNodeList);
				}
			}
		}

		//For each direct edge between nodes initalize the adjacency matrix cell with the given travel time and path between nodes
		for (Edge edge : conveyorGraph.getEdgeSet()) {
			startNodeName = edge.getNodeOne();
			endNodeName = edge.getNodeTwo();

			startIndex = nodeNameList.indexOf(startNodeName);
			endIndex = nodeNameList.indexOf(endNodeName);

			adjMatrix[startIndex][endIndex].addPath(edge, startNodeName);

			adjMatrix[endIndex][startIndex].addPath(edge, endNodeName);
		}
		conveyorGraph.setAdjacencyMatrix(adjMatrix);
	}
}
