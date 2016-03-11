/**
 * Project: A00892244Lab7
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

		getLeaderBoardReportEntriesByQuery("", "");

		return entries;
	}

	public List<LeaderBoardReportEntry> getLeaderBoardReportEntriesByQuery(String filter, String sort) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		List<LeaderBoardReportEntry> reportEntries = new ArrayList<LeaderBoardReportEntry>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			statement.executeQuery("drop view win");
			statement.executeQuery("drop view loss");
			statement.executeQuery("drop view report");
			

			statement.executeQuery(
					"create view win as select count(*) as wins, ADMIN.games.name, ADMIN.persona.gamertag, ADMIN.persona.platform "
					+ "from ADMIN.persona join ADMIN.scores on ADMIN.persona.id = ADMIN.scores.persona_id "
					+ "join ADMIN.player on ADMIN.persona.playerid = ADMIN.player.identifier "
					+ "join ADMIN.games on ADMIN.scores.game_id = ADMIN.games.id "
					+ "where ADMIN.scores.win = 'WIN' group by ADMIN.persona.gamertag, ADMIN.games.name, ADMIN.persona.platform");

			statement.executeQuery(
					"CREATE VIEW loss as select count(*) as loses, ADMIN.games.name, ADMIN.persona.gamertag, ADMIN.persona.platform  "
					+ "from ADMIN.persona join ADMIN.scores on ADMIN.persona.id = ADMIN.scores.persona_id "
					+ "join ADMIN.player on ADMIN.persona.playerid = ADMIN.player.identifier "
					+ "join ADMIN.games on ADMIN.scores.game_id = ADMIN.games.id "
					+ "where ADMIN.scores.win = 'LOSE'  group by ADMIN.persona.gamertag, ADMIN.games.name, ADMIN.persona.platform");
			
			statement.execute("create view report as "
					+ "select case when win.wins is null then 0 else win.wins end as wins, "
					+ "case when loss.loses is null then 0 else loss.loses end as loses, "
					+ "win.name, win.gamertag, win.platform "
					+ "from win left outer JOIN loss on win.gamertag = loss.gamertag "
					+ "union "
					+ "select case when win.wins is null then 0 else win.wins end as wins, "
					+ "case when loss.loses is null then 0 else loss.loses end as loses, "
					+ "loss.name, loss.gamertag, loss.platform "
					+ "from win right outer JOIN loss on win.gamertag = loss.gamertag");
			
			resultSet = statement.executeQuery("select * from report " + filter + " " + sort);

			statement.executeQuery("drop view win");
			statement.executeQuery("drop view loss");
			statement.executeQuery("drop view report");
			
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
		entry.setWinLoss(resultSet.getString(Fields.WINS.name()) + ":" + resultSet.getString(Fields.WINS.name()));
		entry.setGameName(resultSet.getString(Fields.GAMENAME.name()));
		entry.setGamerTag(resultSet.getString(Fields.GAMERTAG.name()));
		entry.setPlatform(resultSet.getString(Fields.PLATFORM.name()));
		return entry;
	}

	public enum Fields {

		WINS(1), LOSES(2), GAMENAME(3), GAMERTAG(4), PLATFORM(5);

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
