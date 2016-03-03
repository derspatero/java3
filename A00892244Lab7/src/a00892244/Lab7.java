/**
 * Project: A00892244Lab7
 * File: Lab7.java
 * Date: March 1, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

import a00892244.data.Player;
import a00892244.database.PlayerDao;
import a00892244.database.Database;
import a00892244.utils.ApplicationException;
import a00892244.utils.PlayerReader;
import a00892244.utils.PlayerReport;
import a00892244.utils.Validator;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab7 {

	private static Database database;
	private PlayerDao playerDao;

	private final Properties dbProperties;
	private Connection connection;
	public static final String DB_PROPERTIES_FILENAME = "db.properties";

	private static final Logger LOG = LogManager.getLogger(Lab7.class);
	LocalDateTime startDate = LocalDateTime.now();

	/**
	 * @param
	 * 
	 */
	public static void main(String[] args) {

		File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);
		if (!dbPropertiesFile.exists()) {
			showUsage();
			System.exit(-1);
		}

		try {
			Lab7 lab7 = new Lab7(dbPropertiesFile);
			lab7.run();
		} catch (Exception e) {
			LOG.error(e.getMessage());	
			LOG.error("Terminating Program");
			System.exit(-1);
		} finally {
			database.shutdown();
		}

	}

	/**
	 * 
	 * @param dbPropertiesFile
	 * @throws IOException
	 */
	private Lab7(File dbPropertiesFile) throws IOException {
		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));

		database = new Database(dbProperties);
	}

	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	private void run() throws ClassNotFoundException, SQLException, ApplicationException {
		PlayerReport playerReport = new PlayerReport();
		PlayerReader playerReader;
		connection = database.getConnection();
		playerDao = new PlayerDao(database);

		try {
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
				
				System.out.println("\nRaw Data");
				playerReport.printReport(playerDao.selectAll());
				System.out.println("\nsort by birth date");
				playerReport.printReport(playerDao.sortByBirthDate());
				System.out.println("\nsort by birth date desc");
				playerReport.printReport(playerDao.sortDescByBirthDateDesc());
				LOG.info("Writing player report to players_report.txt");
				playerReport.writeReport(playerDao.sortDescByBirthDateDesc(), "\n\nSort by Birthdate (Descending)", startDate, "players_report.txt");
			
				System.out.println("\nUpdating #1");
				playerDao.update(new Player(1, "asdf", "asdf", "asdf@asdf.asdf", "asdf", Validator.validateBirthdate("21120101")));
				playerReport.printReport(playerDao.selectAll());
				
				System.out.println("\nDeleting #3");
				playerDao.delete(3);
				playerReport.printReport(playerDao.selectAll());
				

			} catch (Exception e) {
				throw new ApplicationException("Read Error:  " + e.getMessage());

			}

		} catch (SQLException e) {
			throw new ApplicationException("SQL Exception: " + e.getMessage());
		} finally {
			connection.close();
		}


	}

	/**
	 * 
	 */
	private static void showUsage() {
		LOG.info("The database properties filename must be passed in the commandline.");
	}
}
