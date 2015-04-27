package com.bcus.cms;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bcus.cms.model.Bag;
import com.bcus.cms.model.ConveyorGraph;
import com.bcus.cms.model.Flight;
import com.bcus.cms.model.Output;
import com.bcus.cms.model.OutputList;
import com.bcus.cms.processor.FloydWarshallGraphrProcessor;
import com.bcus.cms.processor.GraphProcessor;
import com.bcus.cms.reader.FileInputReader;
import com.bcus.cms.util.ConveyorSystemConstants;
import com.bcus.cms.writer.FileOutputWriter;


/**
 * The main class
 * @author hima_shah
 *
 */
public class RunConveyorSystem {
	
	public static void main(String[] args){

		String inputFile;
		if(args.length>0){
			inputFile = args[0];
		}else{
			inputFile = ConveyorSystemConstants.FILE_PATH;
		}
		
		List<Bag> bagList = new ArrayList<Bag>();
		Map<String,Flight> flightMap = new HashMap<String, Flight>();
		ConveyorGraph conveyorGraph = new ConveyorGraph();
		
		FileInputReader reader=new FileInputReader();
		reader.readFile(inputFile,bagList,flightMap,conveyorGraph);
		GraphProcessor graphProcessor = new FloydWarshallGraphrProcessor();
		graphProcessor.buildGraph(conveyorGraph);
			
		List<String> nodeNameList = conveyorGraph.getNodeNameList();
		ConveyorGraph.MatrixCell[][] adjMatrix = conveyorGraph.getAdjacencyMatrix();
		
		Output output ;
		OutputList outList = new OutputList();
		
		List<Output> outputList = outList.getOutputList();
		
		int startIndex, endIndex;
		for(Bag bag : bagList){
			output = new Output();
			output.setBagNum(bag.getBagNum());
			String startNode = bag.getEntryGate();
			String endNode="";
			if(bag.getFlight().equals(ConveyorSystemConstants.ARRIVAL_NODE)){
				endNode = ConveyorSystemConstants.BAGGAGE_CLAIM_NODE;
			}else{
				endNode = (flightMap.get(bag.getFlight())).getFlightGate();
			}
			
			startIndex = nodeNameList.indexOf(startNode);
			endIndex = nodeNameList.indexOf(endNode);
			output.setNodeNameList(adjMatrix[startIndex][endIndex].getPath());
			output.setTraveltime(new DecimalFormat("#").format(adjMatrix[startIndex][endIndex].getTravelTime()));
			outputList.add(output);
		}
		FileOutputWriter fileOutputWriter = new FileOutputWriter();
		fileOutputWriter.write(outList.toString());
	}
	
}
