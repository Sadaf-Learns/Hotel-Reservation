import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private BookingDAO bookingDAO;
    private RoomDAO roomDAO;
    private UserDAO userDAO;
    public BookingRepository(BookingDAO bookingDAO,RoomDAO roomDAO,UserDAO userDAO){
        this.bookingDAO=bookingDAO;
        this.roomDAO=roomDAO;
        this.userDAO=userDAO;
    }
    public List<Booking> getAllBookings()throws SQLException{
        List<BookingData> result=bookingDAO.getAllBookings();
        List<Booking> bookings=new ArrayList<>();
        for(BookingData bookingData:result){
            User user=userDAO.getUserById(bookingData.getUserId());
            Room room=roomDAO.getRoomById(bookingData.getRoomId());
            Booking booking=new Booking(room,user,bookingData.getCheckInDate(),
                    bookingData.getCheckOutDate(),bookingData.getTotalPrice(),
                    bookingData.getDepositAmount(),LocalDate.now());
            booking.setBookingId(bookingData.getBookingId());
            booking.setRemainingAmount(bookingData.getRemainingAmount());
            booking.setPaymentStatus(Booking.PaymentStatus.valueOf(bookingData.getPaymentStatus()));
            bookings.add(booking);
        }
        return bookings;

    }
}
