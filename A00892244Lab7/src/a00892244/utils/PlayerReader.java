/**
 * Project: A00892244Lab5
 * File: PlayerReader.java
 * Date: Feb16, 2016
 * Time: 10:08:53 AM
 */

package a00892244.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	public PlayerReader(String inputFile) throws ApplicationException {
		Scanner scanner;

		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e.getMessage());
		}
		// Close the input stream

		validator = new Validator();
		this.playerStrings = new ArrayList<String>();

		while (scanner.hasNextLine()) {
			String player = scanner.nextLine();
			validator.validatePlayerString(player);
			this.playerStrings.add(player);
		}

		if (scanner != null) {
			try {
				scanner.close();
			} catch (Exception e) {
				throw new ApplicationException("File error");
			}
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
