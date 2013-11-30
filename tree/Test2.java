/**
 * Test1 -- Example test class extending {@link TestHarness}
 * <p>
 * This test loads a filesystem and ensures a certain file appears in the tree.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-09-01
 */


public class Test2 extends TestHarness {

  public Test2(String s) { super(s); }

  public boolean test() {
    boolean pass = true;

    //build tree
    //root -> [kitchen, refrig., a -> [b,c,d]]

    try {
      Tree<FileNode> root = null;
      root = new Tree<FileNode>(new FileNode("Apartment", true));
      root.addChild(new Tree<FileNode>(new FileNode("Kitchen", true)));
      root.addChild(new Tree<FileNode>(new FileNode("Refrigerator", true)));
      FileNode pre_a = new FileNode("a", true);
      FileNode pre_b = new FileNode("b", false);
      FileNode pre_c = new FileNode(null, false);
      Tree<FileNode> a = new Tree<FileNode>(pre_a);
      Tree<FileNode> b = new Tree<FileNode>(pre_b);
      Tree<FileNode> c = new Tree<FileNode>(pre_c);
      Tree<FileNode> d = new Tree<FileNode>(new FileNode("d", false));
      root.addChild(a);
      a.addChild(b);
      a.addChild(c);
      a.addChild(d);
      root.displayXML();
      //test findchild
      //checks for finding proper child
      if (root.findChild(pre_a) != a) {pass = false;}
      //makes sure it's only immediate children
      if (root.findChild(pre_b) != null) {pass = false;}
      //Makes sure it doesn't find parents
      if (b.findChild(pre_a) != null) {pass = false;}
      //Checks to see what happens when name of file node is null...no
      //specifications were given as to what it should return
      //and the .equals() will throw a null pointer exception,
      //so as long as it doesn't crash, it passes.
      a.findChild(pre_c);
      //Checks for null entry
      if (a.findChild(null) != null) {pass = false;}

    } catch (Exception e) {
      return false;}

    return pass;

  }
}
