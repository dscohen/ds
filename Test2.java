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
  Tree<FileNode> apartment = new Tree<FileNode>();
 apartment.displayXML();
 Tree<FileNode> kitchen;
 Tree<FileNode> refigerator;
 Tree<FileNode> milk;
 Tree<FileNode> eggs;
 Tree<FileNode> bathroom;
 Tree<FileNode> shower;

 return true;
}
}
