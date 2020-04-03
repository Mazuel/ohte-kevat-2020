package ohte2020.WorkingHours.entities;

public class User {

	private String name;
	private String username;
	private String password;

	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
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
