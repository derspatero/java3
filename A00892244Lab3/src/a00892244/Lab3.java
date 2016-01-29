/**
 * Project: A00892244Lab3
 * File: Lab3.java
 * Date: Jan 27, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import java.util.ArrayList;

import a00892244.data.Player;
import a00892244.utils.ApplicationException;
import a00892244.utils.PlayerReader;
import a00892244.utils.PlayerReport;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab3 {

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

		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

	}

}
