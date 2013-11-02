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
	    return false;
	}
	fs.display();

	// make sure 'sample/foo/bar' is in the file system
	Tree<FileNode> rt = fs.getTree();
	if(!rt.getLabel().getName().equals("ROOT"))
	    return false;
	Tree<FileNode> sampleT = rt.findChild(new FileNode("sample",true));
	if(sampleT == null) return false;
	Tree<FileNode> fooT    = sampleT.findChild(new FileNode("foo",true));
	if(fooT == null) return false;
	Tree<FileNode> barT    = fooT.findChild(new FileNode("bar",true));
	if(fooT == null) return false;
	return true;
	
    }

}
