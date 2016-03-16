/**
 * Project: A00892244Lab7
 * File: PlayerDaoTest.java
 * Date: Mar 2, 2016
 * Time: 8:44:43 PM
 */

package a00892244.database;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import a00892244.data.Player;
import a00892244.utils.PlayerReader;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerDaoTester {
	private static Database database;
	private static PlayerDao playerDao;

	private static Properties dbProperties;
	private static Connection connection;
	public static final String DB_PROPERTIES_FILENAME = "db.properties";

	private static final Logger LOG = LogManager.getLogger(PlayerDaoTester.class);
	private static PlayerReader playerReader;
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main("a00892244.database.PlayerDaoTester");            
	}

	@BeforeClass
	public static void setup() {
		File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);
		if (!dbPropertiesFile.exists()) {
			System.exit(-1);
		}
		dbProperties = new Properties();

		try {
			dbProperties.load(new FileReader(dbPropertiesFile));
			database = new Database(dbProperties);
			connection = database.getConnection();
			playerDao = new PlayerDao(database);
			LOG.info("drop the tables if they exist");
			playerDao.drop();

			LOG.info("create the tables");
			playerDao.create();

			LOG.info("Reading from players.txt");
			try {
				playerReader = new PlayerReader("players.txt");

				while (playerReader.morePlayers()) {
					playerDao.add(playerReader.getNextPlayer());
				}
			} catch (Exception e) {
				LOG.error("Read Error:  " + e.getMessage());
			}
		} catch (Exception e) {
			LOG.error("SQL Exception: " + e.getMessage());
		}

	}

	@Test
	public void testSelectAll() {
		LOG.info("testing selectAll()");
		try {
			List<Player> players = playerDao.selectAll();
			LOG.info(players.toString());
			assertTrue("Results should not be empty", !players.isEmpty());
		} catch (Exception e) {
			LOG.error(e.getMessage());

		}
	}

	@Test
	public void testGetPlayer() {
		LOG.info("testing getPlayer(\"CastleRock\")");
		List<Player> players;
		try {
			players = playerDao.getPlayer("CastleRock");
			LOG.info(players.toString());
			assertTrue("There should be 1 result", players.size() == 1);
			assertTrue("Player 'CastleRock' not found", players.get(0).getGamerTag().equals("CastleRock"));
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	public void testUpdate() {
		LOG.info("testing update()");
		List<Player> players;
		try {
			players = playerDao.getPlayer("NewGamerTag");
			assertTrue("Results should be empty", players.isEmpty());
			playerDao.update(new Player(2, "asdf", "asdf", "asdf@asdf.asdf", "NewGamerTag", Validator.validateBirthdate("21120101")));
			players = playerDao.getPlayer("NewGamerTag");
			LOG.info(players.toString());
			assertTrue("There should be 1 result", players.size() == 1);
			assertTrue("Player 'NewGamerTag' not found", players.get(0).getGamerTag().equals("NewGamerTag"));
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	public void testGetGamertags() {
		LOG.info("testing getGamertags()");
		List<String> gamertags;
		try {
			gamertags = playerDao.getGamertags();
			LOG.info(gamertags.toString());
			assertTrue("Results should not be empty", !gamertags.isEmpty());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@Test
	public void testDelete() {
		LOG.info("testing delete()");
		List<Player> players;
		try {
			players = playerDao.selectAll();
			int originalSize = players.size();
			LOG.info(players.toString());
			int testPlayerID = players.get(0).getIdentifier();
			String testPlayerGamertag = players.get(0).getGamerTag();
			LOG.info("Deleting " + testPlayerID);
			playerDao.delete(testPlayerID);
			LOG.info(players.toString());
			players = playerDao.selectAll();
			assertTrue("Size should be " + (originalSize - 1), players.size() == (originalSize - 1));
			LOG.info("getting " + testPlayerGamertag);
			players = playerDao.getPlayer(testPlayerGamertag);
			LOG.info(players.toString());
			assertTrue("Player ID=" + testPlayerID + " should have been deleted", players.isEmpty());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	@AfterClass
	public static void tearDown() {
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error("SQL Exception: " + e.getMessage());
		}
	}

}
