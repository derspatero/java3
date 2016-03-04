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

import a00892244.data.Persona;
import a00892244.data.Player;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class PersonaDao extends Dao {

	public static final String TABLE_NAME = "persona";
	private ResultSet resultSet;

	/**
	 * 
	 */
	public PersonaDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String createStatement = String.format("create table %s(%s VARCHAR(8), %s VARCHAR(8), %s VARCHAR(32), %s VARCHAR(16), primary key (%s) )",
				tableName, Fields.ID, Fields.PLAYERID, Fields.GAMERTAG, Fields.PLATFORM, Fields.ID);
		System.out.println(createStatement);
		super.create(createStatement);
	}

	public void add(Persona persona) throws SQLException {
		String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s')", tableName, //
				persona.getId(), //
				persona.getPlayerId(), //
				persona.getGamerTag(), //
				persona.getPlatform());
		executeUpdate(insertString);
	}

	public void update(Persona persona) throws SQLException {
		String insertString = String.format(
				"update %s set " + Fields.PLAYERID + "='%s', " + Fields.GAMERTAG + "='%s', " + Fields.PLATFORM + 
				"='%s' WHERE " + Fields.ID + "='%s'",
				tableName, //
				persona.getPlayerId(), //
				persona.getGamerTag(), //
				persona.getPlatform(), //
				persona.getId());
		executeUpdate(insertString);
	}

	public void delete(int personaID) throws SQLException {
		String deleteString = String.format("delete from %s where " + Fields.ID + "='" + personaID + "'", tableName);
		executeUpdate(deleteString);
	}

	public List<Persona> selectAll() throws SQLException, Exception {
		return getPersonasByQuery("SELECT * FROM %s");
	}

	public List<Persona> sortDescByBirthDateDesc() throws SQLException, Exception {
		return getPersonasByQuery("SELECT * FROM %s ORDER BY BIRTHDATE DESC");
	}
	

	public List<Persona> sortByBirthDate() throws SQLException, Exception {
		return getPersonasByQuery("SELECT * FROM %s ORDER BY BIRTHDATE");
	}
	
	public List<Persona> getPersonaById(int id) throws SQLException, Exception {
		return getPersonasByQuery("SELECT * FROM %s WHERE " + Fields.ID + "='" + id + "'");
	}

	public List<Persona> getPersonasByQuery(String sqlStatement) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		List<Persona> persona = new ArrayList<Persona>();
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format(sqlStatement, tableName);
			resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				persona.add(nextPersonaResult());
			}

		} finally {
			close(statement);
		}
		return persona;
	}

	public Persona nextPersonaResult() throws SQLException {
		Persona persona = new Persona();
		persona.setId(Integer.parseInt(resultSet.getString(Fields.ID.name())));
		persona.setPlayerId(Integer.parseInt(resultSet.getString(Fields.PLAYERID.name())));
		persona.setGamerTag(resultSet.getString(Fields.GAMERTAG.name()));
		persona.setPlatform(resultSet.getString(Fields.PLATFORM.name()));
		return persona;
	}


	public enum Fields {

		ID(1), PLAYERID(2), GAMERTAG(3), PLATFORM(4);

		private final int column;

		Fields(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	}

}
