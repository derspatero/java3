/**
 * Project: A00892244Lab10
 * File: Lab10.java
 * Date: March 22, 2016
 * Time: 10:32:18 AM
 */

package a00892244;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import a00892244.database.PlayerDao;
import a00892244.ui.MainFrame;
import a00892244.database.Database;
import a00892244.utils.ApplicationException;
import a00892244.utils.PlayerReader;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Lab10 {

	private static Database database;
	private static PlayerDao playerDao;

	private final Properties dbProperties;
	public static final String DB_PROPERTIES_FILENAME = "db.properties";

	private static final Logger LOG = LogManager.getLogger(Lab10.class);
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

			Lab10 lab10 = new Lab10(dbPropertiesFile);
			lab10.initializeDB();

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
						MainFrame frame = new MainFrame(playerDao);
						frame.setTitle("Lab10");
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
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
	private Lab10(File dbPropertiesFile) throws IOException {
		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));

		database = Database.getTheInstance();
		database.setProperties(dbProperties);
	}

	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ApplicationException
	 */
	private void initializeDB() throws ClassNotFoundException, SQLException, ApplicationException {
		PlayerReader playerReader;
		// connection = database.getConnection();
		playerDao = new PlayerDao();

		try {
			LOG.info("drop the tables if they exist");
			playerDao.drop();

			LOG.info("create the tables");
			playerDao.create();

			LOG.info("Reading from players.txt");

			playerReader = new PlayerReader("players.txt");

			while (playerReader.morePlayers()) {
				playerDao.add(playerReader.getNextPlayer());
			}

		} catch (Exception e) {
			throw new ApplicationException("Read Error:  " + e.getMessage());

		}

	}

	/**
	 * 
	 */
	private static void showUsage() {
		LOG.info("The database properties filename must be passed in the commandline.");
	}
}
