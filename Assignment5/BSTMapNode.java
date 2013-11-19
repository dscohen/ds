// ***********************************************************************
//
// The BSTMap node class.  YOU NEED TO IMPLEMENT THIS CLASS
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************


public class BSTMapNode<K extends Comparable<K>,V> {

  //use inorder sort to print in order
  //Implement this class
  //Don't forget to override toString()
  protected K key;
  protected V value;
  protected BSTMapNode<K, V> right;
  protected BSTMapNode<K, V> parent;
  protected BSTMapNode<K, V> left;

  protected BSTMapNode(K k, V v, BSTMapNode<K, V> l, BSTMapNode<K, V> r, BSTMapNode<K, V> p)
  { key = k; value = v; left = l; right = r; parent = p;}

  protected boolean isLeaf() {return (left == null) && (right == null);} 

  protected boolean hasOneChild(){
    return (left==null && right != null)
      || (left!=null && right == null);
  }
  protected boolean isRoot(){return parent == null;}



  @Override public String toString() {
    String build = "";
    build = "\""+key.toString() +"\" \"" + value.toString()+"\"";
    return build;
  }


}
