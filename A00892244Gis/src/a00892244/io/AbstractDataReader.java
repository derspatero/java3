/**
 * Project: A00892244Lab5
 * File: PlayerReader.java
 * Date: Feb16, 2016
 * Time: 10:08:53 AM
 */

package a00892244.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import a00892244.utils.ApplicationException;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public abstract class AbstractDataReader {
	List<String> dataStrings;
	Validator validator;

	/**
	 * default constructor
	 */
	public AbstractDataReader() {

	}

	/**
	 * 
	 * @param inputString
	 * @throws Exception
	 */
	public AbstractDataReader(String inputFile) throws ApplicationException {
		Scanner scanner;

		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e.getMessage());
		}
		// Close the input stream

		validator = new Validator();
		this.dataStrings = new ArrayList<String>();

		while (scanner.hasNextLine()) {
			String dataString = scanner.nextLine();
			this.dataStrings.add(dataString);

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
	 */
	public boolean moreData() {
		return !dataStrings.isEmpty();
	}

	/**
	 * 
	 * @return
	 */
	protected String getNextDataString() {
		return dataStrings.remove(0);
	}

}
