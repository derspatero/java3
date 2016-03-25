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
import a00892244.data.Score;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class ScoresDao extends Dao {

	public static final String TABLE_NAME = "scores";
	private ResultSet resultSet;

	/**
	 * 
	 */
	public ScoresDao() {
		super(TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("create table %s(%s INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), %s VARCHAR(8), %s VARCHAR(8), %s VARCHAR(8), primary key (%s) )",
				tableName, Fields.UIDPK, Fields.PERSONA_ID, Fields.GAME_ID, Fields.WIN, Fields.UIDPK);
		System.out.println(createStatement);
		super.create(createStatement);
	}

	public void add(Score score) throws SQLException {
		String insertString = String.format("insert into %s(%s, %s, %s) values('%s', '%s', '%s')", tableName, //
				Fields.GAME_ID,
				Fields.PERSONA_ID,
				Fields.WIN,
				score.getGameId(), //
				score.getPersonaId(), //
				score.getWin());
		executeUpdate(insertString);
	}

	public void update(Score score) throws SQLException {
		String insertString = String.format(
				"update %s set " + Fields.GAME_ID + "='%s', " + Fields.PERSONA_ID + 
				"='%s' WHERE " + Fields.WIN + "='%s'",
				tableName, //
				score.getGameId(), //
				score.getPersonaId(), //
				score.getWin());
		executeUpdate(insertString);
	}

	public void delete(int scoreId) throws SQLException {
		String deleteString = String.format("delete from %s where " + Fields.UIDPK + "='" + scoreId + "'", tableName);
		executeUpdate(deleteString);
	}

	public List<Score> selectAll() throws SQLException, Exception {
		return getScoresByQuery("SELECT * FROM %s");
	}
	
	public List<Score> getScoreById(int id) throws SQLException, Exception {
		return getScoresByQuery("SELECT * FROM %s WHERE " + Fields.UIDPK + "='" + id + "'");
	}

	public List<Score> getScoresByQuery(String sqlStatement) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		List<Score> score = new ArrayList<Score>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format(sqlStatement, tableName);
			resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				score.add(nextScoreResult());
			}

		} finally {
			close(statement);
		}
		return score;
	}

	public Score nextScoreResult() throws SQLException {
		Score score = new Score();
		score.setGameId(resultSet.getString(Fields.GAME_ID.name()));
		score.setPersonaId(Integer.parseInt(resultSet.getString(Fields.PERSONA_ID.name())));
		score.setWin(resultSet.getString(Fields.WIN.name()));
		return score;
	}


	public enum Fields {

		UIDPK(1), PERSONA_ID(2), GAME_ID(3), WIN(4);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
