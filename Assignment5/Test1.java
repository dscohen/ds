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
	    //Check what happens if we try to get a value from an empty set
      m.put("aedvark", 4);
      m.put("aqdvark", 2);
      m.put("ajdvark", 5);
      m.put("ardvark", 6);
      m.put("aydvark", 7);
      m.put("audvark", 8);
      m.put("afdvark", 9);
      m.put("addvark", 0);
      m.put("addvark", 12);
      m.put("asdvark", 22);
      m.put("axdvark", 32);
      m.put("ardvark", 42);
      m.remove("aydvark");
      m.iterator();
        System.out.println("fool!");
      for (String i : m) {
        System.out.println(i);
      }
      System.out.println(m.get("aqdvark"));
      System.out.println(m.calculateStats());

	    //The result should be null
	    return (true);
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
