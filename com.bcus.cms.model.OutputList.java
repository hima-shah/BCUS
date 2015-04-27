package com.bcus.cms.model;

import java.util.ArrayList;
import java.util.List;


public class OutputList {
	private List<Output> outputList = new ArrayList<Output>();
	private static final String LINE_SEPERATOR = System.getProperty( "line.separator" );
	public List<Output> getOutputList() {
		return outputList;
	}

	public void setOutputList(List<Output> outputList) {
		this.outputList = outputList;
	}
	
	@Override
	public String toString(){
		StringBuilder result=new StringBuilder();
		for(Output o: outputList){
			result.append(o).append(LINE_SEPERATOR);
		}
		return result.toString();
	}

}
