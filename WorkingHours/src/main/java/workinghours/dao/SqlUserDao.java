package workinghours.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import workinghours.entities.User;

public class SqlUserDao implements SqlDao<User>, UserDao {
	
    private final String dbURL;
    private Connection connection;
    private PreparedStatement stmt;
    private Statement s;
    
    public SqlUserDao(String dbUrl) {
    	this.dbURL = dbUrl;
    }
	
	
    @Override
	public User findByUsername(String username) {
		return null;
	}
	
	
    private void startConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbURL);
        s = connection.createStatement();
    }

    private void endConnection() throws SQLException {
        s.close();
        connection.close();
    }

	@Override
	public void create(User object) throws SQLException {
		String sql = "INSERT INTO User (?, ?)";
		startConnection();
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, object.getName());
		stmt.setString(2, object.getUsername());
		stmt.executeUpdate();
		stmt.close();
		endConnection();
	}

	@Override
	public User read(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User update(User object) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Integer key) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<User> list() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
