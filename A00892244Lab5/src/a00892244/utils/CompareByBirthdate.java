/**
 * Project: A00892244Lab5
 * File: CompareByBirthdate.java
 * Date: Feb 16, 2016
 * Time: 4:17:31 PM
 */

package a00892244.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class CompareByBirthdate {

	/**
	 * 
	 */
	public CompareByBirthdate() {

	}

	/**
	 * I got this idea from http://stackoverflow.com/questions/4066538/sort-an-arraylist-based-on-an-object-field
	 * 
	 * @param playerList
	 * @return
	 */
	public static List<Player> sortByBirthdateAscending(List<Player> playerList) {
		Collections.sort(playerList, new Comparator<Player>() {
			@Override
			public int compare(Player arg0, Player arg1) {
				if (arg0.getBirthdate().isAfter(arg1.getBirthdate())) {
					return 1;
				}
				if (arg0.getBirthdate().isBefore(arg1.getBirthdate())) {
					return -1;
				}
				return 0;
			}
		});
		return playerList;
	}

	/**
	 * 
	 * @param playerList
	 * @return
	 */
	public static List<Player> sortByBirthdateDescending(List<Player> playerList) {
		Collections.sort(playerList, new Comparator<Player>() {
			@Override
			public int compare(Player arg0, Player arg1) {
				if (arg0.getBirthdate().isAfter(arg1.getBirthdate())) {
					return -1;
				}
				if (arg0.getBirthdate().isBefore(arg1.getBirthdate())) {
					return 1;
				}
				return 0;
			}
		});
		return playerList;
	}
}
