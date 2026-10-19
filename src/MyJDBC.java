import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
    public class MyJDBC {
        public static void main(String[] args) throws SQLException {

            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/JavaHotel",
                    "postgres",
                    "sadaf1380"
            );

            System.out.println("Connected successfully!");

            connection.close();
        }
        public static Connection getConnection() throws SQLException{
            Connection connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/JavaHotel",
                    "postgres",
                    "sadaf1380");
            return connection;

        }
    }
