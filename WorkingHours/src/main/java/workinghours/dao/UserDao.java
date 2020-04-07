package workinghours.dao;

import java.util.List;

import workinghours.entities.User;

public interface UserDao {
	
	User create(User user) throws Exception;
	
	User findByUsername(String username);
	
	List<User> getAll();

}
