/**
 * Project: A00892244Lab3
 * File: PlayerReader.java
 * Date: Jan 27, 2016
 * Time: 10:08:53 AM
 */

package a00892244.utils;
import java.util.ArrayList;
import java.util.List;

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerReader {
	List<String> playerStrings;
	Validator validator;

	/**
	 * default constructor
	 */
	public PlayerReader() {

	}

	/**
	 * 
	 * @param inputString
	 * @throws Exception
	 */
	public PlayerReader(String[] inputString) throws ApplicationException {
		validator = new Validator();
		this.playerStrings = new ArrayList<String>();
		validator.validateInputString(inputString);
		String[] playerStrings = inputString[0].trim().split(":");
		for (String player : playerStrings) {
			validator.validatePlayerString(player);
			this.playerStrings.add(player);
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Player getNextPlayer() throws ApplicationException {
		Player newPlayer = new Player();
		String playerString = getNextPlayerString();
		String[] playerAttributes = playerString.trim().split("\\|");
		newPlayer.setIdentifier(Integer.parseInt(playerAttributes[0]));
		newPlayer.setFirstName(playerAttributes[1]);
		newPlayer.setLastName(playerAttributes[2]);
		newPlayer.setEmailAddress(validator.validateEmail(playerAttributes[3]));
		newPlayer.setGamerTag(playerAttributes[4]);
		newPlayer.setBirthdate(validator.validateBirthdate(playerAttributes[5]));
		return newPlayer;
	}

	/**
	 * 
	 * @return
	 */
	public boolean morePlayers() {
		return !playerStrings.isEmpty();
	}

	/**
	 * 
	 * @return
	 */
	private String getNextPlayerString() {
		return playerStrings.remove(0);
	}

}
