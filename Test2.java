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

	// make sure 'sample/foo/bar' is in the file system

      Tree<FileNode> root = null;
      root = new Tree<FileNode>(new FileNode("Apartment", true));
      root.addChild(new Tree<FileNode>(new FileNode("Kitchen", true)));
      root.addChild(new Tree<FileNode>(new FileNode("Refrigerator", true)));
      Tree<FileNode> a = new Tree<FileNode>(new FileNode("a", true));
      Tree<FileNode> b = new Tree<FileNode>(new FileNode("b", false));
      Tree<FileNode> c = new Tree<FileNode>(new FileNode("c", false));
      Tree<FileNode> d = new Tree<FileNode>(new FileNode("d", false));
      root.addChild(a);
      a.addChild(b);
      a.addChild(c);
      a.addChild(d);
      root.displayXML();
      System.out.println(root);
      return true;
}
}
