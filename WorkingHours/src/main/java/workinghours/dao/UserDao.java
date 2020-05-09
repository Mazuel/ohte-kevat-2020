package workinghours.dao;

import java.sql.SQLException;

import workinghours.entities.User;

public interface UserDao extends SqlDao<User> {

	User findByUsername(String username) throws SQLException;

}
