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
    public User getUserById(int userId) throws SQLException{
        String sql="SELECT * FROM users WHERE user_id=?";
        try (Connection connection=MyJDBC.getConnection();
        PreparedStatement statement= connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                String name= resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                String job = resultSet.getString("job");
                String education = resultSet.getString("education");
                String phoneNumber = resultSet.getString("phone_number");
                String nationalId = resultSet.getString("national_id");
                User user=new User(name, lastName, job, education, phoneNumber, nationalId);//database variables
                user.setUserId(resultSet.getInt("user_id"));
                return user;

            }
            else {
                return null;
            }

        }
    }
}
