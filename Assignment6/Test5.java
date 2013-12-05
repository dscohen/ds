
//Getnode
/**
 * Test1 -- Example test class extending {@link TestHarness}
 * <p>
 * This test creates a Graph with a few nodes/edges
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
import java.util.List;
public class Test5 extends TestHarness {

  public Test5(String s) { super(s); }

  public boolean test() {
    Graph<String,Integer> g = new Graph<String,Integer>();
    Node<String,Integer> n1;
    boolean correct = true;
    Node<String, Integer> test;

    try {
      List<Node<String,Integer>> result =  g.getNodes();
      if (result.size() != 0) {correct = false;}
      Node<String, Integer> a = g.addNode("a");
      Node<String, Integer> b = g.addNode("b");
      result =  g.getNodes();
      if (!result.contains(a) || !result.contains(b)) {correct = false;}
      


    } catch (Exception e) {
      return false;
    }

    // Make sure n1's toStringWithEdges() works correctly

    return correct;
  }

}
