import java.io.File;
import java.io.IOException;
/**
 * FileNode: implementation of TreeNode, used in {@link FileSystem}.
 * <p>
 * ***********************************************************************
 * Computer Science 102: Data Structures
 * New York University, Fall 2013,
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-10-30
 */
public class FileNode implements TreeNode {
    public String name;
    public boolean isDir;

    /**
     * Constructors, getters/setters, .equals()
     */
    public FileNode(String n, boolean i) { name = n; isDir = i; }
    public String getName() { return name; }
    public boolean equals(TreeNode tn) { return name.equals(tn.getName()); }

    /**
     * Output methods used as part of the display methods of the {@link Tree}.
     * <p>
     * preString() generates a string of the node to be displayed *before*
     * traversal of children, postString() generates a string of the node 
     * to be displayed *after* traversal of children.
     */
    public String preString() {
	return "<"+(isDir?"dir":"file")+" name=\""+name+"\">";
    }
    public String postString() {
	return "</"+(isDir?"dir":"file")+">";
    }
    public String toString() {
	return name;
    }
}
