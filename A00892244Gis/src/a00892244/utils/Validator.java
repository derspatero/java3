/**
 * Project: A00892244Lab2
 * File: Validator.java
 * Date: Jan 19, 2016
 * Time: 10:09:08 AM
 */

package a00892244.utils;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Validator {

	private static String EMAIL_REGEX = ".*@.*\\..*";

	/**
	 * default constructor
	 */
	public Validator() {

	}

	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	public String validateEmail(String emailAddress) {
		if (emailAddress.matches(EMAIL_REGEX)) {
			return emailAddress;
		} else {
			return "N/A";
		}
	}

	/**
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public void validateInputString(String[] arg) throws Exception {
		if (arg.length < 1) {
			throw new Exception("Invalid argument String.");
		}

		String inputString = arg[0];

		if (inputString.split(":").length < 1) {
			throw new Exception("Invalid argument String.");
		}
	}

	/**
	 * 
	 * @param playerString
	 * @throws Exception
	 */
	public void validatePlayerString(String playerString) throws Exception {
		if (playerString.split("\\|").length != 5) {
			throw new Exception("Invalid argument String.  Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
		}
	}
	
	public void validatePersonaString(String personaString) throws Exception {
		if (personaString.split("\\|").length != 4) {
			throw new Exception("Invalid argument String.  Must be \"<ID_1>\\|<PLAYER_ID_1>\\|<GAMERTAG_1>\\|<PLATFORM>:<ID_2>\\|<PLAYER_ID_2>\\|<GAMERTAG_2>\\|<PLATFORM_2>...");
		}
	}
	
	public void validateGameString(String gameString) throws Exception {
		if (gameString.split("\\|").length != 4) {
			throw new Exception("Invalid argument String.  Must be \"<ID_1>\\|<PLAYER_ID_1>\\|<PRODUCER_1>:<ID_2>\\|<PLAYER_ID_2>\\|<PRODUCER_2>...");
		}
	}
	
	public void validateScoreString(String scoreString) throws Exception {
		if (scoreString.split("\\|").length != 3) {
			throw new Exception("Invalid argument String.  Must be \"<PERSONA_ID_1>\\|<GAME_ID_1>\\|<WIN_1>:<PERSONA_ID_2>\\|<GAME_ID_2>\\|<WIN_2>...");
		}
	}
}
