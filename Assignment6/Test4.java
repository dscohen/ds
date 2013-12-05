   //Addnode

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
public class Test4 extends TestHarness {

  public Test4(String s) { super(s); }

  public boolean test() {
    Graph<String,Integer> g = new Graph<String,Integer>();
    Node<String,Integer> n1;
    boolean correct = true;
    Node<String, Integer> test;

    try {
      try {
        test = g.addNode(null);
        return false;
      } catch (InvalidOperationException a) {}
        test = g.addNode("a");
        correct = false;

      test = g.findNode("a");
      if (!test.getLabel().equals("a")) {correct = false;}
      try {
        test = g.addNode("a");
        correct = false;
      } catch (InvalidOperationException c) {
      }

    } catch (Exception e) {
      return false;
    }

    // Make sure n1's toStringWithEdges() works correctly

    return correct;
  }

}
