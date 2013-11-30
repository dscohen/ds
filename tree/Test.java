/**
 * Test Class -- The main function that registers and executes tests.
 * <p>
 * Register your new tests in this file by extending {@link Test#main(String[])}
 * <p>
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-09-01
 */

public class Test {

	public static void main(String [] args) {
		Test1 t1 = new Test1("t1");
		Test2 t2 = new Test2("t2");
		Test3 t3 = new Test3("t3");

		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// %%%%% Register your new tests here, by extending this method %%%%%
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


		TestHarness.run();
	}
}
