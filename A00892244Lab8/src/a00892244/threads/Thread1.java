/**
 * Project: A00892244Lab8
 * File: Thread1.java
 * Date: Mar 9, 2016
 * Time: 10:22:19 AM
 */

package a00892244.threads;

import java.time.LocalDateTime;

import a00892244.Lab8;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Thread1 extends Thread {

	public Thread1() {

	}

	public void run() {

		synchronized (Lab8.lockObj) {
			while (true) {
				try {
					System.out.format("=== Start: %s ===\n", LocalDateTime.now());
					Lab8.isPrinted = true;
					Lab8.lockObj.notifyAll();
					Lab8.lockObj.wait();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.format("=== End: %s ===\n", LocalDateTime.now());
				break;
			}

		}
	}
}
