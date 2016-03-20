/**
 * Project: A00892244Gis
 * File: ReportGenerator.java
 * Date: Feb 24, 2016
 * Time: 1:31:56 PM
 */

package a00892244.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.database.Database;
import a00892244.database.LeaderReportDao;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class LeaderBoardReport {
	private List<Player> players;
	private List<LeaderBoardReportEntry> reportLines;
	private LeaderReportDao reportDao;

	/**
	 * 
	 * @param players
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public LeaderBoardReport(Database database, List<String> arguments) throws SQLException, Exception {
		reportLines = new ArrayList<LeaderBoardReportEntry>();
		reportDao = new LeaderReportDao(database);
		generateReport(arguments);
	}

	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 */

	private void generateReport(List<String> arguments) throws SQLException, Exception {
		reportLines = reportDao.getLeaderBoardReportEntriesByQuery(arguments);
	}

	/**
	 * 
	 * @return
	 */
	private String getGameTotals() {
		StringBuilder report = new StringBuilder();
		Iterator<LeaderBoardReportEntry> iterator = reportLines.iterator();
		Map<String, Integer> games = new HashMap<String, Integer>();
		while (iterator.hasNext()) {
			LeaderBoardReportEntry line = iterator.next();
			if (games.containsKey(line.getGameName())) {
				int total = games.get(line.getGameName());
				total += Integer.parseInt(line.getWinLoss().split(":")[0]) + Integer.parseInt(line.getWinLoss().split(":")[1]);
				games.put(line.getGameName(), (Integer) total++);
			} else {
				games.put(line.getGameName(), Integer.parseInt(line.getWinLoss().split(":")[0]) + Integer.parseInt(line.getWinLoss().split(":")[1]));
			}
		}
		for (String key : games.keySet()) {
			report.append(String.format("%-20s%s\n", key, games.get(key)));
		}
		report.append("----------------------------------------------------------\n");
		return report.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getReport() {
		StringBuilder report = new StringBuilder();
		report.append("\n----------------------------------------------------------\n");
		report.append("Win:Loss   Game Name            Gamertag        Platform\n");
		report.append("----------------------------------------------------------\n");
		Iterator<LeaderBoardReportEntry> iterator = reportLines.iterator();
		while (iterator.hasNext()) {

			LeaderBoardReportEntry line = iterator.next();

			report.append(String.format("%-10s %-20s %-20s%-30s\n", line.getWinLoss(), line.getGameName(), line.getGamerTag(), line.getPlatform()));

		}
		report.append("----------------------------------------------------------\n");
		return report.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getReportWithTotal() {
		return getReport() + getGameTotals();
	}

}
