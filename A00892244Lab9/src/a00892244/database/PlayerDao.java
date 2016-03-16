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

import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PlayerDao extends Dao {

	public static final String TABLE_NAME = "player";
	private ResultSet resultSet;

	/**
	 * 
	 */
	public PlayerDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("create table %s(%s VARCHAR(8), %s VARCHAR(32), %s VARCHAR(32), %s VARCHAR(64), %s VARCHAR(32), %s DATE, primary key (%s) )",
				tableName, Fields.IDENTIFIER, Fields.FIRSTNAME, Fields.LASTNAME, Fields.EMAILADDRESS, Fields.GAMERTAG, Fields.BIRTHDATE, Fields.IDENTIFIER);
		System.out.println(createStatement);
		super.create(createStatement);
	}

	public void add(Player player) throws SQLException {
		String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s', '%s')", tableName, //
				player.getIdentifier(), //
				player.getFirstName(), //
				player.getLastName(), //
				player.getEmailAddress(), //
				player.getGamerTag(), //
				player.getBirthdate());
		executeUpdate(insertString);
	}

	public void update(Player player) throws SQLException {
		String insertString = String.format(
				"update %s set " + Fields.FIRSTNAME + "='%s', " + Fields.LASTNAME + "='%s', " + Fields.EMAILADDRESS + "='%s', " + Fields.GAMERTAG + "='%s', " + Fields.BIRTHDATE
						+ "='%s' WHERE " + Fields.IDENTIFIER + "='%s'",
				tableName, //
				player.getFirstName(), //
				player.getLastName(), //
				player.getEmailAddress(), //
				player.getGamerTag(), //
				player.getBirthdate(), player.getIdentifier());
		executeUpdate(insertString);
	}

	public void delete(int playerUID) throws SQLException {
		String insertString = String.format("delete from %s where " + Fields.IDENTIFIER + "='" + playerUID + "'", tableName);
		executeUpdate(insertString);
	}

	public List<Player> selectAll() throws SQLException, Exception {
		return getPlayersByQuery("SELECT * FROM %s");
	}

	public List<Player> sortDescByBirthDateDesc() throws SQLException, Exception {
		return getPlayersByQuery("SELECT * FROM %s ORDER BY BIRTHDATE DESC");
	}

	public List<Player> sortByBirthDate() throws SQLException, Exception {
		return getPlayersByQuery("SELECT * FROM %s ORDER BY BIRTHDATE");
	}

	public List<Player> getPlayer(String gamertag) throws SQLException, Exception {
		return getPlayersByQuery("SELECT * FROM %s WHERE " + Fields.GAMERTAG + " = '" + gamertag + "'");
	}

	public List<Player> getPlayersByQuery(String sqlStatement) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		List<Player> players = new ArrayList<Player>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format(sqlStatement, tableName);
			resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				players.add(nextPlayerResult());
			}

		} finally {
			close(statement);
		}
		return players;
	}

	public Player nextPlayerResult() throws SQLException {
		Player player = new Player();
		player.setIdentifier(Integer.parseInt(resultSet.getString(Fields.IDENTIFIER.name())));
		player.setFirstName(resultSet.getString(Fields.FIRSTNAME.name()));
		player.setLastName(resultSet.getString(Fields.LASTNAME.name()));
		player.setEmailAddress(resultSet.getString(Fields.EMAILADDRESS.name()));
		player.setBirthdate(LocalDate.parse(resultSet.getString(Fields.BIRTHDATE.name())));
		player.setGamerTag(resultSet.getString(Fields.GAMERTAG.name()));
		return player;
	}

	public List<String> getGamertags() throws SQLException {
		Connection connection;
		Statement statement = null;
		List<String> results = new ArrayList<String>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT %s FROM %s", Fields.GAMERTAG, tableName);
			resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				results.add(resultSet.getString(Fields.GAMERTAG.name()));
			}

		} finally {
			close(statement);
		}
		return results;
	}

	public enum Fields {

		IDENTIFIER(1), FIRSTNAME(2), LASTNAME(3), EMAILADDRESS(4), GAMERTAG(5), BIRTHDATE(6);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
