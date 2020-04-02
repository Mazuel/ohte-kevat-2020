package ohte2020.WorkingHours.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ohte2020.WorkingHours.entities.User;

public class FileUserDao implements UserDao {

	private String file;
	private List<User> users;

	public FileUserDao(String file) throws Exception {
		users = new ArrayList<>();
		this.file = file;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			BufferedReader br = new BufferedReader(new FileReader(file));
			users = new ArrayList<User>(Arrays.asList(gson.fromJson(br, User[].class)));
		} catch (Exception e) {
			FileWriter fr = new FileWriter(new File(file));
			fr.close();
		}
	}

	@Override
	public User create(User user) throws Exception {
		users.add(user);
		save();
		return user;
	}

	private void save() throws Exception {
		Writer writer = new FileWriter(file);
		Gson gson = new GsonBuilder().create();
		gson.toJson(users, writer);
		writer.flush();
		writer.close();
	}

	@Override
	public User findByUsername(String username) {
		return users.stream()
				.filter(u -> u.getUsername()
				.equals(username))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<User> getAll() {
		return users;
	}

}
