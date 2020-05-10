package workinghours.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
	
	private static final String dbPath = "jdbc:sqlite:";

	public static void createDatabase(String dbUrl) throws SQLException {
		Connection connection = DriverManager.getConnection(dbPath + dbUrl);
		Statement statement = connection.createStatement();

		statement.execute("CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(30), username VARCHAR(25))");
		statement.execute("CREATE TABLE IF NOT EXISTS WorkhourEvent (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, description VARCHAR(255), insertDate DATETIME, hours DECIMAL(6,2), FOREIGN KEY (user_id) REFERENCES User(id))");
		statement.close();
		connection.close();

	}

}
