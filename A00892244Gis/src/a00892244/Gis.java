/**
 * Project: A00892244Gis
 * File: Gis.java
 * Date: Jan 13, 2016
 * Time: 11:27:41 AM
 */

package a00892244;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.data.Score;
import a00892244.database.Database;
import a00892244.database.GamesDao;
import a00892244.database.LeaderReportDao;
import a00892244.database.PersonaDao;
import a00892244.database.PlayerDao;
import a00892244.database.ScoresDao;
import a00892244.io.GameReader;
import a00892244.io.PersonaReader;
import a00892244.io.PlayerReader;
import a00892244.io.ScoreReader;
import a00892244.ui.MainFrame;

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

			LOG.info("Starting program");
			LOG.info("program arguments: " + Arrays.toString(args));

			File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);
			if (!dbPropertiesFile.exists()) {
				System.exit(-1);
			}
			dbProperties = new Properties();

			try {
				dbProperties.load(new FileReader(dbPropertiesFile));
				database = Database.getTheInstance();
				database.setProperties(dbProperties);

				LOG.info("Reading data");
				readData();
				LOG.info("data is read:");
				LOG.info(playerDao.selectAll().toString());
				LOG.info(personaDao.selectAll().toString());
				LOG.info(gamesDao.selectAll().toString());
				LOG.info(scoresDao.selectAll().toString());

				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					// If Nimbus is not available, use the default.
				}
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainFrame frame = new MainFrame();
							frame.setTitle("GIS");
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			} catch (Exception e) {
				LOG.error("SQL Exception: " + e.getMessage());
			} finally {
				LOG.info("closing connection");
				database.shutdown();
				LOG.info("connection closed");
			}

		} catch (Exception e) {
			LOG.error(e);
			LOG.error("Terminating Program");
			System.exit(-1);
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	private static void readData() throws Exception {

		playerDao = new PlayerDao();

		LOG.info("drop the views if they exist");

		new LeaderReportDao().dropView("report");
		new LeaderReportDao().dropView("win");
		new LeaderReportDao().dropView("loss");

		// playerDao.drop();

		if (!playerDao.tableExist()) {
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
		}

		personaDao = new PersonaDao();
		if (!personaDao.tableExist()) {

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
		}

		gamesDao = new GamesDao();
		if (!gamesDao.tableExist()) {

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
		}

		scoresDao = new ScoresDao();
		if (!scoresDao.tableExist()) {
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

}
