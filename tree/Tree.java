/**
 * A basic generic tree
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
 * @author      Ernie Davis <davise@cs.nyu.edu> and Eric Koskinen <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-01-01
 */

import java.io.File;

public class Tree<T extends TreeNode> {
  private T label;
  private Tree<T> parent;
  /** 
   * next node on the list of parent's children
   */
  private Tree<T> nextSibling; 
  /**
   * first in the linked list of children
   */
  private Tree<T> firstChild;

  public Tree(T l) throws InvalidOperationException {
    if(l == null)
      throw new InvalidOperationException("invalid label");
    label = l; parent = null; nextSibling = null; firstChild = null; 
  }
  public Tree() {
    System.out.println("Tree: warning -- created a tree with no label.");
    label = null; parent = null; nextSibling = null; firstChild = null; 
  }

  /**
   * getters and setters
   */
  public T getLabel() { return label; }  
  public void setLabel(T v) { label = v; }
  public Tree<T> getParent() { return parent;}
  public Tree<T> getNextSibling() { return nextSibling;}
  public Tree<T> getFirstChild() { return firstChild;}

  /**
   * findChild: searches through the *immediate* children of the tree
   * to see if there is a subtree N whose label is equal to {@link otherLabel}.
   * <p>
   * Will accept null label, and will return null. Will return null
   * if FileNode has a label of null, as .getLabel will throw an exception
   * otherwise.
   *
   * @param otherLabel The label of the node that you are trying to find.
   * @return the Tree<T> node of the matched node, otherwise will return null.
   */
  public Tree<T> findChild(T otherLabel) {
    //first checks if label is null
    if (otherLabel == null) {return null;}
    Tree<T> child = this.getFirstChild();
    //cycle through children to see if they match.
    while (child != null) {
      //reason for try explained in javadoc
      try {
        if (child.getLabel().equals(otherLabel)) {return child;}
      } catch (NullPointerException e) {return null;}
      child = child.getNextSibling();
    }
    return null;
  }






  /**
   * Add c to the front of the children of this. The Tree c should
   * not be null, nor have a nextSibling.  It may, however, have a
   * firstChild. The parent of c will be overwritten. Will throw
   * invalidoperationexception
   *
   * @param c The TreeT node you wish to add
   */
  public void addChild(Tree<T> c) throws InvalidOperationException {
    if(c == null || c.nextSibling != null) 
      throw new InvalidOperationException("invalid child");

    c.parent = this;
    c.nextSibling = null;
    if (firstChild == null) 
      firstChild = c;
    else {
      c.nextSibling = firstChild;
      firstChild = c;
    }
  }
  /**
   * Check if the node is a leaf
   */
  public boolean Leaf() { return firstChild==null; }

  /**
   * Convert the tree into a string. The structure is indicated by
   * square brackets. Uses a preorder listing.
   */
  public String toString() {
    String S = "[ " + label.toString();
    Tree<T> N = firstChild;
    while (N != null) {
      S = S + " " + N.toString();
      N = N.nextSibling;
    }
    return S+" ]";
  }
  /**
   * displayXML: displays the tree in XML format. 
   * <p>
   * Does not accept any arguments and will print out the tree in
   * xml format. If label is null,
   */
  public void displayXML() {

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // Implement this method.  Hint: You will need to do a
    // combination of pre-Order and post-Order. That is, at each
    // level you will need to call the label's "preString()"
    // method before any recursive calls and then call the
    // label's "postString()" method afterward. preString() and
    // postString() have already been implemented for you in
    // FileNode.java.
    //
    // IMPORTANT: make sure you use indenting, increasing the
    // indent by 3 at each new level.
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // Hint: Begin with the following:




    System.out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    XML(0);

  }


  //No javadoc for this as it is a private function that
  //should never be used outside of displayXML()

  private void XML(int indent) {
    //prints out the required indent
    for (int i = 0; i < indent; i++) {
      System.out.print(" ");
    }
    //if it's a leaf, prints out prestring and post on its own line
    //otherwise prints prestring on its own line
    if (this.Leaf()) {
      System.out.printf("%s", label.preString());
    } else {
      System.out.printf("%s\n", label.preString());
    }
    Tree<T> N = firstChild;
    //if node has children, recursively calls this function
    //on them and their siblings.
    while (N != null) {
      N.XML(indent+3);
      N = N.nextSibling;
    }
    //Again, required indentation
    if (!this.Leaf()) {
      for (int i = 0; i < indent; i++) {
        System.out.print(" ");
      }
    }
    //And postorder visiting with postString.
    System.out.printf("%s\n", label.postString());
  }


}

