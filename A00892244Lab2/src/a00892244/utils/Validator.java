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
			throw new Exception("Invalid argument String. Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
		}

		String inputString = arg[0];

		if (inputString.split(":").length < 1) {
			throw new Exception("Invalid argument String. Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
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
}
