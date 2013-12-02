/**
 * An implementation of Dijkstra's shortest path algorithm on a {@link Graph}
 * <p>
 * Once this class is instantiated, a user invokes {@link execute} to run
 * the algorithm, and then {@link getPath} to view the path that was found.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstrasAlgorithm<T> {
  private final List<Node<T,Integer>> nodes;
  private final List<Edge<T, Integer>> edgees;
  private Set<Node<T,Integer>> settledNodes;
  private Set<Node<T, Integer>> unSettledNodes;
  private Map<Node<T, Integer>, Node<T, Integer>> predecessors;
  private Map<Node<T, Integer>, Integer> distance;
  private Graph<T,Integer> graph;

  /** 
   * Constructor. A Graph<T,Integer> must be provided.
   *
   * Note that Dijkstra's Algorithm assumes that the edges
   * are weighted, so this constructor only operates on Graphs
   * whose edges have Integer labels.
   */
  public DijkstrasAlgorithm(Graph<T,Integer> graph) {
    this.nodes = graph.getNodes();
    graph = graph;

    // Implement.

    // Hint: You should be able to reuse much of
    // the code from the lecture notes.

  }


  /** 
   * The main method that kicks off the algorithm. 
   */
  public void execute(Node<T,Integer> source) {
    settledNodes = new HashSet<Node<T,Integer>>();
    unSettledNodes = new HashSet<Node<T, Integer>>();
    distance = new HashMap<Node<T,Integer>, Integer>();
    predecessors = new HashMap<Node<T,Integer>, Node<T,Integer>>();
    distance.put(source,0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0) {
      Node<T,Integer> node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  private void findMinimalDistances(Node<T,Integer> node) {
    List<Node<T,Integer>> adjacentNodes = getNeighbors(node);
    for (Node<T,Integer> target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node) + getDistance(node, target));
        predecessors.put(target, node);
        unSettledNodes.add(target);
      }
    }
  }

  private int getDistance(Node<T,Integer> node, Node<T,Integer> target) {
    List<Edge<T,Integer>> edges = node.getOutArcs();
    //assuming tail is what the arrow points to
    for (Edge<T, Integer> edge : edges) {
      if (edge.getTail().equals(target)) {
        return edge.getLabel();
      }
    }
  }

  private List<Node<T,Integer> getNeighbors(Node<T,Integer> node) {
    List<Node<T,Integer>> neighbors = new ArrayList


    // Implement.

    // Hint: You should be able to reuse much of
    // the code from the lecture notes.

  }

  /**
   * This method returns the path from the source to the selected target and
   * null if no path exists
   */
  public LinkedList<Node<T,Integer>> getPath(Node<T,Integer> target) {

    // Implement.

    // Hint: You should be able to reuse much of
    // the code from the lecture notes.

  }

}
