/**
 * Project: A00892244Lab4
 * File: Validator.java
 * Date: Jan 27, 2016
 * Time: 10:09:08 AM
 */

package a00892244.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import a00892244.utils.ApplicationException;

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
	 * @throws ApplicationException
	 */
	public String validateEmail(String emailAddress) throws ApplicationException {
		if (!emailAddress.matches(EMAIL_REGEX)) {
			throw new ApplicationException("'" + emailAddress + "' is an invalid email address");
		}
		return emailAddress;
	}

	/**
	 * 
	 * @param arg
	 * @throws Exception
	 */
	public void validateInputString(String[] arg) throws ApplicationException {
		if (arg.length < 1) {
			throw new ApplicationException(
					"Invalid argument String. Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>\\|<birthdate1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
		}

		String inputString = arg[0];

		if (inputString.split(":").length < 1) {
			throw new ApplicationException(
					"Invalid argument String. Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>\\|<birthdate1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
		}
	}

	/**
	 * 
	 * @param playerString
	 * @throws Exception
	 */
	public void validatePlayerString(String playerString) throws ApplicationException {
		if (playerString.split("\\|").length != 6) {
			throw new ApplicationException("Invalid argument String.  Expected 6 elements but got " + playerString.split("\\|").length);
		}
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public LocalDate validateBirthdate(String dateString) throws ApplicationException {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate bdate = LocalDate.parse(dateString, formatter);
			return bdate;
		} catch (Exception e) {
			throw new ApplicationException("Birthdate needs to be in the format yyyyMMdd");
		}
	}
}
