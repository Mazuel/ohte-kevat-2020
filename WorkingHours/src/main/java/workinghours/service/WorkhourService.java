package workinghours.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import workinghours.dao.UserDao;
import workinghours.dao.WorkhourEventDao;
import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public class WorkhourService {

	private UserDao userDao;
	private User loggedIn;
	private WorkhourEventDao whEventDao;

	public WorkhourService(UserDao userDao, WorkhourEventDao whEventDao) {
		this.userDao = userDao;
		this.whEventDao = whEventDao;
	}

	public WorkhourEvent createWorkhourEvent(LocalDate insertDate, String description, double hours) throws Exception {
		return whEventDao
				.create(new WorkhourEvent(Timestamp.valueOf(insertDate.atStartOfDay()), loggedIn, description, hours));
	}

	public boolean deleteItem(WorkhourEvent whEvent) {
		try {
			whEventDao.delete(whEvent.getId());
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public void logout() {
		loggedIn = null;
	}

	public List<WorkhourEvent> getWorkhourEvents() {
		try {
			return whEventDao.getAllByUsername(loggedIn);
		} catch (SQLException e) {
			return Collections.emptyList();
		}
	}

	public List<WorkhourEvent> getEventsByCurrentDate() {
		try {
			return whEventDao.getAllByDate(loggedIn, LocalDate.now());
		} catch (SQLException e) {
			return Collections.emptyList();
		}

	}

	public List<WorkhourEvent> getEventsBySelectedDate(LocalDate date) {
		try {
			return whEventDao.getAllByDate(loggedIn, date);
		} catch (SQLException e) {
			return Collections.emptyList();
		}

	}

	public boolean login(String username) {
		try {
			loggedIn = userDao.findByUsername(username);
		} catch (SQLException e) {
			return false;
		}
		if (loggedIn == null) {
			return false;
		}
		return true;
	}

	public boolean createUser(String name, String username) {
		User foundUser;
		try {
			foundUser = userDao.findByUsername(username);

			if (foundUser != null) {
				return false;
			}

			userDao.create(new User(name, username));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
