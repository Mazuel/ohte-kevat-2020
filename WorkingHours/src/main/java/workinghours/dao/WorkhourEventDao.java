package workinghours.dao;

import java.util.List;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public interface WorkhourEventDao {

	WorkhourEvent create(WorkhourEvent event) throws Exception;
	
	List<WorkhourEvent> getAllByUsername(User user);

}
