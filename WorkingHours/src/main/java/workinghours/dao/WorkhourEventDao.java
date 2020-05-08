package workinghours.dao;

import java.util.List;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public interface WorkhourEventDao {
	
	List<WorkhourEvent> getAllByUsername(User user);
}
