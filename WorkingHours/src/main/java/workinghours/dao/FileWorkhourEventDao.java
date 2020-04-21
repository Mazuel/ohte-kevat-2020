package workinghours.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public class FileWorkhourEventDao implements WorkhourEventDao {

	private String file;
	private List<WorkhourEvent> workhourEvents;

	public FileWorkhourEventDao(String file) throws Exception {
		workhourEvents = new ArrayList<>();
		this.file = file;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			BufferedReader br = new BufferedReader(new FileReader(file));
			workhourEvents = new ArrayList<WorkhourEvent>(Arrays.asList(gson.fromJson(br, WorkhourEvent[].class)));
		} catch (Exception e) {
			FileWriter fr = new FileWriter(new File(file));
			fr.close();
		}
	}

	@Override
	public WorkhourEvent create(WorkhourEvent event) throws Exception {
		workhourEvents.add(event);
		save();
		return event;
	}
	
	private void save() throws Exception {
		Writer writer = new FileWriter(file);
		Gson gson = new GsonBuilder().create();
		gson.toJson(workhourEvents, writer);
		writer.flush();
		writer.close();
	}

	@Override
	public List<WorkhourEvent> getAllByUsername(User user) {
		return workhourEvents.stream()
				.filter(e -> e.getUser().getUsername().equals(user.getUsername()))
				.collect(Collectors.toList());
	}

}
