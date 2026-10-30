import java.sql.SQLException;

public interface UserDAO {
    void saveUser(User user) throws SQLException;
    User getUserById(int userId)throws SQLException;
}
