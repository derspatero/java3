/**
 * Project: A00892244Lab2
 * File: PlayerReader.java
 * Date: Jan 19, 2016
 * Time: 10:08:53 AM
 */

package a00892244.utils;

import java.util.ArrayList;
import java.util.List;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.data.Score;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class DataReader {
	List<String> strings;
	Validator validator;

	/**
	 * default constructor
	 */
	public DataReader() {

	}

	/**
	 * 
	 * @param inputString
	 * @throws Exception
	 */
	public DataReader(String[] inputString) throws Exception {
		validator = new Validator();
		this.strings = new ArrayList<String>();
		validator.validateInputString(inputString);
		String[] lines = inputString[0].trim().split(":");
		for (String line : lines) {
			this.strings.add(line);
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Player getNextPlayer() throws Exception {
		Player newPlayer = new Player();
		String playerString = getNextLineString();
		validator.validatePlayerString(playerString);
		String[] playerAttributes = playerString.trim().split("\\|");
		newPlayer.setId(Integer.parseInt(playerAttributes[0]));
		newPlayer.setFirstName(playerAttributes[1]);
		newPlayer.setLastName(playerAttributes[2]);
		newPlayer.setEmail(validator.validateEmail(playerAttributes[3]));
		newPlayer.setBirthDate(playerAttributes[4]);
		return newPlayer;
	}
	
	public Persona getNextPersona() throws Exception {
		Persona newPersona = new Persona();
		String personaString = getNextLineString();
		validator.validatePersonaString(personaString);
		String[] personaAttributes = personaString.trim().split("\\|");
		newPersona.setId(personaAttributes[0]);
		newPersona.setPlayerId(personaAttributes[1]);
		newPersona.setGamerTag(personaAttributes[2]);
		newPersona.setPlatform(personaAttributes[3]);
		return newPersona;
	}

	public Game getNextGame() throws Exception {
		Game newGame = new Game();
		String gameString = getNextLineString();
		validator.validateGameString(gameString);
		String[] gameAttributes = gameString.trim().split("\\|");
		newGame.setId(gameAttributes[0]);
		newGame.setName(gameAttributes[1]);
		newGame.setProducer(gameAttributes[2]);
		return newGame;
	}
	
	public Score getNextScore() throws Exception {
		Score newScore = new Score();
		String scoreString = getNextLineString();
		validator.validateScoreString(scoreString);
		String[] scoreAttributes = scoreString.trim().split("\\|");
		newScore.setPersonaId(scoreAttributes[0]);
		newScore.setGameId(scoreAttributes[1]);
		newScore.setWin(scoreAttributes[2]);
		return newScore;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean moreLines() {
		return !strings.isEmpty();
	}

	/**
	 * 
	 * @return
	 */
	private String getNextLineString() {
		return strings.remove(0);
	}
}
