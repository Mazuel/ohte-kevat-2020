package workinghours.dao;

import workinghours.entities.User;

public interface UserDao {
	
	User findByUsername(String username);

}
