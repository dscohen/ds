// ***********************************************************************
//
// Test3 -- An example test : confirms that the set is initially of size 0
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

public class Test3 extends TestHarness {

    public Test3(String s) { super(s); }

    public boolean test() { 

	try {
      boolean result = true;
	SortedMap<String, Integer> m = new SkiplistMap<String,Integer>
	    ();
      //testing correct performance of basic functions
      //edge cases for skip and BST...test is repeated for skip, then BST
      if (m.remove("b") != false) {result = false;}
      if (m.get("b") != null) {result = false;}
      m.put("b", 2);
      if(m.get("b") != 2) {result = false;}
      if (m.remove("b") != true) {result = false;}
      if (m.get("b") != null) {result = false;}
      try {
      m.put(null, null);
      //test null case, will work if it handles null without crashing
      int f = m.get(null);
      } catch (SortedMapException e) {}
      m.put("a", 4);
      m.put("c", 5);
      m.put("d", 6);
      m.put("d", 7);
      m.put("x", 8);
      m.put("d", 9);
      m.put("s", 0);
      m.put("v", 12);
      m.put("p", 22);
      m.put("z", 32);
      m.put("f", 42);
      //testing correct order, uses iterator and to string to do so.
      String answerKey = "acdfpsvxz";
      String test = "";
      m.iterator();
      for (String i : m) {
        System.out.println(i);
        test += i;
      }
      if (test.compareTo(answerKey) != 0) {result = false;}
      //stats accuracy
      SortedMap<Integer, Integer> stats = m.calculateStats(); 
      int totalNodes = 0;
      for (Integer i : stats) {
        totalNodes += stats.get(i);
      }
      if (totalNodes != 9) {result = false;}
      //BST time

	    m = new BSTMap<String,Integer>();
      //testing correct performance of basic functions
      //edge cases for skip and BST...test is repeated for skip, then BST
      if (m.remove("b") != false) {result = false;}
      System.out.println(result);
      if (m.get("b") != null) {result = false;}
      m.put("b", 2);
      if(m.get("b") != 2) {result = false;}
      if (m.remove("b") != true) {result = false;}
      if (m.get("b") != null) {result = false;}

      try {
      m.put(null, null);
      //test null case, will work if it handles null without crashing
      int f = m.get(null);
      } catch (SortedMapException e) {}
      m.put("a", 4);
      m.put("c", 5);
      m.put("d", 6);
      m.put("d", 7);
      m.put("x", 8);
      m.put("d", 9);
      m.put("s", 0);
      m.put("v", 12);
      m.put("p", 22);
      m.put("z", 32);
      m.put("f", 42);
      //testing correct order, uses iterator and to string to do so.
       answerKey = "acdfpsvxz";
      test = "";
      m.iterator();
      for (String i : m) {
        System.out.print(m.get(i));
        System.out.println(i);
        test += i;
      }
      if (test.compareTo(answerKey) != 0) {result = false;}

      //calculate stats accurateness
      stats = m.calculateStats(); 
      totalNodes = 0;
      for (Integer i : stats) {
        totalNodes += stats.get(i);
      }
      if (totalNodes != 9) {result = false;}

      

	    return result;
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
