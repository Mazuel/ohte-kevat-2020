package ohte2020.WorkingHours.entities;

public class User {

	private String username;
	private String name;
	private String password;

	public User(String username, String password, String name) {
		this.username = username;
		this.name = name;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return name;
	}

	public void setLastName(String lastName) {
		this.name = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
