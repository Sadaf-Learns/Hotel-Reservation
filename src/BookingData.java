import java.time.LocalDate;

public class BookingData {
    private int bookingId;
    private int userId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private double depositAmount;
    private double remainingAmount;
    private String paymentStatus;

    public BookingData(int bookingId, int userId, int roomId, LocalDate checkInDate,
                       LocalDate checkOutDate, double totalPrice, double depositAmount,
                       double remainingAmount, String paymentStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.depositAmount = depositAmount;
        this.remainingAmount = remainingAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
