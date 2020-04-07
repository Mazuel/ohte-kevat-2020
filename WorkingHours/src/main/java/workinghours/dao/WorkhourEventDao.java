package workinghours.dao;

import java.util.List;

import workinghours.entities.WorkhourEvent;

public interface WorkhourEventDao {

	WorkhourEvent create(WorkhourEvent event) throws Exception;
	
	List<WorkhourEvent> getAll();

}
