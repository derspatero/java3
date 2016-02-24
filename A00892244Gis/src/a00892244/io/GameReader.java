/**
 * Project: A00892244Gis
 * File: AbstractDatReader.java
 * Date: Feb22, 2016
 * Time: 10:08:53 PM
 */

package a00892244.io;

import a00892244.data.Game;
import a00892244.utils.ApplicationException;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class GameReader extends AbstractDataReader {
	Validator validator;

	/**
	 * default constructor
	 */
	public GameReader() {

	}

	/**
	 * 
	 * @param inputFile
	 * @throws ApplicationException
	 */
	public GameReader(String inputFile) throws ApplicationException {
		super(inputFile);
		validator = new Validator();

		if (!this.getNextDataString().equals("ID|NAME|PRODUCER")) {
			throw new ApplicationException("Missing file header.  Expecting \"ID|NAME|PRODUCER\"");
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Game getNextGame() throws ApplicationException {
		Game newGame = new Game();
		String gameString = getNextDataString();
		String[] playerAttributes = gameString.trim().split("\\|");
		newGame.setId(playerAttributes[0]);
		newGame.setName(playerAttributes[1]);
		newGame.setProducer(playerAttributes[2]);
		return newGame;
	}

}
