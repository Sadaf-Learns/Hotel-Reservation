import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private Room room;
    private User user;
    private LocalDate bookingDate;
    private LocalDate checkInDate;
    private LocalDate CheckOutDate;
    private double totalPrice;
    private double depositAmount;
    private double remainingAmount;

    enum PaymentStatus {
        PENDING, PAID, CANCELLED
    }

    private PaymentStatus paymentStatus;

    public Booking(int bookingId, Room room, User user, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice, double depositAmount, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.room = room;
        this.user = user;
        this.checkInDate = checkInDate;
        this.totalPrice = totalPrice;
        this.CheckOutDate = checkOutDate;
        this.depositAmount = depositAmount;
        this.remainingAmount = totalPrice - depositAmount;
        this.paymentStatus = PaymentStatus.PENDING;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return CheckOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public User getUser() {
        return user;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public  boolean payRemaining(User user){
        if(user.withdraw(remainingAmount)){
            remainingAmount=0;
            paymentStatus=PaymentStatus.PAID;
            return true;
        }
        return false;
    }
}

