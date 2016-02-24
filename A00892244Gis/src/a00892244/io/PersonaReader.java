/**
 * Project: A00892244Gis
 * File: PersonaReader.java
 * Date: Feb22, 2016
 * Time: 10:08:53 PM
 */

package a00892244.io;

import a00892244.data.Persona;
import a00892244.utils.ApplicationException;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PersonaReader extends AbstractDataReader {
	Validator validator;

	/**
	 * default constructor
	 */
	public PersonaReader() {

	}

	/**
	 * 
	 * @param inputFile
	 * @throws ApplicationException
	 */
	public PersonaReader(String inputFile) throws ApplicationException {
		super(inputFile);
		validator = new Validator();

		if (!this.getNextDataString().equals("ID|PLAYER_ID|GAMERTAG|PLATFORM")) {
			throw new ApplicationException("Missing file header.  Expecting \"ID|PLAYER_ID|GAMERTAG|PLATFORM\"");
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Persona getNextPersona() throws ApplicationException {
		Persona newPersona = new Persona();
		String personaString = getNextDataString();
		String[] playerAttributes = personaString.trim().split("\\|");
		newPersona.setId(Integer.parseInt(playerAttributes[0]));
		newPersona.setPlayerId(Integer.parseInt(playerAttributes[1]));
		newPersona.setGamerTag(playerAttributes[2]);
		newPersona.setPlatform(playerAttributes[3]);
		return newPersona;
	}

}
