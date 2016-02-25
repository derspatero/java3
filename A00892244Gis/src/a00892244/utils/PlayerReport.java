/**
 * Project: A00892244Gis
 * File: PlayerReport.java
 * Date: Feb 16, 2016
 * Time: 10:08:17 AM
 */

package a00892244.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerReport {
	ArrayList<Player> players;

	public PlayerReport() {

	}

	/**
	 * 
	 * @param players
	 */
	public PlayerReport(Map<Integer, Player> players) {
		this.players = new ArrayList<Player>(players.values());
	}

	/**
	 * 
	 * @return
	 */
	public String getReport() {
		StringBuilder report = new StringBuilder();
		report.append("\n----------------------------------------------------------------------------------------------\n");
		report.append("Player ID  Full name            Email                     Age  Total games played   Total Wins\n");
		report.append("----------------------------------------------------------------------------------------------\n");

		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			report.append(String.format("        %-2s %-20s %-26s%-22s%-12s %s \n", player.getIdentifier(), player.getFirstName() + " " + player.getLastName(),
					player.getEmailAddress(), player.getAge(), player.getTotalGamesPlayed(), player.getTotalWins()));

		}
		report.append("----------------------------------------------------------------------------------------------\n");

		return report.toString();
	}

}
