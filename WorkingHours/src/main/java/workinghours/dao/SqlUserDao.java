package workinghours.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import workinghours.entities.User;

public class SqlUserDao extends SqlConnection implements UserDao {
	
	public SqlUserDao(String dbname) {
		super(dbname);
	}


	public User findByUsername(String username) throws SQLException {
		User userFound;
		String sql = "SELECT * FROM User WHERE username = ? LIMIT 1";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet user = stmt.executeQuery();

		if (user.next()) {
			userFound = new User(user.getInt("id"), user.getString("name"), user.getString("username"));
			endConnection();
			return userFound;
		}
		endConnection();

		return null;

	}

	@Override
	public User create(User object) throws SQLException {
		String sql = "INSERT INTO User (name, username) values (?, ?)";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, object.getName());
		stmt.setString(2, object.getUsername());
		stmt.executeUpdate();
		endConnection();
		return object;
	}


	@Override
	public void update(User object) throws SQLException {
		String sql = "UPDATE User SET name = ?, username = ? WHERE id = ?";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, object.getName());
		stmt.setString(2, object.getUsername());
		stmt.setInt(3, object.getId());
		stmt.executeUpdate();
		endConnection();
	}

	@Override
	public void delete(Integer key) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void clear() throws SQLException {
		String sql = "DELETE FROM User";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.executeUpdate();
		endConnection();
	}

}
