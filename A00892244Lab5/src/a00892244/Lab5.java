/**
 * Project: A00892244Lab5
 * File: Lab5.java
 * Date: Feb 16, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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

	private static final Logger LOG = LogManager.getLogger(Lab5.class);

	/**
	 * @param
	 * 
	 */
	public static void main(String[] args) {

		try {
			LOG.info("Starting program");
			LOG.info("program arguments: " + Arrays.toString(args));

			LocalDateTime startDate = LocalDateTime.now();

			List<Player> players = new ArrayList<Player>();
			PlayerReport playerReport = new PlayerReport();
			PlayerReader playerReader;

			if (args.length == 0) {
				LOG.info("Reading from players.txt");
				playerReader = new PlayerReader("players.txt");
			} else {
				LOG.info("Reading from " + args[0]);
				playerReader = new PlayerReader(args[0]);
			}

			while (playerReader.morePlayers()) {
				players.add(playerReader.getNextPlayer());
			}

			LOG.info("Sort Players By Birthdate Descending");
			players = CompareByBirthdate.sortByBirthdateDescending(players);

			if (args.length < 2) {
				LOG.info("Writing player report to players_report.txt");
				playerReport.writeReport(players, "\n\nSort by Birthdate (Descending)", startDate, "players_report.txt");
			} else {
				LOG.info("Writing player report to " + args[1]);
				playerReport.writeReport(players, "\n\nSort by Birthdate (Descending)", startDate, args[1]);
			}

			LocalDateTime endDate = LocalDateTime.now();
			long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
			LOG.info("Duration: " + timeDelta + " ms\n");

		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Terminating Program");
			System.exit(-1);
		}

	}

}
