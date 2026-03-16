import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Reservation ID: " + reservationId + " | Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {
    void generateReport(List<Reservation> reservations) {
        System.out.println("Booking History Report");
        for (Reservation r : reservations) {
            r.display();
        }
        System.out.println("Total Bookings: " + reservations.size());
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        BookingHistory bookingHistory = new BookingHistory();

        Reservation r1 = new Reservation("R101", "Alice", "Single");
        Reservation r2 = new Reservation("R102", "Bob", "Double");
        Reservation r3 = new Reservation("R103", "Charlie", "Suite");

        bookingHistory.addReservation(r1);
        bookingHistory.addReservation(r2);
        bookingHistory.addReservation(r3);

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(bookingHistory.getHistory());
    }
}