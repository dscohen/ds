// ***********************************************************************
//
// SkiplistMap -- Implements a sorted map using a skiplist
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;
import java.lang.Math;

public class SkiplistMap<K extends Comparable<K>,V> implements SortedMap<K,V> {
  class SkipListMapIterator<K extends Comparable<K>> implements Iterator<K> {
    SkiplistMapNode<K,V> node;
    int edition;
    SkipListMapIterator(SkiplistMapNode<K,V> start){
      node = start.flinks.get(0);
      int edition = version;
    }
    

    public boolean hasNext(){
      return node.key != null && edition == version;
    }
    public void remove() 
    {
    }
    public K next() throws NoSuchElementException {
      //Every next() function should start with this line
      if (!hasNext()) throw new NoSuchElementException();

      K temp = node.key;
      node = node.flinks.get(0);
      return temp;
    }
  }

  //Make iterators private class!!!!!!!!!!!!!
  //version number
  private int version = 0;
  private int steps = 0;
  private int max_val = 33;
  private ArrayList<SkiplistMapNode<K,V>> list;
  private int size = 0;
  private SkiplistMapNode<K,V> head;
  private SkiplistMapNode<K,V> tail;


  /**
   * Initiates new SkiplistMap.
   *
   * <p>
   * Creates a skiplist with two infinite start and end towers, and links them.
   */

  public SkiplistMap() {
    head = new SkiplistMapNode<K,V>(max_val, null, null);
    tail = new SkiplistMapNode<K,V>(max_val, null, null);
    list = new ArrayList<SkiplistMapNode<K,V>>();
    list.add(head);
    list.add(tail);
    for (int i = 0; i < head.flinks.size(); i++) {
      head.flinks.set(i, tail);
      tail.plinks.set(i, head);
    }
  }
  public SkipListMapIterator<K> iterator() {
    SkipListMapIterator<K> it = new SkipListMapIterator<K>(head);
    return it;

  }
  /**
   * Finds node in skip list.
   *
   * <p>
   * This method will return a SkipList Node, and keeps track of the number
   * of steps needed to find it. If it does not exists, or is null, throws
   * error.
   *
   * @param toFind the key you wish to find
   * @return       the node that has been found
   */

  private SkiplistMapNode<K,V> find(K toFind) throws SortedMapException{
    steps = 0;
    if (toFind == null) {throw new SortedMapException ("No null");}
    int level = max_val-1;
    SkiplistMapNode<K,V> N = head;
    //cycle through same level
    while (level != -1) {
      //see if Current Node is equal
        if (N.flinks.get(level).key == null) {
          level--;
          continue;
        }
        steps++;
        int ct = toFind.compareTo(N.flinks.get(level).key);
        if (ct == 0) {
          return N.flinks.get(level);}
        if (ct > 0) {
          //means find key > node.key
          N = N.flinks.get(level);
          //go to next node
        } else {
          level--;
        }
      }
    return N;
  }

  /**
   * Places new node into current skip list in order.
   *
   * <p>
   * Height is determined by 1/2^n. If key exists, update value. Throws
   * error is key or value is null.
   *
   * @param key key of node.
   * @param value value of node.
   */


  public void put(K key, V value) throws SortedMapException{
    if (key == null || value == null) {throw new SortedMapException("No null");}
    SkiplistMapNode<K,V> N = find(key);
    if (N.key != null) {
      if(N.key.compareTo(key) == 0) {N.value = value; return;}
    }

    double flip = Math.random();
    int height;
    for ( height = 0; flip < 0.5; height++) {
      flip = Math.random();
    }
    SkiplistMapNode<K,V> insert = new SkiplistMapNode<K,V>(height, key, value);
    for (int i = 0; i < height+1; i++) {
      while (i > N.flinks.size()-1) {
        N = N.plinks.get(i-1);
      }
      insert.flinks.set(i,N.flinks.get(i));
      insert.plinks.set(i, N);
      insert.flinks.get(i).plinks.set(i, insert);
      N.flinks.set(i, insert); 
    }
  }

  /**
   * Returns value of associated key.
   * <p>
   * throws error is key is null or if it doesn't exist.
   *
   * @param key key of node you want to find.
   * @return value of key.
   */
  public V get(K key) throws SortedMapException {
    if (key == null) {throw new SortedMapException ("No Null");}
    SkiplistMapNode<K,V> N = find(key);
    if (N.key != null && key.compareTo(N.key) == 0) {return N.value;}
    return null;
  }

  public boolean remove(K key) throws SortedMapException {
    if (key == null) {throw new SortedMapException("NO NULL!");}
    SkiplistMapNode<K,V> N = find(key);
    if (N.key == null || key.compareTo(N.key) != 0) {return false;}
    for (int i = 0; i < N.flinks.size(); i++) {
      //Put pointers over removed node on each level
      SkiplistMapNode<K,V> prevN = N.plinks.get(i);
      SkiplistMapNode<K,V> nextN = N.flinks.get(i);
      prevN.flinks.set(i, nextN);
      nextN.plinks.set(i, prevN);
    }
    return true;

  }
  @Override public String toString() {
    this.iterator();
    String build = "";
    for (K key : this) {
      try {
      SkiplistMapNode<K,V> N = find(key);
      build += N.toString() + "\n";
      } catch (SortedMapException e) {}
    }
    return build;
  }

  public SkiplistMap<Integer, Integer> calculateStats() {
    SkiplistMap<Integer,Integer> stats = new SkiplistMap<Integer,Integer>();
    this.iterator();
    for (K ke : this) {
      try {
        SkiplistMapNode<K,V> N = this.find(ke);
        SkiplistMapNode<Integer, Integer> Z = stats.find(steps);
        if (Z.key != null && Z.key.compareTo(steps) == 0) {
          Z.value += 1;}
        else {
          stats.put(steps, 1);}
      } catch (Exception e) {System.out.println(e);}
    }
    
    Deque<SkiplistMapNode<K,V>> stack = new LinkedList<SkiplistMapNode<K,V>>();
    //int depth = 1;
    //if (root ==null){
      //try {
      //stats.put(0,0); return stats;
      //} catch (SortedMapException e) {}}
    //Skipl<K,V> Noderoot;
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
        //Skipl<IntegNodeInteger> adding_node = stats.find(depth);
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


