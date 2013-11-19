
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

public class Test2 extends TestHarness {

    public Test2(String s) { super(s); }

    public boolean test() { 
	SortedMap<String, Integer> m = new BSTMap<String,Integer>
	    ();

	try {
	    //Check what happens if we try to get a value from an empty set
      m.put("addvark", 12);
      m.put("aqdvark", 2);
      m.put("aedvark", 4);
      m.put("ajdvark", 5);
      m.put("ardvark", 6);
      m.put("aydvark", 7);
      m.put("audvark", 8);
      m.put("afdvark", 9);
      m.put("addvark", 12);
      m.put("asdvark", 22);
      m.remove("aqdvark");
      m.put("axdvark", 32);
      m.put("addvark", 22);
      m.put("ardvark", 42);
      m.iterator();
      for (String i : m) {
        System.out.println(i);
      }
      System.out.println(m.get("addvark"));
      System.out.println(m.calculateStats());

	    //The result should be null
	    return (true);
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
