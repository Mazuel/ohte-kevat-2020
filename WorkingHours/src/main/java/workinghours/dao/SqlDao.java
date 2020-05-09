package workinghours.dao;

import java.sql.SQLException;
import java.util.List;

public interface SqlDao<T> {

    T create(T object) throws SQLException;

    T read(Integer key) throws SQLException;
    void update(T object) throws SQLException;

    void delete(Integer key) throws SQLException;

    List<T> list() throws SQLException;
    
}
