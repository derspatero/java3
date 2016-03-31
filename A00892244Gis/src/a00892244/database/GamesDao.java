/**
 * Project: A00892244Gis
 * File: PlayerDao.java
 * Date: Mar 1, 2016
 * Time: 8:43:46 PM
 */

package a00892244.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import a00892244.data.Game;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class GamesDao extends Dao {

	public static final String TABLE_NAME = "games";
	private ResultSet resultSet;

	/**
	 * 
	 */
	public GamesDao() {
		super(TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("create table %s(%s VARCHAR(8), %s VARCHAR(16), %s VARCHAR(16), primary key (%s) )", tableName, Fields.ID, Fields.NAME,
				Fields.PRODUCER, Fields.ID);
		LOG.info(createStatement);
		super.create(createStatement);
	}

	public void add(Game game) throws SQLException {
		String insertString = String.format("insert into %s values('%s', '%s', '%s')", tableName, //
				game.getId(), //
				game.getName(), //
				game.getProducer());
		executeUpdate(insertString);
	}

	public void update(Game game) throws SQLException {
		String insertString = String.format("update %s set " + Fields.NAME + "='%s', " + Fields.PRODUCER + "='%s' WHERE " + Fields.ID + "='%s'", tableName, //
				game.getName(), //
				game.getProducer(), //
				game.getId());
		executeUpdate(insertString);
	}

	public void delete(int gameID) throws SQLException {
		String deleteString = String.format("delete from %s where " + Fields.ID + "='" + gameID + "'", tableName);
		executeUpdate(deleteString);
	}

	public List<Game> selectAll() throws SQLException, Exception {
		return getGamesByQuery("SELECT * FROM %s");
	}

	public List<Game> getGameById(int id) throws SQLException, Exception {
		return getGamesByQuery("SELECT * FROM %s WHERE " + Fields.ID + "='" + id + "'");
	}

	public List<Game> getGamesByQuery(String sqlStatement) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		List<Game> game = new ArrayList<Game>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format(sqlStatement, tableName);
			resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				game.add(nextGameResult());
			}

		} finally {
			close(statement);
		}
		return game;
	}

	public Game nextGameResult() throws SQLException {
		Game game = new Game();
		game.setId(resultSet.getString(Fields.ID.name()));
		game.setName(resultSet.getString(Fields.NAME.name()));
		game.setProducer(resultSet.getString(Fields.PRODUCER.name()));
		return game;
	}

	public enum Fields {

		ID(1), NAME(2), PRODUCER(3);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
