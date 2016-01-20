/**
 * Project: A00892244Lab2
 * File: PlayerReport.java
 * Date: Jan 19, 2016
 * Time: 10:08:17 AM
 */

package a00892244.utils;

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
		System.out.println("Players Report");
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("  #. ID     First name      Last name       Email                          Gamertag");
		System.out.println("-----------------------------------------------------------------------------------------------");

		int count = 0;
		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player player = iterator.next();
			System.out.format("  %s. %-2s %-16s%-16s%-30s %s\n", count, player.getId(), player.getFirstName(), player.getLastName(), player.getEmail(),
					"player.getGamerTag()");
			count++;
		}
	}

}
