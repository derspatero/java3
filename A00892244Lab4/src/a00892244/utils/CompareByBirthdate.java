/**
 * Project: A00892244Lab4
 * File: CompareByBirthdate.java
 * Date: Feb 2, 2016
 * Time: 4:17:31 PM
 */

package a00892244.utils;

import java.util.ArrayList;
import java.util.Collections;
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

	public static List<Player> sortByBirthdate(List<Player> playerList) {
		Collections.sort(playerList);
		return playerList;
	}

}
