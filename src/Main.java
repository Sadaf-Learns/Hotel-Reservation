import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name= input.nextLine();

        System.out.println("Enter your last name:");
        String lastName= input.nextLine();

        System.out.print("Enter your job: ");
        String job = input.nextLine();

        System.out.print("Enter your education: ");
        String education = input.nextLine();

        System.out.print("Enter your phone number: ");
        String phoneNumber = input.nextLine();

        System.out.print("Enter your national ID: ");
        String nationalId = input.nextLine();
        User user = new User(name,lastName,job,education,phoneNumber,nationalId);
        UserDB userDB=new UserDB();
        Room selectedRoom=null;
        userDB.saveUser(user);
        System.out.println(user.getUserId());
        RoomDB roomDB=new RoomDB();
        List<Room> rooms=roomDB.getAllRooms();
        for (Room room:rooms){
            System.out.println("Room ID: "+room.getRoomId());
            System.out.println("Beds: "+room.getRoomBed());
            System.out.println("Price: "+room.getRoomPrice());
            System.out.println("Reserved: "+room.getisReserved());
        }
        System.out.println("please enter Room ID: ");
        int roomID= input.nextInt();
        for (Room currentRoom:rooms){
            if(currentRoom.getRoomId()==roomID){
                selectedRoom=currentRoom;
                break;
            }
        }
        if(selectedRoom==null){
            System.out.println("Invalid Room ID.");
        }
        if(selectedRoom.getisReserved()){
            System.out.println("Room is already reserved.");
            return;
        }

        BookingManager bookingManager = new BookingManager();
        input.nextLine();
        System.out.println("Enter checkIn date(YYYY-MM-DD): ");
        LocalDate checkIn =LocalDate.parse(input.nextLine());
        System.out.println("Enter checkOut date(YYYY-MM-DD): ");
        LocalDate checkout = LocalDate.parse(input.nextLine());
        long numberOfNights = ChronoUnit.DAYS.between(checkIn, checkout);
        double totalPrice = selectedRoom.getRoomPrice() * numberOfNights;
        double depositAmount = totalPrice*0.2;
        System.out.println("Enter  wallet top-up amount: ");
        double amount=input.nextDouble();
        user.deposit(amount);
        bookingManager.createBooking(
                user,
                selectedRoom,
                checkIn,
                checkout,
                totalPrice,
                depositAmount
        );
        System.out.println(user.getReservations().get(0).getTotalPrice());
        Booking booking = user.getReservations().getFirst();
        System.out.println(booking.getRemainingAmount());
        booking.payRemaining(user);


    }
}