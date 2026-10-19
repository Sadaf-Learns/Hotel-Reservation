import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, last_name," +
                "job, education, phone_number, national_id) VALUES (?, ?, ?, ?, ?, ?)" +
                "RETURNING user_Id";
        try(Connection connection=MyJDBC.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1,user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getJob());
            statement.setString(4, user.getEducation());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getNationalId());
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()){
                int userId=resultSet.getInt("user_id");
                user.setUserId(userId);
            }
        }

        catch (SQLException e){
            System.out.println("Could not save user.");
        }




    }
}
