package workinghours.dao;

import java.sql.SQLException;
import java.util.List;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public class SqlEventDao implements SqlDao<WorkhourEvent>, WorkhourEventDao {
	
	

	@Override
	public void create(WorkhourEvent object) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WorkhourEvent read(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkhourEvent update(WorkhourEvent object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WorkhourEvent> list() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkhourEvent> getAllByUsername(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
