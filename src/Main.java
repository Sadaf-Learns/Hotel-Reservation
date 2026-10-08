import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        User user = new User("Ali", "Ahmadi", "employee", "bachelor",
                "09158762565", "022596483156");
        Room room = new Room(101, 2, 2500, true);
        BookingManager bookingManager = new BookingManager();
        LocalDate checkIn = LocalDate.of(2026, 10, 10);
        LocalDate checkout = LocalDate.of(2026, 10, 15);
        long numberOfNights = ChronoUnit.DAYS.between(checkIn, checkout);
        double totalPrice = room.getRoomPrice() * numberOfNights;
        double depositAmount = totalPrice*0.2;
        int generatedBookingId = 1;
        user.deposit(3000);
        bookingManager.createBooking(
                user,
                room,
                checkIn,
                checkout,
                totalPrice,
                depositAmount,
                generatedBookingId
        );
        System.out.println(user.getReservations().get(0).getTotalPrice());
        Booking booking = user.getReservations().getFirst();
        System.out.println(booking.getRemainingAmount());
        user.deposit(booking.getRemainingAmount());
        booking.payRemaining(user);


    }
}