/**
 * Interface of a node of a {@link Tree}
 * <p>
 * This implements a basic generic tree, with labels of type T,
 * pointer to the parent node, and a singly linked list of children nodes.
 * It provides iterators that loop over children, that loop up to the root,
 * and that traverse the tree in prefix and postfix order.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-01-01
 */
import java.io.File;
import java.io.IOException;

public interface TreeNode {
    public String getName();
    public boolean equals(TreeNode tn);
    public String preString();
    public String postString();
}
