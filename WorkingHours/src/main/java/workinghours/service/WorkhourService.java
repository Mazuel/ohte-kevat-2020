package workinghours.service;

import java.sql.SQLException;
import java.util.Collections;
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
		try {
			return whEventDao.getAllByUsername(currentUser);
		} catch (SQLException e) {
			return Collections.emptyList();
		}
	}

	public boolean login(String username){
		try {
			currentUser = userDao.findByUsername(username);
		} catch (SQLException e) {
			return false;
		}
		if (currentUser == null) {
			return false;
		}
		return true;
	}

	public boolean createUser(String name, String username) throws SQLException {
		if (userDao.findByUsername(username) != null) {
			return false;
		}

		User user = new User(name, username);
		try {
			userDao.create(user);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}
