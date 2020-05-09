package workinghours.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import workinghours.entities.User;
import workinghours.entities.WorkhourEvent;

public class SqlEventDao extends SqlConnection implements WorkhourEventDao {

	public SqlEventDao(String dbname) {
		super(dbname);
	}

	@Override
	public WorkhourEvent create(WorkhourEvent object) throws SQLException {
		String sql = "INSERT INTO WorkhourEvent (user_id, description, insertDate, hours) values (?,?,?,?)";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, object.getUser().getId());
		stmt.setString(2, object.getDescription());
		stmt.setTimestamp(3, object.getInsertDate());
		stmt.setDouble(4, object.getHours());
		stmt.executeUpdate();
		endConnection();
		return object;

	}

	@Override
	public WorkhourEvent read(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(WorkhourEvent object) throws SQLException {
		// TODO Auto-generated method stub
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
	public List<WorkhourEvent> getAllByUsername(User user) throws SQLException {
		List<WorkhourEvent> events = new ArrayList<>();
		String sql = "SELECT * FROM WorkhourEvent WHERE user_id = ?";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, user.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			events.add(new WorkhourEvent(user, rs.getString("description"), rs.getDouble("hours")));
		}
		stmt.close();
		endConnection();
		// TODO Auto-generated method stub
		return events;
	}
}
