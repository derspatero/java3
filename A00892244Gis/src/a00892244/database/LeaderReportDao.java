/**Project: A00892244Lab7
 * File: PlayerDao.java
 * Date: Mar 1, 2016
 * Time: 8:43:46 PM
 */

package a00892244.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import a00892244.data.Game;
import a00892244.data.Persona;
import a00892244.data.Player;
import a00892244.utils.LeaderBoardReportEntry;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class LeaderReportDao {

	protected final Database database;
	private ResultSet resultSet;

	/**
	 * 
	 */
	public LeaderReportDao(Database database) {
		this.database = database;
	}

	public List<LeaderBoardReportEntry> selectAll() throws SQLException, Exception {
		List<LeaderBoardReportEntry> entries = new ArrayList<LeaderBoardReportEntry>();
		
		 getLeaderBoardReportEntriesByQuery(
				"SELECT ");
		
		return entries;
	}

	public List<LeaderBoardReportEntry> getLeaderBoardReportEntriesByQuery(String sqlStatement) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		List<LeaderBoardReportEntry> reportEntries = new ArrayList<LeaderBoardReportEntry>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement

			resultSet = statement.executeQuery(sqlStatement);

			while (resultSet.next()) {
				reportEntries.add(nextEntryResult());
			}

		} finally {
			close(statement);
		}
		return reportEntries;
	}

	public LeaderBoardReportEntry nextEntryResult() throws SQLException {
		LeaderBoardReportEntry entry = new LeaderBoardReportEntry();
		entry.setWinLoss(resultSet.getString(Fields.WINLOSS.name()));
		entry.setGameName(resultSet.getString(Fields.GAMENAME.name()));
		entry.setGamerTag(resultSet.getString(Fields.GAMERTAG.name()));
		entry.setPlatform(resultSet.getString(Fields.PLATFORM.name()));
		return entry;
	}


	public enum Fields {

		WINLOSS(1), GAMENAME(2), GAMERTAG(3), PLATFORM(4);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
