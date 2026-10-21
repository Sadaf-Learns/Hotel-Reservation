import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingDB {
    public void saveBooking(Booking booking)
    {
        String sql = "INSERT INTO bookings (user_id, room_id, check_in_date," +
                " check_out_date, total_price, deposit_amount," +
                " remaining_amount, payment_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                " RETURNING booking_id";
        try (
            Connection connection=MyJDBC.getConnection();

        PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1,booking.getUser().getUserId());
            statement.setInt(2,booking.getRoom().getRoomId());
            statement.setDate(3,java.sql.Date.valueOf(booking.getCheckInDate()));
            statement.setDate(4,java.sql.Date.valueOf(booking.getCheckOutDate()));
            statement.setDouble(5,booking.getTotalPrice());
            statement.setDouble(6,booking.getDepositAmount());
            statement.setDouble(7,booking.getRemainingAmount());
            statement.setString(8,booking.getPaymentStatus().name());
           try(ResultSet resultSet=statement.executeQuery()){
            if(resultSet.next()){
                int bookingId=resultSet.getInt("booking_id");
                booking.setBookingId(bookingId);
            }}

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updatePaymentStatus(Booking booking) throws SQLException {
        String sql="UPDATE bookings SET payment_status=?, remaining_amount=? WHERE booking_id=?";
        try(Connection connection=MyJDBC.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1,booking.getPaymentStatus().name());
            statement.setDouble(2,booking.getRemainingAmount());
            statement.setInt(3,booking.getBookingId());
            statement.executeUpdate();
        }

    }
    public List<Booking> getAllBookings()throws SQLException{
        String sql="SELECT * FROM bookings";
        List<Booking> bookings=new ArrayList<>();
        try(Connection connection=MyJDBC.getConnection();
            PreparedStatement statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery()){
            while (resultSet.next()){ //read reservation details
                int bookingId=resultSet.getInt("booking_id");
                double totalPrice=resultSet.getDouble("total_price");
                int userId=resultSet.getInt("user_id");
                int roomId=resultSet.getInt("room_id");
                LocalDate checkInDate = resultSet.getDate("check_in_date").toLocalDate();
                LocalDate checkOutDate = resultSet.getDate("check_out_date").toLocalDate();
                double depositAmount = resultSet.getDouble("deposit_amount");
                double remainingAmount = resultSet.getDouble("remaining_amount");
                String paymentStatus = resultSet.getString("payment_status");
                User user = new UserDB().getUserById(userId);
                Room room = new RoomDB().getRoomById(roomId);
                Booking booking = new Booking(
                        room, user, checkInDate, checkOutDate,
                        totalPrice, depositAmount, LocalDate.now()
                );

                booking.setBookingId(bookingId);
                booking.setRemainingAmount(remainingAmount);
                booking.setPaymentStatus(Booking.PaymentStatus.valueOf(paymentStatus));

                bookings.add(booking);



            }
        }
        return bookings;

    }
}
