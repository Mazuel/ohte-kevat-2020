package workinghours.entities;

import java.util.Date;

public class WorkhourEvent {

	private String description;
	private Date insertDate;
	private int hours;
	private User user;

	public WorkhourEvent(User user, String description, int hours) {
		this.user = user;
		this.description = description;
		this.hours = hours;
		this.insertDate = new Date();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
