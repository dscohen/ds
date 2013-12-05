
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

public class Test3 extends TestHarness {

  public Test3(String s) { super(s); }

  public boolean test() {
    Graph<String,Integer> g = new Graph<String,Integer>();
    Node<String,Integer> n1;
    boolean correct = true;
    Node<String, Integer> test;

    try {
      try{
        test = g.findNode("a");
        if (test != null) {correct = false;}
        test = g.findNode(null);
        if (test != null) {correct = false;}
      } catch (Exception a) {
        correct = false;
      }

      n1 = g.addNode("a");
      test = g.findNode("a");
      if (!test.getLabel().equals("a")) {correct = false;}
      g.addEdge("a",15,"B");
      test = g.findNode("B");
      if (!test.getLabel().equals("B")) {correct = false;}
    } catch (Exception e) {
      return false;
    }

    // Make sure n1's toStringWithEdges() works correctly

    return correct;
  }

}
