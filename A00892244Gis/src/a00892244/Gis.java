/**
 * Project: A00892244Gis
 * File: Gis.java
 * Date: Jan 13, 2016
 * Time: 11:27:41 AM
 */

package a00892244;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.data.Score;
import a00892244.database.Database;
import a00892244.database.GamesDao;
import a00892244.database.PersonaDao;
import a00892244.database.PlayerDao;
import a00892244.database.ScoresDao;
import a00892244.io.GameReader;
import a00892244.io.PersonaReader;
import a00892244.io.PlayerReader;
import a00892244.io.FileWriter;
import a00892244.io.ScoreReader;
import a00892244.utils.ApplicationException;
import a00892244.utils.PlayerReport;
import a00892244.utils.Validator;
import a00892244.utils.LeaderBoardReport;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Gis {

	private static Database database;
	private static PlayerDao playerDao;
	private static PersonaDao personaDao;
	private static GamesDao gamesDao;
	private static ScoresDao scoresDao;

	private static Properties dbProperties;
	private static Connection connection;
	public static final String DB_PROPERTIES_FILENAME = "db.properties";
	public static final String PLAYERS_DATA_FILE_NAME = "players.dat";
	public static final String PERSONAS_DATA_FILE_NAME = "personas.dat";
	private static final String GAMES_DATA_FILE_NAME = "games.dat";
	private static final String SCORES_DATA_FILE_NAME = "scores.dat";

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
			
			File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);
			if (!dbPropertiesFile.exists()) {
				System.exit(-1);
			}
			dbProperties = new Properties();

			try {
				dbProperties.load(new FileReader(dbPropertiesFile));
				database = new Database(dbProperties);
				connection = database.getConnection();


			LOG.info("Reading data");
			readData();
			LOG.info("data is read:");
			LOG.info(playerDao.selectAll().toString());
			LOG.info(personaDao.selectAll().toString());
			LOG.info(gamesDao.selectAll().toString());
			LOG.info(scoresDao.selectAll().toString());

//			LOG.info("Writing report");
//			writeReport(args);

			} catch (Exception e) {
				LOG.error("SQL Exception: " + e.getMessage());
			} finally {
				try {
					LOG.info("closing connection");
					connection.close();
					LOG.info("connection closed");
					System.gc();
				} catch (SQLException e) {
					LOG.error("SQL Exception: " + e.getMessage());
				}
			}

			LocalDateTime endDate = LocalDateTime.now();
			long timeDelta = startDate.until(endDate, ChronoUnit.MILLIS);
			LOG.info("Exiting.  Duration: " + timeDelta + " ms\n");

		} catch (Exception e) {
			LOG.error(e);
			LOG.error("Terminating Program");
			System.exit(-1);
		}
	}

	/**
	 * 
	 * @param args
	 * @throws ApplicationException
	 */
	private static void writeReport(String[] args) throws ApplicationException {
		List<String> arguments = new ArrayList<String>(Arrays.asList(args));
		FileWriter fileWriter = new FileWriter();

		if (arguments.size() == 0) {
			LeaderBoardReport leaderBoardReport = new LeaderBoardReport(players);
			LOG.info(leaderBoardReport.getReport());
			fileWriter.writeFile("leaderboard_report.txt", leaderBoardReport.getReport());
		}

		else if ((arguments.size() == 1) && (arguments.contains("players"))) {
			LOG.info("arguments: " + arguments.toString());
			PlayerReport playerReport = new PlayerReport(players);
			LOG.info(playerReport.getReport());
			fileWriter.writeFile("players_report.txt", playerReport.getReport());
		}

		else {

			LOG.info("Validating arguments " + arguments.toString());
			Validator.verifyListEntries(arguments, "(by_game|by_count|platform=(AN|IO|PC|PS|XB)|total|desc)");

			LeaderBoardReport leaderBoardReport = new LeaderBoardReport(players);
			Iterator<String> iterator = arguments.iterator();
			while (iterator.hasNext()) {
				String arg = iterator.next();
				if (arg.matches("platform=(AN|IO|PC|PS|XB)")) {
					leaderBoardReport.filterByPlatform(arg.split("platform=")[1]);
				}
			}

			if (arguments.contains("by_game")) {
				if (arguments.contains("by_count")) {
					throw new ApplicationException("Invalid arg string. Valid: [by_game|by_count] [patform=AN|IO|PC|PS|XB] [total] [desc]");
				}
				leaderBoardReport.sortByGame();
			} else if (arguments.contains("by_count")) {
				leaderBoardReport.sortByCount();
			}

			if (arguments.contains("desc")) {
				leaderBoardReport.desc();
			}

			if (arguments.contains("total")) {
				LOG.info(leaderBoardReport.getReportWithTotal());
				fileWriter.writeFile("leaderboard_report.txt", leaderBoardReport.getReportWithTotal());

			} else {
				LOG.info(leaderBoardReport.getReport());
				fileWriter.writeFile("leaderboard_report.txt", leaderBoardReport.getReport());
			}
		}
	}

	/**
	 * 
	 * @throws Exception 
	 */
	private static void readData() throws Exception {


			playerDao = new PlayerDao(database);

			LOG.info("drop the tables if they exist");
			playerDao.drop();

			LOG.info("create the players table");
			playerDao.create();

			LOG.info("Reading from " + PLAYERS_DATA_FILE_NAME);
			try {
				PlayerReader playerReader = new PlayerReader(PLAYERS_DATA_FILE_NAME);

				while (playerReader.moreData()) {
					playerDao.add(playerReader.getNextPlayer());
				}
			} catch (Exception e) {
				LOG.error("Read Error:  " + e.getMessage());
			}
			

			LOG.info(playerDao.selectAll().toString());

			personaDao = new PersonaDao(database);
			LOG.info("drop the tables if they exist");
			personaDao.drop();

			LOG.info("create the personas table");
			personaDao.create();
			
			LOG.info("Reading from " + PERSONAS_DATA_FILE_NAME);
			PersonaReader personaReader = new PersonaReader(PERSONAS_DATA_FILE_NAME);

			try {
				personaReader = new PersonaReader(PERSONAS_DATA_FILE_NAME);

				while (personaReader.moreData()) {
					personaDao.add(personaReader.getNextPersona());
				}
			} catch (Exception e) {
				LOG.error("Read Error:  " + e.getMessage());
			}
			

			LOG.info(personaDao.selectAll().toString());
			
			gamesDao = new GamesDao(database);
			LOG.info("drop the tables if they exist");
			gamesDao.drop();

			LOG.info("create the games table");
			gamesDao.create();
			
			LOG.info("Reading from " + GAMES_DATA_FILE_NAME);
			GameReader gameReader = new GameReader(GAMES_DATA_FILE_NAME);

			try {
				gameReader = new GameReader(GAMES_DATA_FILE_NAME);

				while (gameReader.moreData()) {
					gamesDao.add(gameReader.getNextGame());
				}
			} catch (Exception e) {
				LOG.error("Games Read Error:  " + e.getMessage());
			}
			

			LOG.info(gamesDao.selectAll().toString());
			
			
			scoresDao = new ScoresDao(database);
			LOG.info("drop the tables if they exist");
			scoresDao.drop();

			LOG.info("create the scores table");
			scoresDao.create();
			
			LOG.info("Reading from " + SCORES_DATA_FILE_NAME);
			ScoreReader scoreReader = new ScoreReader(SCORES_DATA_FILE_NAME);

			try {
				scoreReader = new ScoreReader(SCORES_DATA_FILE_NAME);

				while (scoreReader.moreData()) {
					scoresDao.add(scoreReader.getNextScore());
				}
			} catch (Exception e) {
				LOG.error("Scores Read Error:  " + e.getMessage());
			}


			LOG.info(scoresDao.selectAll().toString());


	}

}
