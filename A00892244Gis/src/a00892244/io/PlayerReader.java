/**
 * Project: A00892244Lab5
 * File: PlayerReader.java
 * Date: Feb16, 2016
 * Time: 10:08:53 AM
 */

package a00892244.io;

import a00892244.data.Player;
import a00892244.utils.ApplicationException;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerReader extends AbstractDataReader {
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
		super(inputFile);
		validator = new Validator();
		if(!getNextDataString().equals("ID|FIRST_NAME|LAST_NAME|EMAIL|BIRTHDATE")) {
			throw new ApplicationException("Missing file header.  Expecting \"ID|FIRST_NAME|LAST_NAME|EMAIL|BIRTHDATE\"");
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Player getNextPlayer() throws ApplicationException {
		Player newPlayer = new Player();
		String playerString = getNextDataString();
		String[] playerAttributes = playerString.trim().split("\\|");
		newPlayer.setIdentifier(Integer.parseInt(playerAttributes[0]));
		newPlayer.setFirstName(playerAttributes[1]);
		newPlayer.setLastName(playerAttributes[2]);
		newPlayer.setEmailAddress(validator.validateEmail(playerAttributes[3]));
		newPlayer.setBirthdate(validator.validateBirthdate(playerAttributes[4]));
		return newPlayer;
	}



}
