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
// test that nodes reachable = total number of nodes

import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;
import java.lang.Math;

public class SkiplistMap<K extends Comparable<K>,V> implements SortedMap<K,V> {
  public class SkipListMapIterator<K extends Comparable<K>> implements Iterator<K> {
    SkiplistMapNode<K,V> node;
    K temp;
    int edition = version;
    SkiplistMap<K,V> map;
    /**
     * Internal Constructor for iterator.
     */
    SkipListMapIterator(SkiplistMap<K,V> maps){
      SkiplistMapNode<K,V> start = maps.head;
      node = start.flinks.get(0);
      int edition = version;
    }


    /**
     * Gets the next key of the map in order.
     * <p>
     * end.
     */
    public boolean hasNext(){
      //fix version number
      if (edition != version) {throw new RuntimeException("Iterator has invalid version");}
      return (node.key) != null;
    }
    public void remove() 
    {
     if (edition != version) {throw new RuntimeException("Iterator has invalid version");}
      try {
      map.remove(temp);
      edition = -1;
      } catch (Exception e) {}

    }
    public K next() throws NoSuchElementException {
      if (!hasNext()) throw new NoSuchElementException();

      temp = node.key;
      node = node.flinks.get(0);
      return temp;
    }
  }

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
  /**
   * Creates iterator for skiplist.
   * @return iterator for skiplist.
   */
  public SkipListMapIterator<K> iterator() {
    SkipListMapIterator<K> it = new SkipListMapIterator<K>(this);
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
    version++;
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
   * throws error is key is null or returns null if it doesn't exist.
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

  /**
   * Takes key and removes node if it exists.
   *
   * <p>
   * throws error if key is null, returns true if remove was succesful, false if it doesn't exist.
   *
   * @param key key of value you want to remove
   * @return true or false if whether operation was succesful.
   */

  public boolean remove(K key) throws SortedMapException {
    if (key == null) {throw new SortedMapException("NO NULL!");}
    SkiplistMapNode<K,V> N = find(key);
    if (N.key == null || key.compareTo(N.key) != 0) {return false;}
    version++;
    for (int i = 0; i < N.flinks.size(); i++) {
      //Put pointers over removed node on each level
      SkiplistMapNode<K,V> prevN = N.plinks.get(i);
      SkiplistMapNode<K,V> nextN = N.flinks.get(i);
      prevN.flinks.set(i, nextN);
      nextN.plinks.set(i, prevN);
    }
    return true;

  }
  /**
   * Prints out list in correct format.
   * @return String of list.
   */
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
  /**
   * Calculates step - reachable nodes.
   * @return SkiplistMap Integer,Integer of the above pair.
   */

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

    return stats;




  }


}







//Don't forget to override toString()


