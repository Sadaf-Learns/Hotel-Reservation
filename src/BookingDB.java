import java.sql.*;

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
}
