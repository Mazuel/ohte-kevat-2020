package ohteprojekti.workinghours.service;

import ohteprojekti.workinghours.dao.UserDao;
import ohteprojekti.workinghours.entities.User;

public class WorkhourService {

	private UserDao userDao;
	private User currentUser;

	public WorkhourService(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean login(String username, String password) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			return false;
		}
		if (user.getPassword().equals(password)) {
			currentUser = user;
			return true;
		}
		return false;
	}

	public boolean createUser(String name, String username, String password) {
		if (userDao.findByUsername(username) != null) {
			return false;
		}

		User user = new User(name, username, password);
		try {
			userDao.create(user);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
