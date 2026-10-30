import java.sql.SQLException;
import java.util.List;

public interface BookingDAO {
    void updatePaymentStatus(Booking booking) throws SQLException;
    List<BookingData> getAllBookings() throws SQLException;
    void saveBooking(Booking booking) throws SQLException;
}
