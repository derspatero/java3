/**
 * Project: A00892244Gis
 * File: PlayerReport.java
 * Date: Feb 16, 2016
 * Time: 10:08:17 AM
 */

package a00892244.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerReport {
	ArrayList<Player> players;

	/**
	 *  
	 * 
	 */
	public PlayerReport(Map<Integer, Player> players) {
		this.players = new ArrayList<Player>(players.values());
	}

	/**
	 * 
	 * @param players
	 *            ArrayList of Players will be formated and displayed
	 */

	public void printReport() {

		System.out.println("Players Report");
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("Player ID  Full name            Email                     Age  Total games played   Total Wins");
		System.out.println("----------------------------------------------------------------------------------------------");

		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			System.out.format("        %-2s %-20s %-26s%-22s%-12s %s \n", player.getIdentifier(), player.getFirstName() + " " + player.getLastName(), player.getEmailAddress(),
					player.getAge(), player.getTotalGamesPlayed(), player.getTotalWins());

		}
		System.out.println("----------------------------------------------------------------------------------------------");

	}

	/**
	 * 
	 * @param players
	 *            ArrayList of Players will be formated and displayed
	 * @param startDate
	 * @param fileName
	 * @param string
	 */

	// @SuppressWarnings("deprecation")
	// public void writeReport(List<Player> players, String title, LocalDateTime startDate, String fileName) throws ApplicationException {
	// try {
	// output = new Formatter(fileName);
	// } catch (FileNotFoundException e) {
	// throw new ApplicationException(e.getMessage());
	// }
	//
	// output.format("%s\n", title);
	//
	// output.format("Players Report\n");
	// output.format("-------------------------------------------------------------------------------------\n");
	// output.format(" #. ID First name Last name Email Birthdate\n");
	// output.format("-------------------------------------------------------------------------------------\n");
	//
	// int count = 0;
	// Iterator<Player> iterator = players.iterator();
	// while (iterator.hasNext()) {
	// Player player = iterator.next();
	// output.format(" %s. %-2s %-16s%-16s%-20s%-16s \n", count, player.getFormatedIdentifier(), player.getFirstName(), player.getLastName(), player.getEmailAddress(),
	// player.getBirthdate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy")));
	// count++;
	// }
	// LocalDateTime endDate = LocalDateTime.now();
	// long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
	// output.format("%s\nDuration: %s ms\n", endDate, timeDelta);
	//
	// if (output != null) {
	// try {
	// output.close();
	// } catch (Exception e) {
	// throw new ApplicationException(e.getMessage());
	// }
	// }
	//
	// }

}
