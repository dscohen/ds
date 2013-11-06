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

  public Tree(T l) {
    label = l; parent = null; nextSibling = null; firstChild = null; 
  }
  public Tree() {
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
   *
   * @param otherLabel The label of the node that you are trying to find.
   */
  public Tree<T> findChild(T otherLabel) {
    Tree<T> child = this.getFirstChild();
    while (child != null) {
      if (child.getLabel().equals(otherLabel)) {return child;}
      child = child.getNextSibling();
    }
    return null;

    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // Implement this method.
    // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




  }

  /**
   * Add C to the front of the children of this
   */
  public void addChild(Tree<T> c) {
    c.parent = this;
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
  public void XML(int indent) {
    for (int i = 0; i < indent; i++) {
      System.out.print(" ");
    }
    if (this.Leaf()) {
    System.out.printf("%s", label.preString());
    } else {
    System.out.printf("%s\n", label.preString());
    }
    Tree<T> N = firstChild;
    while (N != null) {
      N.XML(indent+3);
      N = N.nextSibling;
    }
   if (!this.Leaf()) {
    for (int i = 0; i < indent; i++) {
      System.out.print(" ");
    }
   }
    System.out.printf("%s\n", label.postString());
  }


}
