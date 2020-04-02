package ohte2020.WorkingHours.dao;

import java.util.List;

import ohte2020.WorkingHours.entities.WorkhourEvent;

public interface WorkhourEventDao {

	WorkhourEvent create(WorkhourEvent event) throws Exception;
	
	List<WorkhourEvent> getAll();

}
