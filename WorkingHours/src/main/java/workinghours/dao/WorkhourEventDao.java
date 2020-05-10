package workinghours.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public interface WorkhourEventDao extends SqlDao<WorkhourEvent> {
	
	List<WorkhourEvent> getAllByUsername(User user) throws SQLException;
	
	List<WorkhourEvent> getAllByDate(User user, LocalDate time) throws SQLException; 
	
	void delete(Integer key) throws SQLException;
}
