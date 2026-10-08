import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String lastName;
    private String job;
    private String education;
    private String phoneNumber;
    private String nationalId;
    private double balance;
    private List<Booking> reservations; //add booking class later

    public User(String name, String lastName, String job, String education, String phoneNumber, String nationalId) {//constractor
        this.name = name;
        this.lastName = lastName;
        this.job = job;
        this.education = education;
        this.phoneNumber = phoneNumber;
        this.nationalId = nationalId;
        this.balance = 0.0;
        this.reservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJob() {
        return job;
    }

    public String getEducation() {
        return education;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public double getBalance() {
        return balance;
    }

    public List<Booking> getReservations() {
        return new ArrayList<>(reservations); //make new list in main list for access limitation out of user class
    }

    public boolean deposit(double amount) { //charge the account
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited. new balance:" + this.balance);
            return true;
        } else {
            System.out.println("Error! the amount should be more than 0!");
            return false;
        }
    }

    public boolean withdraw(double amount) {  //payment
        if (amount <= 0) {
            System.out.println("Error! the withdraw amount should be more than 0!");
            return false;
        }
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + this.balance);
            return true;
        } else System.out.println("Error: Insufficient balance. Current balance: " + this.balance);
        return false;
    }


    public void addReservation(Booking booking) {
     this.reservations.add(booking);
     System.out.println("Reservation added successfully.");}
}
