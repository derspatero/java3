/**
 * Project: A00892244Lab5
 * File: Lab5.java
 * Date: Feb 16, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

public class Lab5 {

	/**
	 * @param count
	 *            must be an integer
	 */
	public static void main(String[] args) {

		try {

			LocalDateTime startDate = LocalDateTime.now();
			System.out.println(startDate);

			List<Player> players = new ArrayList<Player>();
			PlayerReport playerReport = new PlayerReport();
			PlayerReader playerReader;

			if (args.length == 0) {
				playerReader = new PlayerReader("players.txt");
			} else {
				playerReader = new PlayerReader(args[0]);
			}

			while (playerReader.morePlayers()) {
				players.add(playerReader.getNextPlayer());
			}

			players = CompareByBirthdate.sortByBirthdateAscending(players);
			System.out.println("Sort by Birthdate (Ascending)");
			playerReport.printReport(players);

			players = CompareByBirthdate.sortByBirthdateDescending(players);
			playerReport.writeReport(players, "\n\nSort by Birthdate (Descending)", startDate);

			LocalDateTime endDate = LocalDateTime.now();
			long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
			System.out.println(endDate);
			System.out.format("Duration: %s ms\n", timeDelta);

		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

	}

}
