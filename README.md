# BCUS
Proposed Denvar airport Conveyor System:
----------------------------------------

The developed Denvar Conveyor system gathers input on start up by reading the input file in the given format.
On start up it initializes the the conveyor graph by the given paths between flight gates and given travel time.
It uses 'Floyd Warshall' algorithm to find the fasted path between each possible pair of Nodes.
The algorithm has O(N**3) time complexity to find and store optimal path between each pair of nodes.
But once the graph is initialized with the optimal path the time complexity to retrieve the path and time is O(1).
It finds the output in the given format and writes it to output file specified in the application constant file.

How to run:
-----------

1. Specify the file path as command line argument to class RunConveyorSystem. If not given it will read the file path
   from ConveyorSystemConstants class specified as constant variable FILE_PATH.

2. Specify the absolute or relative file path as OUTPUT_FILE_PATH in ConveyorSystemConstants.java

3. Run 'RunConveyorSystem.class' as standalone java application.

4. Output file should be generated at the given location after successful completion of the program.

Known Limitation:
-----------------

1. Presently application receives only a single file and proceeds in a single go. It can be developed as a batch or poller
   which could poll to given location and generate the output on availability of input.
