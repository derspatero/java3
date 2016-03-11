/**
 * Project: A00892244Lab8
 * File: Lab8.java
 * Date: Mar 8, 2016
 * Time: 4:46:11 PM
 */

package a00892244;

import a00892244.threads.Counter;
import a00892244.threads.Thread1;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab8 {
	public static Object lockObj = new Object();
	public static boolean timeIsPrinted = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Thread1 thread1 = new Thread1();
		Thread thread2 = new Thread(new Counter());

		thread1.start();
		thread2.start();

	}

}
