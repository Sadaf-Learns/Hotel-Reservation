import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookingManager {
    private BookingDAO bookingDAO;
    private RoomDAO roomDAO;
    private BookingRepository bookingRepository;

    private ArrayList<Booking>bookings;
    public BookingManager() throws SQLException {
        this.bookingDAO = new BookingDB();
        this.roomDAO = new RoomDB();
        UserDAO userDAO = new UserDB();
        this.bookingRepository = new BookingRepository(bookingDAO,
                roomDAO, userDAO);
        this.bookings = new ArrayList<>(bookingRepository.getAllBookings());
    }

    public boolean isRoomAvailable(int roomId,LocalDate checkInDate,LocalDate checkOutDate){
        for (Booking b:bookings){
            if (b.getRoom().getRoomId()==roomId){
                if(checkInDate.isBefore(b.getCheckOutDate())
                    && checkOutDate.isAfter(b.getCheckInDate())){
                return false;
                }
            }
        }
        return true;
    }
    public boolean createBooking(User user, Room room, LocalDate checkInDate,LocalDate checkOutDate,
                              double totalPrice,double depositAmount) throws SQLException {
        if(!isRoomAvailable(room.getRoomId(),checkInDate,checkOutDate)){
            return false;}
            if (!user.withdraw(depositAmount)){
                return false;}

            Booking newBooking=new Booking(room,user,checkInDate,
                    checkOutDate,totalPrice,depositAmount,LocalDate.now());
            bookingDAO.saveBooking(newBooking);
            roomDAO.updateRoomStatus(room.getRoomId(),true);
            bookings.add(newBooking);
            user.addReservation(newBooking);
            return true;
    }
    public boolean payRemaining(Booking booking) throws SQLException{
        User user=booking.getUser();
        double amount=booking.getRemainingAmount();
        if(!user.withdraw(amount)){
            return false;
        }
        booking.setRemainingAmount(0);
        booking.setPaymentStatus(Booking.PaymentStatus.PAID);
        bookingDAO.updatePaymentStatus(booking);
        return true;
    }
}
