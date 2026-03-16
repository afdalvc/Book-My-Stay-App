import java.util.*;

class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    void addRoomType(String type, int count) {
        rooms.put(type, count);
    }

    void increment(String type) {
        rooms.put(type, rooms.getOrDefault(type, 0) + 1);
    }

    int getAvailability(String type) {
        return rooms.getOrDefault(type, 0);
    }
}

class BookingHistory {
    private Map<String, Reservation> reservations = new HashMap<>();

    void addReservation(Reservation r) {
        reservations.put(r.reservationId, r);
    }

    Reservation getReservation(String id) {
        return reservations.get(id);
    }

    void removeReservation(String id) {
        reservations.remove(id);
    }

    boolean exists(String id) {
        return reservations.containsKey(id);
    }
}

class CancellationService {
    private BookingHistory history;
    private Inventory inventory;
    private Stack<String> rollbackStack = new Stack<>();

    CancellationService(BookingHistory history, Inventory inventory) {
        this.history = history;
        this.inventory = inventory;
    }

    void cancelBooking(String reservationId) {
        if (!history.exists(reservationId)) {
            System.out.println("Cancellation Failed: Reservation not found");
            return;
        }

        Reservation r = history.getReservation(reservationId);
        rollbackStack.push(reservationId);

        inventory.increment(r.roomType);
        history.removeReservation(reservationId);

        System.out.println("Booking Cancelled: " + reservationId + " | Room Type: " + r.roomType);
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addRoomType("Single", 1);
        inventory.addRoomType("Double", 0);

        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("R101", "Single");
        Reservation r2 = new Reservation("R102", "Double");

        history.addReservation(r1);
        history.addReservation(r2);

        CancellationService service = new CancellationService(history, inventory);

        service.cancelBooking("R101");
        service.cancelBooking("R999");
    }
}