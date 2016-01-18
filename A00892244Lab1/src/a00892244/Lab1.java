/**
 * Project: A00892244Lab1
 * File: Lab1.java
 * Date: Jan 12, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import java.util.Arrays;

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab1 {

	/**
	 * @param count
	 *            must be an integer
	 */
	public static void main(String[] count) {

		try {
			Player[] players = new Player[Integer.parseInt(count[0])];

			for (int i = 0; i < players.length; i++) {
				players[i] = new Player("player" + i, "Edward", "Lambke", "fakeemail@asdf.com", "r@ge_qu1tr_" + i);
			}

			System.out.println(Arrays.toString(players));

		} catch (Exception e) {
			System.out.println("An integer value must be passed to the program.");
			System.exit(0);
		}

	}

}
