package workinghours.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class WorkhourEvent {

	private String description;
	private LocalDateTime insertDate;
	private double hours;
	private User user;

	public WorkhourEvent(User user, String description, double hours) {
		this.user = user;
		this.description = description;
		this.hours = hours;
		this.insertDate = LocalDateTime.now();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDateTime insertDate) {
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
