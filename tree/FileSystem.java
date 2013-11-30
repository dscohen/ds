/**
 * A filesystem implemented as a tree of {@link FileNode}s.
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
import java.util.HashMap;
import java.io.File;

public class FileSystem {
  /**
   * The {@link FileSystem} is implemented via a {@link Tree} of {@link FileNode}s
   */
  Tree<FileNode> root = null;
  public Tree<FileNode> getTree() { return root; }

  /**
   * insertPath(): Inserts the file given by filePathQueue into tree t.
   * <p>
   * Recursively calls itself by dequeuing, searching for node in current
   * tree, if it finds it, calls itself on that node, and new queuelist.
   * Otherwise, makes a new node, then call itself with that new node, and
   * the new queuelist.
   *
   *
   *
   * @param t               The current tree node into which the file is
   *                        being inserted
   * @param filePathQueue   A queue of strings, representing the
   *                        relative path of the file from the current working
   *                        directory (CWD). For example, the file
   *                        "foo/bar/myFile" would be represented in 
   *                        the queue as: 
   *                        IN->["myFile","bar","foo","CWD"]->OUT
   */
  public void insertPath(Tree<FileNode> t, QueueList<String> filePathQueue) throws InvalidOperationException {

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // Implement this method. Hint: You will need to use
    // recursion, at each step dequeuing the next path and then
    // searching to see if the directory already exists.
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //
    //Initiates string
    String dequed_node;
    try {
      dequed_node = filePathQueue.dequeue();
    } catch (InvalidOperationException e) {
      //Means path is finished
      return;
    }
    int size = filePathQueue.size();
    //Create new node with the label from the dequeued string
    FileNode dqNode = new FileNode(dequed_node, true);
    Tree<FileNode> cwd = t.findChild(dqNode);
    //If it matches, recursive call on child, and queue
    if (cwd != null) {
      insertPath(cwd, filePathQueue);
    } else {
      //If queue is empty, make new file node and insert
      if (size == 0) {
        Tree <FileNode> f = new Tree<FileNode>  (new FileNode(dequed_node, false));
        t.addChild(f);
      }
      else {
        //Means queue isn't empty, so make new folder node, and recursively call it
        //on itself.
        Tree <FileNode> f = new Tree<FileNode>  (new FileNode(dequed_node, true));
        t.addChild(f);
        insertPath(f, filePathQueue);
      }
    }
  }

  public void display() {
    root.displayXML();
  }

  /**
   * Constructor. You do not need to edit or understand this method.
   *
   * @param dirName The name of the directory to examine.
   */
  public FileSystem(String dirName) throws Exception {
    Loader L = new Loader();
    try {
      root = new Tree<FileNode>(new FileNode("CWD",true));
      HashMap<String,File> hm = L.load(dirName);
      for (String k : hm.keySet()) {
        String[] path = k.split("[\\/]");
        if(false) System.out.println("path: "+k);
        QueueList<String> q = new QueueList<String>();
        for (String p : path) { q.enqueue(p.equals("")?"CWD":p); }
        q.dequeue();
        insertPath(root,q);
      }
    } catch (Exception e) {
      System.out.println("Error loading directory "+dirName);
      throw e;
    }
  }
}
