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
	SortedMap<Integer, Integer> m = new BSTMap<Integer,Integer>
	    ();

	try {
	    //Check what happens if we try to get a value from an empty set
	    Integer result = m.get(2);
	    //The result should be null
	    return (result == null);
	} catch (Exception e){
	    //if we catch an exception, something went wrong
	    return false;
	}
    }
}
