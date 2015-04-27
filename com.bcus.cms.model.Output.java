package com.bcus.cms.model;

import java.io.Serializable;
import java.util.List;

import com.bcus.cms.util.ConveyorSystemConstants;


/**
 * 
 * @author hima_shah
 *
 */
public class Output implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507051610974412530L;
	private String bagNum;
	private List<String> nodeNameList;
	private String traveltime;
	
	public void setBagNum(String bagNum) {
		this.bagNum = bagNum;
	}

	public void setNodeNameList(List<String> nodeNameList) {
		
		this.nodeNameList = nodeNameList;
	}

	public void setTraveltime(String traveltime) {
		this.traveltime = traveltime;
	}
	
	private String getPathString(List<String> nodeNameList){
		StringBuilder strBuilder = new StringBuilder();
		for(String node:nodeNameList){
			strBuilder.append(node).append(ConveyorSystemConstants.WHITESPACE_SEPERATOR);
		}
		return strBuilder.toString();
	}
	
	@Override
	public String toString(){
		return new StringBuilder().append(bagNum).append(ConveyorSystemConstants.WHITESPACE_SEPERATOR).append(getPathString(nodeNameList))
							.append(ConveyorSystemConstants.COLON_SEPERATOR).append(ConveyorSystemConstants.WHITESPACE_SEPERATOR).append(traveltime).toString();
	}
	
	
	
}
