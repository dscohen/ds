// ***********************************************************************
//
// The SkiplistMap node class.  YOU NEED TO IMPLEMENT THIS CLASS
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

import java.util.ArrayList;
import java.util.Collections;

public class SkiplistMapNode<K extends Comparable<K>,V> {
  protected K key;
  protected V value;
  protected ArrayList<SkiplistMapNode<K,V>> flinks;
  protected ArrayList<SkiplistMapNode<K,V>> plinks;

  public SkiplistMapNode(int ht, K k, V v){
  key = k; value = v;
  flinks = new ArrayList<SkiplistMapNode<K,V>>(Collections.nCopies(ht+1,this));
  plinks = new ArrayList<SkiplistMapNode<K,V>>(Collections.nCopies(ht+1,this));
  }

 @Override public String toString() {
   String build = "";
    build = "\""+key.toString() +"\" \"" + value.toString()+"\"";
    return build;
 }



    //Implement this class
    //Don't forget to override toString()

}
