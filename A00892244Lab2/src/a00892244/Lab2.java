/**
 * Project: A00892244Lab1
 * File: Lab1.java
 * Date: Jan 12, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import java.util.ArrayList;

import a00892244.data.Player;
import a00892244.utils.PlayerReader;
import a00892244.utils.PlayerReport;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab2 {

	/**
	 * @param count
	 *            must be an integer
	 */
	public static void main(String[] input) {

		try {

			ArrayList<Player> players = new ArrayList<Player>();
			PlayerReport playerReport = new PlayerReport();
			PlayerReader playerReader = new PlayerReader(input);

			while (playerReader.morePlayers()) {
				players.add(playerReader.getNextPlayer());
			}

			playerReport.printReport(players);

		} catch (Exception e) {
			System.out.println("Usage java -jar A00123456Lab2.jar <input string>");
			System.out.println(e.getMessage());
			System.exit(-1);
		}

	}

}
