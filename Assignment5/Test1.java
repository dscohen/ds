// ***********************************************************************
//
// Test1 -- An example test : confirms that the set is initially of size 0
//
// ***********************************************************************
// Computer Science 102: Data Structures
// New York University, Fall 2013,
//
// Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
//
// ***********************************************************************

public class Test1 extends TestHarness {

    public Test1(String s) { super(s); }

    public boolean test() { 
	SortedMap<String, Integer> m = new SkiplistMap<String,Integer>
	    ();

	try {
      boolean result = true;
      //testing correct performance of basic functions
	    //Check what happens if we try to get a value from an empty set
      m.put("b", 2);
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
      m.remove("d");
      String answerKey = "abcfpsvxz";
      String test = "";
      m.iterator();
      for (String i : m) {
        System.out.println(i);
        test += i;
      }
      if (test.compareTo(answerKey) != 0) {result = false;}

	    //The result should be null
	    return result;
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
