package workinghours.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class WorkhourEvent {

	private String description;
	private Timestamp insertDate;
	private double hours;
	private User user;

	public WorkhourEvent(Timestamp insertTime, User user, String description, double hours) {
		this.user = user;
		this.description = description;
		this.hours = hours;
		this.insertDate = insertTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
