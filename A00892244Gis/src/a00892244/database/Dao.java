/**
 * Project: A00892244Lab7
 * File: Dao.java
 * Date: Mar 1, 2016
 * Time: 8:43:46 PM
 */

package a00892244.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Edward Lambke, A00892244
 *
 */

public abstract class Dao {

	protected final Database database;
	protected final String tableName;
	
	protected Dao(Database database) {
		this.database = database;
		tableName = null;
	}

	protected Dao(Database database, String tableName) {
		this.database = database;
		this.tableName = tableName;
	}

	public abstract void create() throws SQLException;

	protected void create(String createStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(createStatement);
		} finally {
			close(statement);
		}
	}

	protected void add(String updateStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(updateStatement);
		} finally {
			close(statement);
		}
	}

	public void drop() throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			if (DbUtil.tableExists(connection, tableName)) {
				statement.executeUpdate("drop table " + tableName);
			}
		} finally {
			close(statement);
		}
	}
	
	public void dropView(String view) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			if (DbUtil.tableExists(connection, view)) {
				statement.executeUpdate("drop view " + view);
			}
		} finally {
			close(statement);
		}
	}

	public void executeUpdate(String sqlString) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sqlString);
		} finally {
			close(statement);
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
