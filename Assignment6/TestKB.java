
/**
 * Test2 -- Example test class testing {@link DijkstrasAlgorithm}
 * <p>
 * This test creates a Graph with a few nodes/edges and runs the algorithm,
 * using the graph found 
 * <a href="http://en.wikipedia.org/wiki/Dijkstra's_algorithm">here</a>
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-11-25
 */

import java.util.LinkedList;

public class TestKB extends TestHarness {

    public TestKB(String s) { super(s); }

    public boolean test() {
	Graph<String,Integer> g = new Graph<String,Integer>();

	// Construct the graph from wikipedia:
	Node<String,Integer> n1;
	Node<String,Integer> n2;
	Node<String,Integer> n3;
	Node<String,Integer> n4;

	try {
	    n1 = g.addNode("Keven Bacon");
	    n2 = g.addNode("Mickey Rourke");
	    n3 = g.addNode("Marisa Tomei");
	    n4 = g.addNode("Joe Pesci");
	    g.addBiEdge("Keven Bacon",1,"Mickey Rourke");
	    g.addBiEdge("Mickey Rourke",1,"Marisa Tomei");
	    g.addBiEdge("Marisa Tomei",1,"Joe Pesci");
	} catch (InvalidOperationException e) {
	    return false;
	}

	// Run Dijkstra's Algorithm
	DijkstrasAlgorithm da = new DijkstrasAlgorithm(g);
	da.execute(n1);

	// Get the path from n1 to n5 that was computed by da
	LinkedList<Node<String,Integer>> ll = da.getPath(n4);
	System.out.println("path: "+ll.toString());
	// Confirm that this path is what it should be
	if(! ll.pop().getLabel().equals("Keven Bacon")) return false;
	if(! ll.pop().getLabel().equals("Mickey Rourke")) return false;
	if(! ll.pop().getLabel().equals("Marisa Tomei")) return false;
	if(! ll.pop().getLabel().equals("Joe Pesci")) return false;
	if(  ll.size() != 0) return false;

	//System.out.println(g.toString());
	return true;
    }

}
