import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDB {
    public void saveRoom(Room room)throws SQLException {
        String sql="INSERT INTO rooms(bed_count,room_price,is_reserved)" +
                "VALUES(?,?,?)";
        try(Connection connection=MyJDBC.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,room.getRoomBed());
            statement.setDouble(2,room.getRoomPrice());
            statement.setBoolean(3,room.getisReserved());
            statement.executeUpdate();
        }

    }
    public List<Room> getAllRooms()throws SQLException{
        List<Room> rooms=new ArrayList<>();
        String sql="SELECT * FROM rooms";
        try(Connection connection=MyJDBC.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) { //go to next row if its exist
                int roomID = resultSet.getInt("room_id");
                int roomBed = resultSet.getInt("bed_count");
                double roomPrice = resultSet.getDouble("room_price");
                boolean isReserved = resultSet.getBoolean("is_reserved");
                Room room = new Room(roomID, roomBed, roomPrice, isReserved);
                rooms.add(room);
            }
        }
        return rooms;
    }
    public Room getRoomById(int roomId) throws SQLException{
        String sql="SELECT * FROM rooms WHERE room_id=?";
        try(Connection connection=MyJDBC.getConnection();
        PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,roomId);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                int roomID = resultSet.getInt("room_id");
                int roomBed = resultSet.getInt("bed_count");
                double roomPrice = resultSet.getDouble("room_price");
                boolean isReserved = resultSet.getBoolean("is_reserved");
                Room room=new Room(roomID, roomBed, roomPrice, isReserved);
                return room;
            }
            else{
                return null;
            }
        }

    }
    public void updateRoomStatus(int roomID,Boolean isReserved) throws SQLException{
        String sql = "UPDATE rooms SET is_reserved = ? WHERE room_id = ?";

        try (Connection connection = MyJDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, isReserved);
            statement.setInt(2, roomID);
            statement.executeUpdate();
        }
    }
}
