/**
 * Project: A00892244Gis
 * File: Validator.java
 * Date: Feb 16, 2016
 * Time: 10:09:08 AM
 */

package a00892244.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

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
	public void validateInputString(String arg) throws ApplicationException {
		if (arg.length() < 1) {
			throw new ApplicationException(
					"Invalid Player String. Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>\\|<birthdate1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
		}

		String inputString = arg;

		if (inputString.split(":").length < 1) {
			throw new ApplicationException(
					"Invalid Player String. Must be \"<ID1>\\|<FirstName1>\\|<LastName1\\|<email1>\\|<gamertag1>\\|<birthdate1>:<ID2>\\|<FirstName2>\\|<LastName2>\\|...");
		}
	}

	/**
	 * 
	 * @param playerString
	 * @throws Exception
	 */
	public void validatePlayerString(String playerString) throws ApplicationException {
		if (playerString.split("\\|").length != 5) {
			throw new ApplicationException("Invalid Player String.  Expected 5 elements but got " + playerString.split("\\|").length);
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

	/**
	 * 
	 * @param list
	 * @param regex
	 * @throws ApplicationException
	 */
	public static void verifyListEntries(List<String> list, String regex) throws ApplicationException {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String arg = iterator.next();
			if (!arg.matches(regex)) {
				throw new ApplicationException(arg + " is not valid.  Valid strings: " + regex);
			}
		}
	}
}
