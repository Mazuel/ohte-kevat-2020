package ohte2020.WorkingHours.service;

import ohte2020.WorkingHours.dao.UserDao;
import ohte2020.WorkingHours.entities.User;

public class WorkhourService {

	private UserDao userDao;
	private User currentUser;

	public WorkhourService(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean createUser(String username, String password) {
		User user = new User(username, "Maatti Mekkelsson", password);
		try {
			userDao.create(user);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
