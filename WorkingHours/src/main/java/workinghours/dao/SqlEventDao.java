package workinghours.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
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
	public void update(WorkhourEvent object) throws SQLException {
	}

	@Override
	public void delete(Integer key) throws SQLException {
		String sql = "DELETE FROM WorkhourEvent WHERE id = ?";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, key);
		stmt.executeUpdate();
		endConnection();
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
			events.add(new WorkhourEvent(rs.getInt("id"), rs.getTimestamp("insertDate"), user, rs.getString("description"), rs.getDouble("hours")));
		}
		endConnection();
		return events;
	}

	@Override
	public List<WorkhourEvent> getAllByDate(User user, LocalDate date) throws SQLException {
		List<WorkhourEvent> events = new ArrayList<>();
		Timestamp beginning = Timestamp.valueOf(date.atTime(LocalTime.MIN));
		Timestamp end = Timestamp.valueOf(date.atTime(LocalTime.MAX));
		String sql = "SELECT * FROM WorkhourEvent WHERE user_id = ? AND insertDate BETWEEN ? AND ?";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, user.getId());
		stmt.setTimestamp(2, beginning);
		stmt.setTimestamp(3, end);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			events.add(new WorkhourEvent(rs.getInt("id"), rs.getTimestamp("insertDate"), user, rs.getString("description"), rs.getDouble("hours")));
		}
		endConnection();
		return events;
	}
}
