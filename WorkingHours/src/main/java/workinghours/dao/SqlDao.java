package workinghours.dao;

import java.sql.SQLException;

public interface SqlDao<T> {

    T create(T object) throws SQLException;

}
