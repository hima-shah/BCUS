package com.bcus.cms.processor;

import com.bcus.cms.model.ConveyorGraph;

/**
 * Interface to build optimal path for graph.
 * Presently only Floy Warshall implementation for graph traversal is present.
 * @author hima_shah
 *
 */
public interface GraphProcessor {
	public void buildGraph(ConveyorGraph graph);
}
