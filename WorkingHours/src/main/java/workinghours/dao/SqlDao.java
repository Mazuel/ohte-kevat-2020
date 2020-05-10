package workinghours.dao;

import java.sql.SQLException;

public interface SqlDao<T> {

    T create(T object) throws SQLException;

    void update(T object) throws SQLException;

    void delete(Integer key) throws SQLException;

}
