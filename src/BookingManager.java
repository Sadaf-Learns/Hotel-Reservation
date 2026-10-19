import java.time.LocalDate;
import java.util.ArrayList;

public class BookingManager {
    private ArrayList<Booking>bookings;
    public BookingManager(){

        this.bookings=new ArrayList<>();
    }
    public boolean isRoomAvailable(int roomId){
        for (Booking b:bookings){
            if (b.getRoom().getRoomId()==roomId){
                return false;
            }
        }
        return true;
    }
    public void createBooking(User user, Room room, LocalDate checkInDate,LocalDate checkOutDate,
                              double totalPrice,double depositAmount){
        if(isRoomAvailable(room.getRoomId())){
            if (user.withdraw(depositAmount)){
            Booking newBooking=new Booking(room,user,checkInDate,
                    checkOutDate,totalPrice,depositAmount,LocalDate.now());
            BookingDB bookingDB=new BookingDB();
            bookingDB.saveBooking(newBooking);
            bookings.add(newBooking);
            user.addReservation(newBooking);
        }}
    }
}
