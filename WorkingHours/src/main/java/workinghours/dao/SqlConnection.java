package workinghours.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnection {

	private String dbURL;
	public Connection connection;
	public PreparedStatement stmt;
	private Statement s;

	public SqlConnection(String dbname) {
		this.dbURL = dbname;
	}

	public void startConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + dbURL);
		s = connection.createStatement();
	}

	public void endConnection() throws SQLException {
		stmt.close();
		s.close();
		connection.close();
	}

}
