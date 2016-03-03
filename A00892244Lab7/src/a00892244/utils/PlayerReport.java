/**
 * Project: A00892244Lab7
 * File: PlayerReport.java
 * Date: Feb 16, 2016
 * Time: 10:08:17 AM
 */

package a00892244.utils;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerReport {
	Formatter output = null;

	/**
	 *  
	 * 
	 */
	public PlayerReport() {

	}

	/**
	 * 
	 * @param players
	 *            ArrayList of Players will be formated and displayed
	 */

	public void printReport(List<Player> players) {

		System.out.println("Players Report");
		System.out.println("---------------------------------------------------------------------------------------------------------------");
		System.out.println("  #. ID     First name      Last name       Email                         Gamertag            Birthdate");
		System.out.println("---------------------------------------------------------------------------------------------------------------");

		int count = 0;
		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			System.out.format("  %s. %-2s %-16s%-16s%-30s%-20s%s \n", count, player.getFormatedIdentifier(), player.getFirstName(), player.getLastName(), player.getEmailAddress(),
					player.getGamerTag(), player.getBirthdate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy")));
			count++;
		}

	}

	/**
	 * 
	 * @param players
	 *            ArrayList of Players will be formated and displayed
	 * @param startDate
	 * @param fileName
	 * @param string
	 */

	public void writeReport(List<Player> players, String title, LocalDateTime startDate, String fileName) throws ApplicationException {
		try {
			output = new Formatter(fileName);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e.getMessage());
		}

		output.format("%s\n", title);

		output.format("Players Report\n");
		output.format("---------------------------------------------------------------------------------------------------------------\n");
		output.format("  #. ID     First name      Last name       Email                         Gamertag            Birthdate\n");
		output.format("---------------------------------------------------------------------------------------------------------------\n");

		int count = 0;
		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			output.format("  %s. %-2s %-16s%-16s%-30s%-20s%s \n", count, player.getFormatedIdentifier(), player.getFirstName(), player.getLastName(), player.getEmailAddress(),
					player.getGamerTag(), player.getBirthdate().format(DateTimeFormatter.ofPattern("E MMM dd yyyy")));
			count++;
		}
		LocalDateTime endDate = LocalDateTime.now();
		long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
		output.format("%s\nDuration: %s ms\n", endDate, timeDelta);

		if (output != null) {
			try {
				output.close();
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}

	}

}
