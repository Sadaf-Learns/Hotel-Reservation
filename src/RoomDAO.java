import java.sql.SQLException;

public interface RoomDAO {
    void updateRoomStatus(int roomID,boolean isReserved)throws SQLException;
    Room getRoomById(int roomID) throws SQLException;
}
