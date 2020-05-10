package workinghours.entities;

public class User {

	private int id;
	private String name;
	private String username;

	public User(String name, String username) {
		this.name = name;
		this.username = username;
	}

	public User(int id, String name, String username) {
		this.id = id;
		this.name = name;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
