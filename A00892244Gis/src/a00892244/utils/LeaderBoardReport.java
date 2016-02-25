/**
 * Project: A00892244Gis
 * File: ReportGenerator.java
 * Date: Feb 24, 2016
 * Time: 1:31:56 PM
 */

package a00892244.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class LeaderBoardReport {
	private List<Player> players;
	private List<LeaderBoardReportEntry> reportLines;

	/**
	 * 
	 * @param players
	 */
	public LeaderBoardReport(Map<Integer, Player> players) {
		this.players = new ArrayList<Player>(players.values());
		reportLines = new ArrayList<LeaderBoardReportEntry>();
		generateReport();
	}

	/**
	 * 
	 */
	private void generateReport() {
		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			for (int personaKey : player.getPersonas().keySet()) {
				Persona persona = player.getPersona(personaKey);
				for (String gameKey : persona.getGames().keySet()) {
					Game game = persona.getGames().get(gameKey);
					reportLines.add(new LeaderBoardReportEntry(game.getWinLossRatio(), game.getName(), persona.getGamerTag(), persona.getPlatform()));
				}

			}
		}
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
	 */
	public void sortByGame() {
		Collections.sort(reportLines, new Comparator<LeaderBoardReportEntry>() {
			@Override
			public int compare(LeaderBoardReportEntry o1, LeaderBoardReportEntry o2) {
				return o1.getGameName().compareTo(o2.getGameName());
			}
		});
	}

	/**
	 * 
	 */
	public void sortByCount() {
		Collections.sort(reportLines, new Comparator<LeaderBoardReportEntry>() {
			@Override
			public int compare(LeaderBoardReportEntry o1, LeaderBoardReportEntry o2) {
				return o1.getWinLoss().compareTo(o2.getWinLoss());
			}
		});
	}

	/**
	 * 
	 */
	public void desc() {
		Collections.reverse(reportLines);
	}

	/**
	 * 
	 * @param platform
	 */
	public void filterByPlatform(String platform) {
		Iterator<LeaderBoardReportEntry> iterator = reportLines.iterator();
		while (iterator.hasNext()) {

			LeaderBoardReportEntry line = iterator.next();
			if (!line.getPlatform().equals(platform)) {
				iterator.remove();
			}
		}

	}
}
