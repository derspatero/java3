/**
 * Project: A00892244Gis
 * File: ScoreReader.java
 * Date: Feb22, 2016
 * Time: 10:08:53 PM
 */

package a00892244.io;

import a00892244.data.Score;
import a00892244.utils.ApplicationException;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class ScoreReader extends AbstractDataReader {
	Validator validator;

	/**
	 * default constructor
	 */
	public ScoreReader() {

	}

	/**
	 * 
	 * @param inputFile
	 * @throws ApplicationException
	 */
	public ScoreReader(String inputFile) throws ApplicationException {
		super(inputFile);
		validator = new Validator();

		if (!this.getNextDataString().equals("PERSONA_ID|GAME_ID|WIN")) {
			throw new ApplicationException("Missing file header.  Expecting \"PERSONA_ID|GAME_ID|WIN\"");
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Score getNextScore() throws ApplicationException {
		Score newScore = new Score();
		String gameString = getNextDataString();
		String[] playerAttributes = gameString.trim().split("\\|");
		newScore.setPersonaId(Integer.parseInt(playerAttributes[0]));
		newScore.setGameId(playerAttributes[1]);
		newScore.setWin(playerAttributes[2]);
		return newScore;
	}

}
