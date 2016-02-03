/**
 * Project: A00892244Lab4
 * File: Lab3.java
 * Date: Feb 2, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import a00892244.data.Player;
import a00892244.utils.ApplicationException;
import a00892244.utils.CompareByBirthdate;
import a00892244.utils.PlayerReader;
import a00892244.utils.PlayerReport;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab4 {

	/**
	 * @param count
	 *            must be an integer
	 */
	public static void main(String[] input) {

		try {
			
			LocalDateTime startDate = LocalDateTime.now();
			System.out.println(startDate);

			List<Player> players = new ArrayList<Player>();
			PlayerReport playerReport = new PlayerReport();
			PlayerReader playerReader = new PlayerReader(input);

			while (playerReader.morePlayers()) {
				players.add(playerReader.getNextPlayer());
			}
			
			players = CompareByBirthdate.sortByBirthdate(players);

			playerReport.printReport(players);
			
			LocalDateTime endDate = LocalDateTime.now();
			int timeDelta = endDate.getNano() / 1000000 - startDate.getNano() / 1000000;
			System.out.println(endDate);
			System.out.format("Duration: %s ms\n", timeDelta);

		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

	}

}
