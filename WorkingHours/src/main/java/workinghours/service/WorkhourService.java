package workinghours.service;

import java.util.List;

import workinghours.dao.UserDao;
import workinghours.dao.WorkhourEventDao;
import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public class WorkhourService {

	private UserDao userDao;
	private User currentUser;
	private WorkhourEventDao whEventDao;
	
	public WorkhourService(UserDao userDao, WorkhourEventDao whEventDao) {
		this.userDao = userDao;
		this.whEventDao = whEventDao;
	}
	
	public WorkhourEvent createWorkhourEvent(String description, double hours) throws Exception {
		return whEventDao.create(new WorkhourEvent(currentUser, description, hours));
	}
	
	public List<WorkhourEvent> getWorkhourEvents() {
		return whEventDao.getAllByUsername(currentUser);
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
