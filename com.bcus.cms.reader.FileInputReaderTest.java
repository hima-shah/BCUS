package com.bcus.cms.reader;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Flight;

public class FileInputReaderTest {

	private static  String inputFile;
	private static FileInputReader fr;// =new FileInputReader();
	private static List<Bag>  bagList;
	private static Map<String, Flight> flightMap ;
	private static ConveyorGraph graph ;
	
	@BeforeClass
	public static void init(){
		
		inputFile = "C:\\BCUS\\Input.txt";
		fr = new FileInputReader();
		bagList = new ArrayList<Bag>();
		flightMap = new HashMap<String, Flight>();
		graph = new ConveyorGraph();
	}
	
	@Before
	public void cleanUp(){
		bagList.clear();
		flightMap.clear();
		graph = new ConveyorGraph();
	}
	@Test
	public void testPositiveInput(){
		//input
		int exceptedBags = 5;
		int exceptedFlights =9;
		int exceptedNodes = 12;
		int exceptedEdges = 11;
		
		fr.readFile(inputFile, bagList, flightMap, graph);
		assertTrue(bagList.size() == exceptedBags && flightMap.size() == exceptedFlights &&
				 exceptedNodes == graph.getNodeNameList().size() && exceptedEdges == graph.getEdgeSet().size());
		
	}
}
