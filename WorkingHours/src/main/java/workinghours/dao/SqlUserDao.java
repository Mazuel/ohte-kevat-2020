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
		stmt.close();
		endConnection();
		return object;
	}

	@Override
	public User read(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer key) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> list() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
