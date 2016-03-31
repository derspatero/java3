/**
 * Project: A00892244Gis
 * File: Dao.java
 * Date: Mar 1, 2016
 * Time: 8:43:46 PM
 */

package a00892244.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Edward Lambke, A00892244
 *
 */

public abstract class Dao {

	protected final Database database;
	protected final String tableName;

	protected static final Logger LOG = LogManager.getLogger(Dao.class);

	protected Dao() {
		database = Database.getTheInstance();
		tableName = null;
	}

	protected Dao(String tableName) {
		database = Database.getTheInstance();
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
			if (tableExist()) {
				statement.executeUpdate("drop table " + tableName);
			}
		} finally {
			close(statement);
		}
	}

	public boolean tableExist() throws SQLException {
		Connection connection = database.getConnection();
		return DbUtil.tableExists(connection, tableName);
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
			LOG.info("executed update: " + sqlString);
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
			LOG.error(e.getMessage());
		}
	}

}
