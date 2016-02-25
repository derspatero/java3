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
import a00892244.io.FileWriter;
import a00892244.io.ScoreReader;
import a00892244.utils.ApplicationException;
import a00892244.utils.GamesReport;
import a00892244.utils.PlayerReport;
import a00892244.utils.LeaderBoardReport;

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

			LocalDateTime startDate = LocalDateTime.now();
			LOG.info("Starting program");
			LOG.info("program arguments: " + Arrays.toString(args));

			readData();
			writeReport(args);

			LocalDateTime endDate = LocalDateTime.now();
			long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
			LOG.info("Exiting.  Duration: " + timeDelta + " ms\n");

		} catch (ApplicationException e) {
			LOG.error(e.getMessage());
			LOG.error("Terminating Program");
			System.exit(-1);
		}

	}

	private static void writeReport(String[] args) throws ApplicationException {
		List<String> arguments = new ArrayList<String>(Arrays.asList(args));

		FileWriter fileWriter = new FileWriter();

		if (arguments.size() == 0) {
			LeaderBoardReport leaderBoardReport = new LeaderBoardReport(players);
			LOG.info(leaderBoardReport.getReport());
			fileWriter.writeFile("leaderboard_report.txt", leaderBoardReport.getReport());
		}

		else if (arguments.contains("players")) {
			PlayerReport playerReport = new PlayerReport(players);
			LOG.info(playerReport.getReport());
			fileWriter.writeFile("players_report.txt", playerReport.getReport());
		}

		else {
			LeaderBoardReport leaderBoardReport = new LeaderBoardReport(players);
			
			Iterator<String> iterator = arguments.iterator();
			while (iterator.hasNext()) {
				String arg = iterator.next();
				if (arg.matches("platform=[A-Z][A-Z]")){
					leaderBoardReport.filterByPlatform(arg.split("platform=")[1]);
				}
			}

			if (arguments.contains("by_game")) {
				leaderBoardReport.sortByGame();
			} else if (arguments.contains("by_count")) {
				leaderBoardReport.sortByCount();
			}

			if (arguments.contains("desc")) {
				leaderBoardReport.desc();
			}


			if (arguments.contains("total")) {
				GamesReport gamesReport = new GamesReport(games);
				LOG.info(leaderBoardReport.getReport() + gamesReport.getReport());
				fileWriter.writeFile("leaderboard_report.txt", leaderBoardReport.getReport() + gamesReport.getReport());
			} else {
				LOG.info(leaderBoardReport.getReport());
				fileWriter.writeFile("leaderboard_report.txt", leaderBoardReport.getReport());

			}

		}
	}

	private static void readData() throws ApplicationException {

		Map<Integer, Persona> personas = new HashMap<Integer, Persona>();
		games = new HashMap<String, Game>();
		players = new HashMap<Integer, Player>();
		
		LOG.info("Reading from personas.dat");
		PersonaReader personaReader = new PersonaReader("personas.dat");

		while (personaReader.moreData()) {
			Persona newPersona = personaReader.getNextPersona();
			LOG.debug("New :  " + newPersona.toString());
			personas.put(newPersona.getId(), newPersona);
		}

		LOG.debug(personas.toString());
		
		LOG.info("Reading from games.dat");
		GameReader gameReader = new GameReader("games.dat");

		while (gameReader.moreData()) {
			Game newGame = gameReader.getNextGame();
			LOG.debug("New " + newGame.toString());
			games.put(newGame.getId(), newGame);
		}
		LOG.debug(games.toString());
		
		LOG.info("Reading from scores.dat");
		ScoreReader scoreReader = new ScoreReader("scores.dat");

		while (scoreReader.moreData()) {
			Score score = scoreReader.getNextScore();
			LOG.debug("New " + score.toString());
			if (!personas.get(score.getPersonaId()).getGames().containsKey(score.getGameId())) {
				personas.get(score.getPersonaId()).addGame(new Game(games.get(score.getGameId())));
			}
			Game game = personas.get(score.getPersonaId()).getGames().get(score.getGameId());
			game.addScore(score);
			LOG.debug("Added Game " + game.toString() + " to persona id " + personas.get(score.getPersonaId()).getId());
			games.get(score.getGameId()).addScore(score);
		}

		LOG.info("Reading from players.dat");
		PlayerReader playerReader = new PlayerReader("players.dat");

		while (playerReader.moreData()) {
			Player newPlayer = playerReader.getNextPlayer();
			LOG.debug("New " + newPlayer.toString());
			players.put(newPlayer.getIdentifier(), newPlayer);
		}
		LOG.debug(players.toString());
		
		LOG.info("Assigning personas to players");
		for (int key : personas.keySet()) {
			Player player = players.get(personas.get(key).getPlayerId());
			player.addPersona(personas.get(key));
			players.put(player.getIdentifier(), player);
			LOG.debug("Added Persona " + personas.get(key) + " to player id " + player.getIdentifier());
		}
		
		LOG.debug(players.toString());
	}

}
