package ohte2020.WorkingHours.dao;

import java.util.List;

import ohte2020.WorkingHours.entities.User;

public interface UserDao {
	
	User create(User user) throws Exception;
	
	User findByUsername(String username) throws Exception;
	
	List<User> getAll();

}
