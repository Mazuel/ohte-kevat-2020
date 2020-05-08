package workinghours.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

	public static void InitializeDb(String dbUrl) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbUrl);
		Statement statement = connection.createStatement();

		statement.execute(
				"CREATE TABLE IF NOT EXISTS User (id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30), username VARCHAR(25))");
		statement.execute(
				"CREATE TABLE IF NOT EXISTS WorkhourEvent (id INTEGER AUTO_INCREMENT PRIMARY KEY, user_id INTEGER, description VARCHAR(255), insertDate DATETIME, hours DECIMAL(6,2), FOREIGN KEY (user_id) REFERENCES User(id))");
		statement.close();
		connection.close();

	}

}
