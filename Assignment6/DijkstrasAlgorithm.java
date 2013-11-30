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

  /** 
   * Constructor. A Graph<T,Integer> must be provided.
   *
   * Note that Dijkstra's Algorithm assumes that the edges
   * are weighted, so this constructor only operates on Graphs
   * whose edges have Integer labels.
   */
  public DijkstrasAlgorithm(Graph<T,Integer> graph) {

      // Implement.

      // Hint: You should be able to reuse much of
      // the code from the lecture notes.

  }


  /** 
   * The main method that kicks off the algorithm. 
   */
  public void execute(Node<T,Integer> source) {

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