/**
 * Project: A00892244Gis
 * File: Gis.java
 * Date: Jan 13, 2016
 * Time: 11:27:41 AM
 */

package a00892244;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.data.Score;
import a00892244.io.GameReader;
import a00892244.io.PersonaReader;
import a00892244.io.PlayerReader;
import a00892244.io.ScoreReader;
import a00892244.utils.ApplicationException;
import a00892244.utils.CompareByBirthdate;
import a00892244.utils.PlayerReport;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Gis {

	private static final Logger LOG = LogManager.getLogger(Gis.class);
	static Map<Integer, Player> players;
	static Map<String, Game> games;
	static Map<Integer, Persona> personas;
	static List<Score> scores;

	/**
	 * @param
	 * 
	 */
	public static void main(String[] args) {

		try {

			LOG.info("Starting program");
			LOG.info("program arguments: " + Arrays.toString(args));

			readData();
			analyseData();
			reportData();

			LocalDateTime startDate = LocalDateTime.now();

			LocalDateTime endDate = LocalDateTime.now();
			long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
			LOG.info("Duration: " + timeDelta + " ms\n");

		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Terminating Program");
			System.exit(-1);
		}

	}

	private static void readData() throws ApplicationException {

		players = new HashMap<Integer, Player>();

		LOG.info("Reading from players.dat");
		PlayerReader playerReader = new PlayerReader("players.dat");

		while (playerReader.moreData()) {
			Player newPlayer = playerReader.getNextPlayer();
			LOG.info("New Player: " + newPlayer.toString());
			players.put(newPlayer.getIdentifier(), newPlayer);
		}
		LOG.info(players.toString());

		games = new HashMap<String, Game>();

		LOG.info("Reading from games.dat");
		GameReader gameReader = new GameReader("games.dat");

		while (gameReader.moreData()) {
			Game newGame = gameReader.getNextGame();
			LOG.info("New Game:  " + newGame.toString());
			games.put(newGame.getId(), newGame);
		}
		LOG.info(games.toString());

		scores = new ArrayList<Score>();

		LOG.info("Reading from scores.dat");
		ScoreReader scoreReader = new ScoreReader("scores.dat");

		while (scoreReader.moreData()) {
			Score newScore = scoreReader.getNextScore();
			LOG.info("New Score:  " + newScore.toString());
			scores.add(newScore);
		}
		LOG.info(scores.toString());

		personas = new HashMap<Integer, Persona>();

		LOG.info("Reading from personas.dat");
		PersonaReader personaReader = new PersonaReader("personas.dat");

		while (personaReader.moreData()) {
			Persona newPersona = personaReader.getNextPersona();
			LOG.info("New Persona:  " + newPersona.toString());
			personas.put(newPersona.getId(), newPersona);
		}

		LOG.info(personas.toString());
	}

	private static void analyseData() {
		Iterator<Score> iterator = scores.iterator();
		while (iterator.hasNext()) {
			Score score = iterator.next();
			Game game;
			Persona persona = personas.get(score.getPersonaId());
			LOG.warn(score.toString() + " goes to " + persona.toString());
			if (!persona.getGames().containsKey(score.getGameId())) {

				persona.addGame(new Game(games.get(score.getGameId())));
			}

			game = persona.getGames().get(score.getGameId());

			game.addScore(score);
			LOG.info("Added Game " + game.toString() + " to persona id " + persona.getId());

			games.get(score.getGameId()).addScore(score);
			LOG.info("Added Score " + score.toString() + " to Game " + score.getGameId());
		}

		for (int key : personas.keySet()) {
			Player player = players.get(personas.get(key).getPlayerId());
			player.addPersona(personas.get(key));
			players.put(player.getIdentifier(), player);
			LOG.info("Added Persona " + personas.get(key) + " to player id " + player.getIdentifier());
		}

		LOG.info(players.toString());

	}

	private static void reportData() {
		// LOG.info("Win:Loss = " + players.get(1).getPersona(3).getGames().get("CODE").getWinLossRatio());

		System.out.println("Players Report");
		System.out.println("----------------------------------------------------------");
		System.out.println("Win:Loss   Game Name            Gamertag        Platform");
		System.out.println("----------------------------------------------------------");
		for (int key : players.keySet()) {

			Player player = players.get(key);

			for (int personaKey : player.getPersonas().keySet()) {
				Persona persona = player.getPersona(personaKey);
				for (String gameKey : persona.getGames().keySet()) {
					Game game = persona.getGames().get(gameKey);
					System.out.format("%-10s %-20s %-20s%-30s\n", game.getWinLossRatio(), game.getName(), persona.getGamerTag(), persona.getPlatform());

				}

			}
		}
		System.out.println("----------------------------------------------------------");
		for (String key : games.keySet()) {
			System.out.format("%-20s%s\n", games.get(key).getName(), games.get(key).getScores().size());

		}
		System.out.println("----------------------------------------------------------");
	}

}
