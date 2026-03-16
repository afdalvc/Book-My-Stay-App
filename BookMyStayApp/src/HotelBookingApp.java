import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class SystemState implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();
    List<Reservation> bookingHistory = new ArrayList<>();
}

class PersistenceService {
    static void save(SystemState state, String file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(state);
            out.close();
            System.out.println("System state saved.");
        } catch (Exception e) {
            System.out.println("Error saving state.");
        }
    }

    static SystemState load(String file) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("System state loaded.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return new SystemState();
        }
    }
}

public class HotelBookingApp {
    public static void main(String[] args) {
        String file = "hotel_state.dat";

        SystemState state = PersistenceService.load(file);

        if (state.inventory.isEmpty()) {
            state.inventory.put("Single", 3);
            state.inventory.put("Double", 2);
        }

        Reservation r1 = new Reservation("R201", "Alice", "Single");
        Reservation r2 = new Reservation("R202", "Bob", "Double");

        state.bookingHistory.add(r1);
        state.bookingHistory.add(r2);

        System.out.println("Current Bookings:");
        for (Reservation r : state.bookingHistory) {
            System.out.println(r.reservationId + " | " + r.guestName + " | " + r.roomType);
        }

        PersistenceService.save(state, file);
    }
}