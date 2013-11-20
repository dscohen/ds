// ***********************************************************************
//
// The BSTMap main class.  YOU NEED TO IMPLEMENT THIS CLASS
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.Iterator;
import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements SortedMap<K,V> {
  //////////////////////////////////////////////////////////////////////////
  //
  //////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////
  //
  //////////////////////////////////////////////////////////////////////////
//remove will remove last next()
  class BSTInorderIterator<K extends Comparable<K>> implements Iterator<K> {
    Deque<BSTMapNode<K,V>> stack;
    int edition;
    BSTInorderIterator(BSTMapNode<K,V> root){
      stack = new LinkedList<BSTMapNode<K,V>>();
      addLeftChain(root);
      edition = version;
    }

    public void remove() throws UnsupportedOperationException
    {
      throw new UnsupportedOperationException("Not implemented");
    }

    public boolean hasNext()
    {
      return stack.size() != 0 && edition == version;
    }

    private void addLeftChain(BSTMapNode<K,V> N){
      while(N != null){
        stack.push(N);
        N = N.left;
      }
    }

    public K next() throws NoSuchElementException
    {
      //Visit inorder.
      if (!hasNext()) throw new NoSuchElementException();
      BSTMapNode<K,V> N = stack.pop();
      if(N.right != null) addLeftChain(N.right);
      return N.key;
    }
  }
  //////////////////////////////////////////////////////////////////////////
  //
  //////////////////////////////////////////////////////////////////////////
  protected BSTMapNode<K,V> root;
  protected int version = 0;
  protected int steps;

  public BSTInorderIterator<K> iterator() {
    BSTInorderIterator<K> it = new BSTInorderIterator<K>(root);
    return it;
  }
  /**
   * Creates new BSKMap with first node (root) as null.
   */
  public BSTMap() {
    root = null;
  }
  /**
   * Creates new BSTMap with root as argument.
   * @param r node to be the root
   */
  public BSTMap(BSTMapNode<K,V> r) {
    root = r;
  }

  /**
   * finds node of corresponding key.
   * <p>
   * returns Node, or throws error if it doesn't exist.
   * @param key key of node
   */
  private BSTMapNode<K,V> find(K key) throws SortedMapException {
    steps = 0;
    if (key == null) {throw new SortedMapException ("No Null");}
    if (root == null) {return null;}
    BSTMapNode<K, V> N = root;
    while (N != null){
      steps++;
      int ct = key.compareTo(N.key);
      if (ct == 0) {return N;}
      if (ct > 0) {
        N = N.right;
      } else {
        N = N.left;
      }
    }
    return null;
  }
  /**
   * Gets value of corresponding key.
   * <p>
   * throws error if key is null or if key doesn't exist.
   *
   * @param key key to find value of.
   * @return value of key.
   */

  public V get(K key) throws SortedMapException {
    try {
    BSTMapNode<K,V> N = find(key);
    if (N == null) {return null;}
    return N.value;
    } catch (SortedMapException e) {
      return null;
  }
  }
  /**
   * Removes key from map, and its associated value.
   * <p>
   * throws exception if acting on null map or if key is null.
   * 
   * @param key key to remove.
   * @return true if key exists, false otherwise.
   */

  public boolean remove(K key) throws SortedMapException {
    if (key == null || root == null) {throw new SortedMapException ("No Null");}
    //if key exists, find it, if it throws error, return false.
    BSTMapNode<K,V> N = null;
    try {
      N = find(key);
      if (N == null) {return false;}
    } catch (SortedMapException e) {return false;}
    //if leaf, no dependencies so just delete pointer of parent or make root null
    this.version++;
    if (N.isLeaf()) {
      deleteLeaf(N);
    } else if(N.left == null) {
      //no left dependencies, make right node take it's pointer of parent
      shiftNodeUp(N.right);
    } else if (N.right == null) {
    System.out.println(N);
    System.out.println(N.left);
      //same with right
      shiftNodeUp(N.left);
    } else {
      //if node has two depencies, make rightmost node key and value
      //become current node's key and value
      BSTMapNode<K,V> rightmost = getRightmostDecendant(N.left);
      N.value = rightmost.value;
      N.key = rightmost.key;
      //remove or overwrite rightmost with left descendent.
      if(rightmost.isLeaf()){
        deleteLeaf(rightmost);
      } else {
        shiftNodeUp(rightmost.left);
      }
    }
    return true;
  }


  /**
   * Helper function to delete lead node.
   * @param L Node to remove
   */



  private void deleteLeaf(BSTMapNode<K,V> L) throws SortedMapException {
    if(!L.isLeaf()) throw new SortedMapException("L is not a leaf");
    if (L == root) {root = null; return;}
    if(L == L.parent.left){
      L.parent.left = null;
    } else {
      L.parent.right = null;
    }
  }

  /**
   * Helper function to find farthest right node.
   * @param N node to get rightmost descendent of.
   * @return rightmost node.
   */
  private BSTMapNode<K,V> getRightmostDecendant(BSTMapNode<K,V> N) {
    while (N.right != null) {
      N = N.right;
    }
    return N;
  }

  /**
   * Helper function to keep order upon remove.
   * @param N to shift up
   */
  private void shiftNodeUp(BSTMapNode<K,V> N) throws SortedMapException
  {
    if(N.parent == null) throw new SortedMapException("Can't shift up root node");
    //Move the values and the children
    N.parent.key = N.key;
    N.parent.value = N.value;
    N.parent.left = N.left;
    N.parent.right = N.right;
    //patch up the parents
    //of the subtrees
    if(N.parent.left != null){
      N.parent.left.parent = N.parent;
    }
    if(N.parent.right != null){
      N.parent.right.parent = N.parent;
    }
  }

  /**
   * Inserts node of key,value into map.
   * <p>
   * Will not accept null values, will throw error. Keeps map ordered upon insertion.
   * Will not restructure map if it is a binary tree.
   *
   * @param key key of node you wish to insert.
   * @param value of key(node) you wish to attribute it to.
   */

  public void put(K key, V value) throws SortedMapException {
    if (key == null || value == null) throw new SortedMapException("No Null");
    version++;
    if (root == null) {
      root = new BSTMapNode<K, V>(key, value, null, null, null);
      return;
    }
    BSTMapNode<K, V> N = root;
    while (N != null) {
      int ct = key.compareTo(N.key);
      if (ct == 0) {
        N.value = value;
        version--;
        return;
      }
      if (ct > 0) {
        if (N.right == null) {
          N.right = new BSTMapNode<K, V>(key, value, null, null, N);
          return;
        } else {
          N = N.right;
        }
      } else {
        if (N.left == null) {
          N.left = new BSTMapNode<K, V>(key, value, null, null, N);
          return;
        } else {
          N = N.left;
        }
      }
    }

  }
  /**
   * Returns string of map.
   * <p>
   * format if os "Key" "Value"\n
   *
   * @return string.
   */
  @Override public String toString() {
    this.iterator();
    String build = "";
    for (K key : this) {
      try {
      BSTMapNode<K,V> N = find(key);
      build += N.toString() + "\n";
      } catch (SortedMapException e) {}
    }
    return build;
  }
  /**
   * Returns stats of map where it calculates # of nodes reaches in # of steps.
   * <p>
   * format is of "Steps" "Nodes Reachable"
   * @return Map|Integer,Integer| of above format.
   */

  public BSTMap<Integer, Integer> calculateStats() {
    BSTMap<Integer,Integer> stats = new BSTMap<Integer,Integer>();
    this.iterator();
    for (K ke : this) {
      try {
        BSTMapNode<K,V> N = this.find(ke);
        BSTMapNode<Integer, Integer> Z = stats.find(steps);
        System.out.println(steps);
        if (Z != null) {Z.value += 1;}
        else {
          stats.put(steps, 1);}
      } catch (Exception e) {System.out.println(e);}
    }
    
    Deque<BSTMapNode<K,V>> stack = new LinkedList<BSTMapNode<K,V>>();
    //int depth = 1;
    //if (root ==null){
      //try {
      //stats.put(0,0); return stats;
      //} catch (SortedMapException e) {}}
    //BSTMapNode<K,V> N = root;
    ////add left chain
    //while (N != null){
      //stack.push(N);
      //N = N.left;
      //depth++;
    //}
    //while(stack.size() != 0) {
      //depth--;
      //N = stack.pop();
      //System.out.println(depth + " " + N.toString());
      //try {
        //BSTMapNode<Integer, Integer> adding_node = stats.find(depth);
        //stats.put(depth, adding_node.value + 1);
      //} catch(Exception e) {
        //try {
        //stats.put(depth, 1);
        //} catch (SortedMapException f) {}
      //}
      ////add left chain of right node
      //if (N.right != null){
        //N = N.right;
        //System.out.println(N);
        //while (N != null){
          //depth++;
          //System.out.println(depth +" adding left" +N);
          //stack.push(N);
          //N = N.left;
        //}
      //}
    //}
    //System.out.println(depth);
    return stats;




    //Get left most, then as un pop, get right. Keep track of depth(steps)
  }
  

}







//Don't forget to override toString()


