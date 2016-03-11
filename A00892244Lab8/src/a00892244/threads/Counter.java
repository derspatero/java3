/**
 * Project: A00892244Lab8
 * File: Thead2.java
 * Date: Mar 9, 2016
 * Time: 10:23:02 AM
 */

package a00892244.threads;

import a00892244.Lab8;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Counter implements Runnable {

	public Counter() {

	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public synchronized void run() {

		synchronized (Lab8.lockObj) {
			while (true) {
				try {
					if (!Lab8.timeIsPrinted) {
						Lab8.lockObj.notifyAll();
						Lab8.lockObj.wait();
					}
				} catch (InterruptedException e) {
				}

				for (Integer x = 1; x <= 100; x++) {
					System.out.format("%s ", x);
					if (Math.floorMod(x, 10) == 0) {
						System.out.format("\n");
					}
				}
				Lab8.lockObj.notifyAll();
				break;
			}
		}
	}
}
