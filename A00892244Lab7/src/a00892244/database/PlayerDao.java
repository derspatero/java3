/**
 * Project: A00892244Lab7
 * File: PlayerDao.java
 * Date: Mar 1, 2016
 * Time: 8:43:46 PM
 */

package a00892244.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import a00892244.data.Player;
import a00892244.utils.ApplicationException;

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
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s', '%s')", tableName, //
					player.getIdentifier(), //
					player.getFirstName(), //
					player.getLastName(), //
					player.getEmailAddress(), //
					player.getGamerTag(), //
					player.getBirthdate());
			statement.executeUpdate(insertString);
			System.out.println(insertString);
		} finally {
			close(statement);
		}
	}

	public List<Player> selectAll() throws SQLException, Exception {
		return exicuteSQL("SELECT * FROM %s");
	}
	
	public List<Player> sortDescByBirthDateDesc() throws SQLException, Exception{
		return exicuteSQL("SELECT * FROM %s ORDER BY BIRTHDATE DESC");
	}
	
	public List<Player> sortByBirthDate() throws SQLException, Exception{
		return exicuteSQL("SELECT * FROM %s ORDER BY BIRTHDATE");
	}

	public List<Player> exicuteSQL(String sqlStatement) throws SQLException, Exception {
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

	public Player getNextPlayer() throws ApplicationException {
		Player player = new Player();

		try {

			player = new Player();
			player.setIdentifier(Integer.parseInt(resultSet.getString(Fields.IDENTIFIER.name())));
			player.setFirstName(resultSet.getString(Fields.FIRSTNAME.name()));
			player.setLastName(resultSet.getString(Fields.LASTNAME.name()));
			player.setEmailAddress(resultSet.getString(Fields.EMAILADDRESS.name()));
			player.setBirthdate(LocalDate.parse(resultSet.getString(Fields.BIRTHDATE.name())));
			player.setGamerTag(resultSet.getString(Fields.GAMERTAG.name()));

		} catch (Exception e) {
			throw new ApplicationException(e);
		}
		return player;
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
