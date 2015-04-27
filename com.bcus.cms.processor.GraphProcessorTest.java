package com.bcus.cms.processor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Edge;

public class GraphProcessorTest {

	private static ConveyorGraph c = new ConveyorGraph();
	private static GraphProcessor processor;

	@BeforeClass
	public static void init() {
		processor = new FloydWarshallGraphrProcessor();
		List<String> nodes = c.getNodeNameList();
		nodes.add("A1");
		nodes.add("A2");
		nodes.add("A3");
		nodes.add("A4");
		nodes.add("A5");
		Set<Edge> edgeset = c.getEdgeSet();
		edgeset.add(new Edge("A1", "A2", 8));
		edgeset.add(new Edge("A1", "A3", 4));
		edgeset.add(new Edge("A4", "A3", 9));
		edgeset.add(new Edge("A4", "A2", 2));
		edgeset.add(new Edge("A2", "A3", 1));
		processor.buildGraph(c);
	}
	
	@Test
	public void testSelfLoop() {

		// Input
		String strtNodeName = "A2";
		String endNodeName = "A2";
		double expectedTime = 0;
		List<String> expectedPath = new ArrayList<String>();
		expectedPath.add("A2");
		

		int startIndex = c.getNodeNameList().indexOf(strtNodeName);
		int endIndex = c.getNodeNameList().indexOf(endNodeName);
		List<String> path = c.getAdjacencyMatrix()[startIndex][endIndex]
				.getPath();
		double time = c.getAdjacencyMatrix()[startIndex][endIndex].getTravelTime();
		assertTrue(matchNodePath(expectedPath, path) && (time == expectedTime));
	}

	@Test
	public void testDirectPath() {

		// Input
		String strtNodeName = "A2";
		String endNodeName = "A3";
		double expectedTime = 1;
		List<String> expectedPath = new ArrayList<String>();
		expectedPath.add("A2");
		expectedPath.add("A3");

		int startIndex = c.getNodeNameList().indexOf(strtNodeName);
		int endIndex = c.getNodeNameList().indexOf(endNodeName);
		List<String> path = c.getAdjacencyMatrix()[startIndex][endIndex]
				.getPath();
		double time = c.getAdjacencyMatrix()[startIndex][endIndex].getTravelTime();
		assertTrue(matchNodePath(expectedPath, path) && (time == expectedTime));
	}

	@Test
	public void testIndirectPath() {
		// Input
		String strtNodeName = "A4";
		String endNodeName = "A1";
		double expectedTime = 7;
		List<String> expectedPath = new ArrayList<String>();
		expectedPath.add("A4");
		expectedPath.add("A2");
		expectedPath.add("A3");
		expectedPath.add("A1");

		int startIndex = c.getNodeNameList().indexOf(strtNodeName);
		int endIndex = c.getNodeNameList().indexOf(endNodeName);
		List<String> path = c.getAdjacencyMatrix()[startIndex][endIndex]
				.getPath();
		double time = c.getAdjacencyMatrix()[startIndex][endIndex].getTravelTime();
		assertTrue(matchNodePath(expectedPath, path) && (time==expectedTime));
	}
	
	@Test
	public void testIsolatedNode(){
		//input
		String strtNodeName = "A5";
		String endNodeName = "A1";
		List<String> expectedPath = new ArrayList<String>();
		double expectedTime = Double.POSITIVE_INFINITY;
		
		int startIndex = c.getNodeNameList().indexOf(strtNodeName);
		int endIndex = c.getNodeNameList().indexOf(endNodeName);
		List<String> path = c.getAdjacencyMatrix()[startIndex][endIndex]
				.getPath();
		double time = c.getAdjacencyMatrix()[startIndex][endIndex].getTravelTime();

		assertTrue(matchNodePath(expectedPath, path) && (time == expectedTime) );
	}
	
	@Test
	public void testSymmetricPath(){
		//input
		String strtNodeName = "A2";
		String endNodeName = "A1";
		
		int startIndex = c.getNodeNameList().indexOf(strtNodeName);
		int endIndex = c.getNodeNameList().indexOf(endNodeName);
		
		List<String> path1 = c.getAdjacencyMatrix()[startIndex][endIndex]
				.getPath();
		List<String> path2 = c.getAdjacencyMatrix()[endIndex][startIndex]
				.getPath();
		
		double time1= c.getAdjacencyMatrix()[startIndex][endIndex].getTravelTime();
		double time2= c.getAdjacencyMatrix()[endIndex][startIndex].getTravelTime();
		
		Collections.reverse(path2);
		assertTrue(matchNodePath(path1, path2) && (time1==time2));
	}

	private boolean matchNodePath(List<String> expectedPath,
			List<String> actulPath) {
		boolean blnResult = true;

		if (expectedPath.size() == actulPath.size()) {
			for (int i = 0; i < expectedPath.size(); i++) {
				if (!expectedPath.get(i).equals(actulPath.get(i))) {
					blnResult = false;
					break;
				}
			}
		} else {
			blnResult = false;
		}
		return blnResult;
	}
}
