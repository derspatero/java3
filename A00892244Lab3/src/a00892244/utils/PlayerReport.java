/**
 * Project: A00892244Lab3
 * File: PlayerReport.java
 * Date: Jan 27, 2016
 * Time: 10:08:17 AM
 */

package a00892244.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

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
	public void printReport(ArrayList<Player> players) {
		LocalDateTime startDate = LocalDateTime.now();
		System.out.println(startDate);
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
		LocalDateTime endDate = LocalDateTime.now();
		int timeDelta = endDate.getNano()/1000000 - startDate.getNano()/1000000;
		System.out.println(endDate);
		System.out.format("Duration: %s ms\n", timeDelta);
		
	}

}
