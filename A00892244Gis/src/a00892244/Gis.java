/**
 * Project: A00892244Gis
 * File: Gis.java
 * Date: Jan 13, 2016
 * Time: 11:27:41 AM
 */

package a00892244;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

			LocalDateTime startDate = LocalDateTime.now();

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

//			
//			LOG.info("Sort Players By Birthdate Descending");
//			players = CompareByBirthdate.sortByBirthdateDescending(players);
//
//			if (args.length < 2) {
//				LOG.info("Writing player report to players_report.txt");
//				playerReport.writeReport(players, "\n\nSort by Birthdate (Descending)", startDate, "players_report.txt");
//			} else {
//				LOG.info("Writing player report to " + args[1]);
//				playerReport.writeReport(players, "\n\nSort by Birthdate (Descending)", startDate, args[1]);
//			}

			LocalDateTime endDate = LocalDateTime.now();
			long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
			LOG.info("Duration: " + timeDelta + " ms\n");

		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Terminating Program");
			System.exit(-1);
		}

	}

}
