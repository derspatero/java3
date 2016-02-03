/**
 * Project: A00892244Lab4
 * File: PlayerReport.java
 * Date: Jan 27, 2016
 * Time: 10:08:17 AM
 */

package a00892244.utils;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerReport {

	/**
	 * 
	 */
	public PlayerReport() {

	}

	/**
	 * 
	 * @param players
	 *            ArrayList of Players will be formated and displayed
	 */

	@SuppressWarnings("deprecation")
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

}
